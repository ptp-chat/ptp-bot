package me.imsean.ptpbot;

import me.imsean.ptpbot.listeners.*;
import xyz.gghost.jskype.SkypeAPI;
import xyz.gghost.jskype.exception.SkypeException;

import java.util.Scanner;

/**
 * Created by sean on 10/11/15.
 */
public class PTPBot {

    static SkypeAPI skype;
    static boolean isRunning = true;
    static String OWNER = "master_zombiecow";

    private String username, password;


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Credentials (format: user:pass)");
        String[] creds = in.nextLine().split(":");
        skype = new SkypeAPI(creds[0], creds[1]);
        skype.getEventManager().registerListener(new CaptchaListener());
        try {
            skype.login();
            System.out.println("PTPBot running!");
        } catch (SkypeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        skype.getEventManager().registerListener(new ContactRequestListener());
        skype.getEventManager().registerListener(new CommandListener());
        skype.getEventManager().registerListener(new ChatListener());
        skype.getEventManager().registerListener(new BannedUserJoinListener());
        skype.getEventManager().registerListener(new StatsMessageCountListener());
        skype.getEventManager().registerListener(new UnpermittedTopicChangeListener());
        skype.getEventManager().registerListener(new UnpermittedTopicPictureChangeListener());

        while(isRunning) {}
        skype.stop();
    }

    public static SkypeAPI getSkype() {
        return skype;
    }
    public static String getOwner() { return OWNER; }

}
