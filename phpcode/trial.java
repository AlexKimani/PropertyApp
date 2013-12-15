MainActivity.java

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    String str_name;
    String str_age; 
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
       final EditText txtname = (EditText) findViewById(R.id.editText1);
       final EditText txtage = (EditText) findViewById(R.id.editText2);
     Button button = (Button) findViewById(R.id.button1);
    
     SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
     final SharedPreferences.Editor editor = myPrefs.edit();
    
     button.setOnClickListener(new View.OnClickListener() { 
          public void onClick(View v) {
            
             str_name = txtname.getText().toString();
             str_age = txtage.getText().toString();
             
             //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getParent());            
            // SharedPreferences.Editor editor = preferences.edit();
              editor.putString("name",str_name);
              editor.putString("age",str_age);
              editor.commit();
             
      Intent intent = new Intent(MainActivity.this, Second.class);
      //intent.putExtra("name", str_name);
      //intent.putExtra("age", str_age);
     
     startActivity(intent);  
    
       
          }     
});
}
}

Second.java


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Second extends Activity {
    String str_name;
    String str_age;
@Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        
         // EditText txtname= (EditText) findViewById(R.id.editText3);

   
  SharedPreferences myPrefs = this.getSharedPreferences("myPrefs", MODE_PRIVATE);
  String prefName = myPrefs.getString("name", str_name);
  String prefage = myPrefs.getString("age", str_age);
  
  EditText txtname= (EditText) findViewById(R.id.editText3);
  txtname.setText(prefName.toString());
  
  EditText txtage= (EditText) findViewById(R.id.editText1);
  txtage.setText(prefage.toString());
  
  
}

} 
