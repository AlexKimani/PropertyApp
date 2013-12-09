/**
 * 
 */
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

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
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
			if (  ( !user.getText().toString().equals("")) && ( !pass1.getText().toString().equals(""))&& ( !pass2.getText().toString().equals("")) )
            {
				if(p1.matches(p2)){
					new updatepass().execute();
				}
				else{
					Toast.makeText(getApplicationContext(),
	                        "Passwords do not match", Toast.LENGTH_SHORT).show();
				}
			          		         
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "Please fill in all Fields", Toast.LENGTH_SHORT).show();
            }
		break;	
		}
	
	}
	
private class updatepass extends AsyncTask<Object, Object, Object>{
        
		String use = user.getText().toString();
		String code1 = pass1.getText().toString();		
		
		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Updatepass.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Updating password...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
         }	

		@Override
		protected Object doInBackground(Object... params){			
			try {
		    	//create a http default client - initialize the HTTp client
		      DefaultHttpClient httpclient = new DefaultHttpClient();
		        //Create a HTTp post object to hold our data - url
		      HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/updateuser.php");
		        //use HTTPClient to execute the HTTPPost
		        // Execute HTTP Post Request
		      //encode URL
		      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(9);
		      nameValuePairs.add(new BasicNameValuePair("username", use));
		      nameValuePairs.add(new BasicNameValuePair("password",code1));
		
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
		        	/*Toast.makeText(Register.this, "Registered",Toast.LENGTH_LONG).show();*/
		        	/*create();*/
		            }
		          ///check if response is 5
		else if (result.equals("5"))
		{
			pDialog.dismiss();
		   /*Toast.makeText(Register.this, "Registration Failed,Check you connection", Toast.LENGTH_LONG).show();*/
		        	
		 }
		      ///check if response is 2
		else if (result.equals("2"))
		{
			pDialog.dismiss();
		   /*Toast.makeText(Register.this, "Email adress already exists", Toast.LENGTH_LONG).show();*/
		        	
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

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

}
