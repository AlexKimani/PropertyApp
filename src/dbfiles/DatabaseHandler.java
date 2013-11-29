package dbfiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
import java.util.HashMap;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "PropertyApp";
 
    // user table name
    private static final String TABLE_LOGIN = "USER_TB";
 
    // user_tb Table Columns names
    private static final String KEY_ID = "user_id";
    private static final String KEY_FIRSTNAME = "user_fname";
    private static final String KEY_SECONDNAME = "user_sname";
    private static final String KEY_IDNO = "user_idno";
    private static final String KEY_PASSWORD = "user_password";
    private static final String KEY_OCCUPATION = "user_occupation";
    private static final String KEY_GENDER = "user_gender";
    private static final String KEY_ADDRESS = "user_address";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PHONENUMBER = "user_phonenumber";
    private static final String KEY_CREATED_AT = "created_at";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTO_INCREMENT	,"
                + KEY_FIRSTNAME + " VARCHAR,"
                + KEY_SECONDNAME  + " VARCHAR,"                
                + KEY_IDNO + " VARCHAR,"                
                + KEY_PASSWORD + " VARCHAR,"
                + KEY_OCCUPATION + "VARCHAR,"
                + KEY_GENDER + "VARCHAR,"
                + KEY_ADDRESS + "VARCHAR,"
                + KEY_EMAIL + " VARCHAR,"
                + KEY_PHONENUMBER + "VARCHAR,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * Storing user details in database
     * */
    public void addUser(String user_fname, String user_sname, String user_email, String user_idno, String user_password, String user_address,String user_occupation,String user_phonenumber,String user_gender, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, user_fname); // FirstName
        values.put(KEY_SECONDNAME , user_sname); // secondName
        values.put(KEY_IDNO , user_idno); // idno
        values.put(KEY_PASSWORD , user_password); // password
        values.put(KEY_OCCUPATION , user_occupation); // occupation
        values.put(KEY_GENDER , user_gender); // gender
        values.put(KEY_ADDRESS, user_address); // address
        values.put(KEY_EMAIL, user_email); // Email
        values.put(KEY_PHONENUMBER, user_phonenumber); //phonenumber
        values.put(KEY_CREATED_AT, created_at); // Created At
 
        // Inserting Row
        db.insert(TABLE_LOGIN, null, values);
        db.close(); // Closing database connection
    }
 
    /**
     * Getting user data from database
     * */
    public HashMap getUserDetails(){
        HashMap user = new HashMap();
        String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
 
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("user_fname", cursor.getString(1));
            user.put("user_sname", cursor.getString(2));
            user.put("user_idno", cursor.getString(3));
            user.put("user_password", cursor.getString(4));
            user.put("user_occupation", cursor.getString(5));
            user.put("user_gender", cursor.getString(6));
            user.put("user_address", cursor.getString(7));
            user.put("user_email", cursor.getString(8));
            user.put("user_phonenumber", cursor.getString(9));
            user.put("created_at", cursor.getString(10));
        }
        cursor.close();
        db.close();
        // return user
        return user;
    }
 
    /**
     * Getting user login status
     * return true if rows are there in table
     * */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
 
        // return row count
        return rowCount;
    }
 
    /**
     * Re create database
     * Delete all tables and create them again
     * */
    public void resetTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_LOGIN, null, null);
        db.close();
    }
 
}