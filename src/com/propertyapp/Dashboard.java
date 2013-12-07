package com.propertyapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Dashboard extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
//		the toast code to help the user know what to do in the page
		Context context = getApplicationContext();
		CharSequence text = "Please use the menu to add Property";		
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.BOTTOM, 0, duration);
		toast.show();
	
		}


	public void onClick(View v) {
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboardmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.search:
	        	Intent i = new Intent(getApplicationContext(), Dashboard.class);
				startActivity(i);
	            return true;
	        case R.id.addland:
	        	Intent l = new Intent(getApplicationContext(), Land.class);
				startActivity(l);
	            return true;
	        case R.id.addresidential:
	        	Intent r = new Intent(getApplicationContext(), Residential.class);
				startActivity(r);
	            return true;
	        case R.id.addcommercial:
	        	Intent c = new Intent(getApplicationContext(), Commercial.class);
				startActivity(c);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}
