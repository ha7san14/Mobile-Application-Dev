package com.example.mad_proj_bcsf20m525.Activities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_proj_bcsf20m525.R;

public class CalculatorActivity extends AppCompatActivity {
    private TextView workingsTextView, resultTextView;
    private String currentNumber = "";
    private String currentOperator = "";
    private double operand1 = Double.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        workingsTextView = findViewById(R.id.workingsTextView);
        resultTextView = findViewById(R.id.resultTextView);

        Button clearButton = findViewById(R.id.clearOnClick);
        Button divisionButton = findViewById(R.id.divisionOnClick);
        Button sevenButton = findViewById(R.id.sevenOnClick);
        Button eightButton = findViewById(R.id.eightOnClick);
        Button nineButton = findViewById(R.id.nineOnClick);
        Button timesButton = findViewById(R.id.timesOnClick);
        Button fourButton = findViewById(R.id.fourOnClick);
        Button fiveButton = findViewById(R.id.fiveOnClick);
        Button sixButton = findViewById(R.id.sixOnClick);
        Button minusButton = findViewById(R.id.minusOnClick);
        Button oneButton = findViewById(R.id.oneOnClick);
        Button twoButton = findViewById(R.id.twoOnClick);
        Button threeButton = findViewById(R.id.threeOnClick);
        Button plusButton = findViewById(R.id.plusOnClick);
        Button decimalButton = findViewById(R.id.decimalOnClick);
        Button zeroButton = findViewById(R.id.zeroOnClick);
        Button equalsButton = findViewById(R.id.equalsOnClick);
        Button backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("/");
            }
        });

        sevenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("7");
            }
        });

        eightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("8");
            }
        });

        nineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("9");
            }
        });

        timesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("X");
            }
        });

        fourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("4");
            }
        });

        fiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("5");
            }
        });

        sixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("6");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("-");
            }
        });

        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("1");
            }
        });

        twoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("2");
            }
        });

        threeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("3");
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operatorClicked("+");
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked(".");
            }
        });

        zeroButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClicked("0");
            }
        });

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void clear() {
        workingsTextView.setText("");
        resultTextView.setText("");
        currentNumber = "";
        currentOperator = "";
        operand1 = Double.NaN;
    }

    private void numberClicked(String number) {
        currentNumber += number;
        workingsTextView.setText(currentNumber);
    }

    private void operatorClicked(String operator) {
        if (!Double.isNaN(operand1)) {
            calculate();
            currentOperator = operator;
        } else {
            operand1 = Double.parseDouble(currentNumber);
            currentNumber = "";
            currentOperator = operator;
            workingsTextView.setText(operand1 + " " + currentOperator);
        }
    }

    private void calculate() {
        if (!Double.isNaN(operand1) && !currentNumber.isEmpty() && !currentOperator.isEmpty()) {
            double operand2 = Double.parseDouble(currentNumber);
            double result = 0.0;

            switch (currentOperator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "X":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand1 != 0) {
                        result = operand1 / operand2;
                    } else {
                        resultTextView.setText("Error");
                    }
                    break;
            }

            resultTextView.setText(String.valueOf(result));
            operand1 = result;
            currentNumber = "";
            currentOperator = "";
            workingsTextView.setText("");
        }
    }
}
