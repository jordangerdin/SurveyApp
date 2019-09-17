package android.bignerdranch.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mYesCountText;
    TextView mNoCountText;

    Button mYesCountButton;
    Button mNoCountButton;
    Button mResetButton;

    int[] mCounts;

    private static final String COUNT_ARRAY_KEY = "count_bundle_key";

    private static final String TAG = "Main_Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate(Bundle) called");

        mYesCountText = findViewById(R.id.text_count_yes);
        mNoCountText = findViewById(R.id.text_count_no);

        mYesCountButton = findViewById(R.id.button_yes);
        mNoCountButton = findViewById(R.id.button_no);
        mResetButton = findViewById(R.id.button_reset);

        // Checking for saved data
        if (savedInstanceState != null) {
            mCounts = savedInstanceState.getIntArray(COUNT_ARRAY_KEY);
            Log.d(TAG, "Instanced data found");
        }
        // No data means start at default
        if (mCounts == null) {
            mCounts = new int[2];
            Log.d(TAG, "No previous data found");
        }
        // Update display to match save data or defaults
        updateCountList();


        mYesCountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // +1 To primary response (Yes)
                mCounts[0] = mCounts[0] + 1;
                Log.d(TAG, "Primary response has been modified");
                updateCountList();
            }
        });

        mNoCountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // +1 To secondary response (No)
                mCounts[1] = mCounts[1] + 1;
                Log.d(TAG, "Secondary response has been modified");
                updateCountList();
            }
        });

        mResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Reset both counts to 0

                mCounts[0] = 0;
                mCounts[1] = 0;
                Log.d(TAG, "The counts have been reset");
                updateCountList();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putIntArray(COUNT_ARRAY_KEY, mCounts);
        Log.d(TAG, "onSaveInstanceState() called");
    }

    private void updateCountList(){
        // Update text fields for primary (yes) and secondary (no) responses
        mYesCountText.setText(String.valueOf(mCounts[0]));
        mNoCountText.setText(String.valueOf(mCounts[1]));
        Log.d(TAG, "The counts have been updated!");
    }
}