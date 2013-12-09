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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Land extends Activity implements  OnClickListener{
	Button send;
	EditText size,place,jina,value,user,title;
	
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
    private static final String PROPERTY_URL = "http://10.0.2.2/Propertyapp/landstore.php"; 
  //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.land);
		send = (Button)findViewById(R.id.submit);
		send.setOnClickListener(this);
		size = (EditText)findViewById(R.id.pukubwa);
		place = (EditText)findViewById(R.id.plocation);
		jina = (EditText)findViewById(R.id.name3);
		value = (EditText)findViewById(R.id.price);
		user = (EditText)findViewById(R.id.user1);
		title = (EditText)findViewById(R.id.landtitle);
		}
	
	@Override
	public void onClick(View v){
		switch(v.getId()){
		case R.id.submit:
			new Landregister().execute();	
		 break;
	}
	}
	
	
class Landregister extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Land.this);
            pDialog.setMessage("Posting land...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
        @Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            Integer success;
            
        	String siz= size.getText().toString();
        	String plc = place.getText().toString();
        	String jin = jina.getText().toString();
        	String val = value.getText().toString();
        	String use = user.getText().toString();
        	String tit = title.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("psize", siz));
                params.add(new BasicNameValuePair("plocation",plc));  
                params.add(new BasicNameValuePair("pname",jin));
                params.add(new BasicNameValuePair("price", val));
                params.add(new BasicNameValuePair("username", use));
                params.add(new BasicNameValuePair("ltitle", tit));
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(
                       PROPERTY_URL, "POST", params);
 
                // full json response
                Log.d("Registering attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("Land Register!", json.toString());
                	Intent i = new Intent(Land.this, Dashboard.class);
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
            	Toast.makeText(Land.this, file_url, Toast.LENGTH_LONG).show();
            }
 
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
	        	jina.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	
}
