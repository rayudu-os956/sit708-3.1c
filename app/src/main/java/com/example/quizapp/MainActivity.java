package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput;
    private Button startQuizBtn, openCalculatorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        startQuizBtn = findViewById(R.id.startQuizBtn);
        openCalculatorBtn = findViewById(R.id.openCalculatorBtn);

        // Start Quiz Button
        startQuizBtn.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            if (!name.isEmpty()) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("userName", name);
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });

        // Open Calculator Button
        openCalculatorBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });

    }
}
