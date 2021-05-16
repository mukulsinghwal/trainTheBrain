package com.mukul.trainyourbrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sum;
    int loc;
    TextView resultTextView;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timer;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    ConstraintLayout tryAgain;
    CountDownTimer cdt;


    public void start (View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.scoreTextView));
    }

    public void playAgain(View view) {
        gameLayout.setVisibility(View.VISIBLE);
        tryAgain.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timer.setText("10s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        resultTextView.setText("");
        newQuestion();
    }

    public void newQuestion()
    {
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sum.setText(Integer.toString(a)+" + "+Integer.toString(b));
        loc=rand.nextInt(4);
        answers.clear();
        for(int i=0; i<4; i++)
        {
            if(i==loc){
                answers.add(a+b);
            }
            else{
                int wrongNumber=rand.nextInt(41);
                while(wrongNumber==a+b)
                {
                    wrongNumber=rand.nextInt(41);
                }
                answers.add(wrongNumber);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
        cdt=new CountDownTimer(10100, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l / 1000 + "s"));
            }

            @Override
            public void onFinish() {
                tryAgain.setVisibility(View.VISIBLE);
                gameLayout.setVisibility(View.INVISIBLE);

            }
        }.start();
    }

    public void chooseAnswer(View view){
        cdt.cancel();
        if (Integer.toString(loc).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong :/");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton= findViewById(R.id.goButton);
        sum=findViewById(R.id.sum);
        button0 =findViewById(R.id.button0);
        button1 =findViewById(R.id.button1);
        button2 =findViewById(R.id.button2);
        button3 =findViewById(R.id.button3);
        scoreTextView=findViewById(R.id.scoreTextView);
        resultTextView=findViewById(R.id.resultTextView);
        timer=findViewById(R.id.timer);
        gameLayout=findViewById(R.id.gameLayout);
        tryAgain=findViewById(R.id.tryAgain);
        playAgainButton=findViewById(R.id.playAgainButton);

        gameLayout.setVisibility(View.INVISIBLE);
        tryAgain.setVisibility(View.INVISIBLE);
    }
}