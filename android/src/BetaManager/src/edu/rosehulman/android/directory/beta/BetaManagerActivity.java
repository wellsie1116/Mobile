package edu.rosehulman.android.directory.beta;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class BetaManagerActivity extends Activity {
	
	private static String TAG = "BetaManager";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        View btnUnitTests;
        btnUnitTests = findViewById(R.id.btnUnitTests);
        btnUnitTests.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				btnUnitTests_onClick();
			}
		});
    }
    
    private void btnUnitTests_onClick() {
    	boolean started;
    	Log.w(TAG, "Starting instrumentation");

    	
    	ComponentName name;
    	name = new ComponentName(getApplicationContext(), CustomInstrumentationTestRunner.class);
    	started = getApplicationContext().startInstrumentation(name, null, null);
    	if (started) {
    		Log.w(TAG, "Instrumentation started");
    	}
    }
}