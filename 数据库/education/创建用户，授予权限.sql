-- -- 创建管理员登录名
-- CREATE LOGIN admin_login
--     WITH PASSWORD = '123456',
--     DEFAULT_DATABASE = [education];  -- 将 [YourDatabaseName] 替换为实际数据库名称
-- 
-- -- 创建教师登录名
-- CREATE LOGIN teacher_login
--     WITH PASSWORD = '123456',
--     DEFAULT_DATABASE = [education];
-- 
-- -- 创建学生登录名
-- CREATE LOGIN student_login
--     WITH PASSWORD = '123456',
--     DEFAULT_DATABASE = [education];




-- USE education;  -- 使用要操作的数据库
-- 
-- -- 创建管理员数据库用户并关联登录名
-- CREATE USER admin_user FOR LOGIN admin_login;
-- 
-- -- 创建教师数据库用户并关联登录名
-- CREATE USER teacher_user FOR LOGIN teacher_login;
-- 
-- -- 创建学生数据库用户并关联登录名
-- CREATE USER student_user FOR LOGIN student_login;


-- 1.1
-- -- 授予对教师信息表的 SELECT 权限，用于查询教师信息
-- GRANT SELECT ON Teacher TO admin_user;
-- 
-- -- 授予 INSERT 权限，用于添加教师信息
-- GRANT INSERT ON Teacher TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于更新教师信息
-- GRANT UPDATE ON Teacher TO admin_user;
-- 
-- -- 授予 DELETE 权限，用于删除教师信息
-- GRANT DELETE ON Teacher TO admin_user;

-- 1.2
-- 授予对院系信息表的 SELECT 权限，方便查看院系信息
-- GRANT SELECT ON Faculties TO admin_user;
-- 
-- -- 授予 INSERT 权限，以添加新的院系信息
-- GRANT INSERT ON Faculties TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于修改院系相关内容
-- GRANT UPDATE ON Faculties TO admin_user;
-- 
-- -- 授予 DELETE 权限，可删除院系信息
-- GRANT DELETE ON Faculties TO admin_user;

-- 1.3
-- -- 授予 SELECT 权限，用于查询专业信息
-- GRANT SELECT ON Major TO admin_user;
-- 
-- -- 授予 INSERT 权限，便于添加新专业
-- GRANT INSERT ON Major TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于更新专业相关内容
-- GRANT UPDATE ON Major TO admin_user;
-- 
-- -- 授予 DELETE 权限，可删除专业信息
-- GRANT DELETE ON Major TO admin_user;

-- 1.4
-- -- 授予 SELECT 权限，用于查询课程信息
-- GRANT SELECT ON Course TO admin_user;
-- 
-- -- 授予 INSERT 权限，用于添加课程
-- GRANT INSERT ON Course TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于更新课程相关内容
-- GRANT UPDATE ON Course TO admin_user;
-- 
-- -- 授予 DELETE 权限，可删除课程信息
-- GRANT DELETE ON Course TO admin_user;

-- -- 授予 SELECT 权限，用于查询课程信息
-- GRANT SELECT ON Teaching TO admin_user;
-- 
-- -- 授予 INSERT 权限，用于添加课程
-- GRANT INSERT ON Teaching TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于更新课程相关内容
-- GRANT UPDATE ON Teaching TO admin_user;
-- 
-- -- 授予 DELETE 权限，可删除课程信息
-- GRANT DELETE ON Teaching TO admin_user;
-- 
-- -- 授予 SELECT 权限，用于查询课程信息
-- GRANT SELECT ON TeacherFaculties TO admin_user;
-- 
-- -- 授予 INSERT 权限，用于添加课程
-- GRANT INSERT ON TeacherFaculties TO admin_user;
-- 
-- -- 授予 UPDATE 权限，用于更新课程相关内容
-- GRANT UPDATE ON TeacherFaculties TO admin_user;
-- 
-- -- 授予 DELETE 权限，可删除课程信息
-- GRANT DELETE ON TeacherFaculties TO admin_user;

-- 一、授予教师对自身信息（在 Teacher 表中）操作的权限
-- 查询个人信息的权限授予（实现查询功能）
-- 授予教师用户查询 Teacher 表的权限，以便能查看自己的各项信息
-- GRANT SELECT ON Teacher TO teacher_user;
-- GRANT SELECT ON Faculties TO teacher_user;
-- GRANT SELECT ON Course TO teacher_user;
-- GRANT SELECT ON Major TO teacher_user;
-- GRANT SELECT ON Teaching TO teacher_user;

-- 修改个人信息的权限授予（实现修改功能）
-- 如果希望教师只能修改部分指定的个人信息字段（比如姓名和年龄等），一种可行的做法是通过创建视图来限定可更新的字段范围，然后授予对该视图的更新权限，示例如下：
-- 创建视图，只包含允许教师修改的字段（这里以姓名和年龄为例）以及能关联到对应教师记录的主键字段（假设为 TeacherID）
-- CREATE VIEW TeacherPersonalInfoView
-- AS
-- SELECT Tname, TLocation, Tage
-- FROM Teacher;
-- GRANT UPDATE ON TeacherPersonalInfoView TO teacher_user;

-- 二、授予教师对学生信息（在 Student 表中）操作的权限
-- 查询学生信息的权限授予（方便查看学生情况）
-- -- 授予教师用户查询 Student 表的权限，使其可以获取学生的各项基本信息
-- GRANT SELECT ON Student TO teacher_user;
-- -- 授予插入权限，让教师可以添加新学生的信息到 Student 表中
-- GRANT INSERT ON Student TO teacher_user;
-- 
-- -- 授予更新权限，用于教师更新学生已有的各项信息
-- GRANT UPDATE ON Student TO teacher_user;
-- 
-- -- 授予删除权限，使教师在符合业务规则等情况下可以删除学生相关的记录（使用时需谨慎）
-- GRANT DELETE ON Student TO teacher_user;

-- 
-- 一、授予学生对自身信息（在 Student 表中）操作的权限
-- 查询个人信息的权限授予（实现查询功能）
-- GRANT SELECT ON Student TO student_user;

-- 修改个人信息的权限授予（实现修改功能）
-- CREATE VIEW StudentPersonalInfoView
-- AS
-- SELECT Sname, Sage, SLocation
-- FROM Student;
-- GRANT UPDATE ON StudentPersonalInfoView TO student_user;

-- 二、授予学生查看课程信息（在 Course 表中）的权限
-- GRANT SELECT ON Course TO student_user;
-- GRANT SELECT ON Major TO student_user;
-- GRANT SELECT ON Faculties TO student_user;

-- 三、授予学生查看自己成绩信息（在 Grades 表中）的权限
-- CREATE VIEW StudentGradesView
-- AS
-- SELECT g.*
-- FROM Learning g
-- JOIN Student s ON g.Sno = s.Sno;

-- GRANT SELECT ON StudentGradesView TO student_user;