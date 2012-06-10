package bank.tabs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import bank.general.R;
import bank.general.RefreshButtonOnClickListener;
import bank.utils.OurSQLiteHelper;

public class StartActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.project_list);
		
		ListView lv = (ListView) findViewById(R.id.project_list);
		  OurSQLiteHelper sqliteHelper = new OurSQLiteHelper(lv.getContext());
		  
		  Map<String, String> personAccount = sqliteHelper.selectPaymentPersons(1);
		  
		  ArrayList<HashMap<String, String>> myListing = new ArrayList<HashMap<String, String>>();
		  SimpleAdapter saList;
		  
		  saList= new SimpleAdapter(
			        this,
			        myListing,
			        R.layout.project_list_element,
			        new String[] { "projectname", "balance"},
			        new int[] { R.id.projectname, R.id.projectbalance}  );
			lv.setAdapter( saList);
		 // ListAdapter adapter =new ArrayAdapter<String>(this, R.layout.person_list_element, SOURCE);
		 // lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
			      // When clicked, show a toast with the TextView text
			    	Intent intent = new Intent().setClass(getBaseContext(), tabs.projecttabs.Projectscreen_Tabs.class);
					startActivity(intent);
			    }
			  });
		 
		  //setContentView(lv);
//			for (String name : personAccount.keySet()) {
//			    String account = personAccount.get(name);
			OurSQLiteHelper helper = new OurSQLiteHelper(this);
			Map<String, Float> map = helper.selectProject();
			for (String name : map.keySet()){
			    HashMap item = new HashMap();
//			     item.put("line1", name);
//			     item.put("line2", account);
			     item.put("projectname", name);
			     item.put("balance", "3,58 Euro");

			     myListing.add(item);
			}
			saList.notifyDataSetChanged();
	
	}
	
	public void LaunchProjectScreen(View v){
		Intent intent = new Intent().setClass(this, tabs.projecttabs.Projectscreen_Tabs.class);
		startActivity(intent);
	}
	
	public void LaunchAddProjectScreen(View v){
		Intent intent = new Intent().setClass(this, bank.project.NewProjectActivity.class);
		startActivity(intent);
	}
	
	public void RefreshDatabase(View v){
		RefreshButtonOnClickListener listener = new RefreshButtonOnClickListener();
		listener.onClick(v);
	}
}