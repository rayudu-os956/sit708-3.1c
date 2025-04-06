package com.example.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView welcomeText, questionText;
    private RadioGroup optionsGroup;
    private Button nextBtn;
    private ProgressBar progressBar;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String userName = "";
    private boolean answered = false;
    private int totalQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        welcomeText = findViewById(R.id.welcomeText);
        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextBtn = findViewById(R.id.nextBtn);
        progressBar = findViewById(R.id.progressBar);

        userName = getIntent().getStringExtra("userName");
        welcomeText.setText("Welcome, " + userName + "!");

        questionList = generateQuestions();
        totalQuestions = questionList.size();
        progressBar.setMax(totalQuestions);

        showQuestion();

        nextBtn.setOnClickListener(v -> {
            if (!answered) {
                int selectedId = optionsGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    answered = true;
                    checkAnswer(selectedId);
                    nextBtn.setText(currentQuestionIndex == totalQuestions - 1 ? "Submit" : "Next");
                } else {
                    Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
                }
            } else {
                currentQuestionIndex++;
                if (currentQuestionIndex < totalQuestions) {
                    showQuestion();
                    answered = false;
                    nextBtn.setText("Submit");
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("score", score);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void showQuestion() {
        Question q = questionList.get(currentQuestionIndex);
        questionText.setText(q.getQuestionText());
        optionsGroup.removeAllViews();
        progressBar.setProgress(currentQuestionIndex);

        for (String option : q.getOptions()) {
            RadioButton rb = new RadioButton(this);
            rb.setText(option);
            rb.setTextSize(16f);
            rb.setPadding(8, 8, 8, 8);
            rb.setTextColor(Color.BLACK);
            optionsGroup.addView(rb);
        }
    }

    private void checkAnswer(int selectedId) {
        int correctIndex = questionList.get(currentQuestionIndex).getCorrectAnswerIndex();
        RadioButton correctRadioButton = (RadioButton) optionsGroup.getChildAt(correctIndex);

        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) optionsGroup.getChildAt(i);
            rb.setEnabled(false);
            if (i == correctIndex) {
                rb.setTextColor(Color.GREEN);
            } else if (rb.getId() == selectedId) {
                rb.setTextColor(Color.RED);
            }
        }

        RadioButton selectedBtn = findViewById(selectedId);
        int selectedIndex = optionsGroup.indexOfChild(selectedBtn);
        if (selectedIndex == correctIndex) {
            score++;
        }
    }

    private List<Question> generateQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(new Question("Android is based on which language?", new String[]{"Java", "Python", "C++"}, 0));
        list.add(new Question("Which layout is best for complex UI?", new String[]{"Linear", "Constraint", "Frame"}, 1));
        list.add(new Question("What is an Activity?", new String[]{"App component", "A button", "A widget"}, 0));
        return list;
    }
}
