package me.swipez.moblookmultiply;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TimeDecreaseRunnable extends BukkitRunnable {

    @Override
    public void run() {
        if (MobLookMultiply.gamestarted){
            if (MobLookMultiply.timer > 0){
                MobLookMultiply.timer--;
                if (MobLookMultiply.timer == 0){
                    MobLookMultiply.timer = MobLookMultiply.startingTimer;
                    for (Player player : Bukkit.getOnlinePlayers()){
                        UUID uuid = player.getUniqueId();
                        MobLookMultiply.mobMultiplier.put(uuid, MobLookMultiply.mobMultiplier.get(uuid)+1);
                        player.sendMessage(ChatColor.GRAY+"[!] Mobs will now multiply "+ChatColor.LIGHT_PURPLE+MobLookMultiply.mobMultiplier.get(uuid)+ChatColor.GRAY+" times faster!");
                    }
                }
                for (Player player : Bukkit.getOnlinePlayers()){
                    SendTitleBarMessage.sendMessage(player, ChatColor.AQUA+"until mobs multiply faster..", MobLookMultiply.timer);
                }
            }
        }
    }
}
