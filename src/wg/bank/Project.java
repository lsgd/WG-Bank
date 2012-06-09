package wg.bank;
import java.util.ArrayList;


public class Project {
	
	
	//the name of the project
	public String projectname;
	//the unique project ID
	public int project_ID;
	//the List containing the participants of the project
	public ArrayList<Person> participants;
	
	
	/*
	 * the constructor, creating a Project with an ArrayList of participants
	 * @param projectname the name of the project
	 * @param project_ID the ID of the project
 	*/
	public Project(String projectname, int project_ID,
			ArrayList<Person> participants) {
		this.projectname = projectname;
		this.project_ID = project_ID;
		this.participants = participants;
	}
	
	
	/*
	 * add a Person
	 * @param the person to add
	 */
	public void addPerson(Person person){
		this.participants.add(person);
	}
	
	/*
	 * process a payment, the person who paid gets the amount added to their balance, the rest get the relative amount substracted from their balance
	 */
	
	public void processPayment(Payment payment){
		double rel= payment.amount/this.participants.size();
		for(int i=0; i<this.participants.size();i++){
			if(this.participants.get(i).ID==payment.paying_Person.ID)this.participants.get(i).balance=this.participants.get(i).balance+payment.amount-rel;
			else this.participants.get(i).balance=this.participants.get(i).balance-rel;
		}
	}
	/**
	/*
	 * process a payment and change the balance of the project participants
	 * @param payment the payment to process
	 */
	
	/**
	public void processPayment(Payment payment){
		ArrayList<Person_Payment> list= payment.payment_Distribution;
		for(int i=0;i<list.size();i++){
			for(int j=0;j<this.participants.size();i++){
				if(list.get(i).person_ID==this.participants.get(j).ID){
					this.participants.get(j).balance=(this.participants.get(j).balance+payment.amount);
			}
		
		}
		}
	}
	*/
	

	
}
