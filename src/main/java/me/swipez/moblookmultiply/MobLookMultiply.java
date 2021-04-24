package me.swipez.moblookmultiply;

import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public final class MobLookMultiply extends JavaPlugin {

    public static boolean gamestarted = false;

    public static HashMap<UUID, LivingEntity> duplicatedMobs = new HashMap<>();
    public static HashMap<UUID, Integer> mobMultiplier = new HashMap<>();

    public static int startingTimer = 300;
    public static int timer = startingTimer;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("moblook").setExecutor(new StartCommand(this));
        getCommand("moblook").setTabCompleter(new CommandComplete());
        BukkitTask task = new BlockLookRunnable(this).runTaskTimer(this, 1, 1);
        BukkitTask MobMultiplyTask = new MobMultiplyRunnable(this).runTaskTimer(this, 20, 20);
        BukkitTask TimeDecrease = new TimeDecreaseRunnable().runTaskTimer(this, 20, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
