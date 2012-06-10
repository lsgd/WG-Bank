package bank.tabs;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import bank.general.R;

public class SettingsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView textview = new TextView(this);
//        textview.setText("Das ist der Einstellungen-Tab");
//        setContentView(textview);
        setContentView(R.layout.settings);
        EditText tf_username = (EditText) findViewById(R.settings.username_edit);
        EditText tf_phonenumber = (EditText) findViewById(R.settings.phonenumber_edit);
        EditText tf_password = (EditText) findViewById(R.settings.password_edit);
        
        SharedPreferences sp = getSharedPreferences("wgbank", MODE_PRIVATE);
       
        tf_username.setText(sp.getString("Name", "not readable"));
        tf_phonenumber.setText(sp.getString("Number", "not readable"));
        tf_password.setText(sp.getString("Password", "not readable"));
    }
}