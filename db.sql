
create table stakeholder(role varchar(255) not null, Name varchar(255) not null,Id varchar(255) not null,password varchar(255) not null);

--insert stakeholder
insert into stakeholder values('St','Avnish','2020csb1078','avnish');
insert into stakeholder values('St','Raj','2020csb1079','raj');
insert into stakeholder values('St','Krishna','2020csb1080','krishna');
insert into stakeholder values('St','Pankaj','2020csb1081','pankaj');
insert into stakeholder values('Fc','Cs1nil','cs1nil','cs1nil');
insert into stakeholder values('Fc','Cs2nil','cs2nil','cs2nil');
insert into stakeholder values('Fc','Me1nil','me1nil','me1nil');
insert into stakeholder values('Ao','Jagpreet','jagpreet','jagpreet');



--create table department
create table Department(dept_name varchar(255),PRIMARY KEY (dept_name));

--insert department
insert into department values('HS');
insert into department values('SC');
insert into department values('SE');
insert into department values('GE');
insert into department values('CS');
insert into department values('CE');
insert into department values('EE');
insert into department values('ME');
insert into department values('MNC');
insert into department values('CH');
insert into department values('MM');



create table course_catalog(course_id varchar(255),title varchar(255),dept_name varchar(255),LTPSC varchar(255),credit dec(10,2),Primary Key (course_id),Foreign Key (dept_name) references Department(dept_name));


--hs core min(3)
insert into course_catalog values('HS101','History of Technology','HS','3-1-0-5-1.5',1.5);
insert into course_catalog values('HS201','Economics','HS','3-1-0-5-3',3);

--hs elective (1.5)
insert into course_catalog values('HS103','History of tech','HS','3-1-0-5-1.5',1.5);
insert into course_catalog values('HS203','History of eco','HS','3-1-0-5-1.5',1.5);

--science core(3)
insert into course_catalog values('PH101','Physics for Engineers','SC','3-1-0-5-3',3);
insert into course_catalog values('CY101','Chemistry for Engineers','SC','3-1-2-6-4',4);

--science elective (3)
insert into course_catalog values('PH111','Physics','SE','3-1-0-5-3',3);
insert into course_catalog values('CY111','Chemistry','SE','3-1-2-6-4',4);

--ge (3)
insert into course_catalog values('GE107','tinkering LAB','GE','0-0-3-3/2-1.5',1.5);
insert into course_catalog values('GE108','Basic Electronics','CS','2-2/3-2-13/3-3',3);

--capstone (3)
insert into course_catalog values('CP101','Basic Electronics','CS','0-0-3-3-3',3);

--pc (3)
--pc ch(3)
insert into course_catalog values('CH101','Thermodynamics','CH','3-1-0-5-3',3);
insert into course_catalog values('CH202','Transport Phenomena','CH','3-1-0-5-3',3);

--pc CIV(3)
insert into course_catalog values('CE101','Strength of Materials','CE','2-1-2-4-3',3);
insert into course_catalog values('CE202','Fundamentals of Fluid Mechanics','CE','2-1-0-3-2',2);

--pc cs
insert into course_catalog values('CS201','Data Structures','CS','3-1-2-6-3',3);
insert into course_catalog values('CS202','Programming Paradigms and Pragmatics','CS','3-1-2-6-4',4);

--pc EE
insert into course_catalog values('EE201','Signals and Systems','EE','3-1-0-5-3',3);
insert into course_catalog values('EE203','Digital Circuits','EE','3-1-0-5-3',3);

--pc MNC
insert into course_catalog values('MA411','Real Analysis ','MNC','3-1-0-5-3',3);
insert into course_catalog values('MA201','Differential Equations','MNC','3-1-0-5-3',3);

--pc ME
insert into course_catalog values('ME102','Engineering Thermodynamics','ME','3-1-0-5-3',3);
insert into course_catalog values('ME201','Solid Mechanics','ME','3-1-0-5-3',3);

