package me.alpha.nifty.listeners;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.alpha.nifty.Nifty;
import org.bukkit.event.Listener;

/**
 * Represents an event listener from Nifty.
 *
 * @author Alpha.
 */
@RequiredArgsConstructor
public abstract class NiftyListener implements Listener {
    @Getter(AccessLevel.PROTECTED)
    private final Nifty nifty;

    public final void register() {
        nifty.getServer().getPluginManager().registerEvents(this, nifty);
    }
}