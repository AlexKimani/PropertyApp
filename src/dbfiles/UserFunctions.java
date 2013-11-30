package dbfiles;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;
 
public class UserFunctions {
 
    private JSONParser jsonParser;
 
    //URL of the PHP API
    private static String loginURL = "http://10.0.2.2/Propertyapp/";
    private static String registerURL = "http://10.0.2.2/Propertyapp/";
    private static String forpassURL = "http://10.0.2.2/Propertyapp/";
    private static String chgpassURL = "http://10.0.2.2/Propertyapp/";
 
    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String forpass_tag = "forpass";
    private static String chgpass_tag = "chgpass";
 
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
 
    /**
     * Function to Login
     **/
 
    public JSONObject loginUser(String idnumber, String accesscode){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("id", idnumber));
        params.add(new BasicNameValuePair("passcode", accesscode));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        return json;
    }
 
    /**
     * Function to change password
     **/
 
    public JSONObject chgPass(String newpas, String email){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", chgpass_tag));
 
        params.add(new BasicNameValuePair("newpas", newpas));
        params.add(new BasicNameValuePair("email", email));
        JSONObject json = jsonParser.getJSONFromUrl(chgpassURL, params);
        return json;
    }
 
    /**
     * Function to reset the password
     **/
 
    public JSONObject forPass(String forgotpassword){
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", forpass_tag));
        params.add(new BasicNameValuePair("forgotpassword", forgotpassword));
        JSONObject json = jsonParser.getJSONFromUrl(forpassURL, params);
        return json;
    }
 
     /**
      * Function to  Register
      **/
    public JSONObject registerUser(String user_fname, String user_sname, String user_email, String user_idno, String user_password, String user_address,String user_occupation,String user_phonenumber,String user_gender){
        // Building Parameters
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("user_fname", user_fname));
        params.add(new BasicNameValuePair("user_sname", user_sname));
        params.add(new BasicNameValuePair("user_idno", user_idno));
        params.add(new BasicNameValuePair("user_password", user_password));
        params.add(new BasicNameValuePair("user_occupation", user_occupation));
        params.add(new BasicNameValuePair("user_gender", user_gender));
        params.add(new BasicNameValuePair("user_address", user_address));
        params.add(new BasicNameValuePair("user_email", user_email));
        params.add(new BasicNameValuePair("user_phonenumber", user_phonenumber));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL,params);
        return json;
    }
 
    /**
     * Function to logout user
     * Resets the temporary data stored in SQLite Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
 
}