package com.tecraa.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.tecraa.bmi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        double fixnum = 0.3048;

        binding.heightET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String getft = String.valueOf(charSequence);

                if (getft.equals("")){
                    binding.ftomconTV.setText("1m = 3.28084Ft || Your Height is : 0m");
                    binding.bmicalresultTV.setText("Results will be printed here");
                    binding.bmicalnoteTV.setText("");
                }else{
                    double getfeet = Double.parseDouble(getft);
                    double conmres =getfeet*fixnum;
                    String convmeter = new Double(Math.round(conmres * 100.0) / 100.0).toString();
                    binding.ftomconTV.setText("1m = 3.28084Ft || Your Hight is : "+convmeter+"m");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {



            }
        });

        binding.wightET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String getwt = String.valueOf(charSequence);

                if (getwt.equals("")){
                    binding.bmicalresultTV.setText("Results will be printed here");
                    binding.bmicalnoteTV.setText("");
                    binding.bmicalresultTV.setTextColor(0xFFFF0000);
                }else{
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.calculatebmibtn.setOnClickListener(view -> {

            String heightStr = binding.heightET.getText().toString();
            String weightStr = binding.wightET.getText().toString();

            if (heightStr.equals("") || weightStr.equals("")){
                Toast.makeText(getApplicationContext(),"Please Enter Your Height & Weight",Toast.LENGTH_LONG).show();

            }else{
                double height = Double.parseDouble(heightStr);
                double weight = Double.parseDouble(weightStr);

                double bmiResult = new Double(Math.round(weight/(Math.pow((height*fixnum), 2)) * 100.0) / 100.0);

                if (bmiResult<18.4){
                    binding.bmicalresultTV.setText("Your BMI Result \nStatus: under weight. \nPoint: "+ bmiResult);
                    binding.bmicalresultTV.setTextColor(this.getResources().getColor(R.color.info));
                    binding.bmicalnoteTV.setText("Suggestion : Eat more nutritious food and care of your health, also You can eat fast food to gain weight.");
                }else if(bmiResult>18.4 && bmiResult <=24.9){
                    binding.bmicalresultTV.setText("Your BMI Result \nStatus: Normal. \nPoint: "+ bmiResult);
                    binding.bmicalresultTV.setTextColor(this.getResources().getColor(R.color.success));
                    binding.bmicalnoteTV.setText("Suggestion : Your health is good. You can eat any food");
                }else if (bmiResult>24.9 && bmiResult <=39.9){
                    binding.bmicalresultTV.setText("Your BMI Result \nStatus: Overweight. \nPoint: "+ bmiResult);
                    binding.bmicalresultTV.setTextColor(this.getResources().getColor(R.color.warning));
                    binding.bmicalnoteTV.setText("Suggestion : You are healthy, just a little overweight. You can do gym for lose overweight.");
                }else{
                    binding.bmicalresultTV.setText("Your BMI Result \nStatus: Obese. \nPoint: "+ bmiResult);
                    binding.bmicalresultTV.setTextColor(this.getResources().getColor(R.color.danger));
                    binding.bmicalnoteTV.setText("Suggestion : Your health is very high (Obese). You should avoid eating fast food. Diet and gym should be done to maintain good health.");
                }
            }





        });
    }
}