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

public class Residential extends Activity implements OnClickListener{
    Button tuma;
    EditText jina,ukubwa,mahali,rumnos,value,user;
    
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
    private static final String RESIDENTIAL_URL = "http://10.0.2.2/Propertyapp/resstore.php"; 
  //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";
    
    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    
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
		value = (EditText)findViewById(R.id.price);
		user = (EditText)findViewById(R.id.user2);
		}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.rsubmit:
			new resstore().execute();
			break;		
		}
		
	}
	

class resstore extends AsyncTask<String, String, String> {
		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Residential.this);
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Posting Residential House...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
		
        @Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			 // Check for success tag
            Integer success;
            
        	String siz= ukubwa.getText().toString();
        	String plc = mahali.getText().toString();
        	String jin = jina.getText().toString();
        	String val = value.getText().toString();
        	String use = user.getText().toString();
        	String tit = rumnos.getText().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("psize", siz));
                params.add(new BasicNameValuePair("plocation",plc));  
                params.add(new BasicNameValuePair("pname",jin));
                params.add(new BasicNameValuePair("price", val));
                params.add(new BasicNameValuePair("username", use));
                params.add(new BasicNameValuePair("nor", tit));
                Log.d("request!", "starting");
                
                //Posting user data to script 
                JSONObject json = jsonParser.makeHttpRequest(RESIDENTIAL_URL, "POST", params);
 
                // full json response
                Log.d("Registering attempt", json.toString());
 
                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                	Log.d("House Register!", json.toString());
                	Intent i = new Intent(Residential.this, Dashboard.class);
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
            	Toast.makeText(Residential.this, file_url, Toast.LENGTH_LONG).show();
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
