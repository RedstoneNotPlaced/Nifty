package me.alpha.nifty.utils;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

/**
 * A utility class for Components.
 * A special thanks to PaperMC for creating such an
 * amazing and wonderful API!!! Very cool.
 *
 * @author Alpha.
 */
public final class ComponentUtils {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static TextComponent textWithColors(final String message) {
        return (TextComponent) MINI_MESSAGE.deserialize(message);
    }

    public static TextComponent replace(final String message, final TagResolver... tagResolver) {
        return (TextComponent) MINI_MESSAGE.deserialize(message, tagResolver);
    }
}