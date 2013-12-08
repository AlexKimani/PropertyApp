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
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Searchpage extends Activity implements OnClickListener{
    EditText idno,pword;
	TextView changepass,register;
	Button login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginpge);
//		the toast code to help the user know what to do in the page
		Context context = getApplicationContext();
		CharSequence text = "Please login to continue...";		
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.BOTTOM, 0, duration);
		toast.show();
	    login = (Button)findViewById(R.id.sub);
		login.setOnClickListener(this);
		idno = (EditText)findViewById(R.id.userid);
		pword= (EditText)findViewById(R.id.password);
		changepass=(TextView)findViewById(R.id.forgotpass);
		changepass.setOnClickListener(this);
		register= (TextView)findViewById(R.id.register);
		register.setOnClickListener(this);
		}

	public void  onClick(View v){

		switch(v.getId()){
		case R.id.sub:
			  if (  ( !idno.getText().toString().equals("")) && ( !pword.getText().toString().equals("")) )
              {
				 new login().execute();
			          		         
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
			  break;
		case R.id.forgotpass:
			Intent i = new Intent(getApplicationContext(), Updatepass.class);
			startActivity(i);
			break;
		case R.id.register:
			Intent r = new Intent(getApplicationContext(), Register.class);
			startActivity(r);
			break;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

	
	private class login extends AsyncTask<Object, Object, Object>{
		String id = idno.getText().toString();
		String pass = pword.getText().toString();
		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Searchpage.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Logining in...");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
         }
		@Override
		protected Object doInBackground(Object... params) {
//			  What the application should do after the checking					  
			  try {
		    		
		        	//create a http default client - initialize the HTTp client
		          DefaultHttpClient httpclient = new DefaultHttpClient();				            
		         HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/login.php");
		         ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2); 
		           nameValuePairs.add(new BasicNameValuePair("use", id));
		           nameValuePairs.add(new BasicNameValuePair("pas", pass));	       
		           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		            // Execute HTTP Post Request
		           HttpResponse response = httpclient.execute(httppost);				            
		            InputStream inputStream = response.getEntity().getContent();		            
		            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream), 4096);
		            String line;
		            StringBuilder sb =  new StringBuilder();		            
		            while ((line = rd.readLine()) != null) {
		            		sb.append(line);
		            }
		            rd.close();
		            String result = sb.toString();				            
		            inputStream.close();
		         if(result.matches("2")){
		        	 pDialog.dismiss();
		        		        	 
		         }
		         else {
		           // String  password = result;
		            if (result.matches(pass)){					            
			            Intent intent=new Intent(Searchpage.this,Dashboard.class);
						/*Bundle bundle = new Bundle();
						bundle.putString("landlordid", id);*/	//keyword- selected clinic	
						/*intent.putExtras(bundle);*/
					    startActivity(intent);
					    pDialog.dismiss();
		            }
		           else if(result!= pass){
		        	   pDialog.dismiss();
		        	  /*Toast.makeText(Searchpage.this, "Incorrect password please check password", Toast.LENGTH_LONG).show();*/
		            }
		           
		         }
		         }           
		            catch (Exception e)
		            {
		            	pDialog.dismiss();
		                Toast.makeText(Searchpage.this, "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
		            }
			return null;
			  
//			  End of the the work of the code
			  
		}
		
	}
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.login:
	        	Intent i = new Intent(getApplicationContext(), Searchpage.class);
				startActivity(i);
	            return true;
	        case R.id.reset:
	        	EditText	idno = (EditText)findViewById(R.id.userid);
	        	EditText	pword= (EditText)findViewById(R.id.password);
	        	pword.setText("");
	        	idno.setText("");
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	}
	 


