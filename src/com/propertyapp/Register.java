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
	 /**
     *  JSON Response node names.
     **/
 
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_FIRSTNAME = "user_fname";
    private static String KEY_SECONDNAME = "user_sname";
    private static String KEY_IDNO = "user_idno";
    private static String KEY_PASSWORD = "user_password";
    private static String KEY_OCCUPATION= "user_occupation";
    private static String KEY_GENDER = "user_gender";
    private static String KEY_ADDRESS = "user_address";
    private static String KEY_EMAIL = "user_email";
    private static String KEY_PHONENUMBER = "user_phonenumber";
    private static String KEY_CREATED_AT = "created_at";
    private static String KEY_ERROR = "error";
    
    EditText firstname, secondname,idnumber,password,occupation,address,email,phonenumber,confirmpass;
    Spinner gender;
	Button send;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
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
		email = (EditText)findViewById(R.id.email);
		phonenumber = (EditText)findViewById(R.id.telephone);
		gender =(Spinner)findViewById(R.id.spinner1);
		confirmpass=(EditText)findViewById(R.id.passwordcon);
		
		send.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				 if (  ( !firstname.getText().toString().equals("")) && ( !secondname.getText().toString().equals(""))&& ( !idnumber.getText().toString().equals("")) && ( !password.getText().toString().equals("")) && ( !occupation.getText().toString().equals("")) && ( !address.getText().toString().equals("")) && ( !email.getText().toString().equals("")) && ( !phonenumber.getText().toString().equals("")) && ( !gender.getSelectedItem().toString().equals("...."))&& ( !confirmpass.getText().toString().equals("")) )
	                {
					 if((password.getText().toString())!= (confirmpass.getText().toString())){
						 Toast.makeText(getApplicationContext(),
		                            "The passwords do not match check again", Toast.LENGTH_SHORT).show(); 
					 }
					 else{
						 
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
