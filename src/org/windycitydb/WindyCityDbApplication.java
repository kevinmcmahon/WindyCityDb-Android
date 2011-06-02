package org.windycitydb;

import org.windycitydb.model.Location;
import org.windycitydb.model.Session;
import org.windycitydb.model.Sponsor;
import android.app.Application;

public class WindyCityDbApplication extends Application {
	
	private Session session;
	private Sponsor sponsor;
	private Location location;
	
	public WindyCityDbApplication() {
		super();
	}
	
	public Session getCurrentSession() {
		return session;
	}
	
	public void setCurrentSession(Session currentSession) {
		this.session = currentSession;
	}

	public void setCurrentSponsor(Sponsor currentSponsor) {
		this.sponsor = currentSponsor;	
	}
	
	public Sponsor getCurrentSponsor(){
		return this.sponsor;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.location = currentLocation;
	}
	public Location getCurrentLocation() {
		return this.location;
	}
}
