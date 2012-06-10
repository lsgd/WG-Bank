package bank.project;

import org.json.JSONException;
import org.json.JSONObject;

import bank.general.R;
import bank.utils.HttpUtils;
import bank.utils.Utils;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewProjectActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.add_project);
		
	}
	
	public void sendAddProject(View v) {
		EditText tf_projectname = (EditText) findViewById(R.add_project.name_edit);
		SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);;
		
		JSONObject obj = new JSONObject();
		JSONObject project = new JSONObject();
		try {
			Editable e_projectName = tf_projectname.getText();
			
			obj.put("Action", "project-add");
			obj.put("AuthPerson", Utils.sha1(sp.getString("Number", "") + sp.getString("Password", "")));
			obj.put("Number", sp.getString("Number", ""));
			obj.put("Name", e_projectName.toString());
			
			Toast.makeText(v.getContext(), HttpUtils.postData(obj), Toast.LENGTH_LONG).show();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
