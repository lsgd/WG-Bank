package tabs.projecttabs;


import bank.general.R;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class Projectscreen_Tabs extends TabActivity{
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main2);

	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, tabs.projecttabs.PersonActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("start").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_start))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, tabs.projecttabs.EventActivity.class);
	    spec = tabHost.newTabSpec("Event").setIndicator("Event",
	                      res.getDrawable(R.drawable.history))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, tabs.projecttabs.SubmitEventActivity.class);
	    spec = tabHost.newTabSpec("New Event").setIndicator("Neu",
	                      res.getDrawable(R.drawable.add))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(3);
	}
}

