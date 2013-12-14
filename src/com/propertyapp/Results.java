package com.propertyapp;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;


public class Results  extends ListActivity implements FetchDataListener{
	private ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// note that use read_comments.xml instead of our single_post.xml
		setContentView(R.layout.results);
		initView();
		
		
	}
	
	 private void initView() {
		 Bundle b = getIntent().getExtras(); // Getting the Bundle object that pass from another activity
			String val1 = b.getString("msg1");
			String plc1 = b.getString("msg2");
	        // show progress dialog
	        dialog = ProgressDialog.show(this, "Contacting Server", "Loading...");	        
	        String url = "http://10.0.2.2/Propertyapp/comments2.php";
	        
	        
	        FetchDataTask task = new FetchDataTask(this);
	        task.execute(url);
	    }
	    
	    @Override
	    public void onFetchComplete(List<Application> data) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // create new adapter
	        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
	        // set the adapter to list
	        setListAdapter(adapter);        
	    }

	    @Override
	    public void onFetchFailure(String msg) {
	        // dismiss the progress dialog
	        if(dialog != null)  dialog.dismiss();
	        // show failure message
	        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();        
	    }
	

}