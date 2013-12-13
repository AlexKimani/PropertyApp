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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Dashboard extends Activity implements OnClickListener{
	Button send;
	EditText value,place;
	Spinner type;
	
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
    private static final String LAND_URL = "http://10.0.2.2/Propertyapp/comments.php";
    private static final String RESHOUSE_URL = "http://10.0.2.2/Propertyapp/reshouse.php";
    private static final String COMMHOUSE_URL = "http://10.0.2.2/Propertyapp/commhouse.php";
  //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		send = (Button)findViewById(R.id.button1);
		send.setOnClickListener(this);
		value =(EditText)findViewById(R.id.idsearch);
		place =(EditText)findViewById(R.id.location);
		
		 type =(Spinner)findViewById(R.id.spinner1);
	        // Create an ArrayAdapter using the string array and a default spinner layout
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.propertysearch, android.R.layout.simple_spinner_item);
			// Specify the layout to use when the list of choices appears
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
			type.setAdapter(adapter);
			type.setOnItemSelectedListener(new OnItemSelectedListener(){

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


	public void onClick(View v) {
		if(type.getSelectedItem().equals("Land")){
			Intent i = new Intent(Dashboard.this, Results.class);
			/*finish();*/
			startActivity(i);
			/*new findland().execute();*/
		}
		else if(type.getSelectedItem().equals("Commercial House")){
			new findcommhouse().execute();
		}
		else if(type.getSelectedItem().equals("Residential House")){
			new findreshouse().execute();
		}
		else
		{
			Toast.makeText(Dashboard.this, "Please select property type", Toast.LENGTH_LONG).show();
		}
	}
	
class findland extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Dashboard.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            int success;
            String loc = place.getText().toString();
            String val = value.getText().toString();
           
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("price", val));
                params.add(new BasicNameValuePair("location",loc));  
 
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest( LAND_URL, "POST", params);
 
                // full json response
                Log.d("Registering attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
					Intent i = new Intent(Dashboard.this, Results.class);
					finish();
					startActivity(i);
                }else{
                	Log.d("Searching Failure!", json.getString(TAG_MESSAGE));
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
            	Toast.makeText(Dashboard.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}

class findreshouse extends AsyncTask<String, String, String> {

	
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(Dashboard.this);
        pDialog.setTitle("Contacting Servers");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		 // Check for success tag
        int success;
        String loc = place.getText().toString();
        String val = value.getText().toString();
       
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("price", val));
            params.add(new BasicNameValuePair("location",loc));  

            Log.d("request!", "starting");
            
            //Posting user data to script 
            JSONObject json = jsonParser.makeHttpRequest( RESHOUSE_URL, "POST", params);

            // full json response
            Log.d("Registering attempt", json.toString());

            // json success element
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {              	
				Intent i = new Intent(Dashboard.this, Results.class);
				finish();
				startActivity(i);
            }else{
            	Log.d("Searching Failure!", json.getString(TAG_MESSAGE));
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
        	Toast.makeText(Dashboard.this, file_url, Toast.LENGTH_LONG).show();
        }

    }
	
}


class findcommhouse extends AsyncTask<String, String, String> {

	
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(Dashboard.this);
        pDialog.setTitle("Contacting Servers");
        pDialog.setMessage("Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		 // Check for success tag
        int success;
        String loc = place.getText().toString();
        String val = value.getText().toString();
       
        try {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("price", val));
            params.add(new BasicNameValuePair("location",loc));  

            Log.d("request!", "starting");
            
            //Posting user data to script 
            JSONObject json = jsonParser.makeHttpRequest( COMMHOUSE_URL, "POST", params);

            // full json response
            Log.d("Registering attempt", json.toString());

            // json success element
            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {              	
				Intent i = new Intent(Dashboard.this, Results.class);
				finish();
				startActivity(i);
            }else{
            	Log.d("Searching Failure!", json.getString(TAG_MESSAGE));
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
        	Toast.makeText(Dashboard.this, file_url, Toast.LENGTH_LONG).show();
        }

    }
	
}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboardmenu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.search:
	        	Intent i = new Intent(getApplicationContext(), Dashboard.class);
				startActivity(i);
	            return true;
	        case R.id.addland:
	        	Intent l = new Intent(getApplicationContext(), Land.class);
				startActivity(l);
	            return true;
	        case R.id.addresidential:
	        	Intent r = new Intent(getApplicationContext(), Residential.class);
				startActivity(r);
	            return true;
	        case R.id.addcommercial:
	        	Intent c = new Intent(getApplicationContext(), Commercial.class);
				startActivity(c);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}


}
