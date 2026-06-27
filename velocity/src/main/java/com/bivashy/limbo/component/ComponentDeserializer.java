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

package com.bivashy.limbo.component;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public enum ComponentDeserializer {
    PLAIN {
        private final PlainTextComponentSerializer serializer = PlainTextComponentSerializer.plainText();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    }, GSON {
        private final GsonComponentSerializer serializer = GsonComponentSerializer.gson();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    }, GSON_LEGACY {
        private final GsonComponentSerializer serializer = GsonComponentSerializer.colorDownsamplingGson();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    }, LEGACY_AMPERSAND {
        private final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    }, LEGACY_SECTION {
        private final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacySection();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    }, MINIMESSAGE {
        private final MiniMessage serializer = MiniMessage.miniMessage();

        @Override
        public Component deserialize(String text) {
            return serializer.deserialize(text);
        }
    };

    public abstract Component deserialize(String text);
}
