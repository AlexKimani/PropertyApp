package com.propertyapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
	Button login;;
	private ProgressDialog pDialog;

	// JSON parser class
	JSONParser jsonParser = new JSONParser();

	// php login script location:

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String LOGIN_URL =
	// "http://xxx.xxx.x.x:1234/webservice/login.php";

	// testing on Emulator:
	private static final String LOGIN_URL = "http://10.0.2.2/webservice/loginpge.php";

	// testing from a real server:
	// private static final String LOGIN_URL =
	// "http://www.mybringback.com/webservice/login.php";

	// JSON element ids from repsonse of php script:
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sub:
			new AttemptLogin().execute();
			break;
		case R.id.register:
			Intent i = new Intent(this, Register.class);
			startActivity(i);
			break;
		case R.id.forgotpass:
			Intent f = new Intent(this, Updatepass.class);
			startActivity(f);
			break;	
		default:
			break;
		}
	}

	
	class AttemptLogin extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Searchpage.this);
			pDialog.setMessage("Attempting login...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String user = idno.getText().toString();
			String pass = pword.getText().toString();
			try {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>(2);
				params.add(new BasicNameValuePair("username", user));
				params.add(new BasicNameValuePair("password", pass));

				Log.d("request!", "starting");
				// getting product details by making HTTP request
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",	params);

				// check your log for json response
				Log.d("Login attempt", json.toString());

				// json success tag
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Login Successful!", json.toString());
/*					// save user data
					SharedPreferences sp = PreferenceManager
							.getDefaultSharedPreferences(Searchpage.this);
					Editor edit = sp.edit();
					edit.putString("username", username);
					edit.commit();
					*/
					Intent i = new Intent(Searchpage.this, Dashboard.class);
					finish();
					startActivity(i);
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
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
			if (file_url != null) {
				Toast.makeText(Searchpage.this, file_url, Toast.LENGTH_LONG).show();
			}

		}

	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searchpage, menu);
		return true;
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
	 


