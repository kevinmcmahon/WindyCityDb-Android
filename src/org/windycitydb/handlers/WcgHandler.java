package org.windycitydb.handlers;

import java.io.IOException;
import java.net.URLConnection;
import java.util.ArrayList;

import org.xml.sax.helpers.DefaultHandler;

public abstract class WcgHandler extends DefaultHandler {
	public abstract ArrayList<?> retrieve();
	
}