package wg.bank;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class Test extends Activity {


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		OurSQLiteHelper sqliteHelper = new OurSQLiteHelper(getBaseContext());
		sqliteHelper.onCreate(sqliteHelper.getWritableDatabase());
		tv.setText("Hello database");
		setContentView(tv);
	}
}
