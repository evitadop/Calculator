package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText inputsView;
    EditText resultView;

    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputsView = findViewById(R.id.input);
        inputsView.setShowSoftInputOnFocus(false);

        inputsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.input).equals(inputsView.getText().toString())) {
                    inputsView.setText("");
                }
            }
        });

        resultView = (EditText) findViewById(R.id.result);
    }

    private void updateText(String strToAdd) {
        String oldStr;
        String leftStr;
        String rightStr;
        int cursorPos;

        oldStr = inputsView.getText().toString();
        cursorPos = inputsView.getSelectionStart();
        leftStr = oldStr.substring(0, cursorPos);
        rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.input).equals(inputsView.getText().toString())) {
            inputsView.setText(strToAdd);
            inputsView.setSelection(cursorPos + 1);
        } else {
            inputsView.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            inputsView.setSelection(cursorPos + 1);
        }
    }

    public void btnZeroOnClick(View view) {
        updateText("0");
    }

    public void btnOneOnClick(View view) {
        updateText("1");
    }

    public void btnTwoOnClick(View view) {
        updateText("2");
    }

    public void btnThreeOnClick(View view) {
        updateText("3");
    }

    public void btnFourOnClick(View view) {
        updateText("4");
    }

    public void btnFiveOnClick(View view) {
        updateText("5");
    }

    public void btnSixOnClick(View view) {
        updateText("6");
    }

    public void btnSevenOnClick(View view) {
        updateText("7");
    }

    public void btnEightOnClick(View view) {
        updateText("8");
    }

    public void btnNineOnClick(View view) {
        updateText("9");
    }

    public void comaOnClick(View view) {
        updateText(".");
    }

    public void deleteOnClick(View view) {
        int cursorPos = inputsView.getSelectionEnd();
        int textLength = inputsView.getText().length();

        if (cursorPos != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) inputsView.getText();
            selection.replace(cursorPos- 1, cursorPos, "");
            inputsView.setText(selection);
            inputsView.setSelection(cursorPos- 1);
        }
    }

    public void resetOnClick(View view) {
        inputsView.setText("");
    }

    public void bracketOnClick(View view) {
        int leftBracket = 0;
        int rightBracket = 0;
        int textLen = 0;

        pos = inputsView.getSelectionStart();
        textLen = inputsView.getText().toString().length();

        for (int i = 0; i < pos; i++){
            if (inputsView.getText().toString().substring(i, i+1).equals("(")){
                leftBracket += 1;
            }
            else if (inputsView.getText().toString().substring(i, i+1).equals(")")){
                rightBracket += 1;
            }
        }

        if (leftBracket == rightBracket || inputsView.getText().toString().substring(
                textLen - 1, textLen).equals("(")) {
            updateText( "(");
            inputsView.setSelection(pos + 1);
        }
        else if (rightBracket < leftBracket && !inputsView.getText().toString().substring(
                textLen - 1, textLen).equals(")")) {
            updateText(")");
        }
        inputsView.setSelection(pos + 1);
    }

    public void plusOnClick(View view) {
        updateText("+");
    }

    public void minusOnClick(View view) {
        updateText("-");
    }

    public void divideOnClick(View view) {
        updateText("/");
    }

    public void multiplyOnClick(View view) {
        updateText("×");
    }

    public void PercentOnClick(View view) {
        updateText("%");
    }

    public void equalOnClick(View view) {
        String expression;
        String result ;

        expression = inputsView.getText().toString();
        expression = expression.replaceAll("×", "*");

        Expression exp = new Expression(expression);

        result = String.valueOf(exp.calculate());

        resultView.setText(result);
        resultView.setSelection(result.length());
    }
}


