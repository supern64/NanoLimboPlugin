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

package com.bivashy.limbo.command.commands;

import com.bivashy.limbo.NanoLimboVelocity;

import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.CommandPlaceholder;
import revxrsal.commands.annotation.Dependency;
import revxrsal.commands.velocity.actor.VelocityCommandActor;
import revxrsal.commands.velocity.annotation.CommandPermission;
import ua.nanit.limbo.server.LimboServer;

@Command("limbostop")
public class StopCommand {
    @Dependency
    private NanoLimboVelocity plugin;

    @CommandPlaceholder
    @CommandPermission("limbo.stop")
    public void execute(VelocityCommandActor actor, LimboServer limboServer) {
        if (!limboServer.isRunning()) {
            actor.error(plugin.getLimboConfig().getMessages().message("already-stopped"));
        }
        limboServer.stop();
        actor.reply(plugin.getLimboConfig().getMessages().message("successfully-stopped"));
    }
}
