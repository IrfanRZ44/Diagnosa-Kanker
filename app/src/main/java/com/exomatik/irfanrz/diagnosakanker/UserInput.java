package com.exomatik.irfanrz.diagnosakanker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class UserInput extends AppCompatActivity {
    private ImageView imgBack;
    private EditText etNama, etUmur;
    private Button btnLanjut;
    private int umur;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        imgBack = (ImageView) findViewById(R.id.back);
        etNama = (EditText) findViewById(R.id.nama);
        etUmur = (EditText) findViewById(R.id.umur);
        btnLanjut = (Button) findViewById(R.id.lanjut);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etNama.getText().toString().isEmpty()) || (etUmur.getText().toString().isEmpty())){
                    Snackbar snackbar = Snackbar
                            .make(v, "Mohon isi data dengan Valid", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }
                else {
                    umur = Integer.parseInt(etUmur.getText().toString());
                    if ((umur < 0) || (umur >= 250)){
                        Snackbar snackbar = Snackbar
                                .make(v, "Mohon isi data dengan Valid", Snackbar.LENGTH_LONG);

                        snackbar.show();
                    }
                    else {
                        HasilDiagnosa.nama = etNama.getText().toString();
                        HasilDiagnosa.umur = etUmur.getText().toString();
                        Intent intent = new Intent(UserInput.this, Questioner.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInput.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserInput.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
