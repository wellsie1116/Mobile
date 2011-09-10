package edu.rosehulman.android.directory;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MainActivity extends MapActivity {
	
	public static String TAG = "MobileDirectoryActivity";

	private BetaManagerManager m_betaManager;

    private MapView m_mapView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        boolean useBeta = !getIntent().getBooleanExtra("DisableBeta", false);
        if (useBeta) {
	        m_betaManager = new BetaManagerManager(this);
	        
	        if (m_betaManager.hasBetaManager()) {
	        	if (m_betaManager.isBetaRegistered()) {
	        		m_betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_STARTUP);	
	        	} else {
	        		m_betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_REGISTER);
	        	}
	        }
        }
        
        m_mapView = (MapView)findViewById(R.id.mapview);
        m_mapView.setBuiltInZoomControls(true);
        m_mapView.setSatellite(true);
        
        //center the map
        GeoPoint center = new GeoPoint(39483760, -87325929);
        m_mapView.getController().setCenter(center);
        m_mapView.getController().zoomToSpan(6241, 13894);
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

	@Override
	protected boolean isRouteDisplayed() {
		//FIXME update when we start displaying route information
		return false;
	}
    
    
}