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
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener {
	
    
    EditText firstname, secondname,idnumber,password,occupation,address,emaill,phonenumber,confirmpass;
    Spinner gender;
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
		gender =(Spinner)findViewById(R.id.spinner1);
		confirmpass=(EditText)findViewById(R.id.passwordcon);
		
		send.setOnClickListener(new View.OnClickListener(){
			
			@Override
			public void onClick(View v) {
				String p1 = password.getText().toString();
				String p2 = confirmpass.getText().toString();
				if (  (!firstname.getText().toString().equals("")) && ( !secondname.getText().toString().equals(""))&& ( !idnumber.getText().toString().equals("")) && ( !password.getText().toString().equals("")) && ( !occupation.getText().toString().equals("")) && ( !address.getText().toString().equals("")) && ( !emaill.getText().toString().equals("")) && ( !phonenumber.getText().toString().equals(""))&& ( !confirmpass.getText().toString().equals("")) )
	                {
					 if(p1.matches(p2)){
						Registeruser();
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
	
	//the method to register the user into the system
	public void Registeruser(){
		String fname, sname,idno,passcode,job,gen,box,email,tel;
		fname = firstname.getText().toString();
		sname = secondname.getText().toString();
		idno = idnumber.getText().toString();
		passcode = password.getText().toString();
		job = occupation.getText().toString();
		email = emaill.getText().toString();
		gen = gender.getSelectedItem().toString();
		box = address.getText().toString();
		tel = phonenumber.getText().toString();

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
      nameValuePairs.add(new BasicNameValuePair("idnumber", idno));
      nameValuePairs.add(new BasicNameValuePair("password", passcode));
      nameValuePairs.add(new BasicNameValuePair("occupation", job));
      nameValuePairs.add(new BasicNameValuePair("gender", gen));
      nameValuePairs.add(new BasicNameValuePair("address", box));
      nameValuePairs.add(new BasicNameValuePair("mail", email));
      nameValuePairs.add(new BasicNameValuePair("telphone", tel));
     
  
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
        	Toast.makeText(this, "Registration was Succesfull", Toast.LENGTH_LONG).show();
        	/*create();*/
            }
      ///check if response is 5
else if (result.equals("5"))
{
   Toast.makeText(this, "Registration Failed,Check you connection", Toast.LENGTH_LONG).show();
        	
 }
      ///check if response is 2
else if (result.equals("2"))
{
   Toast.makeText(this, "Email adress already exists", Toast.LENGTH_LONG).show();
        	
        }
				 
 }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error inside set:"+e.toString(), Toast.LENGTH_LONG).show();
        }
		}

}
