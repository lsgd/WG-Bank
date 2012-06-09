package wg.bank;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StartActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Das ist der Start-Tab");
        setContentView(R.layout.person_list_element);
    }
}