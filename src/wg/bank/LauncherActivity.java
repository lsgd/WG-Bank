package wg.bank;

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
	    String number = sp.getString("Number", "");
	    if(number.equals("")) {
	    	Intent intent = new Intent().setClass(this, firstlaunch.bank.FirstlaunchActivity.class);
	    	startActivity(intent);
	    }
	    else {
			Intent intent = new Intent().setClass(this, tabs.bank.TabWidget.class);
			startActivity(intent);
	    }
	    finish();
	}
}
