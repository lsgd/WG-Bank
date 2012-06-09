package tabs.projecttabs;

import wg.bank.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends Activity {
	
	private static final String[] SOURCE = {"Lukas", "Anja", "Mandy", "Saskia", "Leon"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  
	 
	  setContentView(R.layout.persons_tab);
	  
	  ListView lv = (ListView) findViewById(R.id.list_persons);
	  ListAdapter adapter =new ArrayAdapter<String>(this, R.layout.person_list_element, SOURCE);
	  lv.setAdapter(adapter);
	  
	 
	  //setContentView(lv);
	  
	 
	}
	public void addPerson(View v){
		Intent intent;
		intent= new Intent().setClass(this, AddPersonActivity.class);
	}
	
}
