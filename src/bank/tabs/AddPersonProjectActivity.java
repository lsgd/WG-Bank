package bank.tabs;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import bank.general.R;
import bank.general.R.addperson;
import bank.general.R.layout;
import bank.utils.HttpUtils;
import bank.utils.Utils;



public class AddPersonProjectActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.addpersonproject);
		  
		  Intent intent = getIntent();
		  TextView bfProject = (TextView)findViewById(R.addperson.project);
		  bfProject.setText(intent.getStringExtra("ProjectName"));
	}
	
	public void submitButton(View v) {
		EditText tfPhone = (EditText) findViewById(R.addperson.tf_phone);
		
		SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
		int code = addPersonToProject(getIntent().getStringExtra("Project"), sp.getString("Number", ""), tfPhone.getText().toString(), Utils.sha1(sp.getString("Number", "") + sp.getString("Password", "")));
		
		Intent intent = getIntent();
		intent.putExtra("code", code);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	public void cancelButton(View v) {
		Intent intent = getIntent();
		intent.putExtra("code", -1);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	protected int addPersonToProject(String Project, String Number, String NumberPerson, String AuthPerson) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("Action", "person-project-add");
			obj.put("Project", Project);
			obj.put("Number", Number);
			obj.put("NumberPerson", NumberPerson);
			obj.put("AuthPerson", AuthPerson);
		} catch (JSONException e) {
			return 0;
		}
		String response = HttpUtils.postData(obj);
		
		if(response == null) {
			return 1;
		}
		else {
			JSONObject responseJson = null;
			try {
				responseJson = new JSONObject(response);
			} catch (JSONException e) {
			}
			
			if(responseJson == null) {
				return 2;
			}
			else {
				try {
					if(responseJson.getString("Status").equals("ok")) {
						return 5;
					}
					else {
						return 4;
					}
				} catch (JSONException e) {
					return 3;
				}
			}
		}
	}
}
