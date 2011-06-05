package org.windycitydb;

import org.windycitydb.model.Location;
import org.windycitydb.util.DownloadImageTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationDetail extends Activity {
	
	private static final String CLASSTAG = LocationDetail.class.getSimpleName();
	private ImageView photoView;
	private Location location;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, LocationDetail.CLASSTAG + " onCreate");
        
        setContentView(R.layout.location_detail);
        location = (Location) getIntent().getSerializableExtra(Constants.LOCATION_EXTRA);
        
        TextView venueView = (TextView) findViewById(R.id.venue_location_detail);
        venueView.setText(location.venueLong);
        
        TextView addressView = (TextView) findViewById(R.id.address_location_detail);
        String addressText = formatAddressText(location.address);
        addressView.setText(addressText);
        
        photoView = (ImageView) findViewById(R.id.picture_location_detail);
        
        if(location.photo != null && !location.photo.trim().equals("")) {
        	Log.v(Constants.LOGTAG, LocationDetail.CLASSTAG + " photo text: "+ location.photo);
        	new DownloadImageTask(photoView).execute(location.photo); 
        }
        
        Button directionsButton = (Button) findViewById(R.id.directions_button_location_detail);
        directionsButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, 
						Uri.parse("http://maps.google.com/maps?daddr="+ location.latitude + "," + location.longitude));
						startActivity(intent);
			}
		});
	}
	
	private String formatAddressText(String address) {
        String formatted = "";
        
        String[] parts = address.split(",");
        if(parts.length == 3) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(parts[0].trim() + "\n");
	        sb.append(parts[1].trim() +"," + parts[2]+ "\n");
	        formatted = sb.toString();
        }
        else {
        	formatted = address;
        }
        return formatted;
	}
}