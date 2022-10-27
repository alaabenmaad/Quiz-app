package com.example.quizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView Question,Score,QuestionNo;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3,rb4;
    private Button bNext;

    int totalQuest;
    int Compteur=0;
    int score;

    //ColorStateList Colors;
    boolean answered;
    private QuestionModel currentQuestion;
private List<QuestionModel> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionList=new ArrayList<>();
        Question=findViewById(R.id.txtQuestion);
        Score=findViewById(R.id.textScore);
        QuestionNo=findViewById(R.id.txtQuestNum);

        radioGroup= findViewById(R.id.radioGroup);

        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rb3=findViewById(R.id.rb3);
        rb4=findViewById(R.id.rb4);

        bNext=findViewById(R.id.bNext);




        addQuestions();
        totalQuest=questionList.size();
        showNextQuestion();
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered==false){
                    if(rb1.isChecked()||rb2.isChecked()||rb3.isChecked()||rb4.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(QuizActivity.this, "choisr une reponse !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    showNextQuestion();
                }
            }
        });


    }

    private void checkAnswer() {
        answered=true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo =radioGroup.indexOfChild(rbSelected)+1;
        if(answerNo==currentQuestion.getCorrectAnsNo()){
            score++;
            Score.setText("Score: "+score);

        }if(Compteur < totalQuest){
            bNext.setText("Suivant");
        }else{
            bNext.setText("Rejouer");
        }
    }

    private void showNextQuestion() {

        if(Compteur < totalQuest){
            currentQuestion=questionList.get(Compteur);
            Question.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());

            Compteur++;
            bNext.setText("OK");
            QuestionNo.setText("Question: "+Compteur+"/"+totalQuest);
            answered=false;

        }else{

            finish();
        }

    }

    private void addQuestions() {
        questionList.add(new QuestionModel("Quel est le plus grand animal sur Terre?","Le lion","La girafe","l'éléphant","Le chameau",2));
        questionList.add(new QuestionModel("Quel est le nombre d’yeux d’une abeille?"," Cinq yeux","Deux yeux","Sept yeux","trois yeux ",1));
        questionList.add(new QuestionModel("Quel est l'animal qui a le plus de mémoire ?","L'éléphant","Le chameau","La chevre","Le chien",1));
        questionList.add(new QuestionModel("Comment les papillons portent-ils leur trompe au repos ?","Tendue","Coudée","Ouverte","Enroulée",4));
        questionList.add(new QuestionModel("À quel animal les îles Canaries doivent-elles leur nom ?","À la caille","Au canari","Au chien","Au canard",3));
    }
}