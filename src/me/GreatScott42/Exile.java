package me.GreatScott42;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exile extends JavaPlugin {
    private File exiledPlayersFile;
    private FileConfiguration exiledPlayers;

    @Override
    public void onEnable() {
        if(!getConfig().getBoolean("configFile")){
            saveDefaultConfig();
            getConfig().set("configFile",true);
            saveConfig();
        }else{
            getConfig().options().copyDefaults(true);
        }
        if(!getConfig().get("config-version").equals(1.5)){

            getConfig().set("config-version",1.5);
            getConfig().set("save-items",true);
            saveConfig();

        }
        //update config to 1.2/.3
        /*if(!getConfig().contains("nether-world")||!getConfig().getString("config-version").equalsIgnoreCase("1.3")){
            saveDefaultConfig();
            //getConfig().set("configFile",true);
            getConfig().set("config-version",1.3);
            List<String> footer = new ArrayList<>();
            footer.add("\n#names of the worlds, if your world have different names change this, if they are the same do not edit\n" +
                    "nether-world: \"world_nether\"\n"+
                    "end-world: \"world_the_end\"");
            getConfig().options().setFooter(footer);
            saveConfig();
        }*/
        createExiledFile();
        getServer().getPluginManager().registerEvents(new Events(this),this);
        getCommand("exile").setExecutor(new ExileCommand(this));
        getCommand("exile").setTabCompleter(new TabSuggest());
        getCommand("forgive").setExecutor(new ForgiveCommand(this));
        Bukkit.getLogger().info("[Exile] Exile is Enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[Exile] Exile is Disabled");
    }

    public FileConfiguration getExiledPlayers() {
        return this.exiledPlayers;
    }

    public void saveExiledPlayers(){
        try {
            exiledPlayers.save(exiledPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createExiledFile() {
        exiledPlayersFile = new File(getDataFolder(), "exiledPlayers.yml");
        if (!exiledPlayersFile.exists()) {
            exiledPlayersFile.getParentFile().mkdirs();
            saveResource("exiledPlayers.yml", false);
        }
        exiledPlayers = new YamlConfiguration();
        try {
            exiledPlayers.load(exiledPlayersFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
