package com.propertyapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class Results  extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// php read comments script

	// localhost :
	// testing on your device
	// put your local ip instead, on windows, run CMD > ipconfig
	// or in mac's terminal type ifconfig and look for the ip under en0 or en1
	// private static final String READ_COMMENTS_URL =
	// "http://xxx.xxx.x.x:1234/webservice/comments.php";

	// testing on Emulator:
	private static final String READ_COMMENTS_URL = "http://10.0.2.2/Propertyapp/comments.php";/*
	 private static final String LAND_URL = "http://10.0.2.2/Propertyapp/comments.php";*/
	// testing from a real server:
	// private static final String READ_COMMENTS_URL =
	// "http://www.mybringback.com/webservice/comments.php";

	// JSON IDS:
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_TELEPHONE = "user_phonenumber";
	private static final String TAG_EMAIL = "user_email";
	private static final String TAG_PNAME = "property_nme";
	private static final String TAG_PRICE = "property_price";
	private static final String TAG_LOCATION = "property_location";
	private static final String TAG_USERNAME = "user_username";
	private static final String TAG_SIZE = "property_size";
	private static final String TAG_POSTS = "posts";
	
	// it's important to note that the message is both in the parent branch of
	// our JSON tree that displays a "Post Available" or a "No Post Available"
	// message,
	// and there is also a message for each individual post, listed under the
	// "posts"
	// category, that displays what the user typed as their message.

	// An array of all of our comments
	private JSONArray mComments = null;
	// manages all of our comments in a list.
	private ArrayList<HashMap<String, String>> mCommentList;

	EditText value,place;
	JSONParser jsonParser = new JSONParser();
	 private static final String TAG_MESSAGE = "message";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.results);
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// loading the comments via AsyncTask
		/*new findland().execute();*/
		new LoadComments().execute();
	}
	
	
/*class findland extends AsyncTask<String, String, String> {

		
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Results.this);
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
    		value =(EditText)findViewById(R.id.idsearch);
    		place =(EditText)findViewById(R.id.location);
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
					onResume();
                }else{
                	Log.d("Searching Failure!", json.getString(TAG_MESSAGE));
                	return json.getString(TAG_MESSAGE);
                	
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
			
		}*/
		
     /*   protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
            	Toast.makeText(Results.this, file_url, Toast.LENGTH_LONG).show();
            }
 
        }
		
	}*/

	
	
	
	
	
	
	
	/**
	 * Retrieves recent post data from the server.
	 */
	public void updateJSONdata() {

		// Instantiate the arraylist to contain all the JSON data.
		// we are going to use a bunch of key-value pairs, referring
		// to the json element name, and the content, for example,
		// message it the tag, and "I'm awesome" as the content..

		mCommentList = new ArrayList<HashMap<String, String>>();

		// Bro, it's time to power up the J parser
		JSONParser jParser = new JSONParser();
		// Feed the beast our comments url, and it spits us
		// back a JSON object. Boo-yeah Jerome.
		JSONObject json = jParser.getJSONFromUrl(READ_COMMENTS_URL);

		// when parsing JSON stuff, we should probably
		// try to catch any exceptions:
		try {

			// I know I said we would check if "Posts were Avail." (success==1)
			// before we tried to read the individual posts, but I lied...
			// mComments will tell us how many "posts" or comments are
			// available
			mComments = json.getJSONArray(TAG_POSTS);

			// looping through all posts according to the json object returned
			for (int i = 0; i < mComments.length(); i++)
				
			{JSONObject c = mComments.getJSONObject(i);

				// gets the content of each tag
				String user_username = c.getString(TAG_USERNAME);
				String user_phonenumber = c.getString(TAG_TELEPHONE);
				String user_email = c.getString(TAG_EMAIL);
				String property_name = c.getString(TAG_PNAME);
				String property_price = c.getString(TAG_PRICE);
				String property_location = c.getString(TAG_LOCATION);
				String property_size = c.getString(TAG_SIZE);

				// creating new HashMap
				HashMap<String, String> map = new HashMap<String, String>();

				map.put(TAG_TELEPHONE, user_phonenumber);
				map.put(TAG_EMAIL, user_email);
				map.put(TAG_USERNAME, user_username);
				map.put(TAG_PNAME, property_name);
				map.put(TAG_PRICE,property_price);
				map.put(TAG_LOCATION, property_location);
				map.put(TAG_SIZE, property_size);

				// adding HashList to ArrayList
				mCommentList.add(map);

				// annndddd, our JSON data is up to date same with our array
				// list
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserts the parsed data into the listview.
	 */
	private void updateList() {
		// For a ListActivity we need to set the List Adapter, and in order to do
		//that, we need to create a ListAdapter.  This SimpleAdapter,
		//will utilize our updated Hashmapped ArrayList, 
		//use our single_post xml template for each item in our list,
		//and place the appropriate info from the list to the
		//correct GUI id.  Order is important here.
		ListAdapter adapter = new SimpleAdapter(this, mCommentList,R.layout.single_post, new String[] { TAG_TELEPHONE, TAG_EMAIL,TAG_USERNAME,TAG_PNAME, TAG_PRICE,TAG_LOCATION,TAG_SIZE}, new int[] { R.id.usertelephone, R.id.useremail,R.id.username,R.id.propertyname, R.id.propertyprice,R.id.location,R.id.propertysize});

		// I shouldn't have to comment on this one:
		setListAdapter(adapter);
		
		// Optional: when the user clicks a list item we 
		//could do something.  However, we will choose
		//to do nothing...
		ListView lv = getListView();	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// This method is triggered if an item is click within our
				// list. For our example we won't be using this, but
				// it is useful to know in real life applications.

			}
		});
	}

	public class LoadComments extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Results.this);
            pDialog.setTitle("Contacting Servers");
			pDialog.setMessage("Loading Results...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			updateJSONdata();
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			updateList();
		}
	}
}