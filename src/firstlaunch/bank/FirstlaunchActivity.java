package firstlaunch.bank;

import org.json.JSONException;
import org.json.JSONObject;

import wg.bank.HttpUtils;
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
			i.putExtra("Number", tfPhone.getText().toString());
			startActivityForResult(i, 1);
		} else {
			// TODO: check account data valid
			if(validatePerson(tfPhone.getText().toString(), tfPassword.getText().toString())) {
				SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
				SharedPreferences.Editor editor = sp.edit();
				editor.putString("Number", tfPhone.getText().toString());
				editor.putString("Password", tfPassword.getText().toString());
				editor.commit();
				
				Intent intent = new Intent().setClass(this, tabs.bank.TabWidget.class);
				startActivity(intent);
				
				finish();
			}
			else {
				Toast.makeText(_context,
						"Die Nummer oder das Passwort sind falsch! Bitte versuche es erneut.",
						Toast.LENGTH_LONG).show();
			}
		}
	}
	
	protected Boolean validatePerson(String Number, String Password) {
		JSONObject obj = new JSONObject();
		try {
			String auth = wg.bank.Utils.sha1(Number+Password);
			obj.put("Action", "person-validate");
			obj.put("Number", Number);
			obj.put("AuthPerson", auth);
		} catch (JSONException e) {
		}
		String response = HttpUtils.postData(obj);
		
		if(response == null) {
			return false;
		}
		else {
			JSONObject responseJson = null;
			try {
				responseJson = new JSONObject(response);
			} catch (JSONException e) {
			}
			
			if(responseJson == null) {
				return false;
			}
			else {
				try {
					if(responseJson.getString("Status").equals("ok")) {
						return true;
					}
					else {
						return false;
					}
				} catch (JSONException e) {
				}
			}
		}
		return false;
	}
	
	protected void evaluateResult(Intent data) {
		String code = data.getStringExtra("code");
		if(code.equals("1")) {
			SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("Number", tfPhone.getText().toString());
			editor.putString("Password", data.getStringExtra("Password"));
			editor.commit();
		}
		else if(code.equals("2")) {
			Toast.makeText(_context,
					"Für diese Nummer existiert bereits ein Benutzer! Bitte gib ein Passwort ein.",
					Toast.LENGTH_LONG).show();
			tfPassword.setFocusableInTouchMode(true);
			tfPassword.requestFocus();
			
		}
		else if(code.equals("3")) {
			Toast.makeText(_context,
					"Es ist ein Fehler aufgetreten. Es wurde kein Zugang angelegt.",
					Toast.LENGTH_LONG).show();
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
