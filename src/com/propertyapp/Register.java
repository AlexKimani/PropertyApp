package com.propertyapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

public class Register extends Activity implements OnItemSelectedListener, OnClickListener{
	
    
    EditText firstname, secondname,idnumber,password,occupation,address,emaill,phonenumber,confirmpass;
   private Spinner gend;
	Button send;
	
	 // Progress Dialog
    private ProgressDialog pDialog;
 
    // JSON parser class
    JSONParser jsonParser = new JSONParser();
    
    //php register script
    
    //localhost :  
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
   // private static final String REGISTER_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";
    
    //testing on Emulator:
    private static final String REGISTER_URL = "http://10.0.2.2/Propertyapp/register.php";
    
  //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
		
		//connect the variables to the fields
		send=(Button)findViewById(R.id.submit);
		send.setOnClickListener(this);
		firstname = (EditText)findViewById(R.id.fname);
		secondname = (EditText)findViewById(R.id.sname);
		idnumber = (EditText)findViewById(R.id.idno);
		password = (EditText)findViewById(R.id.password);
		occupation = (EditText)findViewById(R.id.occupation);
		address = (EditText)findViewById(R.id.address);
		emaill = (EditText)findViewById(R.id.email);
		phonenumber = (EditText)findViewById(R.id.telephone);
		confirmpass=(EditText)findViewById(R.id.passwordcon);
		
		        gend =(Spinner)findViewById(R.id.gender);
		        // Create an ArrayAdapter using the string array and a default spinner layout
				ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.gender, android.R.layout.simple_spinner_item);
				// Specify the layout to use when the list of choices appears
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				gend.setAdapter(adapter);
				gend.setOnItemSelectedListener(new OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> parent, View arg1,int arg2, long arg3) {
						String item = (String) parent.getItemAtPosition(arg2);	
						return;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
					
				});     		
		
	}
	
	
	@Override
	public void onClick(View v) {
		String p1 = password.getText().toString();
		String p2 = confirmpass.getText().toString();
		if(p1.matches(p2)){
			new CreateUser().execute();	
		}
		else{
			Toast.makeText(Register.this, "Your Passwords do not match", Toast.LENGTH_LONG).show();
		}
				
	}
	
	
	class CreateUser extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String fname = firstname.getText().toString();
            String sname = secondname.getText().toString();
            String idno = idnumber.getText().toString();
            String passcode = password.getText().toString();
            String job = occupation.getText().toString();
            String email = emaill.getText().toString();
            String box = address.getText().toString();
            String tel = phonenumber.getText().toString();
            String gen = gend.getSelectedItem().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("firstname", fname));
                params.add(new BasicNameValuePair("secondname",sname));  
                params.add(new BasicNameValuePair("username",idno));
                params.add(new BasicNameValuePair("password", passcode));
                params.add(new BasicNameValuePair("occupation", job));
                params.add(new BasicNameValuePair("gender", gen));
                params.add(new BasicNameValuePair("address", box));
                params.add(new BasicNameValuePair("mail1", email));
                params.add(new BasicNameValuePair("telephone", tel));
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                       REGISTER_URL, "POST", params);
 
                // full json response
                Log.d("Registering attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("User Registered!", json.toString());              	
					Intent i = new Intent(Register.this, Searchpage.class);
					finish();
					startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);
                	
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}
		
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regmenu, menu);
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.register:
	        	Intent i = new Intent(getApplicationContext(), Register.class);
				startActivity(i);
	            return true;
	        case R.id.reset:
	    		 firstname.setText("");
	    		 secondname.setText("");
	    		 idnumber.setText("");
	    		 password.setText("");
	    		occupation.setText("");
	    		emaill.setText("");;
	    		address.setText("");
	    		phonenumber.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
