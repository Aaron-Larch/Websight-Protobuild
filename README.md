# Websight-Protobuild
build a set of Aarays and then by user request take those array and build a report
The Basic Idea: What can you do with an array of data?  How can you group and store an array of data?  How can you input and organize data?  How can you display Data? can I write a program that dose all 4 of these things?

Version 1.0 test to see if java compiler is working.
To do this I like to write a simple sort function. The sort function will work when any list of numbers given to it will be sorted into numerical order:
	To do this we need to use two steps
	is value one greater than value two
If it is not, then swap value one and two
Swap using a temp value to hold data before its erased
That’s done now to write some data for the program.

Version 1.1 Writing data by hand is boring I remember a collage class talked about random number generators.  Let’s build a random number generator to make the data to sort for us.
All you need to make a random number is the built in random number generator subroutine “rand.nextInt(range).” And you wil get a random number from 0 to the given range.

The problem is that a random number is just a number and we need an array of numbers so I made a second value size to represent the number of values in an array.  Put in a for loop to populate the data. so now with two inputs we can make and sort as many numbers as we like. This is when the methods
public int[] rArrayInt(int size, int range)
public double[] rArrayDouble(int size, int range)
public float[] rArrayFloat(int size, int range)
and
public static void sort(int[] array, String order)
were added to the project

Version 2.0
Now that we have the data generator what can we do with it?  I have no idea. But let’s to the classic Statistical Operations Mean, Median, Mode, min, max:
Average/Mean: (x1+x2+…xn)/n
Simple enough put a for loop on that and set n to array length.

Median: x=n/2 or (X((n/2)-1)+X(n/2))/2
Tricky there are two ways to find a mean. If the code is to work for any given data regardless of size, then there needs to be a way to check if the array has an even or odd number of entry’s my solution is to make an if statement and look for the remainder.  You can do that with the % symbol.
**IMPORTANT NOTE: When finding the median of a list with even numbers the order matters so make sure the list sorts from low to high or else you formula will be off**

Mode: there is no equation for this.
Mode is tricky you need to run this like a sort. “if value 1 = value 2: store value 1”  but that’s not really going to work because that logic only works if a number appears 2 or more time so if you get 2 4 values and 4 2 values both will be stored when only the 4 2 values are the correct mode. So there needs to be two checks is temp grater than 1 and is the count higher than the recorded count total.  If an array has 2 modes then add a third check dose temp=the recorded mode value.  Addisonaly  you can add:
&& mode.contains(i)==false. to remove duplicate numbers from your print out statement.
Min & Max
The method to find these values are so similar that I combined then into one method to save of redundant code.  To get the min value sort low to high and take the first value of the array. To get the max value sort highest to lowest and take the first value.
 Methods created for this version were
