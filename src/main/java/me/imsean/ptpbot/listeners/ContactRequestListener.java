package me.imsean.ptpbot.listeners;

import me.imsean.ptpbot.PTPBot;
import xyz.gghost.jskype.event.EventListener;
import xyz.gghost.jskype.events.UserPendingContactRequestEvent;

public class ContactRequestListener implements EventListener {

    public void onContactRequest(UserPendingContactRequestEvent e) {
        System.out.println("New Contact: " + e.getUser());
        e.accept(PTPBot.getSkype());
    }
}
