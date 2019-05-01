package com.exomatik.irfanrz.diagnosakanker;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;

import java.util.ArrayList;

public class Questioner extends AppCompatActivity {
    private BubbleSeekBar seekBar;
    private Button btnNext, btnCek;
    private RadioGroup radioGroup;
    private TextView textQuestion;
    private ArrayList<String> hasilAnswer = new ArrayList<String>();
    private int positionSeekBar = 1;
    private ImageView back;
    int cek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questioner);

        seekBar = (BubbleSeekBar) findViewById(R.id.seek_bar);
        btnNext = (Button) findViewById(R.id.btnDiagnosa);
        btnCek = (Button) findViewById(R.id.btnCek);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        textQuestion = (TextView) findViewById(R.id.textQuestion);
        back = (ImageView) findViewById(R.id.back);

        for (int a = 0; a <= 10; a++) {
            hasilAnswer.add("-");
        }
        configSeekBar();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Questioner.this, UserInput.class);
                startActivity(intent);
                finish();
            }
        });

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cek = 0;
                if (seekBar.getProgress() == 10) {
                    int check = radioGroup.getCheckedRadioButtonId();

                    if (check == -1) {
                        Snackbar snackbar = Snackbar
                                .make(v, "Mohon pilih salah satu", Snackbar.LENGTH_LONG);

                        snackbar.show();
                    } else {
                        RadioButton rb = (RadioButton) findViewById(check);
                        hasilAnswer.set(positionSeekBar, rb.getText().toString());
                    }
                }
                int a = 1;
                while (a <= 10) {
                    if (hasilAnswer.get(a).equals("-")) {
                        positionSeekBar = a;
                        seekBar.setProgress(positionSeekBar);
                        Snackbar snackbar = Snackbar
                                .make(v, "Ada data yang anda lewatkan !", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        cek = 1;
                        a = 100;
                    }
                    a++;
                }
                if (cek == 0) {
                    HasilDiagnosa.hasil = hasilAnswer;
                    Intent intent = new Intent(Questioner.this, HasilDiagnosa.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = radioGroup.getCheckedRadioButtonId();

                if (check == -1) {
                    Snackbar snackbar = Snackbar
                            .make(v, "Mohon pilih salah satu", Snackbar.LENGTH_LONG);

                    snackbar.show();
                } else {
                    RadioButton rb = (RadioButton) findViewById(check);
                    hasilAnswer.set(positionSeekBar, rb.getText().toString());
                    positionSeekBar++;
                    seekBar.setProgress(positionSeekBar);
                }
            }
        });

        seekBar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {

            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                positionSeekBar = progress;
                btnNext.setVisibility(View.VISIBLE);
                btnCek.setVisibility(View.GONE);
                getAnswer(progress);
                getQuestion(progress);
            }
        });
    }

    private void getAnswer(int posisi) {
        if (hasilAnswer.get(posisi).equals("Iya")) {
            radioGroup.check(R.id.rb1);
        } else if (hasilAnswer.get(posisi).equals("Kadang-kadang")) {
            radioGroup.check(R.id.rb2);
        } else if (hasilAnswer.get(posisi).equals("Tidak")) {
            radioGroup.check(R.id.rb3);
        } else {
            radioGroup.clearCheck();
        }
    }

    private void getQuestion(int posisi) {
        switch (posisi) {
            case 1:
                textQuestion.setText(R.string.quest1);
                break;
            case 2:
                textQuestion.setText(R.string.quest2);
                break;
            case 3:
                textQuestion.setText(R.string.quest3);
                break;
            case 4:
                textQuestion.setText(R.string.quest4);
                break;
            case 5:
                textQuestion.setText(R.string.quest5);
                break;
            case 6:
                textQuestion.setText(R.string.quest6);
                break;
            case 7:
                textQuestion.setText(R.string.quest7);
                break;
            case 8:
                textQuestion.setText(R.string.quest8);
                break;
            case 9:
                textQuestion.setText(R.string.quest9);
                break;
            case 10:
                btnCek.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.GONE);
                textQuestion.setText(R.string.quest10);
                break;
        }
    }

    private void configSeekBar() {
        seekBar.getConfigBuilder()
                .min(1)
                .max(10)
                .progress(positionSeekBar)
                .sectionCount(9)
                .trackColor(ContextCompat.getColor(this, R.color.putihGelap1))
                .showSectionText()
                .sectionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .sectionTextSize(18)
                .thumbTextSize(18)
                .bubbleColor(ContextCompat.getColor(this, R.color.green1))
                .bubbleTextSize(18)
                .showSectionMark()
                .autoAdjustSectionMark()
                .sectionTextPosition(BubbleSeekBar.TextPosition.BELOW_SECTION_MARK)
                .build();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Questioner.this, UserInput.class);
        startActivity(intent);
        finish();
    }
}
