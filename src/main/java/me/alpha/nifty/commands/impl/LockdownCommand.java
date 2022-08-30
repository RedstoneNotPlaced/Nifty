package me.alpha.nifty.commands.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.commands.NiftyCommand;
import me.alpha.nifty.utils.ComponentUtils;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public final class LockdownCommand extends NiftyCommand {
    public static boolean lockdown = false;

    public LockdownCommand(@NotNull Nifty nifty, @NotNull String name, int argsCount, boolean checkGreaterArgs, boolean checkZeroArgs) {
        super(nifty, name, argsCount, checkGreaterArgs, checkZeroArgs);
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        final String format = getNifty().getConfig().getString("command-messages.lockdown");
        final TextComponent message = ComponentUtils.textWithColors(format);

        if(!lockdown) {
            lockdown = true;
            Bukkit.getOnlinePlayers().stream().filter(p -> !p.isOp()).forEach(p -> {
                p.kick(message);
            });

            Bukkit.broadcast(ComponentUtils.textWithColors("<red>The server is now in a <bold>LOCKDOWN</bold>!!!"));
        } else {
            lockdown = false;
            Bukkit.broadcast(ComponentUtils.textWithColors("<green>The server is no longer in lockdown!!!"));
        }
    }

    @Override
    protected List<String> tab(String[] args) {
        return Collections.emptyList();
    }
}