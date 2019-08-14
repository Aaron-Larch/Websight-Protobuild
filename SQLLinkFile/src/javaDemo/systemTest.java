package javaDemo;


/**
 * @author gce
 *
 */
public class systemTest {
	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args){
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
