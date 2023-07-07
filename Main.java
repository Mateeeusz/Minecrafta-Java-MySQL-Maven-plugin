package me.mati.skyblock;

import me.mati.skyblock.Commands.*;
import me.mati.skyblock.Commands.admin.*;
import me.mati.skyblock.Commands.premiumRanks.homeCommand;
import me.mati.skyblock.Commands.premiumRanks.repairCommand;
import me.mati.skyblock.Managers.*;
import me.mati.skyblock.Managers.MainManagers.BanManager;
import me.mati.skyblock.Managers.MainManagers.MuteManager;
import me.mati.skyblock.Managers.MainManagers.TurboDropManager;
import me.mati.skyblock.Managers.MainManagers.UserManager;
import me.mati.skyblock.Managers.TimerManagers.TimerManager;
import me.mati.skyblock.api.commands.Command;
import me.mati.skyblock.api.commands.CommandManager;
import me.mati.skyblock.guisCommands.*;
import me.mati.skyblock.listeners.*;
import me.mati.skyblock.listeners.SchowekListeners.SchowekRunnable;
import me.mati.skyblock.listeners.guis.listeners.todo.kitInventoryListener;
import me.mati.skyblock.store.Store;
import me.mati.skyblock.store.modes.StoreMySQL;
import me.mati.skyblock.tasks.*;
import me.mati.skyblock.utils.BlockUtil;
import me.mati.skyblock.utils.ItemBuilder;
import me.mati.skyblock.utils.TimeUtil;
import me.mati.skyblock.utils.Utill;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

public class Main extends JavaPlugin {
    public void onLoad(){
        Main.plugin = this;
    }

    private static JavaPlugin plugin;
    private static Store store;




    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.registerDatabase();
        DropManager.load();
        UserManager.setup();
        BanManager.setup();
        MuteManager.setup();
        DropManager.setup();
        TurboDropManager.loadAll();
        DropFile.saveDefaultConfig();

        BlockUtil.setDurability("WATER", 73.6f);
        BlockUtil.setDurability("LAVA", 73.6f);
        BlockUtil.setDurability("FLOWING_LAVA", 10.0f);
        BlockUtil.setDurability("FLOWING_WATER", 10.0f);
        BlockUtil.setDurability("ENCHANTING_TABLE", 10.0f);
        BlockUtil.setDurability("ANVIL", 10.0f);
        BlockUtil.setDurability("ENDER_CHEST", 73.6f);
        BlockUtil.setDurability("OBSIDIAN", 72.0f);




        plugin = this;

        registerCommand(new KickAllCommand());
        registerCommand(new dropCommand());
        registerCommand(new sponsorCommand());
        registerCommand(new turboDropCommand());
        registerCommand(new KickCommand());
        registerCommand(new helpCommand());
        registerCommand(new kitCommand());
        registerCommand(new gcCommand());
        registerCommand(new enderCommand());
        registerCommand(new invseeCommand());
        registerCommand(new klCommand());
        registerCommand(new helpopCommand());
        registerCommand(new tempBanCommand());
        registerCommand(new muteCommand());
        registerCommand(new unmuteCommand());
        registerCommand(new slotCommand());
        registerCommand(new efektyCommand());
        registerCommand(new slowmodeCommand());
        registerCommand(new banCommand());
        registerCommand(new unbanCommand());
        registerCommand(new dropReloadCommand());
        registerCommand(new buttonCommand());
        registerCommand(new teleportCommand());
        registerCommand(new svipCommand());
        registerCommand(new craftingsCommand());
        registerCommand(new healCommand());
        registerCommand(new gmCommand());
        registerCommand(new replyCommand());
        registerCommand(new tellCommand());
        registerCommand(new flyCommand());
        registerCommand(new vipCommand());
        registerCommand(new setSpawnCommand());
        registerCommand(new spawnCommand());
        registerCommand(new clearCoammand());
        registerCommand(new enchantCommand());
        registerCommand(new ChatLock());
        registerCommand(new brodcastCommand());
        registerCommand(new repairCommand());
        registerCommand(new headCommand());
        registerCommand(new nameCommand());
        registerCommand(new cxCommand());
        registerCommand(new tphereCommand());
        registerCommand(new adminCommand());
        registerCommand(new homeCommand());
        registerCommand(new setHomeCommand());
        registerCommand(new youtubeCommand());
        registerCommand(new premiumCommand());
        registerCommand(new ChatClearCommand());
        registerCommand(new tpaCommand());
        registerCommand(new depozytCommand());
        registerCommand(new tpAcceptCommand());
        registerCommand(new tpDenyCommand());
        registerCommand(new checkCommand());
        registerCommand(new vanishCommand());
        registerCommand(new rankingSetCommand());
        registerCommand(new rankingResetCommand());
        registerCommand(new giveCaseCommand());


