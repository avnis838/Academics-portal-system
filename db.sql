--create stakeholder
create table stakeholder(role varchar(255) not null, Name varchar(255) not null,Id varchar(255) not null,password varchar(255) not null);

--insert stakeholder
insert into stakeholder values('St','Avnish','2020csb1078','avnish');
insert into stakeholder values('St','Raj','2020csb1079','raj');
insert into stakeholder values('St','Krishna','2020csb1080','krishna');
insert into stakeholder values('St','Pankaj','2020csb1081','pankaj');
insert into stakeholder values('St','Tejaswini','2020meb1411','tejaswini');
insert into stakeholder values('St','Himanshu','2020mmb1412','himanshu');
insert into stakeholder values('St','Sarthak','2020chb1413','sarthak');
insert into stakeholder values('St','Sahu','2020mcb1414','sahu');
insert into stakeholder values('St','Vedant','2020eeb1415','vedant');
insert into stakeholder values('St','Yogita','2020ch1416','yogita');
insert into stakeholder values('Fc','Cs1nil','cs1nil','cs1nil');
insert into stakeholder values('Fc','Cs2nil','cs2nil','cs2nil');
insert into stakeholder values('Fc','Hs1nil','hs1nil','hs1nil');
insert into stakeholder values('Fc','Ge1nil','ge1nil','ge1nil');
insert into stakeholder values('Fc','Ce1nil','ce1nil','ce1nil');
insert into stakeholder values('Fc','Ee1nil','ee1nil','ee1nil');
insert into stakeholder values('Fc','Me1nil','me1nil','me1nil');
insert into stakeholder values('Fc','Ch1nil','ch1nil','ch1nil');
insert into stakeholder values('Fc','Mm1nil','mm1nil','mm1nil');
insert into stakeholder values('Fc','MNC1nil','mnc1nil','mnc1nil');
insert into stakeholder values('Ao','Jagpreet','jagpreet','jagpreet');



--create table department
create table Department(dept_name varchar(255),PRIMARY KEY (dept_name));

--insert department
insert into department values('HC');
insert into department values('SC');
insert into department values('SE');
insert into department values('GR');
insert into department values('CS');
insert into department values('CE');
insert into department values('EE');
insert into department values('ME');
insert into department values('MNC');
insert into department values('CH');
insert into department values('MM');


--create table for core_courses
create table core_courses(course_id varchar(255),PRIMARY KEY (course_id));

-- insert into core_courses values('HS101');
-- insert into core_courses values('HS201');
-- insert into core_courses values('PH101');
-- insert into core_courses values('CY101');
insert into core_courses values('CH101');
insert into core_courses values('CH202');
insert into core_courses values('CE101');
insert into core_courses values('CE202');
insert into core_courses values('CS201');
insert into core_courses values('CS200');
insert into core_courses values('CS202');
insert into core_courses values('EE201');
insert into core_courses values('EE203');
insert into core_courses values('MA411');
insert into core_courses values('MA201');
insert into core_courses values('ME102');
insert into core_courses values('ME201');
insert into core_courses values('MM201');
insert into core_courses values('MM202');



--create table course_catalog
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
insert into course_catalog values('CS200','Data St','CS','3-1-2-6-3',3);
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

--prereq
insert into pre_requisite values('CS202','CS201');
insert into pre_requisite values('CS202','CS200');
insert into pre_requisite values('CS201','CS200');


--course_offering
CREATE TABLE Course_Offerings
(
Course_id varchar(255),
dept_name varchar(255),
-- semester int,
credit dec(10,2),
Instructor_id varchar(255),
LTPSC varchar(255),
cgConstraint dec(10,2),
foreign key (Course_id) references Course_Catalog(Course_id),
foreign key (dept_name) references Department(dept_name),
foreign key (Instructor_id) references Faculty(id)
);

--course batch allowed
create table Batch_allowed(
    course_id varchar(255),
    batch int
);

--core-electives
create table core_electives(
    course_id varchar(255)
    
);

--course-btp
create table core_btp(
    course_id varchar(255)
    
);






--student_info
CREATE TABLE student_info
(
    entry_num varchar(255),
    student_name varchar(255), 
    yearOfAdmission int,
    dept_name varchar(255),
    cg dec(10,2),
    total_credits dec(10,2),
    --contactno varchar(255),
    primary key(entry_num)
);

