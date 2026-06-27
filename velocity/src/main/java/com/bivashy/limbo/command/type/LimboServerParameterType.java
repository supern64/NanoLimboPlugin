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

package com.bivashy.limbo.command.type;

import com.bivashy.limbo.NanoLimboVelocity;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.autocomplete.SuggestionProvider;
import revxrsal.commands.node.ExecutionContext;
import revxrsal.commands.parameter.ParameterType;
import revxrsal.commands.stream.MutableStringStream;
import revxrsal.commands.velocity.actor.VelocityCommandActor;
import ua.nanit.limbo.server.LimboServer;

import java.util.List;

public class LimboServerParameterType implements ParameterType<VelocityCommandActor, LimboServer> {
    private final NanoLimboVelocity plugin;

    public LimboServerParameterType(NanoLimboVelocity plugin) {
        this.plugin = plugin;
    }

    @Override
    public LimboServer parse(@NotNull MutableStringStream input, @NotNull ExecutionContext<VelocityCommandActor> context) {
        String limboName = input.readString();
        LimboServer limboServer = plugin.getServers().getOrDefault(limboName, null);
        if (limboServer == null)
            throw new InvalidLimboServerException("limbo server " + limboName + " does not exist");
        return limboServer;
    }

    @Override public @NotNull SuggestionProvider<VelocityCommandActor> defaultSuggestions() {
        return (context) -> List.copyOf(plugin.getServers().keySet());
    }
}
