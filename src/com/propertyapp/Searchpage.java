package com.propertyapp;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import dbfiles.DatabaseHandler;
import dbfiles.UserFunctions;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
	EditText idno,pword;
	TextView changepass,register;
	
	/**
     * Called when the activity is first created.
     */
    private static String KEY_SUCCESS = "success";
    private static final String KEY_FIRSTNAME = "user_fname";
    private static final String KEY_SECONDNAME = "user_sname";
    private static final String KEY_IDNO = "user_idno";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_OCCUPATION = "user_occupation";
    private static final String KEY_GENDER = "user_gender";
    private static final String KEY_ADDRESS = "user_address";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PHONENUMBER = "user_phonenumber";
    private static final String KEY_CREATED_AT = "created_at";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
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
		pword= (EditText)findViewById(R.id.password);
		changepass=(TextView)findViewById(R.id.forgotpass);
		register= (TextView)findViewById(R.id.register);
		
// giving the login button work to do after it is clicked
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// write the code to validate and fetch the login credentials from the database
				
				  if (  ( !idno.getText().toString().equals("")) && ( !pword.getText().toString().equals("")) )
	                {
	                  NetAsync(v);
	                }
	                else if ( ( !idno.getText().toString().equals("")) )
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Please fill in the Password field", Toast.LENGTH_SHORT).show();
	                }
	                else if ( ( !pword.getText().toString().equals("")) )
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Please fill in the ID Number field", Toast.LENGTH_SHORT).show();
	                }
	                else
	                {
	                    Toast.makeText(getApplicationContext(),
	                            "Please fill in the ID Number and Password fields", Toast.LENGTH_SHORT).show();
	                }				
				/*//the code below is to be deleted
				Intent i = new Intent(getApplicationContext(), Dashboard.class);
				startActivity(i);*/
			}
			
		});
//Add the password update link on the app and give it functionality	
		changepass.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// add the intent that the link should redirect to
				Intent i = new Intent(getApplicationContext(), Updatepass.class);
				startActivity(i);
			}
			
		});

//give the registration link functionality
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// add the intent that the link should redirect to
				Intent i = new Intent(getApplicationContext(), Register.class);
				startActivity(i);
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
	//call the netcheck function
	public void NetAsync(View v){
		new ProcessLogin().execute();
	}
	 
	  /**
	     * Async Task to get and send data to My Sql database through JSON respone.
	     **/
	    private class ProcessLogin extends AsyncTask {
	 
	        private ProgressDialog pDialog;
	 
	        String id,passcode;
	 
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	 
	            idno = (EditText) findViewById(R.id.userid);
	            pword= (EditText)findViewById(R.id.password);
	            id = idno.getText().toString();
	            passcode = pword.getText().toString();
	            pDialog = new ProgressDialog(Searchpage.this);
	            pDialog.setTitle("Contacting Servers");
	            pDialog.setMessage("Logging in ...");
	            pDialog.setIndeterminate(false);
	            pDialog.setCancelable(true);
	            pDialog.show();
	        }
	 
	        @Override
			protected Object doInBackground(Object... params) {
	        	
	        	 UserFunctions userFunction = new UserFunctions();
	             JSONObject json = userFunction.loginUser(id, passcode);
	             return json;
				
			}
	        
	        protected void onPostExecute(JSONObject json) {
	            try {
	               if (json.getString(KEY_SUCCESS) != null) {
	 
	                    String res = json.getString(KEY_SUCCESS);
	 
	                    if(Integer.parseInt(res) == 1){
	                        pDialog.setMessage("Loading User Space");
	                        pDialog.setTitle("Getting Data");
	                        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
	                        JSONObject json_user = json.getJSONObject("user");
	                        /**
	                         * Clear all previous data in SQlite database.
	                         **/
	                        UserFunctions logout = new UserFunctions();
	                        logout.logoutUser(getApplicationContext());
	                        db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_SECONDNAME),json_user.getString(KEY_IDNO),json_user.getString(KEY_PASSWORD),json_user.getString(KEY_OCCUPATION),json_user.getString(KEY_GENDER),json_user.getString(KEY_ADDRESS),json_user.getString(KEY_EMAIL),json_user.getString(KEY_PHONENUMBER),json_user.getString(KEY_CREATED_AT));
	                       /**
	                        *If JSON array details are stored in SQlite it launches the User Panel.
	                        **/
	                        Intent upanel = new Intent(getApplicationContext(), Dashboard.class);
	                        upanel.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                        pDialog.dismiss();
	                        startActivity(upanel);
	                        /**
	                         * Close Login Screen
	                         **/
	                        finish();
	                    }else{
	 
	                        pDialog.dismiss();
	                        Toast.makeText(getApplicationContext(),
		                            "Invalid login. Wrong ID Number or Password", Toast.LENGTH_SHORT).show();
	                    }
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	       }
	    }
	    
	}
	 


