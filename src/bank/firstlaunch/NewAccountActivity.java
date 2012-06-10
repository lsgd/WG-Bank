package bank.firstlaunch;

import org.json.JSONException;
import org.json.JSONObject;


import bank.general.R;
import bank.utils.HttpUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewAccountActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.newaccount);

	    //tfPhone = (EditText)findViewById(R.firstlaunch.tf_phone);
	    //tfPassword = (EditText)findViewById(R.firstlaunch.tf_password);
	}
	
	public void submitButton(View v) {
		Intent intent= getIntent();
		
		EditText tfName = (EditText) findViewById(R.newaccount.tf_name);
		JSONObject obj = new JSONObject();
		try {
			obj.put("Action", "person-add");
			obj.put("Number", getIntent().getStringExtra("Number"));
			obj.put("Name", tfName.getText().toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = HttpUtils.postData(obj);
		
		if(response == null) {
			intent.putExtra("code", "3");
		}
		else {
			JSONObject responseJson = null;
			try {
				responseJson = new JSONObject(response);
			} catch (JSONException e) {
			}
			
			if(responseJson == null) {
				intent.putExtra("code", "3");
			}
			else {
				intent.putExtra("code", "3");
				try {
					if(responseJson.getString("Status").equals("ok")) {
						intent.putExtra("Password", responseJson.getString("Password"));
						intent.putExtra("Name", tfName.getText().toString());
						intent.putExtra("code", "1");
					}
					else {
						intent.putExtra("code", "2");
					}
				} catch (JSONException e) {
				}
			}
		}
		
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
