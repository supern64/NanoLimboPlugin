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

package com.bivashy.limbo.config;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.ConfigurationOptions;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializerCollection;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import com.bivashy.limbo.NanoLimboVelocity;
import com.bivashy.limbo.config.model.VelocityLimboServer;
import com.bivashy.limbo.config.model.VelocityLimboServer.VelocityLimboServerSerializer;

import ua.nanit.limbo.server.Log;

public class LimboConfig {

    private List<VelocityLimboServer> servers;
    private MessageConfiguration messages;
    private ConfigurationNode root;

    public LimboConfig(NanoLimboVelocity plugin) {
        try {
            root = YamlConfigurationLoader.builder()
                    .defaultOptions(ConfigurationOptions.defaults()
                            .serializers(
                                    TypeSerializerCollection.builder()
                                            .register(VelocityLimboServer.class, new VelocityLimboServerSerializer())
                                            .build()))
                    .file(ConfigurationUtil.saveDefaultConfig(plugin, "config.yml"))
                    .build().
                    load();
            servers = root.node("limbos").childrenMap().values().stream().map(node -> {
                try {
                    return node.get(VelocityLimboServer.class);
                } catch (SerializationException e) {
                    Log.error("Cannot load limbos from configuration", e);
                    return null;
                }
            }).collect(Collectors.toList());
            messages = new MessageConfiguration(root.node("messages"));
        } catch (ConfigurateException e) {
            Log.warning("Cannot load config.yml", e);
        }
    }

    public MessageConfiguration getMessages() {
        return messages;
    }

    public List<VelocityLimboServer> getServers() {
        return Collections.unmodifiableList(servers);
    }

}