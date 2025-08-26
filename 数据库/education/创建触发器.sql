-- CREATE TRIGGER tr_InsertStudent_SetDefaultMajor
-- ON Student
-- AFTER INSERT
-- AS
-- BEGIN
--     DECLARE @NewSno varchar(20);
--     SELECT @NewSno = Sno FROM inserted;
--     INSERT INTO StudentMajor (Sno, Mno)
--     VALUES (@NewSno, 'M001');
-- END;

-- 不要这个
-- CREATE TRIGGER tr_UpdateCourse_Credit_AdjustLearningScore
-- ON Course
-- AFTER UPDATE
-- AS
-- BEGIN
--     IF UPDATE(Credit)
--     BEGIN
--         DECLARE @OldCredit int, @NewCredit int, @Cno varchar(20);
--         SELECT @OldCredit = Credit, @Cno = Cno FROM deleted;
--         SELECT @NewCredit = Credit FROM inserted;
-- 
--         UPDATE Learning
--         SET Score = Score * (@NewCredit / @OldCredit)
--         WHERE Cno = @Cno;
--     END
-- END;

-- CREATE TRIGGER tr_DeleteTeacher_InsteadOfDeleteTeachingRecords
-- ON Teacher
-- INSTEAD OF DELETE
-- AS
-- BEGIN
--     DECLARE @TnoToDelete varchar(20);
--     SELECT @TnoToDelete = Tno FROM deleted;
-- 
--     DELETE FROM Teaching
--     WHERE Tno = @TnoToDelete;
-- 
--     DELETE FROM Teacher
--     WHERE Tno = @TnoToDelete;
-- END;

-- 创建触发器
CREATE TRIGGER tr_DeleteStudent_InsteadOfDeleteRelatedRecords
ON Student
INSTEAD OF DELETE
AS
BEGIN
    -- 声明变量用于存储即将被删除的学生编号
    DECLARE @SnoToDelete varchar(20);
    -- 获取即将被删除学生的编号，deleted 临时表在执行替代删除操作触发此触发器时包含了原本要被删除的数据行
    SELECT @SnoToDelete = Sno FROM deleted;

    -- 先删除 StudentMajor 表中与该学生相关的记录
    DELETE FROM StudentMajor
    WHERE Sno = @SnoToDelete;

    -- 再删除 Learning 表中与该学生相关的记录
    DELETE FROM Learning
    WHERE Sno = @SnoToDelete;

    -- 最后执行对 Student 表中对应学生记录的删除操作，完成整个替代删除流程
    DELETE FROM Student
    WHERE Sno = @SnoToDelete;
END;