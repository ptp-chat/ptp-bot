package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.PTPBot;
import me.imsean.ptpbot.api.mysql.UserManager;
import me.imsean.ptpbot.exceptions.NotAdminException;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.ChatPictureChangedEvent;

public class UnpermittedTopicPictureChangeListener implements EventListener {

    private final UserManager userManager;

    public UnpermittedTopicPictureChangeListener(UserManager userManager) {
        this.userManager = userManager;
    }

    public void onUnpermittedTopicPictureChangeEvent(ChatPictureChangedEvent e) {
        if (PTPBot.getSkype().getUsername().equalsIgnoreCase(e.getUser().getUsername())
                || this.userManager.isBotAdmin(e.getUser())) return;
        try {
            this.userManager.ban(e.getGroup(), e.getUser().getUsername());
        } catch (NotAdminException e1) {
            e1.printStackTrace();
        }
    }

}
