package com.propertyapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Land extends FragmentActivity implements NoticeDialogFragment.NoticeDialogListener, OnClickListener{
	Button send;
	EditText size,place,jina,value,title;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.land);
		send = (Button)findViewById(R.id.submit);
		send.setOnClickListener(this);
		size = (EditText)findViewById(R.id.pukubwa);
		place = (EditText)findViewById(R.id.plocation);
		jina = (EditText)findViewById(R.id.name3);
		
		
		}
	
	public void showNoticeDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NoticeDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
      value = (EditText)findViewById(R.id.username);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
       
    }
	
	
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.submit:
		 if (  ( !place.getText().toString().equals("")) && ( !size.getText().toString().equals("")) && ( !jina.getText().toString().equals("")))
         {
		 new storeland().execute();
         }
         else
         {
             Toast.makeText(getApplicationContext(),"Please fill in all fields", Toast.LENGTH_SHORT).show();
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

	
	private class storeland extends AsyncTask<Object, Object, Object>{
		
		
		
		String siz= size.getText().toString();
		String plc = place.getText().toString();
		String jin = jina.getText().toString();
		String val = value.getText().toString();
		
		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Land.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Please Wait...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
         }	
		 
		 

		@Override
		protected Object doInBackground(Object... params) { 
			
			try {
								 
				
		    	//create a http default client - initialize the HTTp client
		      DefaultHttpClient httpclient = new DefaultHttpClient();
		        //Create a HTTp post object to hold our data - url
		      HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/postland.php");
		        //use HTTPClient to execute the HTTPPost
		        // Execute HTTP Post Request
		      //encode URL
		      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
		      nameValuePairs.add(new BasicNameValuePair("size",siz));
		      nameValuePairs.add(new BasicNameValuePair("location",plc));  
		      nameValuePairs.add(new BasicNameValuePair("name",jin));
		      nameValuePairs.add(new BasicNameValuePair("username",val));
		      
		      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		      HttpResponse response = httpclient.execute(httppost);
		       
		        //use Input stream to read the http client response
		        InputStream inputStream = response.getEntity().getContent();
		        
		        
		        //use buffered reader and InputStreamReader to read the input stream
		        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
		        String line;
		        //initialize StringBuilder
		        StringBuilder sb =  new StringBuilder();
		        //read everything from the Buffered reader and append the to the
		        //string builder
		        while ((line = rd.readLine()) != null) 
		        {
		        		sb.append(line);
		        }
		        rd.close();
		        //our result
		        String result = sb.toString();
		        
		        inputStream.close();
		        //check if response is 4
		        if(result.equals("4")){
		        	pDialog.dismiss();
		        	new storetitle().execute();
		            }
		          ///check if response is 5
		else if (result.equals("5"))
		{
			pDialog.dismiss();
		  /* Toast.makeText(Register.this, "Registration Failed,Check you connection", Toast.LENGTH_LONG).show();*/
		        	
		 }
		      ///check if response is 2
		else if (result.equals("2"))
		{
			pDialog.dismiss();
		 /*  Toast.makeText(Register.this, "Email adress already exists", Toast.LENGTH_LONG).show();
		        	*/
		        }
						 
		 }
		        catch (Exception e)
		        {
		        	pDialog.dismiss();
		            Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
		        }
			
			return null;
		}
		
	}
	
	private class storetitle extends AsyncTask<Object, Object, Object>{
		String titl= title.getText().toString();
		String siz= size.getText().toString();
		String plc = place.getText().toString();
		String jin = jina.getText().toString();

		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Land.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Storing Title Deed...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
         }	

		@Override
		protected Object doInBackground(Object... params) {
			
			try {

		    	//create a http default client - initialize the HTTp client
		      DefaultHttpClient httpclient = new DefaultHttpClient();
		        //Create a HTTp post object to hold our data - url
		      HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/posttitle.php");
		        //use HTTPClient to execute the HTTPPost
		        // Execute HTTP Post Request
		      //encode URL
		      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
		      nameValuePairs.add(new BasicNameValuePair("title",titl));
		      nameValuePairs.add(new BasicNameValuePair("size",siz));
		      nameValuePairs.add(new BasicNameValuePair("location",plc));  
		      nameValuePairs.add(new BasicNameValuePair("name",jin));
		      
		      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		      HttpResponse response = httpclient.execute(httppost);
		       
		        //use Input stream to read the http client response
		        InputStream inputStream = response.getEntity().getContent();
		        
		        
		        //use buffered reader and InputStreamReader to read the input stream
		        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
		        String line;
		        //initialize StringBuilder
		        StringBuilder sb =  new StringBuilder();
		        //read everything from the Buffered reader and append the to the
		        //string builder
		        while ((line = rd.readLine()) != null) 
		        {
		        		sb.append(line);
		        }
		        rd.close();
		        //our result
		        String result = sb.toString();
		        
		        inputStream.close();
		        //check if response is 4
		        if(result.equals("4")){
		        	pDialog.dismiss();
		        	/*create();*/
		            }
		          ///check if response is 5
		else if (result.equals("5"))
		{
			pDialog.dismiss();
		  /* Toast.makeText(Register.this, "Registration Failed,Check you connection", Toast.LENGTH_LONG).show();*/
		        	
		 }
		      ///check if response is 2
		else if (result.equals("2"))
		{
			pDialog.dismiss();
		 /*  Toast.makeText(Register.this, "Email adress already exists", Toast.LENGTH_LONG).show();
		        	*/
		        }
						 
		 }
		        catch (Exception e)
		        {
		        	pDialog.dismiss();
		            Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
		        }
			
			return null;
		}
		
	}
}
