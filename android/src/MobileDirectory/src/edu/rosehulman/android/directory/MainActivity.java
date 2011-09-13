package edu.rosehulman.android.directory;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.MapView.ReticleDrawMode;

public class MainActivity extends MapActivity {
	
	public static String TAG = "MobileDirectoryActivity";

	private BetaManagerManager m_betaManager;

    private MapView m_mapView;
    
    private LocationManager m_serviceLocation;
    private LocationListener m_locListener;

    private MyLocationOverlay m_locationOverlay;
    
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
        
        m_locationOverlay = new MyLocationOverlay(this, m_mapView);
        m_mapView.getOverlays().add(m_locationOverlay);
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
    protected void onResume() {
    	super.onResume();
    	
        //Start the location services
        m_serviceLocation = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        m_locListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				Log.v(TAG, location.toString());
			}

			@Override
			public void onProviderDisabled(String provider) {
				//TODO yell at user
			}

			@Override
			public void onProviderEnabled(String provider) {
				//TODO stop yelling at user
			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				if (status != LocationProvider.AVAILABLE) {
					//TODO handle appropriately
					//TEMPORARILY_UNAVAILABLE
					//AVAILABLE
				}
			}
		};
        
        m_serviceLocation.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, m_locListener);
        m_serviceLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, m_locListener);
        
        // enable the location overlay
        m_locationOverlay.enableCompass();
        m_locationOverlay.enableMyLocation();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	m_serviceLocation.removeUpdates(m_locListener);
    	
        //disable the location overlay
        m_locationOverlay.disableCompass();
        m_locationOverlay.disableMyLocation();
    }

	@Override
	protected boolean isRouteDisplayed() {
		//FIXME update when we start displaying route information
		return false;
	}
    
    
}