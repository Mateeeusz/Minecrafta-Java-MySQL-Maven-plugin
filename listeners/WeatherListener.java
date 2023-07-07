package me.mati.skyblock.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(!e.toWeatherState()){
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void OnThunderChange(ThunderChangeEvent e) {
        if(!e.toThunderState()){
            return;
        }
        e.setCancelled(true);
    }


    @EventHandler
    public void RainStart(WeatherChangeEvent e) {
        e.setCancelled(true);
    }


}
