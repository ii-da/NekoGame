package com.example.nekoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ClearActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "key_count";
    private ImageView clearImageView;
    private TextView clearCommentTextView;
    private TextView levelCommentTextView;
    private ImageView backBtn;

    public static Intent newIntent(Context context, int count) {
        Intent intent = new Intent(context, ClearActivity.class);
        intent.putExtra(KEY_COUNT, count);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        clearImageView = findViewById(R.id.clear_view);
        clearCommentTextView = findViewById(R.id.clear_comment);
        levelCommentTextView = findViewById(R.id.level_comment);
        backBtn = findViewById(R.id.back_button);

        int count = getIntent().getIntExtra(KEY_COUNT, -1);

        if (70 <= count) {
            // 70以上の時　いちばんいいやつ
            clearImageView.setImageResource(R.drawable.love);
            clearCommentTextView.setText("しあわせ♡♡♡");
            levelCommentTextView.setText("　  猫なでレベル★★★★");
        } else if (50 <= count) {
            // 50以上　ふつう
            clearImageView.setImageResource(R.drawable.standup);
            clearCommentTextView.setText("なでるの上手だね");
            levelCommentTextView.setText("　  猫なでレベル★★★☆");
        } else if (30 <= count) {
            // 30以上　ちょっと悪い
            clearImageView.setImageResource(R.drawable.daze);
            clearCommentTextView.setText("・・・ありがとう");
            levelCommentTextView.setText("　  猫なでレベル★★☆☆");
        } else {
            // 最悪
            clearImageView.setImageResource(R.drawable.angry);
            clearCommentTextView.setText("そうじゃない！！！");
            levelCommentTextView.setText("　  猫なでレベル★☆☆☆");
        }
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = MainActivity.newIntent(ClearActivity.this);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}