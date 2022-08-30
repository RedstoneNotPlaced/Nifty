package me.alpha.nifty.listeners.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.listeners.NiftyListener;
import me.alpha.nifty.utils.ComponentUtils;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * An event listener that handles block placements.
 *
 * @author Alpha.
 */
public final class BlockPlaceListener extends NiftyListener {

    public BlockPlaceListener(Nifty nifty) {
        super(nifty);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(getNifty().getConfig().getBoolean("world.block-restriction.enabled")) {
            final List<String> blockNames = getNifty().getConfig().getStringList("world.block-restriction.restricted-blocks");
            final String format = getNifty().getConfig().getString("world.block-restriction.restriction-message");
            final TextComponent message = ComponentUtils.textWithColors(format);

            blockNames.forEach(b -> {
                if(e.getBlockPlaced().getType().equals(Material.valueOf(b))) {
                    e.setCancelled(true);

                    if(getNifty().getConfig().getBoolean("world.block-restriction.clear-item-on-main-hand")) {
                        e.getPlayer().getEquipment().setItemInMainHand(new ItemStack(Material.AIR));
                    }

                    e.getPlayer().sendMessage(message);
                }
            });
        }
    }
}