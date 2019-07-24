package Chapter7;

public class Dependent extends ActiveMilitary{
	String [] info = new String [6];
	
	public Dependent(String a, String b, String c, String d, String e, String f) {
		super(a, b, c, d);
		// TODO Auto-generated constructor stub
		info[0]=a; info[1]=b; info[2]=c; info[3]=d; info[4]=e; info[5]=f;
	}
	
	@Override
	void showInfo() {
		System.out.println("The information report for this individual is... ");
				for (int i = 0; i < 6; i++) {
					if (info[i]!=null) System.out.print(info[i] + " ");
				}
				System.out.print("\n");
				System.out.println("Remember to fill out the DD7777 FORM for this individual."+"\n");

		}
}
