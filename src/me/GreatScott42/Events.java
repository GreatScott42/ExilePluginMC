package me.GreatScott42;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.util.Vector;

import java.util.EventListener;

public class Events implements Listener {

    private Exile plugin;

    public Events(Exile plugin){
        this.plugin=plugin;
    }

    @EventHandler
    public void changeWorld(PlayerChangedWorldEvent e){
        if(e.getPlayer().getWorld()==Bukkit.getWorld("world")&&plugin.getExiledPlayers().getBoolean(e.getPlayer().getName()+".exiled")){
            e.getPlayer().setHealth(0);
        }
    }

    @EventHandler
    public void respawnEvent(PlayerRespawnEvent e){
        if(plugin.getExiledPlayers().getBoolean(e.getPlayer().getName()+".exiled")){
            Location location = (Location) plugin.getExiledPlayers().get(e.getPlayer().getName()+".location");
            e.setRespawnLocation(location);
        }
    }

    @EventHandler
    public void destroyPortals(PlayerPortalEvent e){
        if(plugin.getExiledPlayers().getBoolean(e.getPlayer().getName()+".exiled")&&e.getPlayer().getLocation().getWorld()==Bukkit.getWorld("world_nether")){
            if(plugin.getConfig().getBoolean("escape-exile")){
               return;
            }
            e.setCancelled(true);
            e.getPlayer().getWorld().createExplosion(e.getPlayer().getLocation(),4f);
        }
    }
    @EventHandler
    public void cancelEndGate(PlayerTeleportEvent e){
        if(plugin.getExiledPlayers().getBoolean(e.getPlayer().getName()+".exiled")){
            if(plugin.getConfig().getBoolean("escape-exile")){
                return;
            }
            if(e.getCause()== PlayerTeleportEvent.TeleportCause.END_GATEWAY){
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void registerPlayers(PlayerJoinEvent e){
        if(!plugin.getExiledPlayers().contains(e.getPlayer().getName())){
            plugin.getExiledPlayers().set(e.getPlayer().getName()+".exiled", false);
            plugin.saveExiledPlayers();
            return;
        }
    }
}
