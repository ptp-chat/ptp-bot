package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.mysql.UserManager;
import me.imsean.ptpbot.exceptions.NotAdminException;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.TopicChangedEvent;

public class UnpermittedTopicChangeListener implements EventListener {

    private final UserManager userManager;

    public UnpermittedTopicChangeListener(UserManager userManager) {
        this.userManager = userManager;
    }

    public void onTopicChange(TopicChangedEvent e) {
        if(PTPBot.getSkype().getUsername().equalsIgnoreCase(e.getUser().getUsername()) || this.userManager.isBotAdmin(e.getUser())) return;
        try {
            this.userManager.ban(e.getGroup(), e.getUser().getUsername());
            e.getGroup().changeTopic(e.getOldTopic());
        } catch (NotAdminException e1) {
            e1.printStackTrace();
        }
    }

}
