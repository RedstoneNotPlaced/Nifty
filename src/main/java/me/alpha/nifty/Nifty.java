package me.alpha.nifty;

import me.alpha.nifty.commands.impl.BroadcastCommand;
import me.alpha.nifty.commands.impl.InventoryPeekCommand;
import me.alpha.nifty.commands.impl.LockdownCommand;
import me.alpha.nifty.commands.impl.MainCommand;
import me.alpha.nifty.listeners.impl.BlockPlaceListener;
import me.alpha.nifty.listeners.impl.JoinAndQuitListener;
import me.alpha.nifty.listeners.impl.ServerPingListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of Nifty.
 *
 * @author Alpha.
 */
public final class Nifty extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Setting up Configuration File...");
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getLogger().info("Registering Commands...");
        new MainCommand(this, "nifty", 1, true, true).register();
        new BroadcastCommand(this, "broadcast", 0, false, false).register();
        new LockdownCommand(this, "lockdown", 0, true, false).register();
        new InventoryPeekCommand(this, "inventorypeek", 1, true, true).register();
        getLogger().info("Registering Event Listeners...");
        new ServerPingListener(this).register();
        new JoinAndQuitListener(this).register();
        new BlockPlaceListener(this).register();
        getLogger().info("Done!!!");
    }
}