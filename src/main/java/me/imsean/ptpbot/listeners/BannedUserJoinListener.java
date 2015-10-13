package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.mysql.BotUser;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserJoinEvent;

public class BannedUserJoinListener implements EventListener {

    public void onBannedUserJoin(UserJoinEvent e) {
        if(e.getGroup().isAdmin()) {
            if(BotUser.isBannedFromGroup(e.getGroup(), e.getUser().getUsername())) {
                e.getGroup().kick(e.getUser().getUsername());
            }
        }
    }

}
