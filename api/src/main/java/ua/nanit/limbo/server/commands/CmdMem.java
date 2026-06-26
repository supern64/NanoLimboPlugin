package ua.nanit.limbo.server.commands;

import org.jspecify.annotations.NonNull;
import ua.nanit.limbo.server.Command;
import ua.nanit.limbo.server.Log;

public class CmdMem implements Command {

    @Override
    public void execute() {
        Runtime runtime = Runtime.getRuntime();
        long mb = 1024 * 1024;
        long used = (runtime.totalMemory() - runtime.freeMemory()) / mb;
        long total = runtime.totalMemory() / mb;
        long free = runtime.freeMemory() / mb;
        long max = runtime.maxMemory() / mb;

        Log.info("Memory usage:");
        Log.info("Used: %d MB", used);
        Log.info("Total: %d MB", total);
        Log.info("Free: %d MB", free);
        Log.info("Max: %d MB", max);
    }

    @Override
    public @NonNull String name() {
        return "mem";
    }

    @Override
    public @NonNull String description() {
        return "Display memory usage";
    }
}
