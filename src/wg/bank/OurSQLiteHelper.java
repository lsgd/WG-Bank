package wg.bank;

import android.content.Context;
import android.database.sqlite.*;

public class OurSQLiteHelper extends android.database.sqlite.SQLiteOpenHelper {
	private static final String DATABASE_NAME = "cashManager.db";
	private static final int DATABASE_VERSION = 1;

	
	// Database creation sql statement
	private static final String TABLE_PAYMENT_CREATE = "CREATE TABLE payment ("
			  + "id int(11) NOT NULL,"
			  +"project int(11) NOT NULL,"
			  + "purpose varchar(100) NOT NULL,"
			  +"`date` datetime NOT NULL,"
			  +"`created` datetime NOT NULL,"
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
			+ "  `created` datetime NOT NULL,"
			+ "  PRIMARY KEY (`person`,`project`));";
	
	private static final String TABLE_PROJECT_CREATE =  "CREATE TABLE `project` ("
			+ "  `id` int(11) NOT NULL,"
			+ "  `name` varchar(50) NOT NULL,"
			+ "  `isactive` tinyint(1) NOT NULL,"
			+ "  `created` datetime NOT NULL,"
			+ "  PRIMARY KEY (`id`));";

	public OurSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL( TABLE_PAYMENT_CREATE+
				          TABLE_PERSON_PAYMENT_CREATE+
				          TABLE_PERSON_PROJECT_CREATE+
				          TABLE_PROJECT_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
