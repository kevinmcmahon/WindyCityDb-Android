package org.windycitydb;

import org.windycitydb.model.Session;
import org.windycitydb.util.DownloadImageTask;
import org.windycitydb.util.ImageHelper;
import org.windycitydb.util.Network;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SessionDetail extends Activity {
	private static final String CLASSTAG = SessionDetail.class.getSimpleName();
	private ImageView imgView = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG," " + SessionDetail.CLASSTAG + " onCreate");
        
        WindyCityDbApplication app = (WindyCityDbApplication) getApplication();
        final Session session = app.getCurrentSession();
        
        setContentView(R.layout.session_detail);
        
        TextView titleTextView = (TextView) findViewById(R.id.title);
        TextView presenterTextView = (TextView) findViewById(R.id.presenter);
        TextView descriptionTextView = (TextView) findViewById(R.id.description);
        TextView speakerBioTextView = (TextView) findViewById(R.id.bio);
        imgView = (ImageView) findViewById(R.id.speaker_icon);
        
        titleTextView.setText(session.title);
        presenterTextView.setText(session.speaker.name);
        if(session.speaker.company != null && session.speaker.company != "")
        	presenterTextView.append(", "+session.speaker.company);
        descriptionTextView.setText(session.description);
        
        if(session.speaker.bio != null && session.speaker.bio != "") {
        	speakerBioTextView.setText(session.speaker.bio);
        }
        else {
        	TextView v = (TextView)findViewById(R.id.bio_header);
        	v.setVisibility(View.INVISIBLE);
        	speakerBioTextView.setVisibility(View.INVISIBLE);
        }
        
        if(Network.isNetworkAvailable(this) && session.speaker.headshot != null && session.speaker.headshot != "") {
        	new DownloadImageTask(imgView, true).execute(session.speaker.headshot);
        }
        
        Button websiteButton = (Button) findViewById(R.id.session_website_button);
        if(session.links.speakerWebsite != null && session.links.speakerWebsite != "") {
        	websiteButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(session.links.speakerWebsite));
					startActivity(i);
				}
			});
        }
        else {
        	websiteButton.setVisibility(View.INVISIBLE);
        }
        
    	Button twitterButton = (Button) findViewById(R.id.session_twitter_button);
    	if(session.links.speakerTwitter != null && session.links.speakerTwitter != "") {
    		twitterButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(session.links.speakerTwitter));
					startActivity(i);
				}
			});
        }
        else {
        	twitterButton.setVisibility(View.INVISIBLE);
        }

    }
}