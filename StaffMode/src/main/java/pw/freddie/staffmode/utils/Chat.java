package pw.freddie.staffmode.utils;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class Chat {

    public static String RED = ChatColor.RED.toString();
    public static String DARK_RED = ChatColor.DARK_RED.toString();
    public static String PINK = ChatColor.LIGHT_PURPLE.toString();
    public static String PURPLE = ChatColor.DARK_PURPLE.toString();

    public static String AQUA = ChatColor.AQUA.toString();
    public static String DARK_AQUA = ChatColor.DARK_AQUA.toString();

    public static String DARK_BLUE = ChatColor.DARK_BLUE.toString();
    public static String LIGHT_BLUE = ChatColor.BLUE.toString();

    public static String BLACK = ChatColor.BLACK.toString();
    public static String GRAY = ChatColor.GRAY.toString();
    public static String DARK_GRAY = ChatColor.DARK_GRAY.toString();
    public static String WHITE = ChatColor.WHITE.toString();

    public static String YELLOW = ChatColor.YELLOW.toString();
    public static String GOLD = ChatColor.GOLD.toString();

    public static String GREEN = ChatColor.GREEN.toString();
    public static String DARK_GREEN = ChatColor.DARK_GREEN.toString();

    public static String RESET = ChatColor.RESET.toString();
    public static String MAGIC = ChatColor.MAGIC.toString();
    public static String ITALIC = ChatColor.ITALIC.toString();
    public static String STRIKETHROUGH = ChatColor.STRIKETHROUGH.toString();
    public static String UNDERLINE = ChatColor.UNDERLINE.toString();
    public static String BOLD = ChatColor.BOLD.toString();

    public static String translate(String message) { return ChatColor.translateAlternateColorCodes('&', message); }
    public static void sendToPermission(String message, String permission) {
        for (Player all : Bukkit.getOnlinePlayers()) if (all.hasPermission(permission)) all.sendMessage(translate(message));
    }

    public static String oreToColor(Material material) {
        switch (material) {
            case GLOWING_REDSTONE_ORE:
            case REDSTONE_ORE:
                return "&c";
            case GOLD_ORE: return "&6";
            case COAL_ORE:
                return "&7";
            case LAPIS_ORE: return "&9";
            case DIAMOND_ORE: return "&b";
            case EMERALD_ORE: return "&a";
            default: return "&f";
        }
    }

    public static void sendStaff(String message) {
        for (Player all : Bukkit.getOnlinePlayers()) if (all.hasPermission("core.staff")) all.sendMessage(message);
    }
    public static void sendTextComponent(BaseComponent[] components, List<Player> players) {
        players.forEach(player -> player.spigot().sendMessage(components));
    }
    public static void sendTextComponent(BaseComponent[] components, String permission) {
        for (Player all : Bukkit.getOnlinePlayers()) {
            if (all.hasPermission(permission)) all.spigot().sendMessage(components);
        }
    }
}
