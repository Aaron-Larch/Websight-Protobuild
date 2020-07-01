package com.webbuild.javabrains.Operations;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import com.webbuild.javabrains.model.Reports;

/**
 * @author gce
 *
 */
public class systemTest {
	/**
	 * @param args
	 * @throws IOException 
	 */
	
	//Java testing location Holds an execute method for purposes of development
	public static void main(String[] args){
		//Export();
		try {
			List<String> inputPath=new ArrayList<String>();
			inputPath.add("C:/Users/gce/Desktop/Timesheet Tracker.xlsx");
			//inputPath.add("C:/Users/gce/Desktop/Project summory version 2..docx");
			
			ArrayList<String> ccEmail=new ArrayList<String>();
			//ccEmail.add("sal.horquita@gce.org");
			//ccEmail.add("a.larch@yahoo.com");
			
			EmailEngine.send(
					"smtp.office.com",
					"a.larch@yahoo.com",
					"aaron.larch@gce.org",
					ccEmail,
					false,
					"My War Report",
					"I'm sending you this with my cusom dynamic Email server. because this is way more fun than Navigating Cytrix. "+
					" will also be makeing a new update to my Git hub server next week looking forward to that.",
					inputPath);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//run project as a method
	public static void Export(){
		//raw data
		int[] length = {26,35,20,15,25};
		int[] random = {50,45,40,35,30};
		BuildPath Build = new BuildPath();
		Reports[][] box = Build.JavaInterFaceBuildArray(length, random); //use the data to populate the objects
		System.out.println("The titles of the files stroed and the number of records in each file are:");
		for(int j=0; j < box.length; j++) {
			int count=0;
			if(box[j] != null) {
				for(int i=0; i<box[j].length; i++) {if(box[j][i]!=null) {count++;}}
				int a = box[j][0].getreportId().indexOf('-');
				System.out.println(box[j][0].getreportId().substring(0, a)+": "+count);
				}
		}
		SimpleSerch.JavaInterFaceSearch(box);
	}
}
