
package com.blstream.myguide.settings;

import java.util.HashMap;

public class Settings extends HashMap<String, String> {

	public static final String KEY_LANGUAGE = "lang_fallback";
	public static final String KEY_INNER_RADIOUS = "internal_object_radius";
	public static final String KEY_EXTER_RADIOUS = "external_object_radius";
	public static final String KEY_MAX_ZOOM = "max_zoom";
	public static final String KEY_MIN_ZOOM = "min_zoom";
	public static final String KEY_START_LAT = "start_lat";
	public static final String KEY_START_LON = "start_lon";

	private static final long serialVersionUID = 1L;

	private String mValue;
	private int mValueInt;
	private float mValueFloat;

	public String getValueAsString(String key) {
		mValue = super.get(key);
		return mValue;
	}

	public int getValueAsInt(String key) {
		mValueInt = Integer.parseInt(super.get(key));
		return mValueInt;
	}

	public float getValueAsFloat(String key) {
		mValueFloat = Float.parseFloat(super.get(key));
		return mValueFloat;
	}
}
