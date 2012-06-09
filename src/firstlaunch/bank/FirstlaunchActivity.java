package firstlaunch.bank;

import wg.bank.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstlaunchActivity extends Activity {
	public EditText tfPhone = null;
	public EditText tfPassword = null;
	private Context _context;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstlaunch);

		tfPhone = (EditText) findViewById(R.firstlaunch.tf_phone);
		tfPassword = (EditText) findViewById(R.firstlaunch.tf_password);
		
	}

	public void createAccount(View v) {
		_context = v.getContext();
		
		if (tfPhone.getText().toString().equals("")) {
			Toast.makeText(_context, "Bitte gib eine Telefonnummer ein.",
					Toast.LENGTH_LONG).show();
			tfPhone.setFocusableInTouchMode(true);
			tfPhone.requestFocus();
			return;
		}

		if (tfPassword.getText().toString().equals("")) {
			Intent i = new Intent().setClass(this, NewAccountActivity.class);
			startActivityForResult(i, 1);
		} else {
			// TODO: check account data valid
			//Action: person-validate
			
		}
	}
	
	private void evaluateResult(Intent data) {
		String code = data.getStringExtra("code");
		if(code.equals("1")) {
			SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("Number", tfPhone.getText().toString());
			editor.putString("Password", data.getStringExtra("password"));
			editor.commit();
		}
		else {
			Toast.makeText(_context,
					"Es wurde kein Zugang angelegt.",
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == 1) {
			evaluateResult(data);
		}
	}
}
