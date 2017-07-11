package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText etweight;
    EditText etheight;
    Button calc;
    Button reset;
    Button details;
    TextView date;
    TextView calcbmi;

    @Override
    protected void onPause() {
        super.onPause();
        String strdate = date.getText().toString();
        String bmi = calcbmi.getText().toString();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = data.edit();
        edit.putString("date", strdate);
        edit.putString("bmi", bmi);
        edit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        String strdate = data.getString("date", "Last Calculated Date:");
        String bmi = data.getString("bmi", "Last Calculated BMI:0.0");
        date.setText(strdate);
        calcbmi.setText(bmi);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etweight = (EditText)findViewById(R.id.editTextWeight);
        etheight = (EditText)findViewById(R.id.editTextHeight);
        calc = (Button)findViewById(R.id.buttonCal);
        reset = (Button)findViewById(R.id.buttonReset);
        details = (Button)findViewById(R.id.buttonDetail);
        date = (TextView)findViewById(R.id.textViewDate);
        calcbmi = (TextView)findViewById(R.id.textViewBMI);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
                String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/"+
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY)+":"+
                        now.get(Calendar.MINUTE);

                Float weight = Float.parseFloat(etweight.getText().toString());
                Float height = Float.parseFloat(etheight.getText().toString());
                Float bmi = weight / (height * height);
                date.setText("Last Calculated Date:" + datetime);
                calcbmi.setText("Last Calculated BMI:" + bmi.toString());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etweight.setText("");
                etheight.setText("");
                date.setText("Last Calculated Date:");
                calcbmi.setText("Last Calculated BMI:0.0");
            }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condition = "";
                Float bmi = Float.parseFloat(calcbmi.getText().toString().split(":")[1]);
                if (bmi < 18.5){
                    condition = "You are underweight.";
                }
                else if(bmi <= 24.9){
                    condition = "You are normal.";
                }
                else if(bmi <= 29.9){
                    condition = "You are overweight.";
                }
                else{
                    condition = "You are obese.";
                }
                Intent intent = new Intent(getBaseContext(), SecondPage.class);
                intent.putExtra("condition", condition);
                startActivity(intent);
            }
        });


    }
}
