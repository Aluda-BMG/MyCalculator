package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    Button addButton, subtractButton;
    Button multiplyButton, divideButton, clearButton;
    TextView resultText;

    DecimalFormat decimalFormat = new DecimalFormat("0.##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect XML components to Java
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);
        clearButton = findViewById(R.id.clearButton);

        resultText = findViewById(R.id.resultText);

        // Addition
        addButton.setOnClickListener(v -> calculate("+"));

        // Subtraction
        subtractButton.setOnClickListener(v -> calculate("-"));

        // Multiplication
        multiplyButton.setOnClickListener(v -> calculate("*"));

        // Division
        divideButton.setOnClickListener(v -> calculate("/"));

        // Clear everything
        clearButton.setOnClickListener(v -> {
            number1.setText("");
            number2.setText("");
            resultText.setText("Result: 0");
        });
    }

    private void calculate(String operation) {

        String first = number1.getText().toString().trim();
        String second = number2.getText().toString().trim();

        if (first.isEmpty() || second.isEmpty()) {
            Toast.makeText(this,
                    "Please enter both numbers",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            double num1 = Double.parseDouble(first);
            double num2 = Double.parseDouble(second);

            double result = 0;

            switch (operation) {

                case "+":
                    result = num1 + num2;
                    break;

                case "-":
                    result = num1 - num2;
                    break;

                case "*":
                    result = num1 * num2;
                    break;

                case "/":
                    if (num2 == 0) {
                        resultText.setText("Cannot divide by zero");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            resultText.setText("Result: " + decimalFormat.format(result));

        } catch (NumberFormatException e) {

            resultText.setText("Invalid input");

        }
    }
}