--pc MM
insert into course_catalog values('MM201','Metallurgical Thermodynamics and
Kinetics','MM','3-1-0-5-3',3);
insert into course_catalog values('MM202','Transport Phenomena in Materials','MM','3-1-0-5-3',3);

--pe (3)
--pe ch
insert into course_catalog values('CH111','Thermodynamics','CH','3-1-0-5-3',3);


--pe CIV
insert into course_catalog values('CE111','Strength of Materials','CE','2-1-2-4-3',3);


--pe cs
insert into course_catalog values('CS211','Data Structures','CS','3-1-2-6-3',3);


--pe EE
insert into course_catalog values('EE211','Signals and Systems','EE','3-1-0-5-3',3);


--pe MNC
insert into course_catalog values('MA413','Real Analysis ','MNC','3-1-0-5-3',3);


--pe ME
insert into course_catalog values('ME112','Engineering Thermodynamics','ME','3-1-0-5-3',3);


--pe MM
insert into course_catalog values('MM211','Metallurgical Thermodynamics and Kinetics','MM','3-1-0-5-3',3);

--op (3)
insert into course_catalog values('MM000','Meta open','MM','3-1-0-5-3',3);
insert into course_catalog values('ME000','Mech open','ME','3-1-0-5-3',3);
insert into course_catalog values('MN000','MAth open','MNC','3-1-0-5-3',3);
insert into course_catalog values('CS000','Cs open','CS','3-1-0-5-3',3);
insert into course_catalog values('CH000','Ch open','CH','3-1-0-5-3',3);
insert into course_catalog values('CE000','CE open','CE','3-1-0-5-3',3);
insert into course_catalog values('EE000','EE open','EE','3-1-0-5-3',3);

--faculty create
create table Faculty
(
    name varchar(255),
    id varchar(255),
    dept_name varchar(255),
    PRIMARY KEY(id),
    Foreign KEY (dept_name) REFERENCES Department(dept_name)
);

--insert faculty
insert into faculty values('Cs1nil','cs1nil','CS');
insert into faculty values('Cs2nil','cs2nil','CS');
insert into faculty values('Hs1nil','hs1nil','HS');
insert into faculty values('Hs2nil','hs2nil','HS');
insert into faculty values('Ge1nil','ge1nil','GE');
insert into faculty values('Ge2nil','ge2nil','GE');
insert into faculty values('Ce1nil','ce1nil','CE');
insert into faculty values('Ce2nil','ce1nil','CE');
insert into faculty values('Ee1nil','ee1nil','EE');
insert into faculty values('Ee2nil','ee2nil','EE');
insert into faculty values('Me1nil','me1nil','ME');
insert into faculty values('Me2nil','me2nil','ME');
insert into faculty values('MNC1nil','mnc1nil','MNC');
insert into faculty values('MNC1nil','mnc1nil','MNC');
insert into faculty values('Ch1nil','ch1nil','CH');
insert into faculty values('Ch2nil','ch2nil','CH');
insert into faculty values('Mm1nil','mm1nil','MM');
insert into faculty values('Mm2nil','mm2nil','MM');

--prerequisite
CREATE TABLE Pre_Requisite
(
Course_id varchar(255),
preRequisite_course_code varchar(255),
foreign key (Course_id) references Course_Catalog(Course_id),
foreign key (preRequisite_course_code) references Course_Catalog(Course_id)
);

--course_offering
CREATE TABLE Course_Offerings
(
Course_id varchar(255),
dept_name varchar(255),
semester int,
credit dec(10,2),
Instructor_id varchar(255),
LTPSC varchar(255),
cgConstraint dec(10,2),
foreign key (Course_id) references Course_Catalog(Course_id),
foreign key (dept_name) references Department(dept_name),
foreign key (Instructor_id) references Faculty(id)
);

--course_registered_by_faculty
create table courses_enrolled_by_student(

);

