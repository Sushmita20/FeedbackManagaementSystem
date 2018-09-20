
--Sequence Creation :

CREATE sequence TrainingCode_seq start with 10001 increment by 1;     --For training code
CREATE sequence CourseId_seq start with 101 increment by 1;         --For Course Id


--EMPLOYEEMASTER TABLE:

CREATE table EMPLOYEE_MASTER(
EmployeeId number(5) PRIMARY KEY,
EmployeeName varchar2(20),
Password varchar2(20),
Role varchar2(8));
--COURSEMASTER TABLE:

CREATE table COURSE_MASTER(CourseId number(5) PRIMARY KEY,
courseName varchar2(15),noOfDays number(5));

--TRAININGPROGRAM TABLE:

CREATE table TRAINING_PROGRAM(TrainingCode number(5) PRIMARY KEY ,coursecode number(5) REFERENCES COURSE_MASTER(courseId) ON DELETE CASCADE,
facultycode number(5) REFERENCES Employee_Master(EmployeeId) ON DELETE CASCADE,
startDate date,endDate date,CHECK (endDate>startDate));


--FACULTYSKILL TABLE:

CREATE table FACULTY_SKILL(FacultyId number(5) REFERENCES Employee_Master(EmployeeId) ON DELETE CASCADE,
skillset varchar2(25));

--TRAINING-PARTICIPANTENROLLMENT TABLE:

CREATE table TRAININGPARTICIPANT_ENROLLMENT(
Trainingcode number(5) REFERENCES TRAINING_PROGRAM(TrainingCode) ON DELETE CASCADE,
ParticipantId number(5) REFERENCES Employee_Master(EmployeeId) ON DELETE CASCADE,CONSTRAINT pk_enrollment PRIMARY KEY(Trainingcode,ParticipantId));

--FEEDBACKMASTER TABLE:

CREATE table FEEDBACK_MASTER(TrainingCode number(5) REFERENCES TRAINING_PROGRAM(TrainingCode) ON DELETE CASCADE,
ParticipantId number(5) REFERENCES Employee_Master(EmployeeId) ON DELETE CASCADE,
FB_Prs_comm number(1),FB_Clrfy_dbts number(1),
FB_TM number(1),FB_Hnd_out number(1),
FB_Hw_Sw_Ntwrk number(1),
Comments varchar2(20),
Suggestions varchar2(20),
fbdate DATE,
CHECK (FB_Prs_comm BETWEEN 1 AND 5),
CHECK (FB_Clrfy_dbts BETWEEN 1 AND 5),
CHECK (FB_TM BETWEEN 1 AND 5),
CHECK (FB_Hnd_out BETWEEN 1 AND 5),
CHECK (FB_Hw_Sw_Ntwrk BETWEEN 1 AND 5),
CONSTRAINT pk_enrollment PRIMARY KEY(Trainingcode,ParticipantId));

--Employee master details:
INSERT into EMPLOYEE_MASTER values(1000,'Admin','admin@123','admin');

INSERT into EMPLOYEE_MASTER values(1001,'Trainer1','trainer1@123','coordinator');

INSERT into EMPLOYEE_MASTER values(1002,'Trainer2','trainer2@123','coordinator');

INSERT into EMPLOYEE_MASTER values(1004,'Trainee1','trainee1@123','participant');

INSERT into EMPLOYEE_MASTER values(1005,'Trainee2','trainee2@123','participant');

--FACULTY_SKILL details:
INSERT into FACULTY_SKILL values(1001,'Java,C++,MainFrames');
INSERT into FACULTY_SKILL values(1002,'Oracle,.Net,Java');

--Course master details:
INSERT into course_master values(501,'Java',45);

INSERT into course_master values(502,'Oracle',35);

INSERT into course_master values(503,'.Net',40);

INSERT into course_master values(504,'C++',30);

--TRAININGPROGRAM details:

INSERT into TRAINING_PROGRAM values(10000,501,1001,'01-feb-2018','17-mar-2018');

INSERT into TRAINING_PROGRAM values(10001,502,1002,'10-feb-2018','18-mar-2018');

INSERT into TRAINING_PROGRAM values(10002,503,1002,'15-feb-2018','27-mar-2018');

INSERT into TRAINING_PROGRAM values(10004,504,1001,'01-mar-2018','30-mar-2018');

--TRAINING-PARTICIPANTENROLLMENT details:
INSERT into TRAININGPARTICIPANT_ENROLLMENT values(10000,1004);

INSERT into TRAININGPARTICIPANT_ENROLLMENT values(10000,1005);

INSERT into TRAININGPARTICIPANT_ENROLLMENT values(10001,1004);

INSERT into TRAININGPARTICIPANT_ENROLLMENT values(10001,1005);

--FEEDBACKMASTER details:

--INSERT into FEEDBACK_MASTER values(10000,1004,4,5,4,4,5,'Good','TimeManagement',SYSDATE,'Trainee1');
--INSERT into FEEDBACK_MASTER values(10001,1005,3,4,5,4,4,'Good','communication skills',SYSDATE,'Trainee2');

