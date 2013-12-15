package land;

import java.util.List;

import com.propertyapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class ApplicationAdapter extends ArrayAdapter<Application> {

	 private List<Application> items;
	    
	    public ApplicationAdapter(Context context, List<Application> items) {
	        super(context, R.layout.single_post, items);
	        this.items = items;
	    }
	    
	    @Override
	    public int getCount() {
	        return items.size();
	    }
	    
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	        
	        if(v == null) {
	            LayoutInflater li = LayoutInflater.from(getContext());
	            v = li.inflate(R.layout.single_post, null);            
	        }
	        
	        Application app = items.get(position);
	        
	        if(app != null) {
	            TextView pname = (TextView)v.findViewById(R.id.propertyname);
	            TextView psize = (TextView)v.findViewById(R.id.propertysize);
	            TextView pprice = (TextView)v.findViewById(R.id.propertyprice);
	            TextView plocation = (TextView)v.findViewById(R.id.location);
	            TextView uname = (TextView)v.findViewById(R.id.username);
	            TextView utel = (TextView)v.findViewById(R.id.usertelephone);
	            TextView uemail = (TextView)v.findViewById(R.id.useremail);
	           
	            
	            if(pname != null) pname.setText("Property Name:  "+app.getProperty_name());
	            if(psize != null) psize.setText("Property Size:  "+app.getProperty_size());
	            if(pprice != null) pprice.setText("Property Price:  "+app.getProperty_price());
	            if(plocation != null) plocation.setText("Property Location:  "+app.getProperty_location());
	            if(uname != null) uname.setText("Owner Username:  "+app.getUser_username());
	            if(utel != null) utel.setText("Owner Phonumber:  "+app.getUser_phonenumber());
	            if(uemail != null)uemail.setText("Owner Email:  "+app.getUser_email());
	            }
	                
	        return v;
	    }
	
}
