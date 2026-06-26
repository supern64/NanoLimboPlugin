package ua.nanit.limbo.server.commands;

import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.LimboServer;

@AllArgsConstructor
public class CmdStop implements Command {

    private final LimboServer server;

    @Override
    public void execute() {
        server.stop();
    }

    @Override
    public @NonNull String name() {
        return "stop";
    }

    @Override
    public @NonNull String description() {
        return "Stop the server";
    }

}
