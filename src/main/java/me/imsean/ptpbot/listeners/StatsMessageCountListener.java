package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.mysql.Stats;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

/**
 * Created by sean on 10/12/15.
 */
public class StatsMessageCountListener implements EventListener {

    public void onMessage(UserChatEvent e) {
        Stats.addMessages();
    }

}
