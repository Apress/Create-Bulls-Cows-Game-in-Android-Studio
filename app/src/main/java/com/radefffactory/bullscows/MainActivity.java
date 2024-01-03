package com.radefffactory.bullscows;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    NumberPicker np1, np2, np3, np4;

    Button b_check;

    TextView tv_output;

    int gen1, gen2, gen3, gen4;

    String output = "";
    int index = 0;

    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);

        //generate a number
        Random r = new Random();

        //generated digit 1 - between 1 and 9
        gen1 = r.nextInt(9) + 1;

        //generated digit 2 - between 1 and 9 with duplucate number check
        do {
            gen2 = r.nextInt(9) + 1;
        } while (gen2 == gen1);

        //generated digit 3 - between 1 and 9 with duplucate number check
        do {
            gen3 = r.nextInt(9) + 1;
        } while (gen3 == gen1 || gen3 == gen2);

        //generated digit 4 - between 1 and 9 with duplucate number check
        do {
            gen4 = r.nextInt(9) + 1;
        } while (gen4 == gen1 || gen4 == gen2 || gen4 == gen3);

        //setup number pickers
        np1 = findViewById(R.id.np1);
        np1.setMinValue(1);
        np1.setMaxValue(9);
        np1.setValue(1);

        np2 = findViewById(R.id.np2);
        np2.setMinValue(1);
        np2.setMaxValue(9);
        np2.setValue(2);

        np3 = findViewById(R.id.np3);
        np3.setMinValue(1);
        np3.setMaxValue(9);
        np3.setValue(3);

        np4 = findViewById(R.id.np4);
        np4.setMinValue(1);
        np4.setMaxValue(9);
        np4.setValue(4);

        tv_output = findViewById(R.id.tv_output);
        tv_output.setText("Guess the number!");

        b_check = findViewById(R.id.b_check);
        b_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = np1.getValue();
                int num2 = np2.getValue();
                int num3 = np3.getValue();
                int num4 = np4.getValue();

                //check if there is duplicate digits
                if (num1 != num2 && num1 != num3 && num1 != num4 &&
                        num2 != num3 && num2 != num4 && num3 != num4) {

                    //check for bulls - guessed digit and place
                    int bulls = 0;

                    if (num1 == gen1) {
                        bulls = bulls + 1;
                    }

                    if (num2 == gen2) {
                        bulls = bulls + 1;
                    }

                    if (num3 == gen3) {
                        bulls = bulls + 1;
                    }

                    if (num4 == gen4) {
                        bulls = bulls + 1;
                    }

                    //cows - guessed digit but misplaced
                    int cows = 0;

                    if (num1 == gen2 || num1 == gen3 || num1 == gen4) {
                        cows = cows + 1;
                    }

                    if (num2 == gen1 || num2 == gen3 || num2 == gen4) {
                        cows = cows + 1;
                    }

                    if (num3 == gen2 || num3 == gen1 || num3 == gen4) {
                        cows = cows + 1;
                    }

                    if (num4 == gen2 || num4 == gen3 || num4 == gen1) {
                        cows = cows + 1;
                    }

                    index = index + 1;

                    output = output + "" + index + ". " + num1 + "" + num2 + "" + num3 + "" + num4 +
                            " Bulls: " + bulls + " Cows: " + cows + "\n";

                    //check for win
                    if (bulls == 4) {
                        b_check.setEnabled(false);
                        output = output + "You won!";
                    }

                    //display result
                    tv_output.setText(output);
                } else {
                    //there are duplicating digits
                    output = output + "Wrong input!\n";
                    tv_output.setText(output);
                }

                scrollView.scrollBy(0, 100);
            }
        });
    }
}