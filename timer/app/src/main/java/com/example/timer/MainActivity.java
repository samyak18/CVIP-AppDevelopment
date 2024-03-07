package com.example.timer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button startButton;
    private TextView countdownText;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        startButton = findViewById(R.id.startButton);
        countdownText = findViewById(R.id.countdownText);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountdown();
            }
        });
    }

    private void startCountdown() {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("Please enter a valid time");
            return;
        }

        long timeInMillis = Long.parseLong(editText.getText().toString()) * 1000;

        countDownTimer = new CountDownTimer(timeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished / 1000;
                countdownText.setText("Time Left: " + secondsLeft + " seconds");
            }

            @Override
            public void onFinish() {
                countdownText.setText("Countdown Finished");
            }
        };

        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
