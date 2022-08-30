package me.alpha.nifty.commands;

import lombok.AccessLevel;
import lombok.Getter;
import me.alpha.nifty.Nifty;
import me.alpha.nifty.utils.ComponentUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Represents a command from Nifty.
 *
 * @author Alpha.
 */
public abstract class NiftyCommand implements CommandExecutor, TabCompleter {
    @Getter(AccessLevel.PROTECTED)
    private final Nifty nifty;
    private final String name;
    private final int argsCount;
    private final boolean checkGreaterArgs, checkZeroArgs;

    protected final TextComponent PREFIX = ComponentUtils.textWithColors("<dark_blue>[<gold><bold>Nifty</bold></gold>]:");
    protected final TextComponent INVALID_ARGUMENT_MESSAGE = PREFIX.append(Component.text(" Invalid Argument!!!", NamedTextColor.RED));

    protected final TextComponent INVALID_PLAYER_MESSAGE = PREFIX.append(Component.text(" That player is either not online or doesn't exist!!!", NamedTextColor.RED));

    public NiftyCommand(@NotNull Nifty nifty, @NotNull String name, int argsCount, boolean checkGreaterArgs, boolean checkZeroArgs) {
        this.nifty = nifty;
        this.name = name;
        this.argsCount = argsCount;
        this.checkGreaterArgs = checkGreaterArgs;
        this.checkZeroArgs = checkZeroArgs;
    }

    protected abstract void run(CommandSender sender, String[] args);

    protected abstract List<String> tab(String[] args);

    /**
     * Registers the command.
     *
     * @author Alpha.
     */
    public final void register() {
        nifty.getCommand(name).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        //TODO: Add console support.
        if(!(sender instanceof Player)) return true;
        if(checkGreaterArgs && args.length > argsCount) return false;
        if(checkZeroArgs && args.length == 0) return false;

        run(sender, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return tab(args);
    }
}