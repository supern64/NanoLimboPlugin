package ua.nanit.limbo.configuration.serializers;

import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.world.DimensionType;

import java.lang.reflect.Type;
import java.util.Locale;

public class DimensionTypeSerializer implements TypeSerializer<DimensionType> {

    @Override
    public DimensionType deserialize(Type type, ConfigurationNode node) throws SerializationException {
        try {
            String value = node.getString("").toUpperCase(Locale.ROOT);
            if (value.equals("NETHER")) {
                value = "THE_NETHER";
            }
            if (value.equals("END")) {
                value = "THE_END";
            }
            return DimensionType.valueOf(value);
        } catch (Exception e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void serialize(Type type, DimensionType obj, ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }

        node.set(String.class, obj.name());
    }
}
