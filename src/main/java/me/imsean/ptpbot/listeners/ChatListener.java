package me.imsean.ptpbot.listeners;

import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserChatEvent;

/**
 * Created by sean on 10/11/15.
 */
public class ChatListener implements EventListener {

    public void onChatEvent(UserChatEvent e) {
        System.out.println("[" + e.getChat().getTopic() + "] " + e.getUser().getDisplayName() + " (" + e.getUser().getUsername() + ") : " + e.getMsg().getMessage());
    }

}
