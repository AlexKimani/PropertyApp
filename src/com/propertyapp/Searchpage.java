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
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
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
						bundle.putString("landlordid", id);	//keyword- selected clinic	
						intent.putExtras(bundle);*/
					    startActivity(intent);
					    pDialog.dismiss();
		            }
		           else if(result!= pass){
		        	   pDialog.dismiss();
		        	
		            }
		           
		         }
		         }           
		            catch (Exception e)
		            {
		            	pDialog.dismiss();
		                Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
		            }
			return null;
			  
//			  End of the the work of the code
			  
		}
		protected void onPostExecute(Object result) {
	        if(((String) result).matches("2")){
	        	Toast.makeText(getApplicationContext(), "ID Not Found Check again", Toast.LENGTH_LONG).show();	
	        }
	        else if(result!= pass){
	        	Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
	        }
	     }
		
	}
	    
	}
	 


