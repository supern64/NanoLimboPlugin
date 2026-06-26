package ua.nanit.limbo.server.commands;

import java.util.Collection;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.LimboServer;
import ua.nanit.limbo.server.Log;

@AllArgsConstructor
public class CmdHelp implements Command {

    private final LimboServer server;

    @Override
    public void execute() {
        Collection<Command> commands = server.getCommandManager().getCommands();

        Log.info("Available commands:");

        for (Command command : commands) {
            Log.info("%s - %s", command.name(), command.description());
        }
    }

    @Override
    public @NonNull String name() {
        return "help";
    }

    @Override
    public @NonNull String description() {
        return "Show this message";
    }
}
