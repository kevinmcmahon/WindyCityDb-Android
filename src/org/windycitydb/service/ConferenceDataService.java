package org.windycitydb.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.windycitydb.R;
import org.windycitydb.util.Network;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

public class ConferenceDataService extends android.app.Service {
	
	private static final String CLASSTAG = ConferenceDataService.class.getSimpleName();
	private DownloaderTask downloader;
	
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(org.windycitydb.Constants.LOGTAG, ConferenceDataService.CLASSTAG + " Received start id " + startId + ": " + intent);
        
        if(Network.isNetworkAvailable(getApplicationContext())) {
        	String baseUrl = getResources().getString(R.string.data_base_url);
        	String protocol = getResources().getString(R.string.data_protocol);
	        try {
	            URL[] urls = new URL[] {
	            	new URL(protocol, baseUrl,"/" + getResources().getString(R.string.data_filename_sessions)),
	            	new URL(protocol, baseUrl,"/" + getResources().getString(R.string.data_filename_locations)),
	            	new URL(protocol, baseUrl,"/" + getResources().getString(R.string.data_filename_sponsors)),
	            };
	            downloader = new DownloaderTask();
	            downloader.execute(urls);
	            
	        } catch (MalformedURLException e) {
	            Log.e(org.windycitydb.Constants.LOGTAG, ConferenceDataService.CLASSTAG + " Bad URL", e);
	        }
        } else {
        	Log.i(org.windycitydb.Constants.LOGTAG,"Network not available so xml files not updated");
        }

        return Service.START_FLAG_REDELIVERY;
    }
   
    private class DownloaderTask extends AsyncTask<URL, Void, Boolean> {
    	 
        private static final String DEBUG_TAG = "ConferenceDataService$DownloaderTask";
        
        @Override
        protected Boolean doInBackground(URL... params) {
        	boolean succeeded = true;
        
        	for(URL url : params) {
                if (url != null) {
                    succeeded &= xmlDownload(url);
                }
        	}
        	
            return succeeded;
        }

        private boolean xmlDownload(URL downloadPath) {
        	InputStream in;
        	String file = "";
        	
        	try {
        		URLConnection conn = downloadPath.openConnection();
            	conn.setConnectTimeout(10000);
            	conn.setReadTimeout(10000);
            	in = conn.getInputStream();
    			file = org.apache.commons.io.IOUtils.toString(in);
    		} catch (IOException e) {
    			Log.e(org.windycitydb.Constants.LOGTAG,"IOException: ", e);
    			return false;
    		}
        	
        	File xmlFile = new File(getCacheDir(),downloadPath.getFile());
        	
        	try {
        		if(file != null && file != "") {
	    			FileOutputStream fos = new FileOutputStream(xmlFile);
	    			fos.write(file.getBytes());
	    			fos.close();
        		}
    		} catch (FileNotFoundException e) {
    			Log.e(org.windycitydb.Constants.LOGTAG,"Output file not found.", e);
    			return false;
    		} catch (IOException e) {
    			Log.e(org.windycitydb.Constants.LOGTAG,"IOException: ", e);
    			return false;
    		}
    		
        	return true;
        }
    }
}