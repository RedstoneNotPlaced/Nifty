package me.alpha.nifty.commands.impl;

import me.alpha.nifty.Nifty;
import me.alpha.nifty.commands.NiftyCommand;
import me.alpha.nifty.utils.ComponentUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Main command of Nifty.
 *
 * @author Alpha.
 */
public final class MainCommand extends NiftyCommand {

    public MainCommand(@NotNull Nifty nifty, @NotNull String name, int argsCount, boolean checkGreaterArgs, boolean checkZeroArgs) {
        super(nifty, name, argsCount, checkGreaterArgs, checkZeroArgs);
    }

    @Override
    protected void run(CommandSender sender, String[] args) {
        if(args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "help":
                    sender.sendMessage(ComponentUtils.textWithColors("<dark_blue><strikethrough>--------------------------------------------"));
                    sender.sendMessage(ComponentUtils.textWithColors("<gold><bold>/nifty help</bold> - Shows all commands in Nifty"));
                    sender.sendMessage(ComponentUtils.textWithColors("<gold><bold>/nifty reload</bold> - Reloads Configuration File"));
                    sender.sendMessage(ComponentUtils.textWithColors("<gold><bold>/nifty version</bold> - Shows the currrent version"));
                    sender.sendMessage(ComponentUtils.textWithColors("<dark_blue><strikethrough>--------------------------------------------"));
                    break;
                case "reload":
                    getNifty().reloadConfig();
                    sender.sendMessage(PREFIX.append(ComponentUtils.textWithColors("<green><bold> Reloaded Configuration File")));
                    break;
                case "version":
                    sender.sendMessage(PREFIX.append(ComponentUtils.textWithColors("<green> Currently using Version<bold> " + getNifty().getDescription().getVersion())));
                    break;
                default:
                    sender.sendMessage(INVALID_ARGUMENT_MESSAGE);
            }
        }
    }

    @Override
    protected List<String> tab(String[] args) {
        return (args.length == 1) ? Arrays.asList("help", "reload", "version") : Collections.emptyList();
    }
}