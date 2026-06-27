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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.spongepowered.configurate.ConfigurationNode;

import com.bivashy.limbo.component.ComponentDeserializer;

import net.kyori.adventure.text.Component;

public class MessageConfiguration {
    private final Map<String, Component> messages = new HashMap<>();
    private final Map<String, MessageConfiguration> subMessages = new HashMap<>();

    public MessageConfiguration(ConfigurationNode node) {
        ComponentDeserializer deserializer = ComponentDeserializer.valueOf(node.node("deserializer").getString(ComponentDeserializer.LEGACY_AMPERSAND.name()));
        for (Entry<Object, ? extends ConfigurationNode> entry : node.childrenMap().entrySet()) {
            String key = entry.getKey().toString();
            ConfigurationNode childNode = entry.getValue();
            if (childNode.isMap()) {
                subMessages.put(key, new MessageConfiguration(childNode));
                continue;
            }
            messages.put(key, deserializer.deserialize(childNode.getString()));
        }
    }

    public MessageConfiguration subMessage(String key) {
        return subMessages.get(key);
    }

    public Component message(String key, Object... placeholders) {
        Component component = messages.get(key);
        for (int i = 0; i < placeholders.length; i += 2) {
            String placeholder = placeholders[i].toString();
            String replacement = placeholders[i + 1].toString();
            component = component.replaceText(builder -> builder.matchLiteral(placeholder).replacement(replacement));
        }
        return component;
    }
}
