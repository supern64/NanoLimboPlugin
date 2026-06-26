package ua.nanit.limbo.configuration.serializers;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.server.data.NamespacedKey;

import java.lang.reflect.Type;

public class NamespacedKeySerializer implements TypeSerializer<NamespacedKey> {

    @Override
    public NamespacedKey deserialize(Type type, ConfigurationNode node) {
        return NamespacedKey.minecraft(node.getString(""));
    }

    @Override
    public void serialize(Type type, NamespacedKey obj, ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }

        node.set(String.class, obj.getKey());
    }
}
