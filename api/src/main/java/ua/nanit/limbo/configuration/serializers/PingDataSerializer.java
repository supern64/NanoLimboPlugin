package ua.nanit.limbo.configuration.serializers;

import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.server.data.PingData;

import java.lang.reflect.Type;

public class PingDataSerializer implements TypeSerializer<PingData> {

    @Override
    public PingData deserialize(Type type, ConfigurationNode node) throws SerializationException {
        PingData pingData = new PingData();
        pingData.setDescription(node.node("description").get(Component.class, Component.empty()));
        pingData.setVersion(node.node("version").get(Component.class, Component.empty()));
        pingData.setProtocol(node.node("protocol").getInt(-1));
        return pingData;
    }

    @Override
    public void serialize(Type type, PingData obj, ConfigurationNode node) {
    }

}
