package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.api.mysql.UserManager;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserJoinEvent;

public class BannedUserJoinListener implements EventListener {

    private final UserManager userManager;

    public BannedUserJoinListener(UserManager userManager) {
        this.userManager = userManager;
    }

    public void onBannedUserJoin(UserJoinEvent e) {
        if (e.getGroup().isAdmin()) {
            if (this.userManager.isBanned(e.getGroup(), e.getUser().getUsername())) {
                e.getGroup().kick(e.getUser().getUsername());
            }
        }
    }

}
