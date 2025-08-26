CREATE UNIQUE INDEX PK_Student_Sno
ON Student(Sno);

CREATE UNIQUE INDEX PK_Course_Cno
ON Course(Cno);

CREATE INDEX IX_Learning_Sno_Cno
ON Learning(Sno, Cno);

CREATE UNIQUE INDEX PK_Teacher_Tno
ON Teacher(Tno);

CREATE INDEX IX_Teaching_Tno_Cno
ON Teaching(Tno, Cno);

