package bank.general;

import bank.general.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class LauncherActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		
		SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
//		SharedPreferences.Editor editor = sp.edit();
//		editor.putString("Number", "");
//		editor.putString("Password", "");
//		editor.putString("Name", "");
//		editor.commit();
	    String number = sp.getString("Number", "");
	    if(number.equals("")) {
	    	Intent intent = new Intent().setClass(this, bank.firstlaunch.FirstlaunchActivity.class);
	    	startActivity(intent);
	    }
	    else {
			Intent intent = new Intent().setClass(this, bank.tabs.TabWidget.class);
			startActivity(intent);
	    }
	    finish();
	}
}
