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

package com.bivashy.limbo.command;

import com.bivashy.limbo.NanoLimboVelocity;

import com.bivashy.limbo.command.type.InvalidLimboServerException;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.exception.MissingArgumentException;
import revxrsal.commands.node.ParameterNode;
import revxrsal.commands.velocity.actor.VelocityCommandActor;
import revxrsal.commands.velocity.exception.VelocityExceptionHandler;

public class CommandExceptionHandler extends VelocityExceptionHandler {
    private final NanoLimboVelocity plugin;

    public CommandExceptionHandler(NanoLimboVelocity plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onMissingArgument(@NotNull MissingArgumentException e, @NotNull VelocityCommandActor actor, @NotNull ParameterNode<VelocityCommandActor, ?> parameter) {
        actor.error(plugin.getLimboConfig().getMessages().message("no-argument"));
    }

    @HandleException
    public void onInvalidLimboServer(InvalidLimboServerException e, VelocityCommandActor actor) {
         actor.error(plugin.getLimboConfig().getMessages().message("invalid-limbo"));
    }
}
