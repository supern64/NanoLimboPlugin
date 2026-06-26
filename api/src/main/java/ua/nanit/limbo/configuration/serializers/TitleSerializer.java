package ua.nanit.limbo.configuration.serializers;

import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;
import ua.nanit.limbo.server.data.Title;

import java.lang.reflect.Type;

public class TitleSerializer implements TypeSerializer<Title> {

    @Override
    public Title deserialize(Type type, ConfigurationNode node) throws SerializationException {
        Title title = new Title();
        title.setTitle(node.node("title").get(Component.class, Component.empty()));
        title.setSubtitle(node.node("subtitle").get(Component.class, Component.empty()));
        title.setFadeIn(node.node("fadeIn").getInt(10));
        title.setStay(node.node("stay").getInt(100));
        title.setFadeOut(node.node("fadeOut").getInt(10));
        return title;
    }

    @Override
    public void serialize(Type type, Title obj, ConfigurationNode node) throws SerializationException {
    }

}
