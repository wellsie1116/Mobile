package edu.rosehulman.android.directory;

import java.util.List;
import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class PointsOfInterestOverlay extends ItemizedOverlay<OverlayItem> {
	
	private List<OverlayItem> overlays = new ArrayList<OverlayItem>();

	public PointsOfInterestOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	@Override
	protected OverlayItem createItem(int i) {
		return this.overlays.get(i);
	}

	@Override
	public int size() {
		return this.overlays.size();
	}

}
