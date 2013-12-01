package com.propertyapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	
    
    EditText firstname, secondname,idnumber,password,occupation,address,emaill,phonenumber,confirmpass;
    Spinner gender;
	Button send;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		//connect the variables to the fields
		send=(Button)findViewById(R.id.submit);
		firstname = (EditText)findViewById(R.id.fname);
		secondname = (EditText)findViewById(R.id.sname);
		idnumber = (EditText)findViewById(R.id.idno);
		password = (EditText)findViewById(R.id.password);
		occupation = (EditText)findViewById(R.id.occupation);
		address = (EditText)findViewById(R.id.address);
		emaill = (EditText)findViewById(R.id.email);
		phonenumber = (EditText)findViewById(R.id.telephone);
		gender =(Spinner)findViewById(R.id.spinner1);
		confirmpass=(EditText)findViewById(R.id.passwordcon);
		
		send.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				String p1 = password.getText().toString();
				String p2 = confirmpass.getText().toString();
				if (  (!firstname.getText().toString().equals("")) && ( !secondname.getText().toString().equals(""))&& ( !idnumber.getText().toString().equals("")) && ( !password.getText().toString().equals("")) && ( !occupation.getText().toString().equals("")) && ( !address.getText().toString().equals("")) && ( !emaill.getText().toString().equals("")) && ( !phonenumber.getText().toString().equals(""))&& ( !confirmpass.getText().toString().equals("")) )
	                {
					 if(p1.matches(p2)){
						 /*NetAsync(v); */
					 }
					 else{
						 Toast.makeText(getApplicationContext(),
		                            "The passwords do not match check again", Toast.LENGTH_SHORT).show();  
					 }
	               }
				else{
				Toast.makeText(getApplicationContext(),
	                           "Please fill in all the fields", Toast.LENGTH_SHORT).show();
				}
				
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
