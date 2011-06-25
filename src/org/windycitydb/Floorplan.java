package org.windycitydb;

import org.windycitydb.util.Network;

import android.app.Activity;
import android.graphics.Bitmap;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Floorplan extends Activity {
	private static final String CLASSTAG = Floorplan.class.getSimpleName();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, Floorplan.CLASSTAG + " onCreate");
        
        setContentView(R.layout.floorplan);
        
        TextView textView = (TextView) findViewById(R.id.floorplan_text);
        ImageView imageView = (ImageView) findViewById(R.id.floorplan_image);
        String url = getIntent().getStringExtra(Constants.FLOOR_PLAN_URL_EXTRA);
        
        if(url != null && url != "") {
	        Bitmap bitmap = Network.downloadBitmap(url);
	        imageView.setImageBitmap(bitmap);
	        imageView.setVisibility(View.VISIBLE);
	        textView.setVisibility(View.INVISIBLE);
        } else {
        	imageView.setVisibility(View.INVISIBLE);
        	textView.setVisibility(View.VISIBLE);
        	textView.setGravity(Gravity.CENTER);
        }
	}
}