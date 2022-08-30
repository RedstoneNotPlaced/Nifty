package me.alpha.nifty.commands.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.commands.NiftyCommand;
import me.alpha.nifty.utils.ComponentUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * A command that broadcast the given message.
 *
 * @author Alpha.
 */
public final class BroadcastCommand extends NiftyCommand {

    public BroadcastCommand(@NotNull Nifty nifty, @NotNull String name, int argsCount, boolean checkGreaterArgs, boolean checkZeroArgs) {
        super(nifty, name, argsCount, checkGreaterArgs, checkZeroArgs);
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        if(args.length > 0) {
            final String format = getNifty().getConfig().getString("command-messages.broadcast");
            final TextComponent mergeArgs = Component.text(String.join(" ", args));
            final TextComponent text = ComponentUtils.replace(format, Placeholder.component("message", mergeArgs));

            Bukkit.broadcast(text);
        }
    }

    @Override
    protected List<String> tab(String[] args) {
        return Collections.emptyList();
    }
}