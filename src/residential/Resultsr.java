package residential;

import java.util.List;

import com.propertyapp.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

public class Resultsr extends ListActivity implements FetchDataListenerr{
    private ProgressDialog dialog;    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.results);        
        initView();   
    }

    private void initView() {
    	
        // show progress dialog
        dialog = ProgressDialog.show(this, "Contacting Servers ", "Searching..."); 
        String url = "http://10.0.2.2/Propertyapp/findreshouse.php"; 
        FetchDataTaskr task = new FetchDataTaskr(this);
        task.execute(url);
    }
    
    @Override
    public void onFetchComplete(List<Applicationr> data) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        ApplicationAdapterr adapter = new ApplicationAdapterr(this, data);
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