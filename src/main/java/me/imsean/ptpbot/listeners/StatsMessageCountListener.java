package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.mysql.StatsManager;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

public class StatsMessageCountListener implements EventListener {

    private final StatsManager statsManager;

    public StatsMessageCountListener(StatsManager statsManager) {
        this.statsManager = statsManager;
    }

    public void onMessage(UserChatEvent e) {
        this.statsManager.addMessages();
    }

}
