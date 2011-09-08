package edu.rosehulman.android.directory.tests.ui;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.google.android.maps.MapView;

import edu.rosehulman.android.directory.MainActivity;
import edu.rosehulman.android.directory.R;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MainActivityTest() {
		super("edu.rosehulman.android.directory", MainActivity.class);
	}

	private MainActivity mActivity;
	// private Instrumentation mInstrumentation;

	private MapView m_mapView;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		
		//disable looping back into the beta system
		Intent intent = new Intent();
		intent.putExtra("DisableBeta", true);
		setActivityIntent(intent); 

		mActivity = getActivity();

		m_mapView = (MapView) mActivity.findViewById(R.id.mapview);
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@UiThreadTest
	public void testMapViewState() {
		assertTrue(m_mapView.isSatellite());
	}

}
