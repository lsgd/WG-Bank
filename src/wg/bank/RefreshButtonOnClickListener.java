package wg.bank;

import java.io.File;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RefreshButtonOnClickListener implements OnClickListener {

	public void onClick(View refreshButton) {
		// TODO Auto-generated method stub
		Toast.makeText(refreshButton.getContext(), "refresh", Toast.LENGTH_LONG).show();
		//refreshButton.
		JSONObject obj = new JSONObject();
		try {
			obj.put("Action", "data");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj.put("Person", "01706218975");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String response = HttpUtils.postData("http://wgbank.lukas-schulze.de/index.php", obj);
		if(response == null) 
		{
			Toast.makeText(refreshButton.getContext(), "sorry", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(refreshButton.getContext(), response, Toast.LENGTH_LONG).show();
		}
	}

}
