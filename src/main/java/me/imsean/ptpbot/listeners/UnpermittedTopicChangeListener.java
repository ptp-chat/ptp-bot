package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.mysql.BotUser;
import me.imsean.ptpbot.exceptions.NotAdminException;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.TopicChangedEvent;

/**
 * Created by sean on 10/12/15.
 */
public class UnpermittedTopicChangeListener implements EventListener {

    public void onTopicChange(TopicChangedEvent e) {
        if(PTPBot.getSkype().getUsername().equalsIgnoreCase(e.getUser().getUsername()) || BotUser.isBotAdmin(e.getUser())) return;
        try {
            BotUser.banFromGroup(e.getGroup(), e.getUser().getUsername());
            e.getGroup().changeTopic(e.getOldTopic());
        } catch (NotAdminException e1) {
            e1.printStackTrace();
        }
    }

}
