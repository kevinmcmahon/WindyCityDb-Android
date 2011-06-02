package org.windycitydb.service;

import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class ConferenceDataService extends Service {
	private static final String CLASSTAG = ConferenceDataService.class.getSimpleName();
	private DownloaderTask downloader;
	
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 
    private class DownloaderTask extends AsyncTask<URL, Void, Boolean> {
 
        private static final String DEBUG_TAG = "ConferenceDataService$DownloaderTask";
 
        @Override
        protected Boolean doInBackground(URL... params) {
        	boolean succeeded = false;
            URL downloadPath = params[0];
         
            if (downloadPath != null) {
                succeeded = xmlParse(downloadPath);
            }
            return succeeded;
        }
 
        private boolean xmlParse(URL downloadPath) {
        	return true;
        }
 
    }
}
