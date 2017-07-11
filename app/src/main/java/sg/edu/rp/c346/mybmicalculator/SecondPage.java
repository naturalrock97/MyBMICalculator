package sg.edu.rp.c346.mybmicalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondPage extends AppCompatActivity {
    TextView cond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);
        cond = (TextView)findViewById(R.id.textViewCondition);
        Intent intentReceived = getIntent();
        String condition = intentReceived.getStringExtra("condition");
        cond.setText(condition);
    }
}
