package io.BM;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class btpCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) return true;
        Player p = (Player) commandSender;
        if(!p.isOp()) return true;

        if (strings.length == 0) return true;


        double x,y,z = 0;

        if(strings.length == 1 && Integer.parseInt(strings[0]) == 0) {
            p.teleport(Bukkit.getWorld("world").getSpawnLocation().clone().add(0,1,0));
            p.sendMessage("x: "+Bukkit.getWorld("world").getSpawnLocation().getX()+" y: "+Bukkit.getWorld("world").getSpawnLocation().getY()+" z: "+Bukkit.getWorld("world").getSpawnLocation().getZ());
            return true;
        }

        if(strings.length == 3) {
            try {
                x = Integer.parseInt(strings[0]);
            } catch (Exception e) {
                x = p.getLocation().getX()-.5;
            }

            try {
                z = Integer.parseInt(strings[2]);
            } catch (Exception e) {
                z = p.getLocation().getZ()-.5;
            }

            try {
                y = Integer.parseInt(strings[1]);
            } catch (Exception e) {
                y = Bukkit.getWorld("world").getHighestBlockYAt((int) (x+.5), (int) (z+.5));
            }

            p.sendMessage("x: "+Math.round(x)+" y: "+(Math.round(y)+1)+" z: "+Math.round(z));

            Location loc = p.getLocation().clone();
            loc.setX(x+0.5);
            loc.setY(y+1.4);
            loc.setZ(z+0.5);
            p.teleport(loc);

        }

        if(strings.length == 4) {
            Player target = Bukkit.getPlayerExact(strings[0]);
            if(target == null) return true;

            try {
                x = Integer.parseInt(strings[0]);
            } catch (Exception e) {
                x = target.getLocation().getX()-.5;
            }

            try {
                z = Integer.parseInt(strings[2]);
            } catch (Exception e) {
                z = target.getLocation().getZ()-.5;
            }

            try {
                y = Integer.parseInt(strings[1]);
            } catch (Exception e) {
                y = Bukkit.getWorld("world").getHighestBlockYAt((int) (x+.5), (int) (z+.5));
            }

            p.sendMessage("x: "+Math.round(x)+" y: "+(Math.round(y)+1)+" z: "+Math.round(z)+"\nTarget: "+target.getName());

            Location loc = target.getLocation().clone();
            loc.setX(x+0.5);
            loc.setY(y+1.4);
            loc.setZ(z+0.5);
            target.teleport(loc);


            return true;


        }

        if(strings.length == 2 && Integer.parseInt(strings[1]) == 0) {
            Player target = Bukkit.getPlayerExact(strings[0]);
            if(target == null) return true;
            target.teleport(Bukkit.getWorld("world").getSpawnLocation().clone().add(0,1,0));
            p.sendMessage("x: "+Bukkit.getWorld("world").getSpawnLocation().getX()+" y: "+Bukkit.getWorld("world").getSpawnLocation().getY()+" z: "+Bukkit.getWorld("world").getSpawnLocation().getZ()+"\nTarget: "+target.getName());

        }





        return true;
    }
}
