/**
 * 
 */
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author benjamin
 *
 */


public class Updatepass extends Activity implements OnClickListener {
	Button send;
	EditText user,pass1,pass2;
	
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
    private static final String UPDATE_URL = "http://10.0.2.2/Propertyapp/updatepass.php";
    
  //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.updatepass);
	send=(Button)findViewById(R.id.update);
	send.setOnClickListener(this);
	user = (EditText)findViewById(R.id.username);
	pass1 = (EditText)findViewById(R.id.newpass);
	pass2 = (EditText)findViewById(R.id.confirmpass);
	}
	
	@Override
	public void onClick(View v) {
		String p1 = pass1.getText().toString();
		String p2 = pass2.getText().toString();
		switch(v.getId()){
		case R.id.update:
			if(p1.matches(p2)){
				new Updatepass2().execute();	
			}
			else{
				Toast.makeText(Updatepass.this, "Your Passwords do not match", Toast.LENGTH_LONG).show();
			}
		break;	
		}
	
	}
	
	class Updatepass2 extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Updatepass.this);
            pDialog.setMessage("Updating Passwords...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String use = user.getText().toString();
            String pas = pass1.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", use));
                params.add(new BasicNameValuePair("password",pas));  
                
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(UPDATE_URL, "POST", params);
 
                // full json response
                Log.d("Updating attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Password Updates!", json.toString());              	
					Intent i = new Intent(Updatepass.this, Searchpage.class);
					finish();
					startActivity(i);
                	return json.getString(TAG_MESSAGE);
                }else{
                	Log.d("Password Update Failure!", json.getString(TAG_MESSAGE));
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
            	Toast.makeText(Updatepass.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}
	

	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

}
