package newfolder;

public class ClassCallTest {

	public ClassCallTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonInfo Person1=new PersonInfo();
		PersonInfo Person2=new PersonInfo();
		
		Person1.setPersonID(1);
		Person1.setFirstName("Keith");
		Person1.setLastName("VonDerLinden");
		Person1.setTricareRegion("West");
		
		System.out.println(Person1.getPersonID()+", "+Person1.getFirstName()+
				", "+Person1.getLastName()+", "+Person1.getTricareRegion());
		
		Person2.setPersonID(2);
		Person2.setFirstName("Eliana");
		Person2.setLastName("Diosa");
		Person2.setTricareRegion("West");
		
		System.out.println(Person2.getPersonID()+", "+Person2.getFirstName()+
				", "+Person2.getLastName()+", "+Person2.getTricareRegion());
		
	}

}
