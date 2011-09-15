package edu.rosehulman.android.directory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class BuildingOverlay extends Overlay implements Overlay.Snappable {
	
	private Context context;
	private GeoPoint topLeft;
	private GeoPoint bottomRight;
	
	public BuildingOverlay(Context context, GeoPoint topLeft) {
		this.topLeft = topLeft;
		this.bottomRight = new GeoPoint(this.topLeft.getLatitudeE6()-1000, this.topLeft.getLongitudeE6()+1000);
		this.context = context;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		if (shadow) return;
		
		//Drawable icon = context.getResources().getDrawable(R.drawable.icon);
		Bitmap icon = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.icon);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		Point pt1 = mapView.getProjection().toPixels(topLeft, null);
		Point pt2 = mapView.getProjection().toPixels(bottomRight, null);
		canvas.drawRect(new Rect(pt1.x, pt1.y, pt2.x, pt2.y), paint);
		
		paint.setStrokeWidth(10);
		canvas.drawPoint(pt1.x, pt1.y, paint);
		
		paint.setColor(Color.RED);
		canvas.drawPoint(pt2.x, pt2.y, paint);
		
		canvas.save();
		canvas.translate(pt1.x, pt1.y);
		canvas.scale((pt2.x-pt1.x)/(float)icon.getWidth(), (pt2.y-pt1.y)/(float)icon.getHeight());
		canvas.drawBitmap(icon, 0, 0, null);
		//canvas.translate(pt.x, pt.y);
		canvas.restore();
	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView) {
		Point pt = mapView.getProjection().toPixels(p, null);
		Point snapPoint = new Point();
		boolean snap = onSnapToItem(pt.x, pt.y, snapPoint, mapView);
		
		if (snap) {
			mapView.getController().animateTo(mapView.getProjection().fromPixels(snapPoint.x, snapPoint.y));
		}
		
		return snap;
	}

	@Override
	public boolean onSnapToItem(int x, int y, Point snapPoint, MapView mapView) {
		Point pt1 = mapView.getProjection().toPixels(topLeft, null);
		Point pt2 = mapView.getProjection().toPixels(bottomRight, null);
		
		boolean snap = (pt1.x <= x) && (x <= pt2.x) && (pt1.y <= y) && (y <= pt2.y);
		
		snapPoint.x = (pt1.x + pt2.x) / 2;
		snapPoint.y = (pt1.y + pt2.y) / 2;
		
		return snap;
	}

}
