package edu.rosehulman.android.directory;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MobileDirectoryActivity extends Activity {
	
	public static String TAG = "MobileDirectoryActivity";

	private BetaManagerManager m_betaManager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_betaManager = new BetaManagerManager(this);
        
        if (m_betaManager.hasBetaManager()) {
        	if (m_betaManager.isBetaRegistered()) {
        		m_betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_STARTUP);	
        	} else {
        		m_betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_REGISTER);
        	}
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
    	menu.setGroupVisible(R.id.beta_channel, m_betaManager.hasBetaManager());
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.beta_manager:
            m_betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_BETA_MANAGER);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    
}