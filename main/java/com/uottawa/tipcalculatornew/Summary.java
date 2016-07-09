package com.uottawa.tipcalculatornew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent = getIntent();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String currency = sharedPref.getString("currency", "");

        // Fetching information stored from the mainactivity for calculation results
        Bundle tipInfo = intent.getExtras();

        double bill = tipInfo.getDouble("bill");
        double percent = tipInfo.getDouble("percent");
        int payees = tipInfo.getInt("payees");
        double tip = bill * percent;
        double totAmount = bill + tip;
        double tipPerPerson = tip / payees;
        double totalPerPerson = totAmount / payees;

        TextView billAmount = (TextView) findViewById(R.id.billAmount);
        TextView tipAmount = (TextView) findViewById(R.id.tipAmount);
        TextView totalAmount = (TextView) findViewById(R.id.totalAmount);
        TextView tppAmount = (TextView) findViewById(R.id.tppAmount);
        TextView eppAmount = (TextView) findViewById(R.id.eppAmount);
        TextView tppText = (TextView) findViewById(R.id.tppText);
        TextView eppText = (TextView) findViewById(R.id.eppText);

        DecimalFormat numberFormat = new DecimalFormat("#.00");

        // Displaying results
        billAmount.setText(currency + String.valueOf(numberFormat.format(bill)));
        tipAmount.setText(currency + String.valueOf(numberFormat.format(tip)));
        totalAmount.setText(currency + String.valueOf(numberFormat.format(totAmount)));
        if(payees > 1) {
            tppAmount.setText(currency + String.valueOf(numberFormat.format(tipPerPerson)));
            eppAmount.setText(currency + String.valueOf(numberFormat.format(totalPerPerson)));
        } else {
            tppText.setText("");
            tppAmount.setText("");
            eppText.setText("");
            eppAmount.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.preferences) {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }


        if(id == R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
