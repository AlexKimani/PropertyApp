package com.propertyapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Searchpage extends Activity implements OnClickListener{
	Button login;
	EditText idno,password;
	TextView changepass,register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchpagexml);
//		the toast code to help the user know what to do in the page
		Context context = getApplicationContext();
		CharSequence text = "Please login to continue...";		
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.BOTTOM, 0, duration);
		toast.show();
		
// link the variables to the items on the xml page
		login = (Button)findViewById(R.id.login);
		idno = (EditText)findViewById(R.id.userid);
		password= (EditText)findViewById(R.id.password);
		changepass=(TextView)findViewById(R.id.forgotpass);
		register= (TextView)findViewById(R.id.register);
		
// giving the login button work to do after it is clicked
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
			
		});
//Add the password update link on the app and give it functionality	
		changepass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// add the intent that the link should redirect to
				
			}
			
		});

//give the registration link functionality
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// add the intent that the link should redirect to
				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
