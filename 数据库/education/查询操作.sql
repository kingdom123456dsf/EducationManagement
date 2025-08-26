-- 1. 查询学生选修的课程及对应成绩（体现学生与课程多对多选修关系）
-- SELECT s.Sname, c.Cname, l.Score
-- FROM Student s
-- JOIN Learning l ON s.Sno = l.Sno
-- JOIN Course c ON l.Cno = c.Cno;

-- 2. 查询教师教授的课程及授课地点（体现教师与课程多对多教授关系）
-- SELECT t.Tname, c.Cname, te.TCLocation
-- FROM Teacher t
-- JOIN Teaching te ON t.Tno = te.Tno
-- JOIN Course c ON te.Cno = c.Cno;

-- 3. 查询某个专业包含的课程（体现课程与专业多对多关系）
-- SELECT m.Mname, c.Cname
-- FROM Major m
-- JOIN CoursesOFMajor cm ON m.Mno = cm.Mno
-- JOIN Course c ON cm.Cno = c.Cno;

-- 4. 查询某个院系下的教师和他们教授的课程（体现教师与院系多对一关系以及教师与课程多对多关系）
-- SELECT f.Dname, t.Tname, c.Cname
-- FROM Faculties f
-- JOIN TeacherFaculties tf ON f.Dno = tf.Dno
-- JOIN Teacher t ON tf.Tno = t.Tno
-- JOIN Teaching te ON t.Tno = te.Tno
-- JOIN Course c ON te.Cno = c.Cno;

-- 5. 查询某个学生所在专业以及该专业所属院系（体现学生与专业多对一关系以及专业与院系多对一关系）
-- SELECT s.Sname, m.Mname, f.Dname
-- FROM Student s
-- JOIN StudentMajor sm ON s.Sno = sm.Sno
-- JOIN Major m ON sm.Mno = m.Mno
-- JOIN Faculties f ON m.Dno = f.Dno;


-- 一、查询学生相关信息
-- 1. 查询每个学生的姓名、选修课程数量以及平均成绩（体现学生与课程多对多选修关系，并进行统计分析）
-- SELECT s.Sname, COUNT(l.Cno) AS CourseCount, AVG(l.Score) AS AvgScore
-- FROM Student s
-- JOIN Learning l ON s.Sno = l.Sno
-- GROUP BY s.Sname;

-- 2. 查询选修了某门特定课程（例如课程编号为 C001）且成绩高于 80 分的学生姓名及所在专业（体现学生与课程多对多、学生与专业多对一关系以及条件筛选）
-- SELECT s.Sname, m.Mname
-- FROM Student s
-- JOIN StudentMajor sm ON s.Sno = sm.Sno
-- JOIN Major m ON sm.Mno = m.Mno
-- JOIN Learning l ON s.Sno = l.Sno
-- WHERE l.Cno = 'C001' AND l.Score > 80;

-- 二、查询教师相关信息
-- 1. 查询每个教师教授的课程门数以及授课的不同地点数量（体现教师与课程多对多教授关系，并做进一步统计分析）
-- SELECT t.Tname, COUNT(DISTINCT te.Cno) AS TeachingCourseCount, COUNT(DISTINCT te.TCLocation) AS LocationCount
-- FROM Teacher t
-- JOIN Teaching te ON t.Tno = te.Tno
-- GROUP BY t.Tname;

-- 2. 查询在多个院系授课的教师姓名及授课院系列表（体现教师与院系多对一关系以及多对多关系中的复杂情况筛选）


-- 三、查询课程相关信息
-- 1. 查询每门课程被多少个专业开设以及选修这门课程的学生人数（体现课程与专业多对多、学生与课程多对多关系，并做统计分析）
-- SELECT c.Cname, COUNT(DISTINCT cm.Mno) AS MajorCount, COUNT(DISTINCT l.Sno) AS StudentCount
-- FROM Course c
-- JOIN CoursesOFMajor cm ON c.Cno = cm.Cno
-- JOIN Learning l ON c.Cno = l.Cno
-- GROUP BY c.Cname;

-- 2. 查询学分最高的课程名称以及教授这门课程的教师姓名列表（体现课程与教师多对多关系以及数据排序筛选）
-- SELECT c.Cname, STRING_AGG(t.Tname, ', ') AS TeacherNames
-- FROM Course c
-- JOIN Teaching te ON c.Cno = te.Cno
-- JOIN Teacher t ON te.Tno = t.Tno
-- WHERE c.Credit = (SELECT MAX(Credit) FROM Course)
-- GROUP BY c.Cname;

-- 四、查询专业相关信息
-- 1. 查询每个专业的名称、所属院系以及该专业学生的平均年龄（体现专业与院系多对一、学生与专业多对一关系，并做统计分析）
-- SELECT m.Mname, f.Dname, AVG(s.Sage) AS AvgStudentAge
-- FROM Major m
-- JOIN Faculties f ON m.Dno = f.Dno
-- JOIN StudentMajor sm ON m.Mno = sm.Mno
-- JOIN Student s ON sm.Sno = s.Sno
-- GROUP BY m.Mname, f.Dname;

-- 2. 查询包含课程数量最多的专业名称及课程明细（体现课程与专业多对多关系以及统计排序筛选）
-- SELECT TOP 1 m.Mname, STRING_AGG(c.Cname, ', ') AS CourseDetails
-- FROM Major m
-- JOIN CoursesOFMajor cm ON m.Mno = cm.Mno
-- JOIN Course c ON cm.Cno = c.Cno
-- GROUP BY m.Mname
-- ORDER BY COUNT(cm.Cno) DESC;

-- 五、查询院系相关信息
-- 1. 查询每个院系的名称、教师数量以及平均每位教师教授的课程门数（体现教师与院系多对一关系以及做相关统计分析）
-- SELECT f.Dname, COUNT(DISTINCT tf.Tno) AS TeacherCount, AVG(TeacherCourseCount) AS AvgTeachingCourseCount
-- FROM Faculties f
-- JOIN TeacherFaculties tf ON f.Dno = tf.Dno
-- JOIN (
--     SELECT t.Tno, COUNT(te.Cno) AS TeacherCourseCount
--     FROM Teacher t
--     JOIN Teaching te ON t.Tno = te.Tno
--     GROUP BY t.Tno
-- ) AS SubQuery ON tf.Tno = SubQuery.Tno
-- GROUP BY f.Dname;

-- 2. 查询开设专业数量最多的院系名称及专业明细（体现专业与院系多对一关系以及统计排序筛选）
-- SELECT TOP 1 f.Dname, STRING_AGG(m.Mname, ', ') AS MajorDetails
-- FROM Faculties f
-- JOIN Major m ON f.Dno = m.Dno
-- GROUP BY f.Dname
-- ORDER BY COUNT(m.Mno) DESC;



