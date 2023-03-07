package com.example.alerts;

import static com.example.alerts.App.CHANNEL_1_ID;
import static com.example.alerts.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;

    // Edit text to allow user to enter the text for the title and message
    // to be used in the message we build
    private EditText editTextTitle;
    private EditText editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get a refrence to the notification manager so we can send our notifications
        notificationManager = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }

    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        //CHANNEL_1_ID is imported from our App class
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_1)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { android.Manifest.permission.POST_NOTIFICATIONS }, 0);
            return;
        }
        notificationManager.notify(1, notification);


    }


    public void sendOnChannel2(View v){
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

        //CHANNEL_1_ID is imported from our App class
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_2)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { android.Manifest.permission.POST_NOTIFICATIONS }, 0);
            return;
        }
        notificationManager.notify(2, notification);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (grantResults.length == 0) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}