package org.windycitydb;

import android.view.Menu;

public class Constants {

    public static final String INTENT_ACTION_VIEW_SESSION_DETAIL = "org.windycitydb.VIEW_SESSION_DETAIL";
    public static final String INTENT_ACTION_VIEW_SESSION_LIST = "org.windycitydb.VIEW_SESSION_LIST";

    public static final String INTENT_ACTION_VIEW_SPONSOR_DETAIL = "org.windycitydb.VIEW_SPONSOR_DETAIL";
    public static final String INTENT_ACTION_VIEW_SPONSOR_LIST = "org.windycitydb.VIEW_SPONSOR_LIST";
    
    public static final String INTENT_ACTION_VIEW_LOCATION_DETAIL = "org.windycitydb.VIEW_LOCATION_DETAIL";
    
    public static final String INTENT_ACTION_VIEW_ABOUT = "org.windycitydb.VIEW_ABOUT_DETAIL";
    
    public static final String LOGTAG = "WindyCityDb";

    // Extras 
    public static final String STARTFROM_EXTRA = "org.windycitydb.StartFrom";
    public static final String FLOOR_PLAN_URL_EXTRA = "org.windycitydb.FloorPlanUrl";
    public static final String LOCATION_LAT_EXTRA = "org.windycitydb.LocationLat";
    public static final String LOCATION_LONG_EXTRA = "org.windycitydb.LocationLong";
	public static final String LOCATION_NAME_EXTRA = "org.windycitydb.LocationName";
	public static final String LOCATION_VENUE_LONG_EXTRA = "org.windycitydb.LocationVenueLong";
	public static final String LOCATION_VENUE_SHORT_EXTRA = "org.windycitydb.LocationVenueShort";
	public static final String LOCATION_ADDRESS_EXTRA = "org.windycitydb.LocationAddress";
	public static final String LOCATION_EXTRA = "org.windycitydb.Location";
	
	public static final int MENU_ID_WEBSITE = 0;
	public static final int MENU_ID_ABOUT = 1;
	public static final int MENU_ORDER_WEBSITE = Menu.FIRST;
	public static final int MENU_ORDER_ABOUT = Menu.FIRST+1;
}