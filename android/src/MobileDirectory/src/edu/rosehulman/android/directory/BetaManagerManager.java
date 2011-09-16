package edu.rosehulman.android.directory;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class BetaManagerManager extends ContextWrapper {

	public static String ACTION_SHOW_STARTUP = "edu.rosehulman.android.directory.beta.SHOW_STARTUP";
    public static String ACTION_SHOW_REGISTER = "edu.rosehulman.android.directory.beta.SHOW_REGISTER";
    public static String ACTION_SHOW_BETA_MANAGER = "edu.rosehulman.android.directory.beta.SHOW_BETA_MANAGER";
    
    public static String PREFS_FILE = "PREFS_BETA";
    public static String PREF_HAS_RUN = "HAS_RUN";
	
	private static final String BETA_PACKAGE = "edu.rosehulman.android.directory.beta";
    private static final String BETA_ACTIVITY = "edu.rosehulman.android.directory.beta.BetaManagerActivity";
	
	public BetaManagerManager(Context base) {
		super(base);
	}

    public boolean hasBetaManager() {
    	PackageManager packageManager = getPackageManager(); 
    	ComponentName name = new ComponentName(BETA_PACKAGE, BETA_ACTIVITY);
    	try {
    		packageManager.getActivityInfo(name, PackageManager.GET_META_DATA);
    	} catch (NameNotFoundException ex) {
    		return false;
    	}

    	return true;
    }

    public boolean isBetaRegistered() {
    	SharedPreferences prefs;
    	try {
    		prefs = createPackageContext(BETA_PACKAGE, 0).getSharedPreferences(PREFS_FILE, MODE_WORLD_READABLE);	
    	} catch (NameNotFoundException ex) {
    		Log.e(C.TAG, "Failed to open beta shared preferences");
    		return false;
    	}
    	
    	return prefs.getBoolean(PREF_HAS_RUN, false);    	
    }
    
    public void launchBetaActivity(String action) {
    	Intent intent = new Intent(action);
    	try {
    		startActivity(intent);
    	} catch (ActivityNotFoundException ex) {
    		Log.e(C.TAG, "Activity not found", ex);
    	}
    }	
}
