package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.mysql.Stats;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

public class StatsMessageCountListener implements EventListener {

    public void onMessage(UserChatEvent e) {
        Stats.addMessages();
    }

}
