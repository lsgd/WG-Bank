package firstlaunch.bank;

import wg.bank.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class NewAccountActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.newaccount);

	    //tfPhone = (EditText)findViewById(R.firstlaunch.tf_phone);
	    //tfPassword = (EditText)findViewById(R.firstlaunch.tf_password);
	}
	
	public void submitButton(View v) {
		//TODO: create account
		//json = Action: person-add, Number: 0170..., Name: Lukas Schulze
		Toast.makeText(v.getContext(), "Noch implementieren: Benutzer anlegen!", Toast.LENGTH_LONG).show();
		
		Intent intent= getIntent();
		intent.putExtra("code", "1");
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void cancelButton(View v) {
		Intent intent= getIntent();
		intent.putExtra("code", "0");
		setResult(RESULT_OK, intent);
		finish();
	}
}
