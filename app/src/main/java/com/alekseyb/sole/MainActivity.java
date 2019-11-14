package com.alekseyb.sole;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClockButton(View view) {
        TextView text = findViewById(R.id.text);
        Spinner spinner = findViewById(R.id.spinner);
        EditText editText1 = findViewById(R.id.editText1);
        EditText editText2 = findViewById(R.id.editText2);
        int num = spinner.getSelectedItemPosition() + 2;

        SystemOfLinearEquation s = new SystemOfLinearEquation(num);

        String [] text1 = editText1.getText().toString().split(" ",num*num);
        String [] text2 = editText2.getText().toString().split(" ",num);

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                s.a[i][j] = Double.parseDouble(text1[(i * num) + j]);
            }
            s.b[i] = Double.parseDouble(text2[i]);
        }
        // Печатаем уравнение
        String solution = "Уравнение:\n";
        for (int i = 0; i < num; i++) {
            solution += Double.toString(s.a[i][0])+"X1";
            for (int j = 1; j < num; j++)
                if (s.a[i][j] < 0) solution += " - " + Double.toString(Math.abs(s.a[i][j])) + "X" +(j+1);
                else solution += " + " + Double.toString(s.a[i][j])+ "X" +(j+1);
            solution += " = " + Double.toString(s.b[i])+"\n";
        }
        // Печатаем решение
        solution += "\nРешение:\n";
        if (s.executeMatrixMethod()) {
            for (int i = 0; i < num; i++) solution += "X" + (i+1) + " = " + Double.toString(s.x[i]) + "\n";
            text.setText(solution);
        }
        else
            text.setText(solution+"Система уравнений вырождена");
    }
}
