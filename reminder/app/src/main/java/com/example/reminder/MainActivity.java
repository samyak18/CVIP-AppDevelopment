package com.example.reminder;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    private EditText medicineNameEditText;
    private TimePicker reminderTimePicker;
    private Button addMedicineButton;
    private TextView addedMedicinesTextView;

    private List<String> addedMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addedMedicines = new ArrayList<>();

        medicineNameEditText = findViewById(R.id.medicineNameEditText);
        reminderTimePicker = findViewById(R.id.reminderTimePicker);
        addMedicineButton = findViewById(R.id.addMedicineButton);
        addedMedicinesTextView = findViewById(R.id.addedMedicinesTextView);

        addMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicineName = medicineNameEditText.getText().toString();
                int hour = reminderTimePicker.getCurrentHour();
                int minute = reminderTimePicker.getCurrentMinute();

                scheduleReminder(hour, minute, medicineName);
                addedMedicines.add(medicineName);
                updateMedicineList();

                Toast.makeText(MainActivity.this, "Reminder added for " + medicineName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void scheduleReminder(int hour, int minute, String medicineName) {
        Intent intent = new Intent(MainActivity.this, ReminderReceiver.class);
        intent.putExtra("medicineName", medicineName);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void updateMedicineList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String medicine : addedMedicines) {
            stringBuilder.append(medicine).append("\n");
        }
        addedMedicinesTextView.setText(stringBuilder.toString());
    }
}
