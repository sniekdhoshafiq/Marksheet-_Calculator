package com.sniekdho.marksheetcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edPhysics, edChemistry, edBiology, edMathematics, edComputer;

    Button buttonCalculate;

    TextView tvPercentage, tvGrade;

    TextToSpeech textToSpeech;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edPhysics = (EditText) findViewById(R.id.edPhysics);
        edChemistry = (EditText) findViewById(R.id.edChemistry);
        edBiology = (EditText) findViewById(R.id.edBiology);
        edMathematics = (EditText) findViewById(R.id.edMathematics);
        edComputer = (EditText) findViewById(R.id.edComputer);

        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);

        tvPercentage = (TextView) findViewById(R.id.tvPercentage);
        tvGrade = (TextView) findViewById(R.id.tvGrade);


        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

            }
        });



        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "NewApi"})
            @Override
            public void onClick(View v) {

                //== Take input from user in string format
                String stPhysics = edPhysics.getText().toString();
                String stChemistry = edChemistry.getText().toString();
                String stBiology = edBiology.getText().toString();
                String stMathematics = edMathematics.getText().toString();
                String stComputer = edComputer.getText().toString();
                //== Take input from user in string format

                if (stPhysics.length()!=0 && stChemistry.length()!=0 &&
                    stBiology.length()!=0 && stMathematics.length()!=0 &&
                    stComputer.length()!=0){

                    int physics = Integer.parseInt(stPhysics);
                    int chemistry = Integer.parseInt(stChemistry);
                    int biology = Integer.parseInt(stBiology);
                    int mathematics = Integer.parseInt(stMathematics);
                    int computer = Integer.parseInt(stComputer);

                    if (physics>100 || chemistry>100 || biology>100
                        || mathematics>100 || computer>100){
                        Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                    }else {
                        int totalMarks = physics+chemistry+biology+mathematics+computer;
                        int percentage = totalMarks/5;
                        tvPercentage.setText("Your Percentage = "+ percentage +"%");

                        if (percentage>=80 && percentage<=100){
                            tvGrade.setText("Your Grade is A+"+"\n Excellent Result. Keep Up The Good Studying Habit.");
                            tvGrade.setTextColor(getResources().getColor(R.color.green));
                            tvPercentage.setTextColor(getResources().getColor(R.color.green));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                    tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);

                        } else if (percentage>=70 && percentage<=79) {
                            tvGrade.setText("Your Grade is A"+"\nVery Good Result. Add 5% Effort on Study");
                            tvGrade.setTextColor(getResources().getColor(R.color.yellow));
                            tvPercentage.setTextColor(getResources().getColor(R.color.yellow));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                            tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);

                        } else if (percentage>=60 && percentage<=69) {
                            tvGrade.setText("Your Grade is B+"+"\nGood Result. Study More");
                            tvGrade.setTextColor(getResources().getColor(R.color.blue));
                            tvPercentage.setTextColor(getResources().getColor(R.color.blue));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                            tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);

                        } else if (percentage>=50 && percentage<=59) {
                            tvGrade.setText("Your Grade is B"+"\nAverage Result. Study More & More");
                            tvGrade.setTextColor(getResources().getColor(R.color.purple));
                            tvPercentage.setTextColor(getResources().getColor(R.color.purple));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                            tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);

                        } else if (percentage>=40 && percentage<=49) {
                            tvGrade.setText("Your Grade is C"+"\nBelow Average. Study More, More & More");
                            tvGrade.setTextColor(getResources().getColor(R.color.orange));
                            tvPercentage.setTextColor(getResources().getColor(R.color.orange));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                            tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);

                        }else {
                            tvGrade.setText("Your Grade is F"+"\nYou Are Fail. Better Luck Next Time");
                            tvGrade.setTextColor(getResources().getColor(R.color.dark_red));
                            tvPercentage.setTextColor(getResources().getColor(R.color.dark_red));
                            textToSpeech.speak(tvPercentage.getText().toString()+"\n"+
                                            tvGrade.getText().toString(),
                                    TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }


                }else {
                    Toast.makeText(MainActivity.this, "Please Input All Fields", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme)
                .setIcon(R.drawable.question)
                .setTitle("EXIT!!")
                .setMessage("Are You Really Want To Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }
}