/*
 * Copyright (C) 2020 Nan1t
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.bivashy.limbo.config.model;

import java.io.File;
import java.lang.reflect.Type;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import com.bivashy.limbo.NanoLimboVelocity;
import com.bivashy.limbo.config.ConfigurationUtil;

import ua.nanit.limbo.configuration.LimboConfig;
import ua.nanit.limbo.configuration.YamlLimboConfig;

public class VelocityLimboServer {
    private final LimboConfig limboConfig;
    private final String limboName;
    private final boolean addToTryList;

    public VelocityLimboServer(LimboConfig limboConfig, String limboName, boolean addToTryList) {
        this.limboConfig = limboConfig;
        this.limboName = limboName;
        this.addToTryList = addToTryList;
    }

    public LimboConfig getLimboConfig() {
        return limboConfig;
    }

    public String getLimboName() {
        return limboName;
    }

    public boolean getAddToTryList() {
        return addToTryList;
    }


    public static class VelocityLimboServerSerializer implements TypeSerializer<VelocityLimboServer> {
        private static final NanoLimboVelocity PLUGIN = NanoLimboVelocity.getInstance();

        @Override
        public VelocityLimboServer deserialize(Type type, ConfigurationNode node) throws SerializationException {
            Object keyObject = node.key();
            if (keyObject == null && node.node("name").virtual())
                throw new SerializationException("Cannot load limbo without name!");
            String limboName = keyObject == null ? node.node("name").getString() : keyObject.toString();
            String settingsFolder = node.node("settingsFolder").getString("");
            boolean addToTryList = node.node("addToTryList").getBoolean();
            LimboConfig limboConfig;
            try {
                File limboSettingsFolder = new File(PLUGIN.getDataFolder().toFile(), settingsFolder);
                ConfigurationUtil.saveDefaultConfig(PLUGIN.getClass().getClassLoader(), limboSettingsFolder, "settings.yml");
                limboConfig = new YamlLimboConfig(limboSettingsFolder.toPath(), PLUGIN.getClass().getClassLoader()).load();
            } catch(Exception e) {
                e.printStackTrace();
                throw new SerializationException();
            }
            return new VelocityLimboServer(limboConfig, limboName, addToTryList);
        }

        @Override
        public void serialize(Type type, @Nullable VelocityLimboServer obj, ConfigurationNode node) throws SerializationException {
        }
    }
}
