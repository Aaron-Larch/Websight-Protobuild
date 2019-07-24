package Chapter7;

public class ActiveMilitary extends Person{

	// the ActiveMilitary subclass adds one more field 
    public String Rank; 
  
    // the ActiveMilitary subclass has one constructor 
	public ActiveMilitary(String a, String b, String c, String d) {
		// invoking base-class(Person) constructor 
	    super((a), (b), (c.concat(" "+d))); 
	    
	}
	
}
