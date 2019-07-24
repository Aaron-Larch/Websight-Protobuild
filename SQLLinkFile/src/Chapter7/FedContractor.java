package Chapter7;

public class FedContractor extends Person{
	String [] info = new String [6];
	public FedContractor(String a, String b, String c, String d, String e) {
		super((a), (b.concat(" "+c)), (d.concat(" "+e))); 
	}

}
