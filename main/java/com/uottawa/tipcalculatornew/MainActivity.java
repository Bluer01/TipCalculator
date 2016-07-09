package com.uottawa.tipcalculatornew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NumberPicker payeePicker = (NumberPicker) findViewById(R.id.payeePicker);
        payeePicker.setMinValue(1);
        payeePicker.setMaxValue(10);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Fetching the preference information shared between activities
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String tPercent = sharedPref.getString("defaultTip", "0");
        final EditText tText = (EditText) findViewById(R.id.tText);

        tText.setText(tPercent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.preferences) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onTipCalculate(View view) {

        String regexStr = "^[0-9\\.]*$"; // Useful for input validation
        Intent intent = new Intent(getApplicationContext(), Summary.class);
        NumberPicker payeePicker = (NumberPicker) findViewById(R.id.payeePicker);
        EditText bText = (EditText) findViewById(R.id.bText);
        EditText tText = (EditText) findViewById(R.id.tText);

        if (bText.getText().toString().length() == 0 || !(bText.getText().toString().trim().matches(regexStr))) {
            bText.setError("Please input a number, which is also greater than 0");
        } else if(tText.getText().toString().length() == 0 || !(tText.getText().toString().trim().matches(regexStr))) {
            tText.setError("Please input a number, which is also greater than 0");
        } else{

            double bAmount = Double.parseDouble(bText.getText().toString());
            double tPercent = (Double.parseDouble(tText.getText().toString())) / 100;
            int pAmount = payeePicker.getValue();

            Bundle tipInfo = new Bundle();
            // Storing to use in the summary activity
            tipInfo.putDouble("bill", bAmount);
            tipInfo.putDouble("percent", tPercent);
            tipInfo.putInt("payees", pAmount);

            intent.putExtras(tipInfo);


            startActivity(intent);
        }
    }



    public void onSuggestTip(View view) {
        Intent intent = new Intent(getApplicationContext(), SuggestATip.class);
        startActivity(intent);
    }
}
