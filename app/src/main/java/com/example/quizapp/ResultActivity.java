package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultText;
    private Button newQuizBtn, finishBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultText = findViewById(R.id.resultText);
        newQuizBtn = findViewById(R.id.newQuizBtn);
        finishBtn = findViewById(R.id.finishBtn);

        int score = getIntent().getIntExtra("score", 0);
        String userName = getIntent().getStringExtra("userName");
        resultText.setText("Congratulations " + userName + "!\nYour score: " + score + "/3");

        newQuizBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        finishBtn.setOnClickListener(v -> finish());
    }
}
