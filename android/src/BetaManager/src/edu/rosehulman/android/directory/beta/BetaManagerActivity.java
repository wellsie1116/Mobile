package edu.rosehulman.android.directory.beta;

import android.app.Activity;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
        
        findViewById(R.id.btnUnregister).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			    String PREFS_FILE = "PREFS_BETA";
			    String PREF_HAS_RUN = "HAS_RUN";

				//mark that we are not registered
				SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_WORLD_READABLE);
				Editor edit = prefs.edit();
		        edit.putBoolean(PREF_HAS_RUN, false);
		        edit.commit();
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