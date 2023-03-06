package com.example.alerts;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";


    @Override
    public void onCreate(){
        super.onCreate();

        // Set up channels
        createNotificationChannels();
    }

    private void createNotificationChannels(){
        // Check if running Android Oreo (LV 26) or higher, lower API's don't support channels
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID, //the unique ID we defined in the global variables
                    "Channel 1", // the title of the channel
                    NotificationManager.IMPORTANCE_HIGH // the level of importance of the notification
            );
            // Setup the default notification settings
            // the user has ultimate control over the settings after we set them
            // aftet the app is installed, we can no longer change these settings,
            // the user has to uninstall and reinstall if we, the developer, make any changes
            //channel1.setLightColor();
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            channel1.setDescription("This is channel 2");

            // Create a refrence to the notification manager
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
