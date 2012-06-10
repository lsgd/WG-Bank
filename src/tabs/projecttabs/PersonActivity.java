package tabs.projecttabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import bank.general.R;
import bank.general.RefreshButtonOnClickListener;
import bank.utils.OurSQLiteHelper;

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
		intent= new Intent().setClass(this, bank.tabs.AddPersonProjectActivity.class);
		intent.putExtra("Project", "4");
		intent.putExtra("ProjectName", "Project Romanus");
		startActivityForResult(intent, 5);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == 5) {
			evaluateResult(data);
		}
	}
	
	protected void evaluateResult(Intent intent) {
		int code = intent.getIntExtra("code", 0);
		
		if(code == 0 || code == 1 || code == 2 || code == 3) {
			Toast.makeText(getBaseContext(), "Es ist ein Fehler aufgetreten! Die Person wurde nicht hinzugefügt.", Toast.LENGTH_LONG).show();
		}
		else if(code == 4) {
			Toast.makeText(getBaseContext(), "Die angegebene Person existiert nicht in der Datenbank.", Toast.LENGTH_LONG).show();
		}
		else if(code == 5) {
			Intent i = new Intent().setClass(this, tabs.projecttabs.Projectscreen_Tabs.class);
			startActivity(i);
			

			//RefreshButtonOnClickListener listener = new RefreshButtonOnClickListener();
			//listener.onClick(null);
			
			Toast.makeText(getBaseContext(), "Die Person wurde erfolgreich hinzugefügt.", Toast.LENGTH_LONG).show();
		}
		else {
			Intent i = new Intent().setClass(this, tabs.projecttabs.Projectscreen_Tabs.class);
			startActivity(i);
		}
	}
	
}
