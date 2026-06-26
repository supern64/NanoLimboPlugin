package ua.nanit.limbo.protocol;

import lombok.NonNull;
import ua.nanit.limbo.protocol.registry.Version;

@FunctionalInterface
public interface MetadataWriter {

    void writeData(@NonNull ByteMessage message, @NonNull Version version);

}