        new AutomsgTask().runTaskTimerAsynchronously(this, 20L, (long) TimeUtil.SECOND.getTick(Config.AUTOMESSAGE_INTERVAL));
        new SchowekRunnable().runTaskTimerAsynchronously(this, 20L, TimeUtil.SECOND.getTick(2));
        new CombatLogTask().runTaskTimerAsynchronously(this, 40L, 20L);
        new AutoSaveTask().runTaskTimerAsynchronously(this, 20L, (long) TimeUtil.MINUTE.getTick(3));
        new ClearItemsTask().runTaskTimerAsynchronously(this, 60L, (long) TimeUtil.MINUTE.getTick(Config.ClearItems_TIME));
        (new TurboDropChecTask()).runTaskTimerAsynchronously(this, 20L, (long) TimeUtil.SECOND.getTick(60));


//        this.getServer().getPluginManager().registerEvents(new DefaultCommandsBlock(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);


        registerListener(this, new PlayerConsumeEvent());
        registerListener(this, new BlockPlaceListener());
        registerListener(this, new EntityDamageByEntityListener());
        registerListener(this, new PlayerDeathListener());
        registerListener(this, new PlayerInteractListener());
        registerListener(this, new WeatherListener());
        registerListener(this, new kitInventoryListener());
        registerListener(this, new EntityExplodeListener());
        registerListener(this, new BugListener());
        registerListener(this, new BlockFromToListener());
        registerListener(this, new OnJoin());
        registerListener(this, new CommandCooldown());
        registerListener(this, new PlayerRespawnListener());
        registerListener(this, new TimerManager());
        registerListener(this, new RandomTp());
        registerListener(this, new ChatListener());
        registerListener(this, new OnLeave());
        registerListener(this, new BlockBreakListener());
        registerListener(this, new BoyFarmerListener());

        Bukkit.addRecipe((new ShapedRecipe(new ItemStack(Material.ENDER_CHEST, 1))).shape(new String[]{"sss", "sps", "sss"}).setIngredient('s', Material.OBSIDIAN).setIngredient('p', Material.ENDER_PEARL));
        Bukkit.addRecipe((new ShapedRecipe((new ItemBuilder(Material.ENDER_STONE)).setTitle(Utill.fixColor("&cGenerator kamienia")).addEnchantment(Enchantment.SILK_TOUCH, 10).addLores(Arrays.asList(Utill.fixColor("&eGeneruje kamien"), Utill.fixColor("&eAby zaczal dzialac nalezy go postawic"))).build())).shape(new String[]{"sss", "sps", "sss"}).setIngredient('s', Material.STONE).setIngredient('p', Material.PISTON_BASE));
        log("core on", Level.WARNING);

    }

    @Override
    public void onDisable() {

        Bukkit.getScheduler().cancelTasks(this);
        for (Player p : Bukkit.getOnlinePlayers()) {
            UserManager.leaveFromGame(p);
        }
        Bukkit.savePlayers();
        Main.plugin = null;
        log("core off", Level.WARNING);
    }

    public static void registerCommand(Command command) {
        CommandManager.register(command);
    }
    public void log(String s, Level l) {
        getLogger().log(l, "[mvti]" + s);
    }
    public static Store getStore() {
        return Main.store;
    }

    private static PluginManager pluginManager;
    public static void registerListener(final Plugin plugin, final Listener... listeners) {
        if (Main.pluginManager == null) {
            Main.pluginManager = Bukkit.getPluginManager();
        }
        for (final Listener listener : listeners) {
            Main.pluginManager.registerEvents(listener, plugin);
        }
    }

    protected void registerDatabase() {
        Main.store = new StoreMySQL(Config.DATABASE_MYSQL_HOST, Config.DATABASE_MYSQL_PORT, Config.DATABASE_MYSQL_USER, Config.DATABASE_MYSQL_PASS, Config.DATABASE_MYSQL_NAME, Config.DATABASE_TABLEPREFIX);
        Main.store.connect();
    }

}