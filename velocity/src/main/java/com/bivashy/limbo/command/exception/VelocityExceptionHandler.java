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

package com.bivashy.limbo.command.exception;

import com.bivashy.limbo.NanoLimboVelocity;

import revxrsal.commands.command.CommandActor;
import revxrsal.commands.exception.MissingArgumentException;
import revxrsal.commands.exception.NoPermissionException;
import revxrsal.commands.velocity.VelocityCommandActor;
import revxrsal.commands.velocity.exception.VelocityExceptionAdapter;

public class VelocityExceptionHandler extends VelocityExceptionAdapter {
    private final NanoLimboVelocity plugin;

    public VelocityExceptionHandler(NanoLimboVelocity plugin) {
        this.plugin = plugin;
    }

    @Override
    public void missingArgument(CommandActor actor, MissingArgumentException exception) {
        actor.as(VelocityCommandActor.class).reply(plugin.getLimboConfig().getMessages().message("no-argument"));
    }

    @Override
    public void noPermission(CommandActor actor, NoPermissionException exception) {
        super.noPermission(actor, exception);
    }
}
