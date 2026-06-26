package ua.nanit.limbo.configuration.serializers;

import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.util.ComponentUtils;

import java.lang.reflect.Type;

public class ComponentSerializer implements TypeSerializer<Component> {

    @Override
    public Component deserialize(Type type, ConfigurationNode node) {
        return ComponentUtils.parse(node.getString(""));
    }

    @Override
    public void serialize(Type type, Component obj, ConfigurationNode node) throws SerializationException {
        if (obj == null) {
            node.raw(null);
            return;
        }

        node.set(String.class, ComponentUtils.toMiniMessageString(obj));
    }
}
