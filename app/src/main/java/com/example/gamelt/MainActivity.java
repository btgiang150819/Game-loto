package com.example.gamelt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText number1, number2;
    Button rd,  mAdd, mReset;
    TextView kq;
    String mTextMin, mTextMax;
    Random mRandom;
    String res = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1 = findViewById(R.id.a);
        number2 = findViewById(R.id.b);
        rd = findViewById(R.id.random);
        kq = findViewById(R.id.result);
        mAdd =  findViewById(R.id.range);
        mReset = findViewById(R.id.reset);


        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
                int sMax = Integer.parseInt(mTextMax);
                int sMin = Integer.parseInt(mTextMin);

                if(sMax <= sMin) {
                    sMax = sMin + 1;
                    number2.setText(String.valueOf(sMax));
                }

                mRandom = new Random();
                int value = mRandom.nextInt(sMax - sMin + 1) + sMin;
                ArrayList<Integer> arrayList = new ArrayList();

                if(res.isEmpty()){
                    res = String.valueOf(value);
                }
                else{
                    res += " - " + value;
                }

                kq.setText(res);
            }

        });

        number2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    checkInput();
                    int sMax = Integer.parseInt(mTextMax);
                    int sMin = Integer.parseInt(mTextMin);

                    if(sMax <= sMin) {
                        sMax = sMin + 1;
                        number1.setText(String.valueOf(sMax));
                    }

                    mRandom = new Random();
                    int value = mRandom.nextInt(sMax - sMin + 1) + sMin;
                    if(res.isEmpty()){
                        res = String.valueOf(value);
                    }
                    else{
                        res += " - " + value;
                    }

                    kq.setText(res);
                }
                return true;
            }
        });
    }

    //xử lí giá trị đầu vào
    private void  checkInput(){
        mTextMin = number1.getText().toString();
        mTextMax = number2.getText().toString();

        if(mTextMax.isEmpty() || mTextMin.isEmpty()){
            Toast.makeText(MainActivity.this, "Please enter A and B", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}