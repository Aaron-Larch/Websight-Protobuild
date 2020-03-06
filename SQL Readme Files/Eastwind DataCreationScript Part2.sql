
set echo on
SET SCAN OFF
SET AUTOCOMMIT ON

/* Set DATEFORMAT so that the date strings are interpreted correctly.*/
alter session set nls_date_format='mm-dd-yyyy hh24:mi';

drop table reservations;
drop table conferencerooms;

CREATE TABLE ConferenceRooms
(RoomID	  int         not null
,Name	  varchar(20) not null
,Capacity int         not null)
/
ALTER TABLE ConferenceRooms
ADD CONSTRAINT PK_ConferenceRooms 
PRIMARY KEY (RoomID)
/
CREATE TABLE Reservations
(ReservationID int  not null
,RoomID        int  not null
,EmployeeID    int  not null
,StartTime     date not null
,EndTime       date not null)
/
ALTER TABLE ConferenceRooms
MODIFY Name varchar(30)
/

ALTER TABLE Reservations
ADD CONSTRAINT PK_Reservations 
PRIMARY KEY (ReservationID)
/


ALTER TABLE Reservations
ADD CONSTRAINT FK_Reservations_Employees
FOREIGN KEY (EmployeeID)
REFERENCES Employees(EmployeeID)
/
ALTER TABLE Reservations
ADD CONSTRAINT CK_Reservations_Times
CHECK(StartTime < EndTime)
/
ALTER TABLE Reservations
ADD CONSTRAINT FK_Reservations_ConferenceRoom
FOREIGN KEY (RoomID)
REFERENCES ConferenceRooms(RoomID)
/

CREATE  INDEX Customers_CompanyName ON Customers(CompanyName)
/
insert into  ConferenceRooms(RoomID,Name,Capacity)
VALUES(101,'Auditorium',250);
insert into  ConferenceRooms(RoomID,Name,Capacity)
VALUES(220,'2nd Floor Big room',30);
insert into  ConferenceRooms(RoomID,Name,Capacity)
VALUES(225,'2nd Floor Small room',10);
insert into  ConferenceRooms(RoomID,Name,Capacity)
VALUES(300,'3rd Floor Small room',10);
insert into  ConferenceRooms(RoomID,Name,Capacity)
VALUES(310,'3rd Floor Tiny room',6);


insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(1,101,'11-20-2006 08:00','11-20-2006 17:00',1);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(2,101,'12-22-2006 09:00','12-22-2006 10:00',2);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(3,300,'12-22-2006 09:00','12-22-2006 10:00',3);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(4,101,'12-22-2006 11:00','12-22-2006 13:00',3);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(5,220,'12-22-2006 10:30','12-22-2006 14:00',2);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(6,220,'12-22-2006 08:00','12-22-2006 10:00',1);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(7,220,'12-22-2006 10:00','12-22-2006 12:00',3);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(8,220,'12-15-2006 09:30','12-15-2006 10:30',3);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(9,220,'12-15-2006 11:00','12-15-2006 12:00',3);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(10,300,'12-15-2006 11:00','12-15-2006 14:00',1);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(11,101,'12-22-2006 08:00','12-22-2006 17:00',2);
insert into  Reservations(ReservationID,RoomID,StartTime,EndTime,EmployeeID)
VALUES(12,220,'12-22-2006 16:30','12-22-2006 17:00',2);



SET AUTOCOMMIT OFF

