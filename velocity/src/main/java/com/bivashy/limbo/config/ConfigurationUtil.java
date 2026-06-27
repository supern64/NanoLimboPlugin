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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.bivashy.limbo.NanoLimboVelocity;

public class ConfigurationUtil {
    private ConfigurationUtil() {
    }

    public static File saveDefaultConfig(NanoLimboVelocity plugin, String configurationName) {
        return saveDefaultConfig(plugin.getClass().getClassLoader(), plugin.getDataFolder().toFile(), configurationName);
    }

    public static File saveDefaultConfig(ClassLoader classLoader, File configurationFolder, String configurationName) {
        try {
            configurationFolder.mkdirs();
            File configurationFile = new File(configurationFolder, configurationName);
            if (!configurationFile.exists()) {
                try (InputStream inputStream = classLoader.getResourceAsStream(configurationName)) {
                    if (inputStream == null)
                        throw new IllegalArgumentException("Configuration with name " + configurationName + " not found in resources!");
                    Files.copy(inputStream, configurationFile.toPath());
                }
            }
            return configurationFile;
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
