package bank.details;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import bank.entities.Person;
import bank.entities.Project;
import bank.general.R;

public class PaymentDetails extends Activity {
	
	public Project relevant_Project; //Name des Projektes zu der Zahlung
	public String name; //Name der Zahlung (z.B. Essen bei Silvio)
	public Date date; // Datum der Zahlung
	public Person paying_Person; // zahlende Person
	public double amount; // Höhe der Zahlung


	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        setContentView(R.layout.details);         
        EditText tf_project = (EditText) findViewById(R.details.payment_project_edit);       
        EditText tf_name = (EditText) findViewById(R.details.payment_name_edit);
        EditText tf_date = (EditText) findViewById(R.details.payment_date_edit);
        EditText tf_payer = (EditText) findViewById(R.details.payer_edit);
        EditText tf_amount = (EditText) findViewById(R.details.payment_amount_edit);
        
        Intent intent = getIntent();
        
        tf_project.setText(intent.getStringExtra("project"));
        tf_name.setText(intent.getStringExtra("paym_subject"));
        tf_date.setText(intent.getStringExtra("date"));
        tf_payer.setText(intent.getStringExtra("person"));
        tf_amount.setText(intent.getStringExtra("amount"));
    }
}