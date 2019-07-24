package scrapCode;

import java.io.IOException;

public class VarargTest {

	public VarargTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("please type in some words then hit enter");
		String sample;
		sample=VarargTest.InputWords();
		showChar(sample.toCharArray());
		System.out.println("the whole sentence you typed was:"+"\n"+ sample);
	}
	public static String InputWords() throws IOException {
		String Input="";
		int i;
		while((i=System.in.read())!='\n') {
				System.out.println("This key was pressed: "+(char)i);
				Input+=(char)i;
		}
		return Input.trim();
	}
	public static void showChar(char...letter){
		for(char i:letter)
		{System.out.println("This key was pressed: "+i);}
		System.out.println("The enter key was pressed.");
		
	}
}
