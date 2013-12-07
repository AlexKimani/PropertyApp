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

public class Commercial extends Activity implements OnClickListener{
     Button send;
     EditText name,size,location,no;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commercial);
		
		send = (Button)findViewById(R.id.csubmit);
		send.setOnClickListener(this);
		name = (EditText)findViewById(R.id.pname);
		size = (EditText)findViewById(R.id.psize);
		location = (EditText)findViewById(R.id.plocation);
		no = (EditText)findViewById(R.id.bnumber);
		}

	public void onClick(View v){
		switch(v.getId()){
		case R.id.csubmit:
			 if (  ( !name.getText().toString().equals("")) && ( !size.getText().toString().equals("")) && ( !location.getText().toString().equals(""))&& ( !no.getText().toString().equals("")))
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
	        	Intent i = new Intent(getApplicationContext(), Commercial.class);
				startActivity(i);
	            return true;
	        case R.id.reset:
	        	name.setText("");
	        	size.setText("");
	        	location.setText("");
	        	no.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	

}