--student-sample
insert into student_info values('2020csb1078','Avnish',2020,'CS',6.4,91.5);
insert into student_info values('2020csb1080','Raj',2020,'CS',6.4,91.5);
insert into student_info values('2020csb1081','Krishna',2020,'CS',6.4,91.5);
insert into student_info values('2020csb1079','Pankaj',2020,'CS',6.4,91.5);
insert into student_info values('2020meb1411','Tejaswini',2020,'ME',7.0,91.0);
insert into student_info values('2020mmb1412','Himanshu',2020,'MM',8,92);
insert into student_info values('2020chb1413','Sarthak',2020,'CH',7.33,90.5);
insert into student_info values('2020mnc1414','Sahu',2020,'MNC',7.5,91.0);
insert into student_info values('2020eeb1415','Vedant',2020,'EE',7.8,95.0);
insert into student_info values('2020ceb1416','Yogita',2020,'CE',7.8,93.0);

--course_enrolled_by_student
create table courses_enrolled_by_student
(
    entry_num varchar(255),
    Course_id varchar(255),
dept_name varchar(255),
-- semester int,
credit dec(10,2),
LTPSC varchar(255),
grading_status varchar(255) DEFAULT "U", --U mean ungraded or G mean graded
grade varchar(255) DEFAULT "U",
foreign key(entry_num) references student_info(entry_num),
foreign key (Course_id) references Course_Catalog(Course_id),
foreign key (dept_name) references Department(dept_name)

);


--courses that has been completed by students with their scores in respective courses
create table courses_completed_by_student(
entry_num varchar(255),
    Course_id varchar(255),
dept_name varchar(255),
-- semester int,
credit dec(10,2),
grade varchar(255),
year1 int,
year2 int,
parity int,
foreign key(entry_num) references student_info(entry_num),
foreign key (Course_id) references Course_Catalog(Course_id),
foreign key (dept_name) references Department(dept_name)
);


--initially let some data are already added for 2020csb1078
insert into courses_completed_by_student values('2020csb1078','GE103','GR',4.5,'B',2020,2021,1);
insert into courses_completed_by_student values('2020csb1078','MA101','SC',3,'B-',2020,2021,1);
insert into courses_completed_by_student values('2020csb1078','HS102','HC',3,'B-',2020,2021,1);
insert into courses_completed_by_student values('2020csb1078','PH101','SC',3,'D',2020,2021,1);
insert into courses_completed_by_student values('2020csb1078','GE105','GR',1.5,'C-',2020,2021,1);
insert into courses_completed_by_student values('2020csb1078','CS101','PC',3,'B-',2020,2021,1);

insert into courses_completed_by_student values('2020csb1078','MA102','SC',4,'B-',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','HS101','HC',1.5,'C',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','GE104','GR',3,'C',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','GE101','GR',1,'A-',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','GE102','GR',2,'B',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','CY101','SC',4,'C',2020,2021,2);
insert into courses_completed_by_student values('2020csb1078','PH102','SC',2,'B',2020,2021,2);

insert into courses_completed_by_student values('2020csb1078','CS201','PC',4,'C',2021,2022,1);
insert into courses_completed_by_student values('2020csb1078','MA201','SC',3,'B-',2021,2022,1);
insert into courses_completed_by_student values('2020csb1078','CS203','PC',4,'D',2021,2022,1);
insert into courses_completed_by_student values('2020csb1078','GE107','GR',1.5,'B',2021,2022,1);
insert into courses_completed_by_student values('2020csb1078','HS201','HC',3,'B-',2021,2022,1);
insert into courses_completed_by_student values('2020csb1078','EE201','GR',3,'C',2021,2022,1);

insert into courses_completed_by_student values('2020csb1078','HS202','HC',3,'B',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','MA202','SC',3,'D',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','GE108','GR',3,'B',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','GE109','GR',1,'A-',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','CS202','PC',4,'C',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','CS204','PC',4,'C-',2021,2022,2);
insert into courses_completed_by_student values('2020csb1078','NS101','NN',1,'A',2021,2022,2);

insert into courses_completed_by_student values('2020csb1078','HS104','HC',1.5,'B',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','GE111','GR',3,'A-',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','BM101','SC',3,'B-',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','CS301','PC',4,'D',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','CS302','PC',3,'D',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','NS102','NN',1,'A',2022,2023,1);
insert into courses_completed_by_student values('2020csb1078','CS303','PC',4,'C',2022,2023,1);


--sessions completed table session is y1 - y2 , parity i.e. 1,2 example 2020-2021:1,2020-2021:2, etc.
create table completed_sessions(
    id int,year1 int,year2 int,parity int
);

--completed sessions
insert into completed_sessions values(1,2020,2021,2);
insert into completed_sessions values(2,2021,2022,1);
insert into completed_sessions values(3,2021,2022,2);
insert into completed_sessions values(4,2022,2023,1);
insert into completed_sessions values(5,2022,2023,2);

--format of grading
-- entrynum courseid grade