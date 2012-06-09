package tabs.bank;

import wg.bank.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TextView textview = new TextView(this);
		// textview.setText("Das ist der Start-Tab");
		setContentView(R.layout.button);

		Button button = (Button) findViewById(R.id.button1);
//		button.setOnClickListener(new View.OnClickListener() {
////			public void onClick(View v) {
////				// Perform action on click
////			}
////		});
		
	}
	public void LaunchProjectScreen(){
		Intent intent;
		intent= new Intent().setClass(this, tabs.projecttabs.Projectscreen_Tabs.class);
	}
}