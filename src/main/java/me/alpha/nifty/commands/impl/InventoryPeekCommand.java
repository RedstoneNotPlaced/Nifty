package me.alpha.nifty.commands.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.commands.NiftyCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A command that let's you peek other player's inventory.
 *
 * @author Alpha.
 */
public final class InventoryPeekCommand extends NiftyCommand {

    public InventoryPeekCommand(@NotNull Nifty nifty, @NotNull String name, int argsCount, boolean checkGreaterArgs, boolean checkZeroArgs) {
        super(nifty, name, argsCount, checkGreaterArgs, checkZeroArgs);
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        if(args.length == 1) {
            final Player player = (Player) sender;
            final Player givenPlayer = Bukkit.getPlayer(args[0]);

            if(givenPlayer != null || !givenPlayer.isOnline()) {
                player.openInventory(givenPlayer.getInventory());
            } else {
                sender.sendMessage(INVALID_PLAYER_MESSAGE);
            }
        }
    }

    @Override
    protected List<String> tab(String[] args) {
        return (args.length == 1) ? Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()) : Collections.emptyList();
    }
}