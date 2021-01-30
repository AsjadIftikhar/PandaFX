BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Semester" (
	"title"	varchar,
	"startDate"	date,
	"endDate"	date,
	"withDrawDate"	date,
	CONSTRAINT "Semester_pk" PRIMARY KEY("title")
);
CREATE TABLE IF NOT EXISTS "Course" (
	"title"	varchar NOT NULL,
	"code"	varchar,
	"credit_hrs"	int NOT NULL,
	"semTitle"	varchar,
	CONSTRAINT "Course_pk" PRIMARY KEY("code"),
	FOREIGN KEY("semTitle") REFERENCES "Semester"
);
CREATE TABLE IF NOT EXISTS "acOfficer" (
	"ID"	int,
	"salary"	integer,
	"email"	varchar,
	"name"	varchar NOT NULL,
	"dob"	date NOT NULL,
	"discipline"	varchar NOT NULL,
	"campus"	varchar NOT NULL,
	"contact"	varchar,
	"gender"	char NOT NULL,
	CONSTRAINT "Teacher_pk" PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "Section" (
	"title"	char,
	"capacity"	int DEFAULT 50,
	"courseCode"	varchar,
	CONSTRAINT "Section_pk" PRIMARY KEY("title","courseCode"),
	FOREIGN KEY("courseCode") REFERENCES "Course" on delete cascade
);
CREATE TABLE IF NOT EXISTS "CourseRegistration" (
	"grade"	char,
	"rollnum"	varchar,
	"sectitle"	char,
	"CourseCode"	varchar,
	"semTitle"	varchar,
	PRIMARY KEY("rollnum","sectitle","CourseCode","semTitle"),
	FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") on delete cascade,
	FOREIGN KEY("semTitle") REFERENCES "Semester"("title") on delete cascade,
	FOREIGN KEY("courseCode","sectitle") REFERENCES "Section"("courseCode","title") on delete cascade
);
CREATE TABLE IF NOT EXISTS "Attendance" (
	"date"	date,
	"status"	char DEFAULT null,
	"rollnum"	varchar,
	"sectitle"	char,
	"CourseCode"	varchar,
	"semTitle"	varchar,
	PRIMARY KEY("rollnum","sectitle","CourseCode","semTitle","date"),
	FOREIGN KEY("courseCode","sectitle") REFERENCES "Section"("courseCode","title") on delete cascade,
	FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") on delete cascade,
	FOREIGN KEY("semTitle") REFERENCES "Semester"("title") on delete cascade
);
CREATE TABLE IF NOT EXISTS "Evaluations" (
	"title"	varchar,
	"weight"	float,
	"totalMarks"	int,
	"obtainedMarks"	int,
	"rollnum"	varchar,
	"sectitle"	char,
	"CourseCode"	varchar,
	"semTitle"	varchar,
	PRIMARY KEY("title","rollnum","sectitle","CourseCode","semTitle"),
	FOREIGN KEY("semTitle") REFERENCES "Semester"("title") on delete cascade,
	FOREIGN KEY("courseCode","sectitle") REFERENCES "Section"("courseCode","title") on delete cascade,
	FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") on delete cascade
);
CREATE TABLE IF NOT EXISTS "Waiting_List_Queue" (
	"Id"	int,
	"rollnum"	varchar,
	"sectitle"	char,
	"CourseCode"	varchar,
	"semTitle"	varchar,
	PRIMARY KEY("Id","rollnum","sectitle","CourseCode","semTitle"),
	FOREIGN KEY("courseCode","sectitle") REFERENCES "Section"("courseCode","title") on delete cascade,
	FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") on delete cascade,
	FOREIGN KEY("semTitle") REFERENCES "Semester"("title") on delete cascade
);
CREATE TABLE IF NOT EXISTS "Messages" (
	"Messages"	varchar,
	"rollnum"	varchar,
	"sectitle"	char,
	"CourseCode"	varchar,
	"semTitle"	varchar,
	PRIMARY KEY("rollnum","sectitle","CourseCode","semTitle"),
	FOREIGN KEY("courseCode","sectitle") REFERENCES "Section"("courseCode","title") on delete cascade,
	FOREIGN KEY("rollnum") REFERENCES "Student"("rollNumber") on delete cascade,
	FOREIGN KEY("semTitle") REFERENCES "Semester"("title") on delete cascade
);
CREATE TABLE IF NOT EXISTS "Teacher" (
	"ID"	int,
	"salary"	integer,
	"email"	varchar,
	"name"	varchar NOT NULL,
	"dob"	date NOT NULL,
	"discipline"	varchar NOT NULL,
	"campus"	varchar NOT NULL,
	"contact"	varchar,
	"gender"	char NOT NULL,
	"password"	varchar DEFAULT 'password',
	CONSTRAINT "Teacher_pk" PRIMARY KEY("ID")
);
CREATE TABLE IF NOT EXISTS "Student" (
	"rollNumber"	varchar,
	"batch"	int NOT NULL,
	"cgpa"	float,
	"name"	varchar NOT NULL,
	"dob"	date NOT NULL,
	"discipline"	varchar NOT NULL,
	"campus"	varchar NOT NULL,
	"contact"	varchar,
	"gender"	char NOT NULL,
	"email"	varchar,
	"password"	varchar DEFAULT 'password',
	CONSTRAINT "Student_pk" PRIMARY KEY("rollNumber")
);
CREATE TABLE IF NOT EXISTS "Teacher_Section" (
	"name"	varchar,
	"sem_title"	varchar,
	"course_code"	varchar,
	"sec_title"	char,
	CONSTRAINT "Teacher_Section_pk" PRIMARY KEY("name","sem_title","course_code","sec_title")
);
INSERT INTO "Semester" VALUES ('Spring19','30/5/2019','30/6/2019','30/6/2019');
INSERT INTO "Semester" VALUES ('Fall19','30/11/2020','30/1/2021','20/2/2021');
INSERT INTO "Semester" VALUES ('Fall18','30/11/2017','1/11/2018','1/11/2018');
INSERT INTO "Course" VALUES ('ITC-Lab','CL117',1,'Fall18');
INSERT INTO "Course" VALUES ('PF-Lab','CL118',1,'Fall18');
INSERT INTO "Course" VALUES ('PF','CS118',3,'Fall18');
INSERT INTO "Course" VALUES ('Applied Physics','EE117',3,'Fall18');
INSERT INTO "Course" VALUES ('Cal','MT119',3,'Fall18');
INSERT INTO "Course" VALUES ('Eng','Q233',3,'Fall18');
INSERT INTO "Course" VALUES ('Eng-Lab','Qs233',1,'Fall18');
INSERT INTO "Course" VALUES ('ISl','ISl11',3,'Fall18');
INSERT INTO "Course" VALUES ('Object Oriented Programming - Lab','CL217',1,'Spring19');
INSERT INTO "Course" VALUES ('Object Oriented Programming','CS217',3,'Spring19');
INSERT INTO "Course" VALUES ('Digital Logic Design','EE227',3,'Spring19');
INSERT INTO "Course" VALUES ('Digital Logic Design - Lab','EL227',1,'Spring19');
INSERT INTO "Course" VALUES ('Differential Equations (Cal II','MT224',3,'Spring19');
INSERT INTO "Course" VALUES ('Communication & Presentation Skills - Lab','SL152',1,'Spring19');
INSERT INTO "Course" VALUES ('Pakistan Studies','SS113',3,'Spring19');
INSERT INTO "Course" VALUES ('Communication & Presentation Skills','SS152',2,'Spring19');
INSERT INTO "Course" VALUES ('Fyp-1','Q1111',4,'Fall19');
INSERT INTO "Course" VALUES ('Fyp-11','Q1112',4,'Fall19');
INSERT INTO "Course" VALUES ('Fyp-111','Q1113',4,'Fall19');
INSERT INTO "Section" VALUES ('A',5,'CL117');
INSERT INTO "Section" VALUES ('A',5,'CL118');
INSERT INTO "Section" VALUES ('A',5,'EE117');
INSERT INTO "Section" VALUES ('A',5,'MT119');
INSERT INTO "Section" VALUES ('A',5,'Q233');
INSERT INTO "Section" VALUES ('A',5,'Qs233');
INSERT INTO "Section" VALUES ('A',5,'ISl11');
INSERT INTO "Section" VALUES ('A',5,'CS118');
INSERT INTO "Section" VALUES ('B',5,'CL117');
INSERT INTO "Section" VALUES ('B',5,'CL118');
INSERT INTO "Section" VALUES ('C',5,'CL118');
INSERT INTO "Section" VALUES ('D',5,'CL117');
INSERT INTO "Section" VALUES ('F',5,'CL118');
INSERT INTO "Section" VALUES ('B',5,'CL217');
INSERT INTO "Section" VALUES ('B',5,'CS217');
INSERT INTO "Section" VALUES ('B',5,'EE227');
INSERT INTO "Section" VALUES ('B',5,'EL227');
INSERT INTO "Section" VALUES ('B',5,'MT224');
INSERT INTO "Section" VALUES ('B',5,'SS152');
INSERT INTO "Section" VALUES ('B',5,'SL152');
INSERT INTO "Section" VALUES ('B',5,'SS113');
INSERT INTO "Section" VALUES ('B',5,'Q1111');
INSERT INTO "Section" VALUES ('B',5,'Q1112');
INSERT INTO "Section" VALUES ('B',5,'Q1113');
INSERT INTO "CourseRegistration" VALUES ('B+','L18-1109','A','CL117','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B+','L18-1109','A','CL118','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B-','L18-1109','A','CS118','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B-','L18-1109','A','EE117','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B','L18-1109','A','MT119','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B','L18-1109','A','Qs233','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B-','L18-1109','A','ISl11','Fall18');
INSERT INTO "CourseRegistration" VALUES ('A-','L18-1109','A','Q233','Fall18');
INSERT INTO "CourseRegistration" VALUES ('B-','L18-1109','B','CL217','Spring19');
INSERT INTO "CourseRegistration" VALUES ('B','L18-1109','B','CS217','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C','L18-1109','B','EE227','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C+','L18-1109','B','EL227','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C+','L18-1109','B','MT224','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C+','L18-1109','B','SL152','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C','L18-1109','B','SS113','Spring19');
INSERT INTO "CourseRegistration" VALUES ('C','L18-1109','B','SS152','Spring19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-2179','B','Q1111','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-1017','B','Q1111','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-0982','B','Q1111','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-0951','B','Q1112','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-2179','B','Q1112','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-1017','B','Q1112','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-0982','B','Q1112','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-0951','B','Q1113','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-2179','B','Q1113','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-1017','B','Q1113','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L18-0982','B','Q1113','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L16-4141','B','Q1111','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L16-4141','B','Q1112','Fall19');
INSERT INTO "CourseRegistration" VALUES ('l','L16-4141','B','Q1113','Fall19');
INSERT INTO "CourseRegistration" VALUES ('-','L18-0951','B','Q1111','Fall19');
INSERT INTO "Attendance" VALUES ('10/12/2020','A','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('1/12/2020','P','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('5/12/2020','A','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('18/12/2020','P','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('20/12/2020','A','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('2/12/2020','P','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('3/12/2020','A','L18-1109','B','SS152','Spring19');
INSERT INTO "Attendance" VALUES ('10/12/2020','A','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('1/12/2020','P','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('5/12/2020','A','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('18/12/2020','P','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('20/12/2020','A','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('2/12/2020','P','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('3/12/2020','A','L18-1109','B','SS113','Spring19');
INSERT INTO "Attendance" VALUES ('10/12/2018','A','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('1/12/2018','P','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('5/12/2018','A','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('18/12/2018','P','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('20/12/2018','A','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('2/12/2018','P','L18-1109','A','CL117','Fall18');
INSERT INTO "Attendance" VALUES ('3/12/2018','A','L18-1109','A','CL117','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid1',20.0,50,49,'L18-1109','A','CL117','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid2',30.0,50,30,'L18-1109','A','CL117','Fall18');
INSERT INTO "Evaluations" VALUES ('Final',30.0,50,39,'L18-1109','A','CL117','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid1',20.0,50,44,'L18-1109','A','CL118','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid2',30.0,50,37,'L18-1109','A','CL118','Fall18');
INSERT INTO "Evaluations" VALUES ('Final',30.0,50,39,'L18-1109','A','CL118','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid1',20.0,50,42,'L18-1109','A','EE117','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid2',30.0,50,34,'L18-1109','A','EE117','Fall18');
INSERT INTO "Evaluations" VALUES ('Final',30.0,50,37,'L18-1109','A','EE117','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid1',20.0,50,40,'L18-1109','A','MT119','Fall18');
INSERT INTO "Evaluations" VALUES ('Mid2',30.0,50,20,'L18-1109','A','MT119','Fall18');
INSERT INTO "Evaluations" VALUES ('Final',30.0,50,42,'L18-1109','A','MT119','Fall18');
INSERT INTO "Waiting_List_Queue" VALUES (3,'L16-4200','B','Q1111','Fall19');
INSERT INTO "Waiting_List_Queue" VALUES (2,'L19-1234','B','Q1111','Fall19');
INSERT INTO "Waiting_List_Queue" VALUES (1,'L16-4200','B','Q1112','Fall19');
INSERT INTO "Waiting_List_Queue" VALUES (2,'L18-1109','B','Q1112','Fall19');
INSERT INTO "Waiting_List_Queue" VALUES (1,'L18-1109','B','Q1111','Fall19');
INSERT INTO "Teacher" VALUES (1,10,'ishaq.raza@nu.pk','Ishaq Raza','1/2/1930','BSCS','Lahore','0300-Ishaq','K','password');
INSERT INTO "Teacher" VALUES (2,20,'lodhi@nu.pk','Kamran Lodhi','2/3/1980','BSCS','Lahore','0312-Lodhi','M','password');
INSERT INTO "Teacher" VALUES (3,100000,'amara.rafiq@nu.pk','Amara Rafiq','3/4/1995','BSCS','Lahore','0345-Amara','F','password');
INSERT INTO "Teacher" VALUES (4,200,'Amir@nu.pk','Amir Raheem','4/5/1980','BSCS','Lahore','0321-Amir','M','password');
INSERT INTO "Student" VALUES ('L18-1109',18,2.99,'M.Zain','1/1/98','BSCS','Lahore','03324228581','M','l181109@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-0951',18,3.1,'Asjad','25/1/96','BSCS','Lahore','03126783142','M','l180951@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-2179',18,3.89,'Haseeb Ahmed','3/3/96','BSCS','Lahore','03046472005','M','l182179@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-1017',18,3.1,'Tayyab Waseem','4/4/97','BSCS','Lahore','03377442695','M','l181017@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-0914',18,3.5,'Fazle Moiz','4/4/99','BSCS','Lahore','03124479018','M','l180914@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-0982',18,3.9998,'Sabeeh Saeed','5/5/96','BSCS','Lahore','03008406659','M','l180982@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-panda',18,3.5,'Panda Zen1','1/1/90','BSCS','Lahore','12345678','F','l181111@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L18-zen3',18,2.5,'zen3 ','2/2/90','BSCS','Lahore','87654321','F','l182222@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L16-4141',16,2.6,'Ali iqbal','28/11/96','BSCS','Lahore','23456','M','l164141@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L19-1234',19,NULL,'Ahmed','1/1/20','BSCS','Lahore','1111111','M','l191234@lhr.nu.edu.pk','password');
INSERT INTO "Student" VALUES ('L16-4200',16,3.1,'Stack Top','2/3/96','BSEE','Lahore','I wish','F','l164200@lhr.nu.edu.pk','password');
INSERT INTO "Teacher_Section" VALUES ('Ishaq Raza','Spring19','CL217','B');
INSERT INTO "Teacher_Section" VALUES ('Ishaq Raza','Spring19','SS113','B');
INSERT INTO "Teacher_Section" VALUES ('Ishaq Raza','Spring19','SL152','B');
COMMIT;
