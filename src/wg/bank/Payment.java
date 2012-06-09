package wg.bank;

import java.util.Date;

//import java.util.ArrayList;

public class Payment {
	
	public String name;
	public Date date;
	public double amount;
	public Person paying_Person;
	public Project relevant_Project;
	//public ArrayList<Person_Payment> payment_Distribution;
	
	/*
	 * create a new Payment
	 * @param amount the overall amount of the payment
	 * @param paying_Person the person who paid the amount
	 * @param
	 * @param
	 */
	public Payment(String name, Date date ,double amount, Person paying_Person, Project relevant_Project) {
		this.amount = amount;
		this.paying_Person = paying_Person;
		this.relevant_Project = relevant_Project;
		this.date=date;
		this.name=name;
		//this.payment_Distribution=payment_Distribution;
	}
	
	
}
