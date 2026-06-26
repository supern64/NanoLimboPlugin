package ua.nanit.limbo.configuration.serializers;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.server.TransportType;

import java.lang.reflect.Type;
import java.util.Locale;

public class TransportTypeSerializer implements TypeSerializer<TransportType> {

    @Override
    public TransportType deserialize(Type type, ConfigurationNode node) throws SerializationException {
        try {
            return TransportType.valueOf(node.getString("").toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void serialize(Type type, TransportType obj, ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }

        node.set(String.class, obj.name());
    }
}
