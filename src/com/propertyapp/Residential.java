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

public class Residential extends Activity implements OnClickListener{
    Button tuma;
    EditText jina,ukubwa,mahali,rumnos;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.residential);
		
		tuma = (Button)findViewById(R.id.rsubmit);
		tuma.setOnClickListener(this);
		jina = (EditText)findViewById(R.id.pname1);
		ukubwa = (EditText)findViewById(R.id.psize1);
		mahali = (EditText)findViewById(R.id.plocation1);
		rumnos = (EditText)findViewById(R.id.nor);
		}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.rsubmit:
			 if (  ( !jina.getText().toString().equals("")) && ( !ukubwa.getText().toString().equals("")) && ( !mahali.getText().toString().equals(""))&& ( !rumnos.getText().toString().equals("")))
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
	        	jina.setText("");
	        	ukubwa.setText("");
	        	mahali.setText("");
	        	rumnos.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
