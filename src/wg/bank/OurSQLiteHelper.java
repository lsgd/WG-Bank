package wg.bank;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.Toast;

public class OurSQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {
	private static final String DATABASE_NAME = "cashManager.db";
	private static final int DATABASE_VERSION = 1;
	
	Context context;

	
	// Database creation sql statement
	private static final String TABLE_PAYMENT_CREATE = "CREATE TABLE payment2 ("
			  + "id INT NOT NULL,"
			  +"project INT NOT NULL,"
			  + "purpose TEXT NOT NULL,"
			  +"`date` REAL NOT NULL,"
			  +" PRIMARY KEY (`id`));";
	
	
	private static final String TABLE_PERSON_PAYMENT_CREATE =  "CREATE TABLE `person_payment` ("
			+ "  `payment` int(11) NOT NULL,"
			+ "  `person` char(40) NOT NULL,"
			+ "  `ispayer` tinyint(1) NOT NULL,"
			+ "  `costs` float NOT NULL,"
			+ "  PRIMARY KEY (`payment`,`person`));";
			
	
	private static final String TABLE_PERSON_PROJECT_CREATE =  "CREATE TABLE `person_project` ("
			+ "  `person` char(40) NOT NULL,"
			+ "  `project` int(11) NOT NULL,"
			+ "  `balance` float NOT NULL,"
			+ "  `isactive` tinyint(1) NOT NULL,"
			+ "  PRIMARY KEY (`person`,`project`));";
	
	private static final String TABLE_PROJECT_CREATE =  "CREATE TABLE `project` ("
			+ "  `id` int(11) NOT NULL,"
			+ "  `name` varchar(50) NOT NULL,"
			+ "  `isactive` tinyint(1) NOT NULL,"
			+ "  PRIMARY KEY (`id`));";

	public OurSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL( "DROP TABLE payment2;");
		
		database.execSQL( TABLE_PAYMENT_CREATE /*+
		          		  TABLE_PERSON_PAYMENT_CREATE+
		          		  TABLE_PERSON_PROJECT_CREATE+
		          	      TABLE_PROJECT_CREATE*/);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertPayments(ArrayList<ContentValues> contentValuesArray) {
		SQLiteDatabase db = this.getWritableDatabase();
		for(ContentValues contentValues : contentValuesArray) {
			db.insert("payment2", null, contentValues);
		}
		db.close();
		
		String payments = "";
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM payment2;", null);
		cursor.moveToFirst();
		
		payments = cursor.getString(2);
		Toast.makeText(context, payments, Toast.LENGTH_LONG).show();
	}

}
