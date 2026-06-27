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

import java.util.Collection;
import java.util.Collections;

import com.bivashy.limbo.NanoLimboVelocity;
import com.bivashy.limbo.command.exception.SendComponentException;
import com.bivashy.limbo.command.exception.VelocityExceptionHandler;

import org.jspecify.annotations.NonNull;
import revxrsal.commands.CommandHandler;
import revxrsal.commands.velocity.core.VelocityHandler;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.LimboServer;

public class LampVelocityCommandHandler implements ua.nanit.limbo.server.CommandHandler<Command> {
    private final NanoLimboVelocity plugin;
    private final CommandHandler commandHandler;

    public LampVelocityCommandHandler(NanoLimboVelocity plugin) {
        this.plugin = plugin;
        commandHandler = new VelocityHandler(plugin, plugin.getServer()).disableStackTraceSanitizing()
                .setExceptionHandler(new VelocityExceptionHandler(plugin));
    }

    public LampVelocityCommandHandler registerAll() {
        commandHandler.registerValueResolver(LimboServer.class, context -> {
            LimboServer limboServer = plugin.getServers().getOrDefault(context.popForParameter(), null);
            if (limboServer == null)
                throw new SendComponentException(plugin.getLimboConfig().getMessages().message("invalid-limbo"));
            return limboServer;
        });
        commandHandler.registerExceptionHandler(SendComponentException.class, (actor, e) -> e.send(actor));
        commandHandler.registerDependency(NanoLimboVelocity.class, plugin);
        commandHandler.register(new ConnectionCountCommand(), new HelpCommand(), new MemoryCommand(), new StopCommand(), new StartCommand());
        return this;
    }

    @Override
    public @NonNull Collection<Command> getCommands() {
        return Collections.emptyList();
    }

    @Override
    public void register(@NonNull Command command) {
        commandHandler.register(command);
    }

    @Override
    public boolean executeCommand(String input) {
        throw new UnsupportedOperationException("Cannot execute command in LampVelocityCommandHandler");
    }
}
