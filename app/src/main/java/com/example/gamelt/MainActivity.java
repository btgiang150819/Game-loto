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

import java.io.StringReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    EditText mMin, mMax, mNumber, mPoint;
    Button mRand,  mAdd, mReset;
    TextView mListRandom, mScore;
    String mTextMin, mTextMax;
    Random mRandom;
    String mRes = "";
    int multiples;
    List<Integer> arrayList;
    boolean isClickRange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMin = findViewById(R.id.a);
        mMax = findViewById(R.id.b);
        mRand = findViewById(R.id.random);
        mListRandom = findViewById(R.id.result);
        mAdd =  findViewById(R.id.range);
        mReset = findViewById(R.id.reset);
        mNumber =  findViewById(R.id.c);
        mPoint = findViewById(R.id.point);
        mScore = findViewById(R.id.score);
        arrayList = new ArrayList<Integer>();


        //set onclick cho button RANGE
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
                if(mTextMax.isEmpty() || mTextMin.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng nhập khoảng để chọn số", Toast.LENGTH_SHORT).show();
                }
                else if(isClickRange){
                    Toast.makeText(MainActivity.this, "Bạn đã tạo khoảng vui long không chọn lại", Toast.LENGTH_SHORT).show();
                }
                else {
                    mMin.setEnabled(false);
                    mMax.setEnabled(false);
                    isClickRange = true;
                    int sMax = Integer.parseInt(mTextMax);
                    int sMin = Integer.parseInt(mTextMin);

                    if(sMax <= sMin) {
                        sMax = sMin + 1;
                        mMax.setText(String.valueOf(sMax));
                    }
                    multiples = (sMax-sMin);
                    for (int i = sMin; i <= sMax; i++){
                        arrayList.add(i);
                    }
                    Toast.makeText(MainActivity.this, "Bạn đã tạo khoảng thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //set onClick cho button mResET
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMin.setEnabled(true);
                mMax.setEnabled(true);
                isClickRange = false;
                arrayList.clear();
                mMin.setText("");
                mMax.setText("");
                mNumber.setText("");
                mPoint.setText("");
                mScore.setText("100");
                mRes = "";
                mListRandom.setText(mRes);
                Toast.makeText(MainActivity.this, "Bạn đã reset thành công", Toast.LENGTH_SHORT).show();
            }
        });


        mRand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chosen = mNumber.getText().toString();
                String point = mPoint.getText().toString();
                if(chosen.isEmpty() || point.isEmpty()){
                    Toast.makeText(MainActivity.this, "Vui lòng chọn số và nhập số điểm cược", Toast.LENGTH_SHORT).show();
                }
                //Kiểm tra xem người chơi đã tạo khoảng để chơi chưa
                else if(!isClickRange){
                    Toast.makeText(MainActivity.this, "Vui lòng set khoảng để chọn số", Toast.LENGTH_SHORT).show();
                }

                else{
                    int chosen1 = Integer.parseInt(chosen);
                    int pointset = Integer.parseInt(point);
                    int sMax = Integer.parseInt(mTextMax);
                    int sMin = Integer.parseInt(mTextMin);

                    //kiểm tra số đã  nhập có nằm trong khoảng đã set hay không
                    if(chosen1 < sMin || chosen1 > sMax){
                        Toast.makeText(MainActivity.this, "Số bạn chọn phải trong khoảng " + sMin + "đến" + sMax, Toast.LENGTH_SHORT).show();
                    }

                    //nếu không thì xử lí
                    else {
                        int sc = Integer.parseInt(mScore.getText().toString());

                        //kiểm tra số điểm cược có nhỏ hơn số điểm mà bạn có không
                        if(sc < pointset){
                            Toast.makeText(MainActivity.this, "Số điểm cược phải nhỏ hơn số điểm bạn có hiện tại", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mRandom = new Random();


                            //check xem mang co rong ko
                            if(!arrayList.isEmpty()){
                                int index = mRandom.nextInt(arrayList.size());
                                int value = arrayList.get(index);
                                arrayList.remove(index);
                                if(mRes.isEmpty()){
                                    mRes = String.valueOf(value);
                                }
                                else{
                                    mRes += " - " + value;
                                }
                                mListRandom.setText(mRes);

                                if(chosen1 == value){
                                    sc += pointset*multiples;
                                    Toast.makeText(MainActivity.this, "Bạn đã chọn đúng",Toast.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity.this, "Bạn đã được cộng " + sc + " điểm",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    sc -= pointset;
                                }
                                String mRes1 = String.valueOf(sc);
                                mScore.setText(mRes1);

                            }
                            //Nếu mảng rỗng thì thông báo
                            else {
                                Toast.makeText(MainActivity.this, "Khoảng bạn chọn đã hết số vui lòng tạo khoảng mới và chơi tiếp", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //get your mScore current


                }
            }
        });



    }

    //xử lí giá trị đầu vào
    private void  checkInput(){
        mTextMin = mMin.getText().toString();
        mTextMax = mMax.getText().toString();

    }

}