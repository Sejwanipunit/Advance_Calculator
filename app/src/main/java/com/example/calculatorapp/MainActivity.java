package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.lang.*;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    CardView zero,one,two,three,four,five,six,seven,eight,nine,add,multiply,divide,minus,equal,AC,back,point;
    TextView calculate,result;
    String input="",output;
    double p = 0;
    ArrayList<String> operands = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineHooks();

        setListener();

        result.setVisibility(View.GONE);
        calculate.setText("0");
    }

    private void setListener(){
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        add.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        point.setOnClickListener(this);
        equal.setOnClickListener(this);
        back.setOnClickListener(this);
        AC.setOnClickListener(this);
        divide.setOnClickListener(this);
    }

    private void defineHooks(){
        zero = findViewById(R.id.zero);
        calculate = findViewById(R.id.calculate);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        add = findViewById(R.id.add);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        back = findViewById(R.id.back);
        point = findViewById(R.id.point);
        equal = findViewById(R.id.equal);
        divide = findViewById(R.id.divide);
        result = findViewById(R.id.result);
        AC = findViewById(R.id.AC);
    }


    @Override
    public void onClick(View view) {
        if(view == zero){
            calculate("0");
        }else if(view == one){
            calculate("1");
        }else if(view == two){
            calculate("2");
        }else if(view == three){
            calculate("3");
        }else if(view == four){
            calculate("4");
        }else if(view == five){
            calculate("5");
        }else if(view == six){
            calculate("6");
        }else if(view == seven){
            calculate("7");
        }else if(view == eight){
            calculate("8");
        }else if(view == nine){
            calculate("9");
        }else if(view == add){
            calculate("+");
        }else if(view == multiply){
            calculate("*");
        }else if(view == minus){
            calculate("-");
        }else if(view == back){
            calculate("--");
        }else if(view == point){
            calculate(".");
        }else if(view == divide){
            calculate("/");
        }else if (view == AC){
            calculate("AC");
        }else if(view == equal){
            calculate("result");
        }
    }

    private void calculate(String no) {

        if(no.equals("AC")){
            input ="";
            result.setText("0");
        } else if (no.equals("--")) {
            if(!input.equals("")){
                if(input.endsWith(" ")){
                    input = input.substring(0, input.length() - 2);
                }
                input = input.substring(0,input.length()-1);
            }

        }else if(no.equals("result")){
            getResult();
        }else{
            boolean operatorCheck = false;
            if((input.endsWith("+") || input.endsWith(".") || input.endsWith("-") || input.endsWith("*") || input.endsWith("/")) && (no.equals("+") || no.equals("-") || no.equals("/") || no.equals("*") || no.equals("."))){
                operatorCheck = true;
            }
            if((input.equals("") || input.equals(".")) && (no.equals("+") || no.equals("-") || no.equals("*") || no.equals("/"))){
                operatorCheck = true;
            }
            if(!operatorCheck){
                input = input + no;
            }

        }

        calculate.setText(input);
    }

    private void getResult() {
        result.setVisibility(View.VISIBLE);
        operands.clear();
        operands.addAll(Arrays.asList(input.split("(?<=[-+*/])|(?=[-+*/])")));

        getAns("/");
        getAns("*");
        getAns("+");
        getAns("-");

        p = Double.parseDouble(operands.get(0));
        DecimalFormat formatter = new DecimalFormat("#.##########");
        result.setText(String.valueOf(formatter.format(p)));

    }

    private void getAns(String operator) {
        int size = operands.size();
        for(int j =0;j<size;j++){
            int len = operands.size();
        for(int i=0;i<len;i++){
            if(operands.get(i).equals(operator)){
                Double num1 = Double.parseDouble(operands.get(i-1));
                Double num2 = Double.parseDouble(operands.get(i+1));
                if(operands.get(i).equals("/")){
                    p = num1 / num2;
                }else if(operands.get(i).equals("*")) {
                    p = num1 * num2;
                }else if(operands.get(i).equals("+")){
                    p = num1+num2;
                }else if(operands.get(i).equals("-")){
                    p = num1-num2;
                }
                operands.remove(i-1);
                operands.add(i-1,String.valueOf(p));
                operands.remove(i+1);
                operands.remove(i);
                break;
            }

        } }

    }

}