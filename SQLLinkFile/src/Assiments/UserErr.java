package Assiments;

public class UserErr {

	public static String[] ErrMsg={
			"The user entered more than one entery before hitting the Enter Key.", 
			"The key entered is to high a value",
			"the user did not enter a value withing the bounds of the array",
			"The user did not enter a reconiszed key",

		};
	public static String UserErr(int i) {
		String message = ErrMsg[i];
		return (message);

	}
}
