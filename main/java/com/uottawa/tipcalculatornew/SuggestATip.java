package com.uottawa.tipcalculatornew;

import android.content.Intent;
import android.media.Rating;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SuggestATip extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_suggest_atip);

        // Following block, definitions and settings related to rating feature of 5 stars
        // User rates their experience at the restaurant and gets a real time percentage suggestion
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(1.0f);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                DecimalFormat numberFormat = new DecimalFormat("#");
                TextView tSuggestion = (TextView) findViewById(R.id.tipSuggestion);
                TextView sSuggestion = (TextView) findViewById(R.id.secretSuggestion);

                ratingBar.setNumStars(5);
                ratingBar.setStepSize(1.0f);
                rating = ratingBar.getRating();

                // We were told to use a base rate of 10, but the rating bar begins with 1 star
                // resulting in a base of (10 + (2 * 1))%
                float tipSuggestion = 10 + (rating * 2);
                tSuggestion.setText("The great wise suggester suggests leaving a tip of "
                        + numberFormat.format(tipSuggestion) + "%");
                sSuggestion.setText("");
                if (rating == 1) {
                    final Animation in = new AlphaAnimation(0.0f, 1.0f);
                    in.setDuration(2000);
                    sSuggestion.setText("However, speaking as a Brit, sod 'em and give 'em bugger all.");
                    sSuggestion.startAnimation(in);
                }
            }


        });}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_suggest_atip, menu);
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

