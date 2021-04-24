package me.swipez.moblookmultiply;

import me.swipez.moblookmultiply.RayCast.Raycast;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BlockLookRunnable extends BukkitRunnable {

    MobLookMultiply plugin;

    public BlockLookRunnable(MobLookMultiply plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (MobLookMultiply.gamestarted) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID uuid = player.getUniqueId();
                LivingEntity entity = getMobPlayerIsLookingAt(player);
                if (entity != null && !(entity instanceof Player)){
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2, 1, false, false));
                    if (!MobLookMultiply.duplicatedMobs.containsKey(uuid) || MobLookMultiply.duplicatedMobs.get(uuid) != entity){
                        MobLookMultiply.duplicatedMobs.put(uuid, entity);
                    }
                }
                else {
                    MobLookMultiply.duplicatedMobs.remove(uuid);
                }
            }
        }
    }

    LivingEntity getMobPlayerIsLookingAt(Player player) {
        Raycast raycast = new Raycast(player.getEyeLocation(), 100);
        if (raycast.compute(Raycast.RaycastType.ENTITY)){
            Entity entity = raycast.getHurtEntity();
            if (entity instanceof LivingEntity && !(entity instanceof EnderDragon)){
                return (LivingEntity) entity;
            }
        }
        return null;
    }
}