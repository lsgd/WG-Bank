package tabs.projecttabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bank.general.R;
import bank.general.RefreshButtonOnClickListener;
import bank.utils.OurSQLiteHelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class PersonActivity extends Activity {
	
	private static final String[] SOURCE = {"Lukas", "Anja", "Mandy", "Saskia", "Leon"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  
	 
	  setContentView(R.layout.persons_tab);
	  
	  
	  
	  ListView lv = (ListView) findViewById(R.id.list_persons);
	  OurSQLiteHelper sqliteHelper = new OurSQLiteHelper(lv.getContext());
	  
	  Map<String, String> personAccount = sqliteHelper.selectPaymentPersons(1);
	  
	  ArrayList<HashMap<String, String>> myListing = new ArrayList<HashMap<String, String>>();
	  SimpleAdapter saList;
	  
	  saList= new SimpleAdapter(
		        this,
		        myListing,
		        R.layout.person_list_element,
		        new String[] { "line1","line2", "button"},
		        new int[] { R.id.name, R.id.balance, R.id.listbutton}  );
		lv.setAdapter( saList);
	 // ListAdapter adapter =new ArrayAdapter<String>(this, R.layout.person_list_element, SOURCE);
	 // lv.setAdapter(adapter);
	  
	 
	  //setContentView(lv);
//		for (String name : personAccount.keySet()) {
//		    String account = personAccount.get(name);
		for (int i=0; i<=4; i++){
		    HashMap item = new HashMap();
//		     item.put("line1", name);
//		     item.put("line2", account);
		     item.put("line1", SOURCE[i]);
		     item.put("line2", "3,58 Euro");
		     item.put("button", "X");

		     myListing.add(item);
		}
		saList.notifyDataSetChanged();
	  
	 
	}
	public void addPerson(View v){
		Intent intent;
		intent= new Intent().setClass(this, tabs.projecttabs.AddPersonActivity.class);
		startActivity(intent);
	}
	
}
