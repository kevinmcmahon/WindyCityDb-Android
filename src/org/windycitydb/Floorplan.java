package org.windycitydb;

import org.windycitydb.util.Network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Floorplan extends Activity {
	private static final String CLASSTAG = Floorplan.class.getSimpleName();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, Floorplan.CLASSTAG + " onCreate");
        
        setContentView(R.layout.floorplan);
        
        ImageView imageView = (ImageView) findViewById(R.id.floorplan_image);
        String url = getIntent().getStringExtra(Constants.FLOOR_PLAN_URL_EXTRA);
        
        if(url != null && url != "") {
	        Bitmap bitmap = Network.downloadBitmap(url);
	        imageView.setImageBitmap(bitmap);
        }
	}
}