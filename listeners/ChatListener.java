package me.mati.skyblock.listeners;

import me.mati.skyblock.Commands.admin.ChatLock;
import me.mati.skyblock.Config;
import me.mati.skyblock.Managers.MainManagers.ChatManager;
import me.mati.skyblock.Managers.MainManagers.MuteManager;
import me.mati.skyblock.base.Mute;
import me.mati.skyblock.utils.TimeUtil;
import me.mati.skyblock.utils.Utill;
import net.dzikoysk.funnyguilds.basic.user.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.Time;
import java.util.regex.Pattern;

public class ChatListener implements Listener {
    public static Pattern IPPATTERN = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    public static Pattern BANNED_WORDS = Pattern.compile(".*(skkf|csrv|hostmc|fhmc|mineserv|crafthost|csrv|easyhc|easyhard|arivi|arivia|easyhc-arivi|mckox|lajthc|essentials|easyhc|tabcraft|hiplay|craftcore|cubix|tryhc|skyen|minelist|muma|mchc|xcrafters|ssij|xkleszcz|craftstory|face2face|f2f|gale|lagi|chuj|chuja|chujek|chuju|chujem|chujnia|chujowy|chujowa|chujowe|cipa|cipe|cipie|dojebac|dojebie|dojebal|dojebala|dojebalem|dojebalam|dojebie|dopieprzac|dopierdalac|dopierdala|dopierdalal|dopierdalala|dopierdoli|dopierdolil|dopierdole|dopierdoli|dopierdalajacy|dopierdolic|dupa|dupie|dupcia|dupeczka|dupy|dupe|huj|hujek|hujnia|huja|huje|hujem|huju|jebac|jebal|jebie|jebia|jebak|jebaka|jebal|jebany|jebane|jebanka|jebanko|jebankiem|jebanymi|jebana|jebanym|jebanej|jebana|jebani|jebanych|jebanymi|jebcie|jebiacy|jebiaca|jebiacego|jebiacej|jebia|jebie|jebliwy|jebnac|jebnal|jebna|jebnela|jebnie|jebnij|jebut|koorwa|korwa|kurestwo|kurew|kurewski|kurewska|kurewskiej|kurewska|kurewsko|kurewstwo|kurwa|kurwaa|kurwami|kurwe|kurwie|kurwiska|kurwo|kurwy|kurwach|kurwami|kurewski|kurwiarz|kurwiÄ…cy|kurwica|kurwic|kurwidoĹ‚ek|kurwik|kurwiki|kurwiszcze|kurwiszon|kurwiszona|kurwiszonem|kurwiszony|kutas|kutasa|kutasie|kutasem|kutasy|kutasow|kutasach|kutasami|matkojebca|matkojebcy|matkojebca|matkojebcami|matkojebcach|najebac|najebal|najebane|najebany|najebana|najebie|najebia|naopierdalac|naopierdalal|naopierdalala|napierdalac|napierdalajacy|napierdolic|nawpierdalac|nawpierdalal|nawpierdalala|obsrywac|obsrywajacy|odpieprzac|odpieprzy|odpieprzyl|odpieprzyla|odpierdalac|odpierdol|odpierdolil|odpierdolila|odpierdoli|odpierdalajacy|odpierdalajaca|odpierdolic|odpierdoli|opieprzajÄ…cy|opierdalac|opierdala|opierdalajacy|opierdol|opierdolic|opierdoli|opierdola|piczka|pieprzniety|pieprzony|pierdel|pierdlu|pierdola|pierdolacy|pierdolaca|pierdol|pierdole|pierdolenie|pierdoleniem|pierdoleniu|pierdolec|pierdola|pierdolicie|pierdolic|pierdolil|pierdolila|pierdoli|pierdolniety|pierdolisz|pierdolnac|pierdolnal|pierdolnela|pierdolnie|pierdolnij|pierdolnik|pierdolona|pierdolone|pierdolony|pierdzÄ…cy|pierdziec|pizda|pizde|pizdzie|pizdnac|pizdu|podpierdalac|podpierdala|podpierdalajacypodpierdolic|podpierdoli|pojeb|pojeba|pojebami|pojebani|pojebanego|pojebanemu|pojebani|pojebany|pojebanych|pojebanym|pojebanymi|pojebem|pojebac|pojebalo|popierdala|popierdalac|popierdolic|popierdoli|popierdolonego|popierdolonemu|popierdolonym|popierdolone|popierdoleni|popierdolony|porozpierdala|porozpierdalac|poruchac|przejebane|przejebac|przyjebali|przepierdalac|przepierdala|przepierdalajacy|przepierdalajaca|przepierdolic|przyjebac|przyjebie|przyjebala|przyjebal|przypieprzac|przypieprzajacy|przypieprzajaca|przypierdalac|przypierdala|przypierdoli|przypierdalajacy|przypierdolic|qrwa|rozjebac|rozjebie|rozjebaĹ‚a|rozpierdalac|rozpierdala|rozpierdolic|rozpierdole|rozpierdoli|rozpierducha|skurwiel|skurwiela|skurwielem|skurwielu|skurwysyn|skurwysynow|skurwysyna|skurwysynem|skurwysynu|skurwysyny|skurwysynski|skurwysynstwo|spieprzac|spieprza|spieprzaj|spieprzajcie|spieprzaja|spieprzajacy|spieprzajaca|spierdalac|spierdala|spierdalal|spierdalalcie|spierdalala|spierdalajacy|spierdolic|spierdoli|spierdolÄ…|spierdola|srac|srajacy|srajac|sraj|sukinsyn|sukinsyny|sukinsynom|sukinsynowi|sukinsynow|ujebac|ujebal|ujebana|ujebany|ujebie|ujebaĹ‚a|ujebala|upierdalac|upierdala|upierdoli|upierdolic|upierdoli|upierdola|upierdoleni|wjebac|wjebie|wjebia|wjebiemy|wjebiecie|wkurwiac|wkurwi|wkurwia|wkurwial|wkurwiajacy|wkurwiajaca|wkurwic|wkurwi|wkurwiacie|wkurwiali|wkurwia|wkurwimy|wkurwicie|wkurwiacie|wkurwic|wkurwia|wpierdalac|wpierdalajacy|wpierdol|wpierdolic|wpizdu|wyjebac|wyjebali|wyjebac|wyjebie|wyjebia|wyjebiesz|wyjebie|wyjebiecie|wyjebiemy|wypieprzac|wypieprza|wypieprzal|wypieprzala|wypieprzy|wypieprzyla|wypieprzyl|wypierdal|wypierdalac|wypierdala|wypierdalaj|wypierdalal|wypierdalala|wypierdolic|wypierdoli|wypierdolimy|wypierdolicie|wypierdola|wypierdolili|wypierdolil|wypierdolila|zajebac|zajebie|zajebia|zajebial|zajebiala|zajebali|zajebana|zajebani|zajebane|zajebany|zajebanych|zajebanym|zajebanymi|zajebiste|zajebisty|zajebistych|zajebista|zajebistym|zajebistymi|zajebiscie|zapieprzyc|zapieprzy|zapieprzyl|zapieprzyla|zapieprza|zapieprz|zapieprzymy|zapieprzycie|zapieprzysz|zapierdala|zapierdalac|zapierdalaja|zapierdalaj|zapierdalajcie|zapierdalala|zapierdalali|zapierdalajacy|zapierdolic|zapierdoli|zapierdolil|zapierdolila|zapierdola|zapierniczac|zapierniczajacy|zasrac|zasranym|zasrywajacy|zesrywac|zesrywajac|zjebac|zjebal|zjebala|zjebana|zjebia|zjebali|zjeby+).*");

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        Mute mute = MuteManager.getMute(e.getPlayer().getUniqueId());
        if (!p.hasPermission("sky.User.bypass") && IPPATTERN.matcher(e.getMessage()).find() || (!p.hasPermission("sky.User.bypass") && BANNED_WORDS.matcher(e.getMessage().toLowerCase()).find())) {
            Utill.sendMsg(p, "&4Blad: &cTwoja wiadomosc zawiera niedozwolone tresci!");
            e.setCancelled(true);
            return;
        }
        if (ChatLock.isChat() == false && !p.hasPermission("sky.User.bypass")) {
            e.setCancelled(true);
            Utill.sendMsg(p, Config.CHAT_OFF_MESSAGE);
            return;
        }
        e.setCancelled(true);
        if (mute != null) {
            if (mute.isAlive()) {
                MuteManager.deleteMute(mute);
                Utill.sendMsg(p, "&7Twoja wiadomosc zostala anulowana, napisz ja jeszcze raz.");
                e.setCancelled(true);
                return;
            }

            Utill.sendMsg(p, "&7Zostales zmutowany!");
            Utill.sendMsg(p, "&7Przez: &3" + mute.getAdmin() + "&7, dnia: &3" + Utill.getDate(mute.getCreateTime()) + "&7!");
            Utill.sendMsg(p, "&7Powod: &3" + mute.getReason() + "&7.");
            if (mute.getExpireTime() > 0L) {
                Utill.sendMsg(p, "&7Wygasa: &3" + Utill.getDate(mute.getExpireTime()) + "&8 (&e" + Utill.getDurationBreakdown((int) (mute.getExpireTime() - System.currentTimeMillis())) + "&8)!");
            }
            e.setCancelled(true);
            return;
        }
        if (!ChatManager.canSendMessage(p) && !p.hasPermission("sky.chat.nolimit")) {
            int time = (int) ((System.currentTimeMillis() - ChatManager.getTimes().get(p.getUniqueId())) / 1000L);
            int elapsed = Config.CHAT_SLOWMODE - time;

            Utill.sendMsg(p, "&7Na czacie bedziesz mogl napisac dopiero za &3" + Utill.secondsToString(elapsed) + "&7!");
            e.setCancelled(true);
            return;
        }
        for(Player online : Bukkit.getOnlinePlayers()) {
            ComponentBuilder cb = new ComponentBuilder("");
            User user = User.get(online);

            //Ranking
            if(!p.hasPermission("stephc.chat.helper")) {
                HoverEvent hoverRank = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczyc ranking gracza &6" + p.getName())).create());
                ClickEvent clickRank = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ranking " + p.getName());
                cb.append(user.getRank().getPoints() + " ").color(ChatColor.GRAY).event(hoverRank).event(clickRank);
            }
            //Gildia
            if(user.hasGuild() && !p.hasPermission("stephc.chat.helper")) {
                HoverEvent hoverGuild = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczyc gildie gracza &6" + p.getName())).create());
                ClickEvent clickGuild = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/info " + user.getGuild().getTag());
                cb.append(user.getGuild().getTag() + " ").color(ChatColor.RED).event(hoverGuild).event(clickGuild);
            }
            //VIP
            if(p.hasPermission("stephc.chat.vip")){
                HoverEvent hoverVip = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby zobaczy informacje o randze vip")).create());
                ClickEvent clickVip = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/vip");
                cb.append(Utill.fixColor("&6VIP ")).event(hoverVip).event(clickVip);
            }
            //NICK
            HoverEvent hoverName = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby skopiowac wiadomosc")).create());
            ClickEvent clickName = new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + p.getName() + " ");
            cb.append(p.getDisplayName()).color(ChatColor.WHITE).event(hoverName).event(clickName);
            //STRZALKA
            HoverEvent hoverStrzalka = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Kliknij aby wejsc na strone serwera")).create());
            ClickEvent clickStrzalka = new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.stephc.pl/");
            cb.append(" » ").color(ChatColor.YELLOW).bold(true).event(hoverStrzalka).event(clickStrzalka);
            //MESSAGE
            HoverEvent hoverMess = new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Utill.fixColor("&7Wyslane: &6" + TimeUtil.getNowTime() + "&7. Kliknij aby skopiowac!")).create());
            cb.append(p.hasPermission("stephc.chat.color") ? Utill.fixColor(e.getMessage()) : e.getMessage()).color(ChatColor.GRAY).bold(false).event(hoverMess);
            //SEND
            BaseComponent[] bc = cb.create();
            online.spigot().sendMessage(bc);
            ChatManager.getTimes().put(p.getUniqueId(), System.currentTimeMillis());
        }
    }

}



