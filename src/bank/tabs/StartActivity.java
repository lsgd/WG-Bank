package bank.tabs;

import bank.general.R;
import bank.general.RefreshButtonOnClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.startscreen);
	
	}
	
	public void LaunchProjectScreen(View v){
		Intent intent = new Intent().setClass(this, tabs.projecttabs.Projectscreen_Tabs.class);
		startActivity(intent);
	}
	
	public void LaunchAddProjectScreen(View v){
		Intent intent = new Intent().setClass(this, bank.project.NewProjectActivity.class);
		startActivity(intent);
	}
	
	public void RefreshDatabase(View v){
		RefreshButtonOnClickListener listener = new RefreshButtonOnClickListener();
		listener.onClick(v);
	}
}