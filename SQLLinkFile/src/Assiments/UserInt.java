package Assiments;

import java.io.IOException;

public class UserInt {

	public UserInt() {
		// TODO Auto-generated constructor stub	
	}
	char input1;
	int input2;
	char ignore;
	//public void UserInt() {}
	public int getUsetInt() {
		try {
			input1=(char)System.in.read();
			ignore=(char)System.in.read();
			input2=(char)System.in.read();
			if(ignore != '\r'| ignore != '\n') {
				System.in.skip(input2);
				System.out.println(UserErr.UserErr(0));
				input1='\0';
			}
			System.in.skip(input2);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input1;
	}
	
	public Boolean checkUserInt(int i) {
		if(i>=0 && i<=9) {
			return true;
		}else {return false;}
	}
}
