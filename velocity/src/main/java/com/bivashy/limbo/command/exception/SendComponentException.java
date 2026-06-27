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

import net.kyori.adventure.text.Component;
import revxrsal.commands.command.CommandActor;
import revxrsal.commands.velocity.VelocityCommandActor;

public class SendComponentException extends RuntimeException {
    private final Component component;

    public SendComponentException(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void send(CommandActor actor) {
        actor.as(VelocityCommandActor.class).reply(component);
    }
}
