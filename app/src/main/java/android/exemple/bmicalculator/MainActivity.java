package android.exemple.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView resultText;
    private Button calculateButton;
    private RadioButton maleButton;
    private RadioButton femaleButton;
    private EditText feetEditText;
    private EditText ageEditText;
    private EditText weightEditText;
    private EditText inchesEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);
        maleButton = findViewById(R.id.radio_button_male);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);
        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        femaleButton = findViewById(R.id.radio_button_female);
        calculateButton = findViewById(R.id.button_calculate);
    }

    //If the person has below 18 years old, the app will show a guidance message to check a doctor
    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    // Function that calculates the BMI
    private double calculateBmi() {
        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        // Converting the number 'String' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        // Height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        //BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResult(double bmi) {
        //Reduse the result of BMI only to 2 decimals
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            // Display underweight
            fullResultString = bmiTextResult + " - You are underweight!";
        } else if (bmi > 25) {
            // Display overweight
            fullResultString = bmiTextResult + " - You are overweight!";
        } else {
            // Display healthy
            fullResultString = bmiTextResult + " - You are healthy weight!";
        }
        resultText.setText(fullResultString);
    }

    //Guidance messages
    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (maleButton.isChecked()) {
            //Display boy guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healty rangs for boys";
        } else if (femaleButton.isChecked()) {
            //Display girl guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healty rangs for girls";
        } else {
            //Display general guidance
            fullResultString = bmiTextResult + " - As you are under 18, please consult with your doctor for the healty rangs";
        }
        resultText.setText(fullResultString);
    }
}