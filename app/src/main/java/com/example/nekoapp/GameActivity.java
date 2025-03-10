package com.example.nekoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private static final long TOTAL_TIME = 15000;
    private static final long INTERVAL_TIME = 1000;

    private int[] heartResIds = { R.id.action_heart1, R.id.action_heart2, R.id.action_heart3 };
    private List<ImageView> heartImageList = new ArrayList<>();

    private CountDownTimer timer;
    private ImageView catImage;
    private TextView timerText;
    private int count = 0;

    public static Intent newIntent(Context context) {
        return new Intent(context, GameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        catImage = findViewById(R.id.cat_image);
        timerText = findViewById(R.id.timer_text);
        for (int resId : heartResIds) {
            ImageView heartImage = findViewById(resId);
            heartImageList.add(heartImage);
        }
        catImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                count++;
                int index = (count - 1) % 3;
                showHeart(index);
                 }
        });

        initTimer();
        timer.start();
    }

    private void showHeart(int index) {
        for (int i = 0; i < heartImageList.size(); i++) {
            if (i == index) {
                heartImageList.get(i).setVisibility(View.VISIBLE);
            } else {
                heartImageList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
        timer = null;
    }

    private void initTimer() {
        timer = new CountDownTimer(TOTAL_TIME, INTERVAL_TIME) {
            @Override
            public void onTick(long millisUntilFinished) {
                long second = millisUntilFinished / 1000;
                timerText.setText(String.valueOf(second));
            }

            @Override
            public void onFinish() {
                Intent intent = ClearActivity.newIntent(GameActivity.this, count);
                startActivity(intent);
            }
        };
    }
}
