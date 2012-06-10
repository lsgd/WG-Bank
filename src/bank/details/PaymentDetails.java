package bank.details;

import java.util.Date;

import android.app.Activity;
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
        EditText tf_project = (EditText) findViewById(R.details.project);       
        EditText tf_name = (EditText) findViewById(R.details.payment_name);
        EditText tf_date = (EditText) findViewById(R.details.payment_date);
        EditText tf_payer = (EditText) findViewById(R.details.payer);
        EditText tf_amount = (EditText) findViewById(R.details.payment_amount);
        
        tf_project.setText("Test");
        tf_name.setText("Test");
        tf_date.setText("Test");
        tf_payer.setText("Test");
        tf_amount.setText("Test");
    }
}