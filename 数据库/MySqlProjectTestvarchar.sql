/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2024/12/21 23:10:39                          */
/*==============================================================*/


alter table CoursesOFMajor 
   drop foreign key FK_COURSESO_REFERENCE_MAJOR;

alter table CoursesOFMajor 
   drop foreign key FK_COURSESO_REFERENCE_COURSE;

alter table Learning 
   drop foreign key FK_LEARNING_REFERENCE_STUDENT;

alter table Learning 
   drop foreign key FK_LEARNING_REFERENCE_COURSE;

alter table Major 
   drop foreign key FK_MAJOR_REFERENCE_FACULTIE;

alter table StudentMajor 
   drop foreign key FK_STUDENTM_REFERENCE_MAJOR;

alter table StudentMajor 
   drop foreign key FK_STUDENTM_REFERENCE_STUDENT;

alter table TeacherFaculties 
   drop foreign key FK_TEACHERF_REFERENCE_TEACHER;

alter table TeacherFaculties 
   drop foreign key FK_TEACHERF_REFERENCE_FACULTIE;

alter table Teaching 
   drop foreign key FK_TEACHING_REFERENCE_COURSE;

alter table Teaching 
   drop foreign key FK_TEACHING_REFERENCE_TEACHER;

drop table if exists Course;


alter table CoursesOFMajor 
   drop foreign key FK_COURSESO_REFERENCE_MAJOR;

alter table CoursesOFMajor 
   drop foreign key FK_COURSESO_REFERENCE_COURSE;

drop table if exists CoursesOFMajor;

drop table if exists Faculties;


alter table Learning 
   drop foreign key FK_LEARNING_REFERENCE_STUDENT;

alter table Learning 
   drop foreign key FK_LEARNING_REFERENCE_COURSE;

drop table if exists Learning;


alter table Major 
   drop foreign key FK_MAJOR_REFERENCE_FACULTIE;

drop table if exists Major;

drop table if exists Student;


alter table StudentMajor 
   drop foreign key FK_STUDENTM_REFERENCE_MAJOR;

alter table StudentMajor 
   drop foreign key FK_STUDENTM_REFERENCE_STUDENT;

drop table if exists StudentMajor;

drop table if exists Teacher;


alter table TeacherFaculties 
   drop foreign key FK_TEACHERF_REFERENCE_TEACHER;

alter table TeacherFaculties 
   drop foreign key FK_TEACHERF_REFERENCE_FACULTIE;

drop table if exists TeacherFaculties;


alter table Teaching 
   drop foreign key FK_TEACHING_REFERENCE_TEACHER;

alter table Teaching 
   drop foreign key FK_TEACHING_REFERENCE_COURSE;

drop table if exists Teaching;

/*==============================================================*/
/* Table: Course                                                */
/*==============================================================*/
create table Course
(
   Cno                  varchar(20) not null  comment '',
   Cname                varchar(50) not null  comment '',
   Credit               int not null  comment '',
   primary key (Cno)
);

/*==============================================================*/
/* Table: CoursesOFMajor                                        */
/*==============================================================*/
create table CoursesOFMajor
(
   Cno                  varchar(20) not null  comment '',
   Mno                  varchar(20) not null  comment ''
);

/*==============================================================*/
/* Table: Faculties                                             */
/*==============================================================*/
create table Faculties
(
   Dno                  varchar(20) not null  comment '',
   Dname                varchar(50) not null  comment '',
   DLocation            varchar(255) not null  comment '',
   primary key (Dno)
);

/*==============================================================*/
/* Table: Learning                                              */
/*==============================================================*/
create table Learning
(
   Sno                  varchar(20) not null  comment '',
   Cno                  varchar(20) not null  comment '',
   Score                decimal(5,2) not null  comment ''
);

/*==============================================================*/
/* Table: Major                                                 */
/*==============================================================*/
create table Major
(
   Mno                  varchar(20) not null  comment '',
   Mname                varchar(50) not null  comment '',
   Dno                  varchar(20) not null  comment '',
   primary key (Mno)
);

/*==============================================================*/
/* Table: Student                                               */
/*==============================================================*/
create table Student
(
   Sno                  varchar(20) not null  comment '',
   Sname                varchar(50) not null  comment '',
   Ssex                 char(2) not null  comment '',
   Sage                 int not null  comment '',
   SLocation            varchar(255) not null  comment '',
   SNation              varchar(25) not null  comment '',
   primary key (Sno)
);

/*==============================================================*/
/* Table: StudentMajor                                          */
/*==============================================================*/
create table StudentMajor
(
   Sno                  varchar(20) not null  comment '',
   Mno                  varchar(20) not null  comment ''
);

/*==============================================================*/
/* Table: Teacher                                               */
/*==============================================================*/
create table Teacher
(
   Tno                  varchar(20) not null  comment '',
   Tname                varchar(50) not null  comment '',
   Tsex                 char(2) not null  comment '',
   Tage                 int not null  comment '',
   TLocation            varchar(255) not null  comment '',
   TNation              varchar(25) not null  comment '',
   primary key (Tno)
);

/*==============================================================*/
/* Table: TeacherFaculties                                      */
/*==============================================================*/
create table TeacherFaculties
(
   Tno                  varchar(20) not null  comment '',
   Dno                  varchar(20) not null  comment ''
);

/*==============================================================*/
/* Table: Teaching                                              */
/*==============================================================*/
create table Teaching
(
   Tno                  varchar(20) not null  comment '',
   Cno                  varchar(20) not null  comment '',
   TCLocation           varchar(50) not null  comment ''
);

alter table CoursesOFMajor add constraint FK_COURSESO_REFERENCE_MAJOR foreign key (Mno)
      references Major (Mno) on delete restrict on update restrict;

alter table CoursesOFMajor add constraint FK_COURSESO_REFERENCE_COURSE foreign key (Cno)
      references Course (Cno) on delete restrict on update restrict;

alter table Learning add constraint FK_LEARNING_REFERENCE_STUDENT foreign key (Sno)
      references Student (Sno);

alter table Learning add constraint FK_LEARNING_REFERENCE_COURSE foreign key (Cno)
      references Course (Cno);

alter table Major add constraint FK_MAJOR_REFERENCE_FACULTIE foreign key (Dno)
      references Faculties (Dno);

alter table StudentMajor add constraint FK_STUDENTM_REFERENCE_MAJOR foreign key (Mno)
      references Major (Mno);

alter table StudentMajor add constraint FK_STUDENTM_REFERENCE_STUDENT foreign key (Sno)
      references Student (Sno);

alter table TeacherFaculties add constraint FK_TEACHERF_REFERENCE_TEACHER foreign key (Tno)
      references Teacher (Tno);

alter table TeacherFaculties add constraint FK_TEACHERF_REFERENCE_FACULTIE foreign key (Dno)
      references Faculties (Dno);

alter table Teaching add constraint FK_TEACHING_REFERENCE_COURSE foreign key (Cno)
      references Course (Cno);

alter table Teaching add constraint FK_TEACHING_REFERENCE_TEACHER foreign key (Tno)
      references Teacher (Tno);

