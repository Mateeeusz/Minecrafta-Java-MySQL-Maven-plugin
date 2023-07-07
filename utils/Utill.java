package me.mati.skyblock.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utill {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static final LinkedHashMap<Integer, String> values;

    static {
        (values = new LinkedHashMap<>(6)).put(31104000, "y");
        values.put(2592000, "msc");
        values.put(86400, "d");
        values.put(3600, "h");
        values.put(60, "min");
        values.put(1, "s");
    }

    public static void RemoveCost(Player p, ItemStack cost, Integer ile){
        if(p.getInventory().containsAtLeast(cost, ile)){
            p.getInventory().removeItem(new ItemStack(Material.EMERALD_BLOCK, ile));
            Utill.sendMsg(p, Utill.fixColor("2Zakupiles efeekt"));
        } else {
            Utill.sendMsg(p, "&4Nie posiadasz " + ile + "Blokow emeraldow");
        }
    }

    public static String getDurationBreakdown(long millis) {
        if (millis == 0L) {
            return "0";
        } else {
            long days = TimeUnit.MILLISECONDS.toDays(millis);

            if (days > 0L) {
                millis -= TimeUnit.DAYS.toMillis(days);
            }

            long hours = TimeUnit.MILLISECONDS.toHours(millis);

            if (hours > 0L) {
                millis -= TimeUnit.HOURS.toMillis(hours);
            }

            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);

            if (minutes > 0L) {
                millis -= TimeUnit.MINUTES.toMillis(minutes);
            }

            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

            if (seconds > 0L) {
                @SuppressWarnings("unused")
                long i = millis - TimeUnit.SECONDS.toMillis(seconds);
            }

            StringBuilder sb = new StringBuilder();
            long i;

            if (days > 0L) {
                sb.append(days);
                i = days % 10L;
                if (i == 1L) {
                    sb.append("d ");
                } else {
                    sb.append("d ");
                }
            }

            if (hours > 0L) {
                sb.append(hours);
                i = hours % 10L;
                if (i == 1L) {
                    sb.append("h ");
                } else if (i < 5L) {
                    sb.append("h ");
                } else {
                    sb.append("h ");
                }
            }

            if (minutes > 0L) {
                sb.append(minutes);
                i = minutes % 10L;
                if (i == 1L) {
                    sb.append("min ");
                } else if (i < 5L) {
                    sb.append("min ");
                } else {
                    sb.append("min ");
                }
            }

            if (seconds > 0L) {
                sb.append(seconds);
                i = seconds % 10L;
                if (i == 1L) {
                    sb.append("s");
                } else if (i < 5L) {
                    sb.append("s");
                } else {
                    sb.append("s");
                }
            }

            return sb.toString();
        }
    }

    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = (Inventory)p.getInventory();
        final HashMap<Integer, ItemStack> notStored = (HashMap<Integer, ItemStack>)i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItemNaturally(p.getLocation(), (ItemStack)e.getValue());
        }
    }
    public static ItemStack getItemStackFromString(final String itemstack) {
        final String[] splits = itemstack.split("@");
        final String type = splits[0];
        final String data = (splits.length == 2) ? splits[1] : null;
        if (data == null) {
            return new ItemStack(Material.getMaterial(type), 1);
        }
        return new ItemStack(Material.getMaterial(type), 1, (short) Integer.parseInt(data));
    }

    public static String getStringFromItemstack(final ItemStack itemstack) {
        if (itemstack.getData().getData() > 0) {
            return itemstack.getType().toString() + "@" + itemstack.getData().getData();
        }
        return itemstack.getType().toString();
    }

    public static Player getDamager(final EntityDamageByEntityEvent e) {
        final Entity damager = e.getDamager();
        if (damager instanceof Player) {
            return (Player) damager;
        }
        if (damager instanceof Projectile) {
            final Projectile p = (Projectile) damager;
            if (p.getShooter() instanceof Player) {
                return (Player) p.getShooter();
            }
        }
        return null;
    }


    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    public static boolean sendMsg(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMsg(sender, message);
        }
        return permission != null && !Objects.equals(permission, "") && sender.hasPermission(permission) && sendMsg(sender, message);
    }


    public static boolean sendMsg(final CommandSender sender, final String message) {
        if (sender instanceof Player) {
            sender.sendMessage(fixColor(" &9 " + message));
        }
        else {
            sender.sendMessage(fixColor(" &9" + message));
        }
        return true;
    }
    public static boolean sendMsg(final Collection<? extends CommandSender> collection, final String message) {
        for(final CommandSender cs : collection) {
            sendMsg(cs, message);
        }
        return true;
    }
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(fixColor(message));
    }


    public static boolean containsIgnoreCase(String[] board, String string) {
        for (String otherString : board) {
            if (otherString.equalsIgnoreCase(string)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }

    public static String secondsToString(int seconds) {
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<Integer, String> e : Utill.values.entrySet()) {
            final int iDiv = seconds / e.getKey();
            if (iDiv >= 1) {
                final int x = (int) Math.floor(iDiv);
                sb.append(x + e.getValue()).append(" ");
                seconds -= x * e.getKey();
            }
        }
        return sb.toString();
    }
    public static String getDate(final long time) {
        return Utill.dateFormat.format(new Date(time));
    }

    public static String getTime(final long time) {
        return Utill.timeFormat.format(new Date(time));
    }

    public static long parseDateDiff(final String time, final boolean future) {
        try {
            final Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);
            final Matcher m = timePattern.matcher(time);
            int years = 0;
            int months = 0;
            int weeks = 0;
            int days = 0;
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            boolean found = false;
            while (m.find()) {
                if (m.group() != null && !m.group().isEmpty()) {
                    for (int i = 0; i < m.groupCount(); ++i) {
                        if (m.group(i) != null && !m.group(i).isEmpty()) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        continue;
                    }
                    if (m.group(1) != null && !m.group(1).isEmpty()) {
                        years = Integer.parseInt(m.group(1));
                    }
                    if (m.group(2) != null && !m.group(2).isEmpty()) {
                        months = Integer.parseInt(m.group(2));
                    }
                    if (m.group(3) != null && !m.group(3).isEmpty()) {
                        weeks = Integer.parseInt(m.group(3));
                    }
                    if (m.group(4) != null && !m.group(4).isEmpty()) {
                        days = Integer.parseInt(m.group(4));
                    }
                    if (m.group(5) != null && !m.group(5).isEmpty()) {
                        hours = Integer.parseInt(m.group(5));
                    }
                    if (m.group(6) != null && !m.group(6).isEmpty()) {
                        minutes = Integer.parseInt(m.group(6));
                    }
                    if (m.group(7) == null) {
                        break;
                    }
                    if (m.group(7).isEmpty()) {
                        break;
                    }
                    seconds = Integer.parseInt(m.group(7));
                    break;
                }
            }
            if (!found) {
                return -1L;
            }
            final Calendar c = new GregorianCalendar();
            if (years > 0) {
                c.add(1, years * (future ? 1 : -1));
            }
            if (months > 0) {
                c.add(2, months * (future ? 1 : -1));
            }
            if (weeks > 0) {
                c.add(3, weeks * (future ? 1 : -1));
            }
            if (days > 0) {
                c.add(5, days * (future ? 1 : -1));
            }
            if (hours > 0) {
                c.add(11, hours * (future ? 1 : -1));
            }
            if (minutes > 0) {
                c.add(12, minutes * (future ? 1 : -1));
            }
            if (seconds > 0) {
                c.add(13, seconds * (future ? 1 : -1));
            }
            final Calendar max = new GregorianCalendar();
            max.add(1, 10);
            if (c.after(max)) {
                return max.getTimeInMillis();
            }
            return c.getTimeInMillis();
        } catch (Exception e) {
            return -1L;
        }
    }


    public static String getDurationBreakdown2(long millis) {
        if (millis == 0L) {
            return "0";
        } else {
            long days = TimeUnit.MILLISECONDS.toDays(millis);

            if (days > 0L) {
                millis -= TimeUnit.DAYS.toMillis(days);
            }

            long hours = TimeUnit.MILLISECONDS.toHours(millis);

            if (hours > 0L) {
                millis -= TimeUnit.HOURS.toMillis(hours);
            }

            long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);

            if (minutes > 0L) {
                millis -= TimeUnit.MINUTES.toMillis(minutes);
            }

            long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

            if (seconds > 0L) {
                @SuppressWarnings("unused")
                long i = millis - TimeUnit.SECONDS.toMillis(seconds);
            }

            StringBuilder sb = new StringBuilder();
            long i;

            if (days > 0L) {
                sb.append(days);
                i = days % 10L;
                if (i == 1L) {
                    sb.append("d ");
                } else {
                    sb.append("d ");
                }
            }

            if (hours > 0L) {
                sb.append(hours);
                i = hours % 10L;
                if (i == 1L) {
                    sb.append("h");
                } else if (i < 5L) {
                    sb.append("h");
                } else {
                    sb.append("h");
                }
            }

            if (minutes > 0L) {
                sb.append(minutes);
                i = minutes % 10L;
                if (i == 1L) {
                    sb.append(" minute i ");
                } else if (i < 5L) {
                    sb.append(" minuty ");
                } else {
                    sb.append(" minut ");
                }
            }

            if (seconds > 0L) {
                sb.append(seconds);
                i = seconds % 10L;
                if (i == 1L) {
                    sb.append(" sekund");
                } else if (i < 5L) {
                    sb.append(" sekundy");
                } else {
                    sb.append(" sekund");
                }
            }

            return sb.toString();
        }
    }
}




