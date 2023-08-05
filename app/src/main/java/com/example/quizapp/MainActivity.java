package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuesTV;
    TextView quesTV;
    Button aA,aB,aC,aD;
    Button submitBtn;
    int score=0;
    int totalQues=QuesAns.question.length;
    int currentQues=0;
    String selectedAns="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuesTV=findViewById(R.id.totalQues);
        quesTV=findViewById(R.id.ques);
        aA=findViewById(R.id.ansA);
        aB=findViewById(R.id.ansB);
        aC=findViewById(R.id.ansC);
        aD=findViewById(R.id.ansD);
        submitBtn=findViewById(R.id.submit);

        aA.setOnClickListener(this);
        aB.setOnClickListener(this);
        aC.setOnClickListener(this);
        aD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuesTV.setText("Total Questions: "+totalQues);

        loadNewQues();
    }

    @Override
    public void onClick(View view) {
        aA.setBackgroundColor(Color.WHITE);
        aB.setBackgroundColor(Color.WHITE);
        aC.setBackgroundColor(Color.WHITE);
        aD.setBackgroundColor(Color.WHITE);

        Button clickedBtn=(Button) view;
        if(clickedBtn.getId()==R.id.submit){
            if(selectedAns.equals(QuesAns.correctAns[currentQues])){
                score++;
            }
            currentQues++;
            loadNewQues();
        }else{
            selectedAns=clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(Color.MAGENTA);
        }

    }

//    void loadNewQues(){
//        if(currentQues==totalQues){
//            finishQuiz();
//        }
//        quesTV.setText(QuesAns.question[currentQues]);
//        aA.setText(QuesAns.choices[currentQues][0]);
//        aB.setText(QuesAns.choices[currentQues][1]);
//        aC.setText(QuesAns.choices[currentQues][2]);
//        aD.setText(QuesAns.choices[currentQues][3]);
//
//    }

    void loadNewQues() {
        if (currentQues < totalQues) {
            quesTV.setText(QuesAns.question[currentQues]);
            aA.setText(QuesAns.choices[currentQues][0]);
            aB.setText(QuesAns.choices[currentQues][1]);
            aC.setText(QuesAns.choices[currentQues][2]);
            aD.setText(QuesAns.choices[currentQues][3]);
        } else {
            finishQuiz();
        }
    }

    void finishQuiz(){
        String passStatus="";
        if(score>totalQues*0.60){
            passStatus="Passed";
        }else{
            passStatus="Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+"out of "+totalQues)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score=0;
        currentQues=0;
        loadNewQues();
    }
}