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

public class MainActivity extends MapActivity {
	
	public static String TAG = "MobileDirectoryActivity";

	private BetaManagerManager betaManager;

    private MapView mapView;
    
    private LocationManager locationManager;
    private LocationListener locationListener;

    private MyLocationOverlay myLocation;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        boolean useBeta = !getIntent().getBooleanExtra("DisableBeta", false);
        if (useBeta) {
	        betaManager = new BetaManagerManager(this);
	        
	        if (betaManager.hasBetaManager()) {
	        	if (betaManager.isBetaRegistered()) {
	        		betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_STARTUP);	
	        	} else {
	        		betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_REGISTER);
	        	}
	        }
        }
        
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        mapView.setSatellite(true);
        
        //center the map
        GeoPoint center = new GeoPoint(39483760, -87325929);
        mapView.getController().setCenter(center);
        mapView.getController().zoomToSpan(6241, 13894);
        
        myLocation = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(myLocation);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
        //Start the location services
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
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
        
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        
        //enable the location overlay
        myLocation.enableCompass();
        myLocation.enableMyLocation();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	locationManager.removeUpdates(locationListener);
    	
        //disable the location overlay
        myLocation.disableCompass();
        myLocation.disableMyLocation();
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	menu.setGroupVisible(R.id.beta_channel, betaManager.hasBetaManager());
    	menu.setGroupVisible(R.id.location_items, myLocation.getMyLocation() != null);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle item selection
        switch (item.getItemId()) {
        case R.id.beta_manager:
            betaManager.launchBetaActivity(BetaManagerManager.ACTION_SHOW_BETA_MANAGER);
            return true;
        case R.id.location:
        	mapView.getController().animateTo(myLocation.getMyLocation());
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