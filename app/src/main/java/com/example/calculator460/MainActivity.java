package com.example.calculator460;
import org.mozilla.javascript.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Scriptable;

/**
 * MainActivity class represents the main screen of the calculator application
 * it handles the initialization of UI elements and user interactions through button clicks
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /** textView to display the result and the input equation/solution */
    TextView resultTv, solutionTv;

    /** MaterialButtons representing the calculator buttons */
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMul, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    /**
     * Initializes the activity. Sets up the layout and assigns button IDs
     *
     * @param savedInstanceState State information saved from prior instances of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonAC, R.id.button_AC);
        assignId(buttonC, R.id.button_c);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMul, R.id.button_mul);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEquals, R.id.button_equal);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
    }

    /**
     * assigns the button object with the provided ID and sets its click listener
     *
     * @param btn The MaterialButton object to be initialized
     * @param id  The resource ID of the button in the layout
     */
    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    /**
     * handles button click events for the calculator
     * updates the solution and result TextViews based on the button clicked
     *
     * @param view The view (button) that was clicked
     */
    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataTocalcuate = solutionTv.getText().toString();

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")) {
            dataTocalcuate = dataTocalcuate.substring(0, dataTocalcuate.length() - 1);
        } else {
            dataTocalcuate = dataTocalcuate + buttonText;
        }

        solutionTv.setText(dataTocalcuate);

        String finalResult = getResults(dataTocalcuate);
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    /**
     * evaluates the mathematical expression provided as a string
     *
     * @param data the string representing the mathematical expression to be evaluated
     * @return the result of the evaluated expression or "Err" in case of an error
     */
    String getResults(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            return context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
        } catch (Exception e) {
            return "Err";
        }
    }
}
