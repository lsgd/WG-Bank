package tabs.projecttabs;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import bank.general.R;

public class EventActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  this.setContentView(R.layout.zahlung_list);
		  
		  ListView lv = (ListView) findViewById(R.id.list_zahlung);
		  ArrayList<HashMap<String, String>> myListing = new ArrayList<HashMap<String, String>>();
		  SimpleAdapter saList;
		  
		  saList= new SimpleAdapter(
			        this,
			        myListing,
			        R.layout.zahlung_list_element,
			        new String[] { "Datum","Betreff", "Betrag", "Bezahlt"},
			        new int[] { R.id.Datum, R.id.Betreff, R.id.Betrag, R.id.Bezahltvon}  );
			lv.setAdapter( saList);
		 // ListAdapter adapter =new ArrayAdapter<String>(this, R.layout.person_list_element, SOURCE);
		 // lv.setAdapter(adapter);
			lv.setOnItemClickListener(new OnItemClickListener() {
			    public void onItemClick(AdapterView<?> parent, View view,
			        int position, long id) {
			      // When clicked, show a toast with the TextView text
			      Intent intent= new Intent().setClass(getBaseContext(),AddPersonActivity.class);
			      startActivity(intent);
			    }
			  });
		 
		  //setContentView(lv);
			for (int i=0; i<=4; i++) {
			     HashMap item = new HashMap();
			     item.put("Datum", "25.05.12");
			     item.put("Betreff", "Risikokapital");
			     item.put("Betrag", "3,58");
			     item.put("Bezahlt", "Anja");

			     myListing.add(item);
			}
			saList.notifyDataSetChanged();
		  
	}
}
