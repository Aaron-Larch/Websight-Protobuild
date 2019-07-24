package Chapter7;

public class Chapter3Code {

	public Chapter3Code() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Person P1 = new ActiveMilitary("Smith", "Holly", "Italy", "Colonel");
		Person P2 = new FedContractor("Diosa", "Eliana", "USA", "Mid-Level Contractor", "Offsite");
		Person P3 = new Dependent("Smith", "Nathan", "Italy", "Colonel", "Son", "16");

		P1.showInfo();
		P2.showInfo();
		P3.showInfo();		

	}

}


