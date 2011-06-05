package org.windycitydb;

import org.windycitydb.Constants;
import org.windycitydb.MainActivity;
import org.windycitydb.MapTab;
import org.windycitydb.R;
import org.windycitydb.SessionList;
import org.windycitydb.SponsorsList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {
	
	private static final String CLASSTAG = MainActivity.class.getSimpleName();
	private TabHost tabHost = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(Constants.LOGTAG, MainActivity.CLASSTAG + " onCreate");
        
        setContentView(R.layout.main);
    
        buildTabs();
    }
    
    private void buildTabs() {
        Resources res = getResources();
        tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        intent = new Intent().setClass(this, SessionList.class);

        spec = tabHost.newTabSpec("sessions").setIndicator("Sessions",
                          res.getDrawable(R.drawable.ic_tab_clock))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, MapTab.class);
        spec = tabHost.newTabSpec("maps").setIndicator("Maps",
                          res.getDrawable(R.drawable.ic_tab_globe))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SponsorsList.class);
        spec = tabHost.newTabSpec("sponsors").setIndicator("Sponsors",
                          res.getDrawable(R.drawable.ic_tab_seal))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);
    }
    
    private void setupTab(final String tag, Intent intent) {
    	Log.v(Constants.LOGTAG, MainActivity.CLASSTAG + " setupTab : " + tag);
    	View tabview = createTabView(tabHost.getContext(), tag);
    	
        TabSpec setContent = tabHost.newTabSpec(tag)
        							.setIndicator(tabview)
        							.setContent(intent);
    	tabHost.addTab(setContent);
    }

    private static View createTabView(final Context context, final String text) {
    	Log.v(Constants.LOGTAG, MainActivity.CLASSTAG + " createTabView with text : " + text);
    	View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
    	TextView tv = (TextView) view.findViewById(R.id.tabsText);
    	tv.setText(text);
    	return view;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

      menu.add(0,Constants.MENU_ID_WEBSITE, Constants.MENU_ORDER_WEBSITE, "Official Website").setIcon(R.drawable.ic_menu_laptop);
      menu.add(0,Constants.MENU_ID_ABOUT, Constants.MENU_ORDER_ABOUT, "About").setIcon(R.drawable.ic_menu_gear);
      
      return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

		switch(item.getItemId())
	    {
			case Constants.MENU_ID_WEBSITE:
				Intent web = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.official_website_url)));
				startActivity(web);
			break;
			case Constants.MENU_ID_ABOUT:
				Intent about = new Intent(Constants.INTENT_ACTION_VIEW_ABOUT);
				startActivity(about);
			break;
	    }
		return super.onOptionsItemSelected(item);

    }
    
}