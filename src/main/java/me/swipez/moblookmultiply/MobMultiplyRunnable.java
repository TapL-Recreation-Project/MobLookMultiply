package me.swipez.moblookmultiply;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public class MobMultiplyRunnable extends BukkitRunnable {

    MobLookMultiply plugin;

    public MobMultiplyRunnable(MobLookMultiply plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (MobLookMultiply.gamestarted){
            for (Player player : Bukkit.getOnlinePlayers()){
                UUID uuid = player.getUniqueId();
                if (MobLookMultiply.duplicatedMobs.containsKey(uuid)){
                    LivingEntity livingEntity = MobLookMultiply.duplicatedMobs.get(uuid);
                    int times = MobLookMultiply.mobMultiplier.get(uuid);
                    for (int i = 0; i < times; i++){
                        for (int k = 0; k < times; k++){
                            BukkitTask soundTask = new BukkitRunnable() {
                                @Override
                                public void run() {
                                    player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1, 1);
                                }
                            }.runTaskLater(plugin, (k* 3L));
                        }
                        livingEntity.getWorld().spawnEntity(livingEntity.getLocation(), livingEntity.getType());
                    }
                }
            }
        }
    }
}
