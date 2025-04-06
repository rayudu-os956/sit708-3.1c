package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    EditText input1, input2;
    Button addBtn, subBtn, backToMainBtn;
    TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        addBtn = findViewById(R.id.addBtn);
        subBtn = findViewById(R.id.subBtn);
        resultText = findViewById(R.id.resultText);
        backToMainBtn = findViewById(R.id.backToMainBtn);

        addBtn.setOnClickListener(v -> calculate('+'));
        subBtn.setOnClickListener(v -> calculate('-'));

        // Button to go back to MainActivity
        backToMainBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Optionally finish this activity
        });
    }

    private void calculate(char op) {
        String val1 = input1.getText().toString();
        String val2 = input2.getText().toString();

        if (val1.isEmpty() || val2.isEmpty()) {
            resultText.setText("Please enter both numbers");
            return;
        }

        int num1 = Integer.parseInt(val1);
        int num2 = Integer.parseInt(val2);
        int result = (op == '+') ? num1 + num2 : num1 - num2;

        resultText.setText("Result: " + result);
    }
}
