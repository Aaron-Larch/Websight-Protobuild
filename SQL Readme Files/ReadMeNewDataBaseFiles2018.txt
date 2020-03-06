Readme for the installation of the Eastwind Compony Database.


Eastwind Database:

1.  Ensure SQL Developer and SQL Database software have been previously installed correctly on laptop.

2.  Open SQL Developer.

3.  In SQL Developer, create a new connection called "admin" in SQL Developer, with the administrator username and password.

4.  In Notepad or Notepad ++, open the user creation file script file to create a new user.  Copy all the lines of script in that file.

5.  In SQL Developer, paste in the copied user creation file script to create a new user.  Run all of those lines in SQL Developer.

6.  In SQL Developer, create a new connection titled by the database name e.g. "Eastwind."  The admin connection must still be 
up and running, but the user name and password for this new connection should be the user level, e.g. student/student.
  
7.  In SQL Developer, disconnect from the administrator connection.

8.  In Notepad or Notepad ++, open the data creation file script file to populate data in the database.  Copy all the lines of script in that file.

9.  In SQL Developer, paste in the copied data creation file script to create the tables and insert data.

10.  In SQL Developer, test that the tables are populated with data.  E.g., SELECT * FROM DEPENDENTS;

(Possible may need to clear out tables and restart....E.g., drop TABLE DEPENDENTS; or delete connections; or delete user
from the admin level)