package com.ragzzyr.yaslauncher.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.jakkarrl.jakkarrllauncher.R;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AppGrid extends LinearLayout {

	GridView appGrid;
	ImageView icon;
	ArrayList<Drawable> icons = new ArrayList<Drawable>();
	AsyncTask<String,Integer,ArrayList<AppInfo>> task;
	public AppGrid(Context context) {
		super(context);
		initializeViews(context);
		
	}
	public AppGrid(Context context,AttributeSet attrs){
		super(context,attrs);
		initializeViews(context);
	}
	
	public AppGrid(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
		initializeViews(context);
	}

	private void initializeViews(Context context) {
		LayoutInflater inflater= (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.app_list, this);
		appGrid = (GridView)findViewById(R.id.appGrid);
		appGrid.setAdapter(new IconAdapter(context));
	}
	
	
}
