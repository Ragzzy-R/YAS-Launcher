package com.ragzzyr.yaslauncher.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.jakkarrl.jakkarrllauncher.R;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class IconAdapter extends BaseAdapter implements OnTouchListener{
	private Context m_context;
	AsyncTask<String,Integer,ArrayList<AppInfo>> task;
	ArrayList<Drawable> m_icons = new ArrayList<Drawable>();
	ArrayList<AppInfo>  apps;
	public IconAdapter(Context c) {
		m_context  = c;
		task = new getApplist().execute("");
		apps = null;
		try {
			apps = task.get();
			//Toast.makeText(getContext(), apps.toString(), Toast.LENGTH_LONG).show();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DisplayMetrics metrics = m_context.getResources().getDisplayMetrics();
		for(int i=0; i < apps.size();i++) {
			m_icons.add(apps.get(i).icon);
		}
		//Toast.makeText(m_context, m_icons.toString(), Toast.LENGTH_LONG).show();
			
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return m_icons.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return m_icons.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = null;
		TextView appName = null;
		LinearLayout iconView;
		final int pos = position;
        //Get the current alert object
         
        //Inflate the view
        if(convertView==null)
        {
            iconView = new LinearLayout(m_context);
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)m_context.getSystemService(inflater);
            vi.inflate(R.layout.icon_details,iconView, true);
        	imageView = (ImageView)iconView.findViewById(R.id.imageView1);
        	appName = (TextView)iconView.findViewById(R.id.textView1);
				
		}
		else {
			iconView = (LinearLayout)convertView;
			imageView = (ImageView)iconView.findViewById(R.id.imageView1);
        	appName = (TextView)iconView.findViewById(R.id.textView1);
			
		}
		iconView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(m_context, v.toString(), Toast.LENGTH_LONG).show();
				Intent launchIntent = m_context.getPackageManager().getLaunchIntentForPackage(apps.get(pos).pName);
				m_context.startActivity(launchIntent);
			}
			
		});
		imageView.setImageDrawable(m_icons.get(position));
		appName.setText(apps.get(position).appName);
		return iconView;
	}
	
	

	class getApplist extends AsyncTask<String, Integer, ArrayList<AppInfo>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//Toast.makeText(getContext(), "Fetching Apps Please Wait", Toast.LENGTH_SHORT).show();
		}
		@Override
		protected ArrayList<AppInfo> doInBackground(String... params) {
			List<PackageInfo> apps = m_context.getPackageManager().getInstalledPackages(0);
			
			ArrayList<AppInfo> res = new ArrayList<AppInfo>();
			for(int i=0;i<apps.size();i++) {
				PackageInfo p =  apps.get(i);
				
				if((m_context.getPackageManager().getLaunchIntentForPackage(p.packageName)==null)) {
					continue;
				}
				AppInfo appinfo = new AppInfo();
				appinfo.appName = p.applicationInfo.loadLabel(m_context.getPackageManager()).toString();
				appinfo.pName=p.packageName;
				appinfo.versionName =p.versionName;
				appinfo.versionCode = p.versionCode;
				appinfo.icon = p.applicationInfo.loadIcon(m_context.getPackageManager());
				res.add(appinfo);
			}
			Collections.sort(res);
		    return res;
		}
		
		@Override
		protected void onPostExecute(ArrayList<AppInfo> result) {
			//Toast.makeText(m_context,"Apps Fetched", Toast.LENGTH_SHORT).show();
		}
		
	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch(event.getAction()){

	    case(MotionEvent.ACTION_DOWN):
	        Toast.makeText(m_context, v.toString(), Toast.LENGTH_LONG).show();
	    break;

	    case(MotionEvent.ACTION_MOVE):
	        //the user is moving with his finger down
	    break;

	    case(MotionEvent.ACTION_UP):
	        //the users finger is off the screen
	    break;

	    }
		
		return true;
	}

}
