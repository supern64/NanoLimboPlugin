package ua.nanit.limbo.server;

import lombok.NonNull;

public interface Command {

    void execute();

    @NonNull
    String name();

    @NonNull
    String description();

}
