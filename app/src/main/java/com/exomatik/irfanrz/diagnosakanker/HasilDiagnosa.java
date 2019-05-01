package com.exomatik.irfanrz.diagnosakanker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;

import java.util.ArrayList;

public class HasilDiagnosa extends AppCompatActivity {
    private IconRoundCornerProgressBar progressBar;
    public static ArrayList<String> hasil = new ArrayList<String>();
    public static String nama, umur;
    private int point;
    private TextView textNama, textUmur, textProgress, textTitle, textIsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        progressBar = (IconRoundCornerProgressBar) findViewById(R.id.progress_bar);
        textNama = (TextView) findViewById(R.id.nama);
        textUmur = (TextView) findViewById(R.id.umur);
        textProgress = (TextView) findViewById(R.id.text_progress);
        textTitle = (TextView) findViewById(R.id.text_title);
        textIsi = (TextView) findViewById(R.id.text_info);

        point = point(0);
        setData();
        setInfo(point);

    }

    private void setInfo(int p) {
        if ((p >= 70) && (p <= 100)){
            textTitle.setText(R.string.title1);
            textIsi.setText(R.string.answer1);
        }
        else if ((p >= 50) && (p <= 69)){
            textTitle.setText(R.string.title2);
            textIsi.setText(R.string.answer2);
        }
        else if ((p >= 0) && (p <= 49)){
            textTitle.setText(R.string.title3);
            textIsi.setText(R.string.answer3);
        }
    }

    private void setData(){
        progressBar.setIconImageResource(R.drawable.ic_healty);
        progressBar.setProgress(point);
        textNama.setText("Nama   : " + nama);
        textUmur.setText("Umur    : " + umur);
        textProgress.setText(Integer.toString(point) + " %");
        progressBar.setSecondaryProgress(++point);
    }

    private int point(int p){
        p = 0;
        for (int a = 1; a <= 10; a++){
            if (hasil.get(a).equals("Iya")){
                p = p + 10;
            }
            else if (hasil.get(a).equals("Kadang-kadang")){
                p = p + 5;
            }
        }
        return p;
    }
}
