package bank.entities;

public class Person {
	public int ID;
	public double balance;
	public String name;
	
	public Person(){
		this.ID=0;
		this.balance=0;
		this.name=null;
	}
	
	public Person(int ID, double balance, String name){
		this.ID=ID;
		this.balance=balance;
		this.name=name;
	}
	
	
	
	
	
	
}