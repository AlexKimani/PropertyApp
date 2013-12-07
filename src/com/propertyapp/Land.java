package com.propertyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Land extends Activity implements OnClickListener{
	Button send;
	EditText size,place,titl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.land);
		send = (Button)findViewById(R.id.submit);
		send.setOnClickListener(this);
		size = (EditText)findViewById(R.id.pukubwa);
		place = (EditText)findViewById(R.id.plocation);
		titl = (EditText)findViewById(R.id.ltitle);
		}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.submit:
			 if (  ( !place.getText().toString().equals("")) && ( !size.getText().toString().equals("")) && ( !titl.getText().toString().equals("")))
             {
				 /*new login().execute();*/
			          		         
             }
             else
             {
                 Toast.makeText(getApplicationContext(),
                         "Please fill in all fields", Toast.LENGTH_SHORT).show();
             }	
			break;		
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.property, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.addmore:
	        	Intent i = new Intent(getApplicationContext(), Land.class);
				startActivity(i);
	            return true;
	        case R.id.reset:
	        	size.setText("");
	        	place.setText("");
	        	titl.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}
