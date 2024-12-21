package me.GreatScott42;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExileCommand implements CommandExecutor {

    private Exile plugin;

    public ExileCommand(Exile plugin){
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            /*if(strings.length==0){
                Player player = (Player)commandSender;
                Location location = player.getLocation();
                location.setWorld(Bukkit.getWorld("world_nether"));
                location.setY(65);
                Block tobreak = location.getBlock();
                while(!tobreak.getRelative(0,-1,0).getType().isSolid()||tobreak.getType()!=Material.AIR||tobreak.getRelative(0,1,0).getType()!=Material.AIR){
                    location.setX(location.getX()+1);
                    tobreak = location.getBlock();
                }
                player.teleport(location);
                plugin.getExiledPlayers().set(player.getName()+".exiled", true);
                plugin.getExiledPlayers().set(player.getName()+".location", location);
                plugin.saveExiledPlayers();
                if(plugin.getConfig().getBoolean("clear-inventory")){
                    player.getInventory().clear();
                }
                player.sendMessage(plugin.getConfig().getString("exile-yourself-message"));
            } */if(strings.length==1){
                //Player player = commandSender.getServer().getPlayerExact(strings[0]);
                Player player = (Player)commandSender;
                if(player!=null){
                    Location location = player.getLocation();
                    String world = strings[0];
                    if(plugin.getConfig().getBoolean("save-items")){
                        Inventory playerStuff = player.getInventory();
                        Location playerLoc = player.getLocation();
                        Block playerChest = playerLoc.getBlock();
                        playerChest.setType(Material.CHEST);
                        Chest chest1 = (Chest)playerChest.getBlockData();
                        chest1.setType(Chest.Type.LEFT);
                        playerChest.setBlockData(chest1,true);
                        org.bukkit.block.Chest chest2 = (org.bukkit.block.Chest) playerChest.getState();
                        //chest2.setCustomName(player.getName()+"'s stuff");

                        playerChest = playerChest.getRelative(BlockFace.EAST);
                        playerChest.setType(Material.CHEST);
                        chest1 = (Chest)playerChest.getBlockData();
                        chest1.setType(Chest.Type.RIGHT);
                        playerChest.setBlockData(chest1,true);
                        org.bukkit.block.Chest chest0 = (org.bukkit.block.Chest) playerChest.getState();
                        //chest0.setCustomName(player.getName()+"'s stuff");


                        for(int i = 0; i<playerStuff.getSize();i++){
                            //player.sendMessage(String.valueOf(playerStuff.getItem(i))+"/"+i);
                            /////!!!!!!!!!!!!!!!!!!!!!VvvvvvvvvvV
                            if(i>26&&playerStuff.getItem(i)!=null){
                                chest2.getBlockInventory().addItem(playerStuff.getItem(i));
                            }else if(playerStuff.getItem(i)!=null){
                                chest0.getBlockInventory().addItem(playerStuff.getItem(i));
                            }

                            //((org.bukkit.block.Chest) playerChest).getInventory().addItem(playerStuff.getItem(i));
                        }
                        ItemStack letter = new ItemStack(Material.PAPER);
                        ItemMeta letterMeta = letter.getItemMeta();
                        letterMeta.setDisplayName(player.getName()+"'s stuff");
                        letter.setItemMeta(letterMeta);
                        chest2.getBlockInventory().addItem(letter);
                    }

                    if(world.equalsIgnoreCase("end")){
                        location.setWorld(Bukkit.getWorld(plugin.getConfig().getString("end-world")));
                        location.setZ(location.getZ()-900);
                        location.setY(60);
                    }else if(world.equalsIgnoreCase("nether")){
                        location.setWorld(Bukkit.getWorld(plugin.getConfig().getString("nether-world")));
                        location.setY(65);
                    }
                    Block tobreak = location.getBlock();
                    while(!tobreak.getRelative(0,-1,0).getType().isSolid()||tobreak.getType()!=Material.AIR||tobreak.getRelative(0,1,0).getType()!=Material.AIR){
                        location.setX(location.getX()+1);
                        tobreak = location.getBlock();
                    }
                    player.teleport(location);
                    plugin.getExiledPlayers().set(player.getName()+".exiled", true);
                    plugin.getExiledPlayers().set(player.getName()+".location", location);
                    plugin.saveExiledPlayers();
                    if(plugin.getConfig().getBoolean("clear-inventory")){
                        player.getInventory().clear();
                    }
                    player.sendMessage(plugin.getConfig().getString("exile-yourself-message"));
                }
                //si alguien exilia a otro
            }else if(strings.length==2){
                Player player = commandSender.getServer().getPlayerExact(strings[0]);
                String world = strings[1];
                //
                //saveitems star
                if(plugin.getConfig().getBoolean("save-items")){
                    Inventory playerStuff = player.getInventory();
                    Location playerLoc = player.getLocation();
                    Block playerChest = playerLoc.getBlock();
                    playerChest.setType(Material.CHEST);
                    Chest chest1 = (Chest)playerChest.getBlockData();
                    chest1.setType(Chest.Type.LEFT);
                    playerChest.setBlockData(chest1,true);
                    org.bukkit.block.Chest chest2 = (org.bukkit.block.Chest) playerChest.getState();
                    //chest2.setCustomName(player.getName()+"'s stuff");

                    playerChest = playerChest.getRelative(BlockFace.EAST);
                    playerChest.setType(Material.CHEST);
                    chest1 = (Chest)playerChest.getBlockData();
                    chest1.setType(Chest.Type.RIGHT);
                    playerChest.setBlockData(chest1,true);
                    org.bukkit.block.Chest chest0 = (org.bukkit.block.Chest) playerChest.getState();
                    //chest0.setCustomName(player.getName()+"'s stuff");


                    for(int i = 0; i<playerStuff.getSize();i++){
                        //player.sendMessage(String.valueOf(playerStuff.getItem(i))+"/"+i);
                        /////!!!!!!!!!!!!!!!!!!!!!VvvvvvvvvvV
                        if(i>26&&playerStuff.getItem(i)!=null){
                            chest2.getBlockInventory().addItem(playerStuff.getItem(i));
                        }else if(playerStuff.getItem(i)!=null){
                            chest0.getBlockInventory().addItem(playerStuff.getItem(i));
                        }

                        //((org.bukkit.block.Chest) playerChest).getInventory().addItem(playerStuff.getItem(i));
                    }
                    ItemStack letter = new ItemStack(Material.PAPER);
                    ItemMeta letterMeta = letter.getItemMeta();
                    letterMeta.setDisplayName(player.getName()+"'s stuff");
                    letter.setItemMeta(letterMeta);
                    chest2.getBlockInventory().addItem(letter);
                }

                //saveitems end



                if(player!=null){
                    Location location = player.getLocation();
                    if(world.equalsIgnoreCase("end")){
                        location.setWorld(Bukkit.getWorld(plugin.getConfig().getString("end-world")));
                        location.setZ(location.getZ()-900);
                        location.setY(60);
                    }else if(world.equalsIgnoreCase("nether")){
                        location.setWorld(Bukkit.getWorld(plugin.getConfig().getString("nether-world")));
                        location.setY(65);
                    }
                    Block tobreak = location.getBlock();
                    while(!tobreak.getRelative(0,-1,0).getType().isSolid()||tobreak.getType()!=Material.AIR||tobreak.getRelative(0,1,0).getType()!=Material.AIR){
                        location.setX(location.getX()+1);
                        tobreak = location.getBlock();
                    }
                    player.teleport(location);
                    plugin.getExiledPlayers().set(player.getName()+".exiled", true);
                    plugin.getExiledPlayers().set(player.getName()+".location", location);
                    plugin.saveExiledPlayers();
                    if(plugin.getConfig().getBoolean("clear-inventory")){
                        player.getInventory().clear();
                    }
                    player.sendMessage(plugin.getConfig().getString("exile-message"));
                    Player sender = (Player)commandSender;
                    sender.sendMessage(plugin.getConfig().getString("exile-feedback-message"));
                    //
                }
            }
            return true;
        }else{
            Bukkit.getLogger().info("[Exiled] Error, have you try to google it?");
        }
        return false;
    }
}