public static double[] sortEX(double[] array, String order)
public static double average(double...array)    
Used double…array instead of double[] array because it looked neat and I didn’t know you could code like that.
public static Double minMax(double[] array, String choice)
public static double median(double...array) {
public static List<Double> mode(double...array)
Also the sort method was changed to private. No reason really. I did that just because I could. 

Version 3.0 Learning new things
I was researching System.in.read() What is was and how to use it.  I found two resources that really inspired me and taught me a lot of new coding techniques.  The first resources was “Examples of class: Using System.in.read() Instructor: Mainak Chaudhuri mainakc@cse.iitk.ac.in” (See PDF attachment for more) and the web site https://coderanch.com/t/275045/java/Loop-System-read-weirdness which gave me a fascinating alternate approach to Mainak Chaudhuri lecture.  This is also the point where I started experimenting with Object storage and Getter and Setter methods.  Due to the projects rapidly increasing size and the introduction to getters and setters I split the project into several classes:
public class Reports be sure to set a name value to make fining the object easier
public class Statistics
public class Store
public class SwitchBoard
and
public class systemTest This is where is tested and worked on code

The object class, Reports was pretty straight forward put a variable for each operation you want saved add a name value and then make sure all your getter and setter variable types match with the stored value name.  
Mr. Chaudhuri’s lecture is broken down into three parts that I worked with separately to better understand and modify till they met my project requirements.  He starts by defining how to store a single key stroke like this:
// Read a character 
public char ReadChar()  throws java.io.IOException{ return (char)System.in.read(); }
after a bit of work I replaced the “throws java.io.IOException” with a try/catch block I did this because it makes the method more self-contained and simpler for external files to call. There by making it more end user friendly.  The next part of Mr. Chaudhuri’s lecture was how to save multiple key strokes:
	// Read multiple characters
public String ReadString(int howmany) throws java.i o.IOException{ 
String str= “”; 
for (Int k=0; k<howmany; k++) { str+= (char)System.in.read(); } 
return str; }
I did not like this approach either because it required for knowing the exact number of characters required before going into the method.  After a bit of web searching and testing I arrived at this pice of logic. “while((i=System.in.read())!='\n') {Input+=(char)i;}”  and “System.in.skip(i);” witch gives me better buffer control and empties the buffer at the end of each use.  Though maybe a flush command might work better?  Then I found this web-site https://coderanch.com/t/275045/java/Loop-System-read-weirdness witch really caught my imagination with its error handling approach so I studied the code samples and then reworked it to fit into my code.  The Idea is that you can use a nested do/while loop to ignore the return key until after the user starts typing in other values first.  In the example the programmer used the s key to force the loop to close I chose a Boolean flag instead seemed more profeshinal.
	After I found my Program for collecting user input I followed Mr. Chaudhuri’s lecture and repeated my code for each variable type.  The Third and final part of his lecture was this really cool while loop that just seemed so neat that I had to add it to my code.  He uses a While loop and a Switch Statement to take in user input and preform basic mathematical operation that the user requests.  The code then checks if the user wants to leave, if no. the code goes back to the top to the loop.  What I changed was the inputs instead of adding two values the code would first generate an object and ask the user what Statistical operations they wish to perform on the array.  The input is then turned into an array and put into a for loop containing the Switch Statement. I then replaced the calculation operations with my statistical operations and paired it with the matching setter methods. Then I asked the user if they wanted to continue. If yes, the loop starts again only with a count incremented by 1 to give each object created with this method a unique name.
Methods created were
public char symbol()
public String words()
public int number()
public double complex()
public float value()
public String altstyle() throws IOException
public static Reports[] StatbuildEX10(double[] input, String name)
public static int[] buildArray(String name)
public String getreportId()
public void setreportId(String rID) 
public double[] gethighC()
public void sethighC(double[] hiC) 
public double[] getlowC()
public void setlowC(double[] loC)
public double getmax()
public void setmax(double mx)
public double getmin()
public void setmin(double mn)
public double getaverage()
public void setaverage(double avg)
public double getmedian()
public void setmedian(double md)
public List<Double> getmode()
public void setmode(List<Double> mod)
public void showRecord()

Version 3.1 Multi-Dimensional Arrays
One idea to expand the content that could be saved is through the use of double arrays.  With one array variable you could store 100’s of arrays. But let’s keep it to 5.  So there was no real template but after a bit of experimentation that I can’t really remember but, the end result was that I found out that I could take two single arrays of 5 numbers and combine them to form a Multi-Dimensional Array that could  auto generate large amounts of arrays to work with and test for very little work. Turning this method
public static int[] buildArray(String name) {
	System.out.println("Enter the size of Your Array:");
	int size = scan.number(); // Scans the next token of the input as an int.
	System.out.println("Enter the upper range of Your Array:");
	int range = scan.number(); // Scans the next token of the input the range of random numbers.
	// once finished
	int[] array = scan.rArrayInt(size, range);
System.out.print("This is your array " + name + ". This array has "+ 
Math.abs(size) + " enteries ant the largest number is " + range + "\n" + name + ": " + Arrays.toString(array) + "\n");
	return array;
}
Into this:
public double[][] arrayBoxDouble(int[] size, int[] range, String name){
	double[][] arrayBox = new double [size.length][];
	for(int i=0; i<arrayBox.length; i++) {
		arrayBox[i]=scan.rArrayDouble(size[i], range[i]);
System.out.print("This is your array " + name + ". This array has " + Math.abs(size[i]) + " enteries and the largest number is " + range[i] + "\n" + name + ": " + Arrays.toString(arrayBox[i]) + "\n");
	}
return arrayBox;
}
The code is much smaller and simpler to understand as well as generating a large number of arrays for very little work.  The other Multi-Dimensional Arrays you could make is an array of objects.  The idea is that with an array of objects you can store a unique set of values for each array.  
double[][] array =Build.arrayBoxInt(length,random, name);
Reports[] box=SwitchBoard.StatbuildEX10(array,name);
Now you have one variable containing information on multiple operations for multiple arrays.
You can then go one level deeper and have multiple  Reports[] box variables. Make a Reports[][] variable add a for loop and change
Reports[] box=SwitchBoard.StatbuildEX10(array,name); 
to 
Reports[i] box=SwitchBoard.StatbuildEX10(array,name);
 The final part is to expand on the for loop
what if you wanted to change the name?
what if you want to leave early?
What if you wanted to change the name more than three times?
What if you wanted to delete a record?
What if the user makes a mistake?
this is where I started thinking about my code in a more overall science form more than just a developer trying to get code to work and more as an end user.  This is where I went back through my code and did a massive revision to incorporate these 5 questions to every part of the code I had from:
building arrays
running statistical operations
user input
creating objects.

Class created
public class BuildPath

Methods Added
public double[][] arrayBoxInt(int[] size, int[] range, String name)
public double[][] arrayBoxDouble(int[] size, int[] range, String name)
public static int[] buildArray(String name)
public Reports[] BuildRecord(int[] length, int[] random )
public  Reports[][] buildNetwork(int[] length, int[] random)
There is a bit of redundancy due to the code reaching such a large size that these smaller intermediate methods increased production speed.

Version 4.0  Now that we have all this data created and organized what do we do with it?
By now I have a program that allows a user to create, process, and store large amounts of data with minimal effort on the part of the user and print out a representation of all the data stored.  But now what do we do with this information?
While searching the web for this answer I found this sight: https://www.mathsisfun.com/data/index.html#stats.  So I thought why not add that as a next step?  So we need to preform Standard deviation, sample variance, Q1 ,Q2, Q3, and Q4 values and the  total and Q2-Q3 range, as well as a frequency table with bins for a histogram.
	But what record do I want to test? It will be a mess if I have to test all 45 records at once. So why not build a search engine like the one google has. Only a lot smaller and scaled down in function so I might actually live long enough to see it coded.  After a bit of thought I decided that the search engine needs to have key words to search by.  The key words I chose to search for are:
Primary key, Value, Operator(>,<,=), parameter 
The primary key value is easy just refer to the print statement for available  key titles.
next is the big problem: how do you convert a user input string into a method call.  According to this page: https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
You can use a reflect and invoke statements like this:
val = file[i][j].getClass().getDeclaredMethod(name); 
Object space = val.invoke(file[i][j]); 
Using this approach you can turn any method call into a string.  To make thing safer I’m going to create a switch statement that will convert normal words into the actual method call commands.  The next part is to convert the comparator stamen >,<,=, ect. From the string into something that java could understand. To do this I make a second switch statement to convert the sting into the desired operation.
Set the flag values to true and add a nested for loop and the search method is complete.  To further restrict the results make a user choice loop to put only the search results they want into the final report. This could turn 45 records into 3 a much easier number to work with.

Version 4.1
	The final step it so create the final round of formulas
 sample variance=(∑(Xi-mean)^2)/(n-1): a simple formula to follow a for loop like the one used to find the mean should be enough
Standard deviation= the square root of sample variance: A continuation of sample variance put both operation in one statement
Q0=min: simple already have this value
 Q1=the median of Q0&Q2:
 Q2= the median: simple already have this value
 Q3= the median of Q2&Q4:
 Q4 =max: simple already have this value
Q1 and Q3 are tricky you need to find the median or an array that is half the size of the staring array.  The question is how do you get an array to start from the halfway point and move forward.  I never found a good answer so I just ran a count variable with an if statement to see which of two arrays the value goes into.  The really difficult part was to make sure that the arrays were even regardless if the starting array was an even or odd number. In the case of the odd the number you need to skip the median value. 
frequency table is just the count of every accurance of every value  the set up looks very similar to the mode function. For an even easier time try using hash maps.  The real problem is with the bins for a histogram. Bins are a grouping of numbers that consist of a certain number of values from the range of 0 and the max value of the given array.  There is no set formula for the ideal number of bins or the ideal range of values with in a bin.  So I made one up.

the minimum number of buckets is 4
the minimum range inside a bucket is 5
the maximum number of buckets is 15

I then put these rules into a for loop and built a multi-dimensional array to hold them.
Server set up
The Problem: Why Will my external Java Project not Compile or run in my Server project? 
The Rest API server Has 4 requirements that must be met if it is to read any external Java Project First step is to import the project proper.  The processes are Pretty straight forward any google search will revile how to do this.  Once the project you wish to import is identified you may need to Identify you projects path. Newer versions of Eclipse IDE java have a new form of java projects called a Mode.  A simple Google search will revile the necessary steps to create a mode project.  One of the steps to create a mode project is clicking the Mode-Info file check box in project creation menu.  If this box is accidently clicked nothing will happen excepted for a Mode-Info.java file appearing in your src folder.  This file will not negatively affect your Java coding or your ability to compile the code in your project regardless of whether your building a standard class base project or a mode based project.  However, if you build a class based program with a mode-info.java folder and then attach it to the build path of another project, nothing will happen, it will throw errors at your code clamming that the project is unaccusable but it will still run.  The reason for this is that your new project thinks that the imported project is both a modepath project and a Classpath project at the same time.  To fix the problem just delete mode-info.java from the projects src folder.  I did not find this solution through any google searches.  This was based on an educated guess when I learned what a Mode Java project was.
Once you Fixed your project so that it can be read in the build path you need to attach the needed libraries to the file. In the build path menu open the project tab and you see a tab Called “Native Library Location: none.”  Tis needs to be changed to match your projects library location.  Then this needs to be added to the WEB-INF.lib so your server can compile the libraries.  This can be done automatically through the quick fix suggestions in your Eclipse Problems tab viable if not by default then by selecting the option from the “Windows->Show View->problems.”  This will add a META-MF file to the external project. This step is optional, but, Eclipse may give the warning icon on the project if this action is not taken.  This information was discovered through the problem tab in Eclipse as opposed to looking it up on line.
Step three you need to turn the project into a jar file for the server to compile and load to the web page.  This is done in the “Properties->Development Assembly.” Click the add button and then click the projects option from the menu and then select the matching project from your build path.  This information can be found via a google search with the key words “Import External projects into a RestAIP server.”  It will reveal how to get the build path and the jar file built if you read all of the pages.
Step four is optional, but, if after preforming steps one through three your project metadata becomes Corrupted and your entire workbench becomes unusable, then you might have a version conflict.  This information may be found on google under “How to fix metadata” But I found this solution by scrolling through the properties menus and seeing which files had errors.  Using this method I found that the main server project and the external java Project were using different versions of java (v.11 and v.9)  to fix this go to properties, and then to Java compiler. Set both versions to the same compiler and the clean the projects if errors persist.
After that the projcect can be called using the import stamen. “import Package.class” after that the code can be called like any other java method.     
To set up a servlet the basic form of a servlet has three distinct parts.  Here is the template I used to build and understand how to make a servlet
https://docs.oracle.com/cd/E13222_01/wls/docs92/webapp/configureservlet.html

The other part of this template is how to make a “Bootstrap Modal”
https://www.w3schools.com/bootstrap/bootstrap_modal.asp
The demo covers how all the parts of a JSP page work together to make a web page.

Version 5.0 convert the logic path in java to a servlet basted program
	The idea here is to create the exact same program path with the same loops in the java projects with the added requirements that the API must be in three distinct parts:
All the user input is in the HTML
All data management must be handled in the Servlet
All processing is done in an external java class
Pages stored outside of the Web.xml file requires a   <welcome-file-list> entry so the API can see the page from the local host without a welcome page entry the page will not load on the server.  Use this format to add a page: <welcome-file>IntroPage.jsp</welcome-file>.  Servlets work off a get/post method get methods retrieve data from some somewhere and send it somewhere else. A post method retrieves information from a source preforms and operation and sends the result somewhere else.  A second thing a servlet cannot do is use the java console for System.out.print statements.  They need to be converted to a byte array buffer 
//Create a stream to hold the output
	 	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	PrintStream ps = new PrintStream(baos);
	 	// IMPORTANT: Save the old System.out!
	 	PrintStream old = System.out;
	 	// Tell Java to use your special stream
	 	System.setOut(ps);
	 	// Print some output: goes to your special stream
	 	double[][] array=CallApplet.arrayBoxInt(length, random, name);
	 	// Put things back
	 	System.out.flush();
	 	System.setOut(old);
The other thing to rembure is that HTML data can only be strings so using this guide.
https://stackoverflow.com/questions/4253660/passing-an-object-from-jsp-page-back-to-servlet
the big thing to convert objects into strings using this method.
String ObjectId = UUID.randomUUID().toString();
request.getSession().setAttribute(ObjectId, rep);

request.setAttribute("Record", ObjectId); 

another thing to keep in mind is that the dopost and doget methods work like full java programs and will not pause for user input like a normal java program. So if you still have code at the end of you page load statement it will continue to run in the background.
The pages are split into three different types of pages and each page is split into an input and output mode.  The first type of page is a modal. Modals as described on the w3Schools are dynamic popup windows that appear over a web page and do simple tasks of hold a servlet.  The Modal acts like a subroutine and I use it to run the public  Reports[][] buildNetwork(int[] length, int[] random)  Method in the main java project.  The other type of page is the main page type and is divided into two states a display state and a input state there can only be one state visible at a time.  To control the stats I set this code to a load function:
function SwichLoadout(a){
	if(a==1){
		[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
		  	i.style.display="block";});
		[].forEach.call(document.querySelectorAll(".display"), function (i) {
		  	i.style.display="none";});
		}else{
			[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
		  		i.style.display="none";});
			[].forEach.call(document.querySelectorAll('.display'), function (i) {
		  		i.style.display="block";});
		}
	}
The pages are paired to these methods.
Display public double[][] arrayBoxInt(int[] size, int[] range, String name)
Input public static Reports[] StatbuildEX10(double[][] input, String name)

Display public static Reports[] StatbuildEX10(double[][] input, String name)
Input public static void SearchEngein(Reports[][] file)

Display public static void SearchEngein(Reports[][] file)
Input public static void complexOps(Reports[] temp)

The third page type is a load page type and has a servlet that runs only once on the page load and that is done with a <jsp:include page="/PrintFinalData" /> This page can only display data sets created by the code.
