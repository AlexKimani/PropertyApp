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
		
		
		send.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				String p1 = password.getText().toString();
				String p2 = confirmpass.getText().toString();
				if (  (!firstname.getText().toString().equals("")) && ( !secondname.getText().toString().equals(""))&& ( !idnumber.getText().toString().equals("")) && ( !password.getText().toString().equals("")) && ( !occupation.getText().toString().equals("")) && ( !address.getText().toString().equals("")) && ( !emaill.getText().toString().equals("")) && ( !phonenumber.getText().toString().equals(""))&& ( !confirmpass.getText().toString().equals("")) )
	                {
					 if(p1.matches(p2)){
						new Registeruser().execute();
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
	
/*	public void selection(){
		gend.setOnItemSelectedListener(new Spinnerselection());
	}
	*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
	}

	 
	
	//the method to register the user into the system
	private class Registeruser extends AsyncTask<Object, Object, Object>{
        
		String fname = firstname.getText().toString();
		String sname = secondname.getText().toString();
		String idno = idnumber.getText().toString();
		String passcode = password.getText().toString();
		String job = occupation.getText().toString();
		String email = emaill.getText().toString();
		String box = address.getText().toString();
		String tel = phonenumber.getText().toString();
		
		/*String gen = String.valueOf(gend.getSelectedItem());*/
		
		
		 private ProgressDialog pDialog;
		 @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(Register.this);
             pDialog.setTitle("Contacting Servers");
             pDialog.setMessage("Registering user...");
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
		      HttpPost httppost = new HttpPost("http://10.0.2.2/Propertyapp/registeruser.php");
		        //use HTTPClient to execute the HTTPPost
		        // Execute HTTP Post Request
		      //encode URL
		      ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
		      nameValuePairs.add(new BasicNameValuePair("firstname", fname));
		      nameValuePairs.add(new BasicNameValuePair("secondname",sname));  
		      nameValuePairs.add(new BasicNameValuePair("idnumber",idno));
		      nameValuePairs.add(new BasicNameValuePair("password", passcode));
		      nameValuePairs.add(new BasicNameValuePair("occupation", job));
		      /*nameValuePairs.add(new BasicNameValuePair("gender", gen));*/
		      nameValuePairs.add(new BasicNameValuePair("address", box));
		      nameValuePairs.add(new BasicNameValuePair("mail", email));
		      nameValuePairs.add(new BasicNameValuePair("telephoner", tel));
		     
		  
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
		        	Toast.makeText(Register.this, "Registered",Toast.LENGTH_LONG).show();
		        	/*create();*/
		            }
		          ///check if response is 5
		else if (result.equals("5"))
		{
			pDialog.dismiss();
		   Toast.makeText(Register.this, "Registration Failed,Check you connection", Toast.LENGTH_LONG).show();
		        	
		 }
		      ///check if response is 2
		else if (result.equals("2"))
		{
			pDialog.dismiss();
		   Toast.makeText(Register.this, "Email adress already exists", Toast.LENGTH_LONG).show();
		        	
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
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
