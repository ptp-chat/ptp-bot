package me.imsean.ptpbot;

import me.imsean.ptpbot.listeners.*;
import xyz.gghost.jskype.SkypeAPI;
import xyz.gghost.jskype.event.EventManager;

public class PTPBot {

    // TODO: Have these passed via Dependency Injection.
    private static final String OWNER = "master_zombiecow";
    private static SkypeAPI skype;

    private boolean running;

    public static String getOwner() {
        return OWNER;
    }

    public static SkypeAPI getSkype() {
        return skype;
    }

    public void login(String username, String password) {
        if (this.running) {
            stop();
        }

        try {
            skype = new SkypeAPI(username, password);
            skype.login();

            System.out.println("Successfully logged in!");
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred whilst logging in!", e);
        }
    }

    public void start() {
        this.running = true;
        registerListeners();

        System.out.println("PTPBot has started!");
    }

    public void stop() {
        this.running = false;
        skype.stop();
        skype = null;
    }

    private void registerListeners() {
        if (skype == null) {
            return;
        }

        final EventManager em = skype.getEventManager();
        em.registerListener(new ContactRequestListener());
        em.registerListener(new CommandListener());
        em.registerListener(new ChatListener());
        em.registerListener(new BannedUserJoinListener());
        em.registerListener(new StatsMessageCountListener());
        em.registerListener(new UnpermittedTopicChangeListener());
        em.registerListener(new UnpermittedTopicPictureChangeListener());
    }

}
