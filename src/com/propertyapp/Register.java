package com.propertyapp;

import org.json.JSONException;
import org.json.JSONObject;

import dbfiles.DatabaseHandler;
import dbfiles.UserFunctions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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

	 private class ProcessRegister extends AsyncTask {
		 
		 /**
		  * Defining Process dialog
		  **/
		         private ProgressDialog pDialog;
		  
		         String user_fname,user_sname,user_idno,user_password,user_occupation,user_gender,user_address,user_mail,user_phonenumber;
		         @Override
		         protected void onPreExecute() {
		             super.onPreExecute();
		                user_fname = firstname.getText().toString();
		                user_sname = secondname.getText().toString();
		                user_idno = idnumber.getText().toString();
		                user_password = password.getText().toString();
		                user_occupation = occupation.getText().toString();
		                user_gender = gender.getSelectedItem().toString();
		                user_address = address.getText().toString();
		                user_phonenumber = phonenumber.getText().toString();
		                user_mail = emaill.getText().toString();
		             pDialog = new ProgressDialog(Register.this);
		             pDialog.setTitle("Contacting Servers");
		             pDialog.setMessage("Registering ...");
		             pDialog.setIndeterminate(false);
		             pDialog.setCancelable(true);
		             pDialog.show();
		         }
		  
		         protected JSONObject doInBackground(Object... args) {
		  
		         UserFunctions userFunction = new UserFunctions();
		         JSONObject json = userFunction.registerUser(user_fname,user_sname,user_idno,user_password,user_occupation,user_gender,user_address,user_mail,user_phonenumber);
		  
		             return json;
		  
		         }
		        protected void onPostExecute(JSONObject json) {
		        /**
		         * Checks for success message.
		         **/
		                 try {
		                     if (json.getString(KEY_SUCCESS) != null) {
		                    	 Toast.makeText(getApplicationContext(),
				                            "", Toast.LENGTH_SHORT).show();
		                         String res = json.getString(KEY_SUCCESS);
		  
		                         String red = json.getString(KEY_ERROR);
		  
		                         if(Integer.parseInt(res) == 1){
		                             pDialog.setTitle("Getting Data");
		                             pDialog.setMessage("Loading Info");
		  
		                             Toast.makeText(getApplicationContext(),
		 		                            "Successfully Registered", Toast.LENGTH_SHORT).show();
		  
		                             DatabaseHandler db = new DatabaseHandler(getApplicationContext());
		                             JSONObject json_user = json.getJSONObject("USER_TB");
		  
		                             /**
		                              * Removes all the previous data in the SQlite database
		                              **/
		  
		                             UserFunctions logout = new UserFunctions();
		                             logout.logoutUser(getApplicationContext());
		                             db.addUser(json_user.getString(KEY_FIRSTNAME),json_user.getString(KEY_SECONDNAME),json_user.getString(KEY_IDNO),json_user.getString(KEY_PASSWORD),json_user.getString(KEY_OCCUPATION),json_user.getString(KEY_GENDER),json_user.getString(KEY_ADDRESS),json_user.getString(KEY_EMAIL),json_user.getString(KEY_PHONENUMBER),json_user.getString(KEY_CREATED_AT));
		                             /**
		                              * Stores registered data in SQlite Database
		                              * Launch Registered screen
		                              **/
		  
		                             Intent registered = new Intent(getApplicationContext(), Searchpage.class);
		  
		                             /**
		                              * Close all views before launching Registered screen
		                             **/
		                             registered.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		                             pDialog.dismiss();
		                             startActivity(registered);
		  
		                               finish();
		                         }
		  
		                     }
		  
		                         else{
		                         pDialog.dismiss();
		                         Toast.makeText(getApplicationContext(),
				                            "Error in Registration", Toast.LENGTH_SHORT).show();
		                         }
		  
		                 } catch (JSONException e) {
		                     e.printStackTrace();
		  
		                 }
		             }

	 }
	 
	 public void NetAsync(View view){
         new ProcessRegister().execute();
     }
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
