package com.propertyapp;

import land.Results;
import land.Results2;
import land.Results3;

import residential.Resultsr;
import residential.Resultsr2;
import residential.Resultsr3;

import commercial.Resultsc;
import commercial.Resultsc2;
import commercial.Resultsc3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Dashboard extends Activity implements OnClickListener{
	Button send;
	Spinner type, purp;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		send = (Button)findViewById(R.id.button1);
		send.setOnClickListener(this);	
		 type =(Spinner)findViewById(R.id.spinner1);
	        // Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.propertysearch, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			type.setAdapter(adapter);
			type.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> parent, View arg1,int arg2, long arg3) {
					parent.getItemAtPosition(arg2);	
					return;
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
			 purp =(Spinner)findViewById(R.id.purpose);
		        // Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.purpose, android.R.layout.simple_spinner_item);
				// Specify the layout to use when the list of choices appears
				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
			 purp.setAdapter(adapter2);
			purp.setOnItemSelectedListener(new OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> parent, View arg1,int arg2, long arg3) {
						parent.getItemAtPosition(arg2);	
						return;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});
		}


	public void onClick(View v) {
		if(type.getSelectedItem().equals("Land")){
			if(purp.getSelectedItem().equals("For Rent")){
			Intent i = new Intent(Dashboard.this, Results.class);
			startActivity(i);
			}
			else if(purp.getSelectedItem().equals("For Sale")){
				Intent e = new Intent(Dashboard.this, Results2.class);
				startActivity(e);	
			}
			else
			{
				Intent w= new Intent(Dashboard.this, Results3.class);
				startActivity(w);	
			}
		}
		else if(type.getSelectedItem().equals("Commercial House")){
			if(purp.getSelectedItem().equals("For Rent")){
				Intent i = new Intent(Dashboard.this, Resultsc.class);
				startActivity(i);
				}
				else if(purp.getSelectedItem().equals("For Sale")){
					Intent e = new Intent(Dashboard.this, Resultsc2.class);
					startActivity(e);	
				}
				else
				{
					Intent w= new Intent(Dashboard.this, Resultsc3.class);
					startActivity(w);	
				}
		}
		else if(type.getSelectedItem().equals("Residential House")){
			if(purp.getSelectedItem().equals("For Rent")){
				Intent i = new Intent(Dashboard.this, Resultsr.class);
				startActivity(i);
				}
				else if(purp.getSelectedItem().equals("For Sale")){
					Intent e = new Intent(Dashboard.this, Resultsr2.class);
					startActivity(e);	
				}
				else
				{
					Intent w= new Intent(Dashboard.this, Resultsr3.class);
					startActivity(w);	
				}
		}
		else
		{
			Toast.makeText(Dashboard.this, "Please select property type", Toast.LENGTH_LONG).show();
		}
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
