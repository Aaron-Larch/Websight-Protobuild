package Assiments;

public class ArrayandMore {
	UserInt usr=new UserInt();
	
	public ArrayandMore() {
		// TODO Auto-generated constructor stub
	}
	int[] Arr1= {0, 10, 20, 30, 40};
	//public void ArrayandMore(){}
	public Boolean chechInBounds(int i) {
		if(i>0 && i<Arr1.length) {return true;}
			else {return false;}
		
	}
	public void displayArrayValue(int i, boolean int1, boolean int2) {
		 if(int1=false) {
				System.out.println(UserErr.UserErr(1));
				System.out.println(UserErr.UserErr(3));
			}else if(int2=false){
				System.out.println(UserErr.UserErr(2));
				System.out.println(UserErr.UserErr(3));
			}else {
				System.out.println("the user insered the ASCII value "+i
						+" the array value that shares its velue is: "+Arr1[i]);
			}
	}
	
}
