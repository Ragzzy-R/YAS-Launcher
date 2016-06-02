package com.ragzzyr.yaslauncher.views;

import android.graphics.drawable.Drawable;

public class AppInfo implements Comparable<AppInfo> {

	String appName = "";
	String pName ="";
	String versionName = "";
	int versionCode = 0;
	Drawable icon;
	
	@Override
	public String toString() {
		return appName;
	}

	@Override
	public int compareTo(AppInfo another) {
		/*if(this.appName.equalsIgnoreCase(another.appName)) {
			return 1;
		}
		else{
			return 0;
		}*/
		return (this.appName.compareToIgnoreCase(another.appName));
	}
}
