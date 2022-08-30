package me.alpha.nifty.listeners.impl;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import me.alpha.nifty.Nifty;
import me.alpha.nifty.listeners.NiftyListener;
import me.alpha.nifty.utils.ComponentUtils;
import org.bukkit.event.EventHandler;

import java.util.List;
import java.util.Random;

/**
 * An event listener that handles server pings.
 *
 * @author Alpha.
 */
public final class ServerPingListener extends NiftyListener {

    public ServerPingListener(Nifty nifty) {
        super(nifty);
    }

    @EventHandler
    public void onPing(PaperServerListPingEvent e) {
        if(!getNifty().getConfig().getBoolean("server.motd.enabled")) return;
        final List<String> messages = getNifty().getConfig().getStringList("server.motd.messages");

        if(getNifty().getConfig().getBoolean("server.motd.random")) {
            final int randomIndex = new Random().nextInt(messages.size());
            e.motd(ComponentUtils.textWithColors(messages.get(randomIndex)));
        } else {
            e.motd(ComponentUtils.textWithColors(messages.get(0)));
        }
    }
}