package me.imsean.ptpbot;

import me.imsean.ptpbot.api.mysql.Connection;
import me.imsean.ptpbot.api.mysql.StatsManager;
import me.imsean.ptpbot.api.mysql.UserManager;
import me.imsean.ptpbot.listeners.*;
import xyz.gghost.jskype.SkypeAPI;
import xyz.gghost.jskype.event.EventManager;

public class PTPBot {

    // TODO: Have these passed via Dependency Injection.
    private static final String OWNER = "master_zombiecow";
    private static SkypeAPI skype;
    private static Connection connection;
    private static UserManager userManager;
    private static StatsManager statsManager;

    private boolean running;

    public static String getOwner() {
        return OWNER;
    }

    public static SkypeAPI getSkype() {
        return skype;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static UserManager getUserManager() {
        return userManager;
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

        // TODO: Make this configurable! (2 much hard-coding m9)
        connection   = new Connection("root", "root");
        userManager  = new UserManager(skype, connection);
        statsManager = new StatsManager();
        registerListeners();

        System.out.println("PTPBot has started!");
    }

    public void stop() {
        this.running = false;
        skype.stop();
        skype = null;
        connection   = null;
        userManager  = null;
        statsManager = null;
    }

    private void registerListeners() {
        if (skype == null) {
            return;
        }

        final EventManager em = skype.getEventManager();
        em.registerListener(new ContactRequestListener());
        em.registerListener(new CommandListener(userManager, statsManager));
        em.registerListener(new ChatListener());
        em.registerListener(new BannedUserJoinListener(userManager));
        em.registerListener(new StatsMessageCountListener(statsManager));
        em.registerListener(new UnpermittedTopicChangeListener(userManager));
        em.registerListener(new UnpermittedTopicPictureChangeListener(userManager));
    }

}
