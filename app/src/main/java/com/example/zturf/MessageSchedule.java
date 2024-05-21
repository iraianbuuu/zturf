package com.example.zturf;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageSchedule extends AppCompatActivity {
    private EditText editTextPhone1, editTextPhone2, editTextPhone3, editTextMessage;
    private Button buttonSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_schedule);

        editTextPhone1 = findViewById(R.id.editTextPhone1);
        editTextPhone2 = findViewById(R.id.editTextPhone2);
        editTextPhone3 = findViewById(R.id.editTextPhone3);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone1 = editTextPhone1.getText().toString();
                String phone2 = editTextPhone2.getText().toString();
                String phone3 = editTextPhone3.getText().toString();
                String message = editTextMessage.getText().toString();

                // Send message after 30 seconds
                sendDelayedSMS(new String[]{phone1, phone2, phone3}, message, 30);
            }
        });
    }

    private void sendDelayedSMS(String[] phoneNumbers, String message, int delaySeconds) {
        // Convert delaySeconds to milliseconds
        long delayMillis = delaySeconds * 1000;

        // Create PendingIntent for the BroadcastReceiver to handle the sending of SMS
        Intent intent = new Intent(getApplicationContext(), SmsBroadcastReceiver.class);
        intent.putExtra("phoneNumbers", phoneNumbers);
        intent.putExtra("message", message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Schedule the SMS sending
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    // Get SmsManager instance
                    SmsManager smsManager = SmsManager.getDefault();

                    // Send SMS to each phone number
                    for (String phoneNumber : phoneNumbers) {
                        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
                    }

                    Toast.makeText(getApplicationContext(), "Messages sent successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Failed to send messages: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, delayMillis);
    }

}