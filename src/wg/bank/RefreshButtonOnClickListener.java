package wg.bank;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class RefreshButtonOnClickListener implements OnClickListener {

	final static String PERSON_PROJECT = "PersonProject";
	final static String PAYMENT = "Payment";
	final static String PAYMENT_PERSONS = "PaymentPersons";
	final static String STATUS = "Status";
	final static String DATE = "Date";
	
	OurSQLiteHelper ourSQLiteHelper;
	
	String currentDate = "0";
	
	public void onClick(View refreshButton) {
		// TODO Auto-generated method stub
		Toast.makeText(refreshButton.getContext(), "refresh", Toast.LENGTH_LONG).show();
		
		ourSQLiteHelper = new OurSQLiteHelper(refreshButton.getContext());
		
		//refreshButton.
		JSONObject obj = new JSONObject();
		try {
			obj.put("Action", "data");
			obj.put("Number", "01706218975");
			obj.put("Date", currentDate);
			obj.put("AuthPerson", "e90143a899bfffe3de43907c12f558789537af4d");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(refreshButton.getContext(), "JSONException1", Toast.LENGTH_LONG).show();
		}
		String response = HttpUtils.postData("http://wgbank.lukas-schulze.de/index.php", obj);
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
	
	private void parseJsonResponse(JSONObject responseJson) {		
		try {
//			String date = responseJson.get(DATE).toString();
//			Object personProject = responseJson.get(PERSON_PROJECT);
//			ourSQLiteHelper.insertPayments(createContentValuesForPayment(responseJson.get(PAYMENT)));
			JSONArray paymentJSON = responseJson.getJSONArray(PAYMENT);
			ourSQLiteHelper.insertPayments(createContentValuesForPayment(paymentJSON));
//			Object paymentPersons = responseJson.get(PAYMENT_PERSONS);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<ContentValues> createContentValuesForPayment(JSONArray paymentArray) {
		ArrayList<ContentValues> contentValuesArray = new ArrayList<ContentValues>();
		
		for(int i = 0; i < paymentArray.length(); i++)
		{
			JSONObject paymentObject;
			try {
				paymentObject = paymentArray.getJSONObject(i);
				int id = Integer.parseInt(paymentObject.getString("payment"));
				int project = Integer.parseInt(paymentObject.getString("project"));
				String name = paymentObject.getString("purpose");
				String date = paymentObject.getString("date");
				
				ContentValues contentValues = new ContentValues();
				contentValues.put("id", id);
				contentValues.put("project", project);
				contentValues.put("purpose", name);
				contentValues.put("date", date);
				contentValuesArray.add(contentValues);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contentValuesArray;
	}

}
