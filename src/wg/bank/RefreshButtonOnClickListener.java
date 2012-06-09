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
			obj.put("Number", "01706218975");
			obj.put("Date", "0");
			obj.put("AuthPerson", "e90143a899bfffe3de43907c12f558789537af4d");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(refreshButton.getContext(), "JSONException1", Toast.LENGTH_LONG).show();
		}
		String response = HttpUtils.postData(obj);
		if(response == null) 
		{
			Toast.makeText(refreshButton.getContext(), "sorry", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(refreshButton.getContext(), response, Toast.LENGTH_LONG).show();
			JSONObject responseJson;
			try {
				responseJson = new JSONObject(response);
				parseJsonResponse(responseJson);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void parseJsonResponse(JSONObject responseJson) {
		
	}

}
