package com.blindmatchrace.classes;

import android.os.Environment;

/**
 * The application's constants.
 *
 */
public class C {

	// Location constants.
	public static final long MIN_TIME = 4000;
	public static final float MIN_DISTANCE = 10f;

	// Map constants.
	public static final float RADIUS_BUOY = 40f;
	public static final float ZOOM_LEVEL = 17.0f;
	public static final int MAX_BUOYS = 10;

	// Users constants.
	public static final String SAILOR_PREFIX = "Sailor";
	public static final String BUOY_PREFIX = "BuoyNum";

	// Login constants.
	public static final String USER_NAME = "user_name";
	public static final String USER_PASS = "user_pass";
	public static final String EVENT_NUM = "event_num";
	public static final String PREFS_USER = "user_prefs";
	public static final String PREFS_FULL_USER_NAME = "full_user_name";

	// DB constants.
	public static final String URL_INSERT_CLIENT = "http://kcg-lab.info/map-tracking-2d/services/insertClient.php?table=clients";
	public static final String URL_CLIENTS_TABLE = "http://kcg-lab.info/map-tracking-2d/services/json-clients.php?table=clients";
	public static final String URL_HISTORY_TABLE = "http://kcg-lab.info/map-tracking-2d/services/json-clients.php?table=history";

	// Data constants.
	public static final String APP_DIR = Environment.getExternalStorageDirectory().getPath() + "/BlindMatchRace/";
}
