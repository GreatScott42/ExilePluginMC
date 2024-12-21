package me.GreatScott42;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabSuggest implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> commands = new ArrayList<>();
        if(strings.length==2){
            List<String> suggestions = new ArrayList<>();
            suggestions.add("end");
            suggestions.add("nether");
            for (String c : suggestions){
                if(c.toLowerCase().startsWith(strings[1].toLowerCase())){
                    commands.add(c);
                }
            }
            return commands;
        }
        return null;
    }
}
