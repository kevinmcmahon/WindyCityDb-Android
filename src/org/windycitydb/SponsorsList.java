package org.windycitydb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.windycitydb.handlers.SessionHandler;
import org.windycitydb.handlers.SponsorHandler;
import org.windycitydb.model.Sponsor;
import org.windycitydb.model.SponsorLevel;

import com.commonsware.cwac.merge.MergeAdapter;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class SponsorsList extends ListActivity {
	private static final String CLASSTAG = SponsorsList.class.getSimpleName();
	ArrayList<SponsorLevel> sponsorLevels = null;
	
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.v(Constants.LOGTAG, " " + SponsorsList.CLASSTAG + " onCreate");
	        
	        AssetManager assetManager = getAssets();
	        InputStream stream = null;
	        String filename = getResources().getString(R.string.data_filename_sponsors);
	        try {
	        	File cachedFile = new File(getCacheDir(), "/"+ filename);
	    		
	    		if(cachedFile.exists()) {
	    			Log.i(Constants.LOGTAG,"Reading sponsors data from cached copy.");
	    			stream = new FileInputStream(cachedFile);
	    		} else {
	    			Log.i(Constants.LOGTAG,"Reading sponsors data from assets.");
	    			stream = assetManager.open(filename);
	    		}
	            
	            sponsorLevels = new XmlParser().parseSponsorResponse(stream);
	        } catch (IOException e) {
        		Log.e(Constants.LOGTAG, e.getMessage());
	        }
	        
	        LayoutInflater vi = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
	        for (SponsorLevel level : sponsorLevels)
	        {
	        	TextView header = (TextView) vi.inflate(R.layout.header, null);
	        	header.setText(level.name);
	        	header.setTextSize(21.0f);
	        	
	        	adapter.addView(header);
	        	adapter.addAdapter(new SponsorAdapter(this,R.layout.sponsor_row,level.sponsors));
	        }
	        
	        this.setListAdapter(adapter);
	   }
	   
	   MergeAdapter adapter = new MergeAdapter();
	   
		@Override
	    protected void onListItemClick(ListView l, View v, int position, long id) {
			
			Log.v(Constants.LOGTAG, SponsorsList.CLASSTAG + " onListItemClick");
			
			Sponsor sponsor = null;
			
			try {
				sponsor = (Sponsor) adapter.getItem(position);
				Log.v(Constants.LOGTAG, " " + SponsorsList.CLASSTAG + " sponsor selected : " + sponsor.name);
			}
			catch (Exception e) {
				Log.e(Constants.LOGTAG, "items from adapter blew up", e);
			}
			
			// set the current review to the Application (global state placed there)
	        WindyCityDbApplication application = (WindyCityDbApplication) getApplication();
	        application.setCurrentSponsor(sponsor);
	        
	        // startFrom page is not stored in application, for example purposes it's a simple "extra"
	        Intent intent = new Intent(Constants.INTENT_ACTION_VIEW_SPONSOR_DETAIL);
	        intent.putExtra(Constants.STARTFROM_EXTRA, getIntent().getIntExtra(Constants.STARTFROM_EXTRA, 1));
	        startActivity(intent);
	    }
}