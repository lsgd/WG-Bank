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

	private static final String TABLE_PAYMENT_NAME = "payment";
	private static final String TABLE_PERSON_PAYMENT_NAME = "person_payment";
	private static final String TABLE_PERSON_PROJECT_NAME = "person_project";
	private static final String TABLE_PROJECT_NAME = "project";
	
	// Database creation sql statement
	private static final String TABLE_PAYMENT_CREATE = "CREATE TABLE " + TABLE_PAYMENT_NAME + " ("
			  + "id INT NOT NULL,"
			  +"project INT NOT NULL,"
			  + "purpose TEXT NOT NULL,"
			  +"`date` REAL NOT NULL,"
			  +" PRIMARY KEY (`id`));";
	
	
	private static final String TABLE_PERSON_PAYMENT_CREATE =  "CREATE TABLE " + TABLE_PERSON_PAYMENT_NAME + " ("
			+ "  `payment` INT NOT NULL,"
			+ "  `person` TEXT NOT NULL,"
			+ "  `ispayer` INT,"
			+ "  `costs` REAL NOT NULL,"
			+ "  PRIMARY KEY (`payment`,`person`));";
			
	
	private static final String TABLE_PERSON_PROJECT_CREATE =  "CREATE TABLE " + TABLE_PERSON_PROJECT_NAME + " ("
			+ "  `person` TEXT NOT NULL,"
			+ "  `project` INT NOT NULL,"
			+ "  `name` TEXT NOT NULL,"
			+ "  PRIMARY KEY (`person`,`project`));";
	
	private static final String TABLE_PROJECT_CREATE =  "CREATE TABLE " + TABLE_PROJECT_NAME + " ("
			+ "  `project` INT NOT NULL,"
			+ "  `name` TEXT NOT NULL,"
			+ "  PRIMARY KEY (`project`));";

	public OurSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL( "DROP TABLE IF EXISTS " + TABLE_PAYMENT_NAME + ";");
		database.execSQL( "DROP TABLE IF EXISTS " + TABLE_PERSON_PROJECT_NAME + ";");
		database.execSQL( "DROP TABLE IF EXISTS " + TABLE_PERSON_PAYMENT_NAME + ";");
		database.execSQL( "DROP TABLE IF EXISTS " + TABLE_PROJECT_NAME + ";");
		
		database.execSQL( TABLE_PAYMENT_CREATE );
		database.execSQL( TABLE_PERSON_PROJECT_CREATE );
		database.execSQL( TABLE_PERSON_PAYMENT_CREATE );
		database.execSQL( TABLE_PROJECT_CREATE );
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertPayments(ArrayList<ContentValues> contentValuesArray) {
		SQLiteDatabase db = this.getWritableDatabase();
		for(ContentValues contentValues : contentValuesArray) {
			db.insert(TABLE_PAYMENT_NAME, null, contentValues);
		}
		db.close();
		
		String payments = "";
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PAYMENT_NAME + ";", null);
		cursor.moveToFirst();
		
		payments = cursor.getString(2);
		Toast.makeText(context, payments, Toast.LENGTH_LONG).show();
	}

	public void insertPersonProject(ArrayList<ContentValues> contentValuesArray) {
		SQLiteDatabase db = this.getWritableDatabase();
		for(ContentValues contentValues : contentValuesArray) {
			db.insert(TABLE_PERSON_PROJECT_NAME, null, contentValues);
		}
		db.close();
		
		String personProjects = "";
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PERSON_PROJECT_NAME + ";", null);
		cursor.moveToFirst();
		
		personProjects = cursor.getString(2);
		Toast.makeText(context, personProjects, Toast.LENGTH_LONG).show();		
	}

	public void insertPaymentPersons(ArrayList<ContentValues> contentValuesArray) {
		SQLiteDatabase db = this.getWritableDatabase();
		for(ContentValues contentValues : contentValuesArray) {
			db.insert(TABLE_PERSON_PAYMENT_NAME, null, contentValues);
		}
		db.close();
		
		String personProjects = "";
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PERSON_PAYMENT_NAME + ";", null);
		cursor.moveToFirst();
		
		personProjects = cursor.getString(3);
		Toast.makeText(context, personProjects, Toast.LENGTH_LONG).show();		
	}

	public void insertProjects(ArrayList<ContentValues> contentValuesArray) {
		SQLiteDatabase db = this.getWritableDatabase();
		for(ContentValues contentValues : contentValuesArray) {
			db.insert(TABLE_PROJECT_NAME, null, contentValues);
		}
		db.close();
		
		String project = "";
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PROJECT_NAME + ";", null);
		cursor.moveToFirst();
		
		project = cursor.getString(1);
		Toast.makeText(context, project, Toast.LENGTH_LONG).show();
		
	}

}
