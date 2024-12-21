package me.GreatScott42;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ForgiveCommand implements CommandExecutor {

    private Exile plugin;
    public ForgiveCommand(Exile plugin) {
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            if(strings.length==0){
                Player player = (Player)commandSender;
                plugin.getExiledPlayers().set(player.getName()+".exiled", false);
                plugin.saveExiledPlayers();
                player.sendMessage((String) plugin.getConfig().get("forgive-yourself-message"));
            }else if(strings.length==1){
                Player player = commandSender.getServer().getPlayerExact(strings[0]);
                if(player!=null){
                    plugin.getExiledPlayers().set(player.getName()+".exiled", false);
                    plugin.saveExiledPlayers();
                    player.sendMessage((String) plugin.getConfig().get("forgive-message"));
                }
            }
            return true;
        }else{
            Bukkit.getLogger().info("[Exiled] Error, have you try to google it?");
        }
        return false;
    }
}
