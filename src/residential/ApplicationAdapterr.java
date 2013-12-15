package residential;

import java.util.List;

import com.propertyapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
public class ApplicationAdapterr extends ArrayAdapter<Applicationr> {

	 private List<Applicationr> items;
	    
	    public ApplicationAdapterr(Context context, List<Applicationr> items) {
	        super(context, R.layout.single_postr, items);
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
	            v = li.inflate(R.layout.single_postr, null);            
	        }
	        
	        Applicationr app = items.get(position);
	        
	        if(app != null) {
	            TextView pname = (TextView)v.findViewById(R.id.propertyname);
	            TextView psize = (TextView)v.findViewById(R.id.propertysize);
	            TextView pprice = (TextView)v.findViewById(R.id.propertyprice);
	            TextView plocation = (TextView)v.findViewById(R.id.location);
	            TextView uname = (TextView)v.findViewById(R.id.username);
	            TextView utel = (TextView)v.findViewById(R.id.usertelephone);
	            TextView uemail = (TextView)v.findViewById(R.id.useremail);
	            TextView nor = (TextView)v.findViewById(R.id.nor);
	            TextView purp = (TextView)v.findViewById(R.id.purposer);
	           
	            
	            if(pname != null) pname.setText("Property Name:  "+app.getProperty_name());
	            if(psize != null) psize.setText("Property Size:  "+app.getProperty_size());
	            if(pprice != null) pprice.setText("Property Price:  "+app.getProperty_price());
	            if(plocation != null) plocation.setText("Property Location:  "+app.getProperty_location());
	            if(uname != null) uname.setText("Owner Username:  "+app.getUser_username());
	            if(utel != null) utel.setText("Owner Phonumber:  "+app.getUser_phonenumber());
	            if(uemail != null)uemail.setText("Owner Email:  "+app.getUser_email());
	            if(nor != null)nor.setText("Number of Bedrooms:  "+app.getResidential_nor());
	            if(purp != null)purp.setText("Property is:  "+app.getPurpose());
	            }
	                
	        return v;
	    }
	
}
