package pw.freddie.staffmode;

import lombok.Getter;
import net.frozenorb.qlib.command.FrozenCommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pw.freddie.staffmode.staff.StaffModeHandler;
import pw.freddie.staffmode.listeners.StaffItemListeners;
import pw.freddie.staffmode.listeners.StaffModeListeners;
import pw.freddie.staffmode.staff.vanish.VanishHandler;
import pw.freddie.staffmode.staff.vanish.VanishListeners;

public final class StaffMode extends JavaPlugin {

    @Getter private static StaffMode instance;
    @Getter private StaffModeHandler staffModeHandler;
    @Getter private VanishHandler vanishHandler;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        staffModeHandler = new StaffModeHandler();
        vanishHandler = new VanishHandler();

        FrozenCommandHandler.registerAll(this);

        Bukkit.getPluginManager().registerEvents(new StaffItemListeners(), this);
        Bukkit.getPluginManager().registerEvents(new StaffModeListeners(), this);
        Bukkit.getPluginManager().registerEvents(new VanishListeners(), this);
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
