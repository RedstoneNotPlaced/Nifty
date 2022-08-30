package me.alpha.nifty.listeners.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.commands.impl.LockdownCommand;
import me.alpha.nifty.listeners.NiftyListener;
import me.alpha.nifty.utils.ComponentUtils;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * An event listener that handles player join and quit.
 *
 * @author Alpha.
 */
public final class JoinAndQuitListener extends NiftyListener {

    public JoinAndQuitListener(Nifty nifty) {
        super(nifty);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        //TODO: Organize but i'm quite lazy to do it.
        if(!getNifty().getConfig().getBoolean("server.player-join.enabled")) return;
        final Player player = e.getPlayer();

        if(getNifty().getConfig().getBoolean("server.player-join.notice-first-join") && !player.hasPlayedBefore()) {
            final String format = getNifty().getConfig().getString("server.player-join.first-join-message");
            final TextComponent firstJoinMessage = ComponentUtils.replace(format, Placeholder.component("player", ComponentUtils.textWithColors(player.getName())));
            e.joinMessage(firstJoinMessage);
        } else {
            final String format = getNifty().getConfig().getString("server.player-join.main-join-message");
            final TextComponent mainJoinMessage = ComponentUtils.replace(format, Placeholder.component("player", ComponentUtils.textWithColors(player.getName())));
            e.joinMessage(mainJoinMessage);
        }

        if(LockdownCommand.lockdown && !player.isOp()) {
            final String message = getNifty().getConfig().getString("command-messages.lockdown");
            player.kick(ComponentUtils.textWithColors(message));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(!getNifty().getConfig().getBoolean("server.player-quit.enabled")) return;
        final String format = getNifty().getConfig().getString("server.player-quit.quit-message");
        final TextComponent quitMessage = ComponentUtils.replace(format, Placeholder.component("player", ComponentUtils.textWithColors(e.getPlayer().getName())));

        e.quitMessage(quitMessage);
    }
}