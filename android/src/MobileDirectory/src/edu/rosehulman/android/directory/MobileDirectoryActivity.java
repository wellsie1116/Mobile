package edu.rosehulman.android.directory;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MobileDirectoryActivity extends Activity {
	
	public static String TAG = "MobileDirectoryActivity";

    private static final String BETA_PACKAGE = "edu.rosehulman.android.directory.beta";
    private static final String BETA_ACTIVITY = "edu.rosehulman.android.directory.beta.BetaManagerActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if (hasBetaManager()) {
        	launchBetaManagerStartup();
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	menu.setGroupVisible(R.id.beta_channel, hasBetaManager());
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.beta_manager:
            launchBetaManager();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private boolean hasBetaManager() {
    	PackageManager packageManager = getPackageManager(); 
    	ComponentName name = new ComponentName(BETA_PACKAGE, BETA_ACTIVITY);
    	
    	try {
    		packageManager.getActivityInfo(name, PackageManager.GET_META_DATA);
    	} catch (NameNotFoundException ex) {
    		return false;
    	}

    	return true;
    }
    
    private void launchBetaManagerStartup() {
    	Intent intent = new Intent("edu.rosehulman.android.directory.beta.SHOW_STARTUP");
    	try {
    		startActivity(intent);
    	} catch (ActivityNotFoundException ex) {
    		Log.e(TAG, "Startup activity not found");
    	}
    }
    
    private void launchBetaManager() {
    	Intent intent = new Intent("edu.rosehulman.android.directory.beta.SHOW_BETA_MANAGER");
    	try {
    		startActivity(intent);
    	} catch (ActivityNotFoundException ex) {
    		Log.e(TAG, "Beta Manager activity not found");
    	}
    }
    
}