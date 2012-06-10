package bank.general;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import bank.utils.HttpUtils;
import bank.utils.OurSQLiteHelper;

public class RefreshButtonOnClickListener implements OnClickListener {

	final static String PERSON_PROJECT = "PersonProject";
	final static String PAYMENT = "Payment";
	final static String PROJECT = "Project";
	final static String PAYMENT_PERSONS = "PaymentPersons";
	final static String STATUS = "Status";
	final static String DATE = "Date";
	
	OurSQLiteHelper ourSQLiteHelper;
	
	String currentDate = "0";
	
	public void onClick(View refreshButton) {
		// TODO Auto-generated method stub
		//Toast.makeText(refreshButton.getContext(), "refresh", Toast.LENGTH_LONG).show();
		
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
	
	private void parseJsonResponse(JSONObject responseJson) {		
		try {
//			String date = responseJson.get(DATE).toString();
			JSONArray paymentJSON = responseJson.getJSONArray(PAYMENT);
			ourSQLiteHelper.insertPayments(createContentValuesForPayments(paymentJSON));
			
			JSONArray projectJSON = responseJson.getJSONArray(PROJECT);
			ourSQLiteHelper.insertProjects(createContentValuesForProjects(projectJSON));
			
			JSONArray personProjectJSON = responseJson.getJSONArray(PERSON_PROJECT);
			ourSQLiteHelper.insertPersonProject(createContentValuesForPersonProject(personProjectJSON));
			
			JSONArray paymentPersonsJSON = responseJson.getJSONArray(PAYMENT_PERSONS);
			ourSQLiteHelper.insertPaymentPersons(createContentValuesForPaymentPersons(paymentPersonsJSON));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<ContentValues> createContentValuesForPayments(JSONArray paymentArray) {
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

	private ArrayList<ContentValues> createContentValuesForPersonProject(JSONArray personProjectArray) {
		ArrayList<ContentValues> contentValuesArray = new ArrayList<ContentValues>();
		
		for(int i = 0; i < personProjectArray.length(); i++)
		{
			JSONObject personProjectObject;
			try {
				personProjectObject = personProjectArray.getJSONObject(i);
				String person = personProjectObject.getString("person");
				int project = Integer.parseInt(personProjectObject.getString("project"));
				String name = personProjectObject.getString("name");
				
				ContentValues contentValues = new ContentValues();
				contentValues.put("person", person);
				contentValues.put("project", project);
				contentValues.put("name", name);
				contentValuesArray.add(contentValues);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contentValuesArray;
	}

	private ArrayList<ContentValues> createContentValuesForPaymentPersons(JSONArray paymentPersonsArray) {
		ArrayList<ContentValues> contentValuesArray = new ArrayList<ContentValues>();
		
		for(int i = 0; i < paymentPersonsArray.length(); i++)
		{
			JSONObject paymentPersonsObject;
			try {
				paymentPersonsObject = paymentPersonsArray.getJSONObject(i);
				String person = paymentPersonsObject.getString("person");
				int payment = Integer.parseInt(paymentPersonsObject.getString("payment"));
				float costs = Float.parseFloat(paymentPersonsObject.getString("costs"));
				
				ContentValues contentValues = new ContentValues();
				contentValues.put("person", person);
				contentValues.put("payment", payment);
				contentValues.put("costs", costs);
				contentValuesArray.add(contentValues);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contentValuesArray;
	}

	private ArrayList<ContentValues> createContentValuesForProjects(JSONArray projectArray) {
		ArrayList<ContentValues> contentValuesArray = new ArrayList<ContentValues>();
		
		for(int i = 0; i < projectArray.length(); i++)
		{
			JSONObject projectObject;
			try {
				projectObject = projectArray.getJSONObject(i);
				int project = Integer.parseInt(projectObject.getString("project"));
				String name = projectObject.getString("name");
				
				ContentValues contentValues = new ContentValues();
				contentValues.put("project", project);
				contentValues.put("name", name);
				contentValuesArray.add(contentValues);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return contentValuesArray;
	}
}
