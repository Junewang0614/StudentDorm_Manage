drop database dormitory;
create database dormitory;
use dormitory;
create table ADMIN(
Admin_ID varchar(15) primary key,
Admin_pass varchar(15) not null
);

create table STUDENT_LOGIN(
Stu_ID varchar(15) primary key,
Stu_pass varchar(15) not null
);

create table STUDENT(
Stu_ID varchar(15) primary key,
Stu_name varchar(15) not null,
Dom_ID varchar(15) not null,
foreign key(Dom_ID) references DORMITORY(Dom_ID),
Bed_ID varchar(15) not null,
Phone varchar(15),
Major varchar(15) not null,
foreign key(Stu_ID) references STUDENT_LOGIN(Stu_ID)
);

create table DORMITORY(
Dom_ID varchar(15) primary key,
occupied int check(occupied <= 4 and occupied >= 0),
state varchar(15)
);


insert into ADMIN(Admin_ID, Admin_pass) values('20201228', '123456');
insert into ADMIN(Admin_ID, Admin_pass) values('20201222', '123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('1','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('2','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('3','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('4','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('5','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('6','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('7','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('8','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('9','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('10','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('11','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('12','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('21','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('22','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('23','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('24','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('25','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('26','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('27','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('28','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('29','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('30','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('31','123456');
insert into STUDENT_LOGIN(Stu_ID, Stu_pass) values('32','123456');
insert into DORMITORY(Dom_ID, occupied, state) values('101', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('201', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('301', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('401', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('501', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('601', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('701', 0, 0);
insert into DORMITORY(Dom_ID, occupied, state) values('801', 0, 0);


-- 存储过程
DELIMITER $
-- 插入学生入住信息（会先判断是否满，满了无作为，不满会更新学生表和dormitory表，更新occupied值和state值）
-- 包含phone的输入的
create procedure insert_stu1(in ID varchar(15), in Sname varchar(15), in Dom varchar(15), in Bed varchar(15), in Phone varchar(15), in Major varchar(15))
begin
	declare num int;
		insert into STUDENT(Stu_ID, Stu_name, Dom_ID, Bed_ID, Phone, Major) values(ID, Sname, Dom, Bed, Phone, Major);
		update DORMITORY set occupied = occupied + 1 where Dom_ID = Dom;
		set num = (select occupied from DORMITORY where Dom = Dom_ID);
		if num = 4 then
			update DORMITORY set state = 1 where Dom_ID = Dom;
		end if;
end$
DELIMITER ;


DELIMITER $
-- 插入学生入住信息（会先判断是否满，满了无作为，不满会更新学生表和dormitory表，更新occupied值和state值）
-- 不包含phone的输入的
create procedure insert_stu2(in ID varchar(15), in Sname varchar(15), in Dom varchar(15), in Bed varchar(15), in Major varchar(15))
begin
	declare num int;
		insert into STUDENT(Stu_ID, Stu_name, Dom_ID, Bed_ID,Major) values(ID, Sname, Dom, Bed, Major);
		update DORMITORY set occupied = occupied + 1 where Dom_ID = Dom;
		set num = (select occupied from DORMITORY where Dom = Dom_ID);
		if num = 4 then
			update DORMITORY set state = 1 where Dom_ID = Dom;
		end if;
end$
DELIMITER ;


DELIMITER $
-- 删除搬离学生
create procedure delete_stu(in ID varchar(15))
begin
	declare dom varchar(15);
	declare flag int;
	
	set dom = (select Dom_ID from STUDENT where ID = Stu_ID);
	set flag = (select state from DORMITORY where Dom_ID = dom);
	
	delete from STUDENT where Stu_ID = ID;
	update DORMITORY set occupied = occupied - 1 where Dom_ID = dom;
	if flag = 1 then
		select * from DORMITORY;
		update DORMITORY set state = 0 where Dom_ID = dom;
	end if;
end$
DELIMITER ;


DELIMITER $
CREATE PROCEDURE `change_dom`(in ID varchar(15), in ori_dom varchar(15), in to_dom varchar(15), in to_bed varchar(15))
begin
	declare ori_flag varchar(15);
	declare to_flag varchar(15);
	declare to_num int;

				set ori_flag = (select state from DORMITORY where Dom_ID = ori_dom);
				set to_flag = (select state from DORMITORY where Dom_ID = to_dom);
				set to_num = (select occupied from DORMITORY where Dom_ID = to_dom);
								if ori_flag = '1' then
									update DORMITORY set state = '0' where Dom_ID = ori_dom;
								end if;
								if to_flag = '0' and to_num = 3 then
									update DORMITORY set state = '1' where Dom_ID = to_dom;
								end if;
				update DORMITORY set occupied = occupied - 1 where Dom_ID = ori_dom;
				update DORMITORY set occupied = occupied + 1 where Dom_ID = to_dom;
				update STUDENT set Dom_ID = to_dom where Stu_ID = ID;
				update STUDENT set Bed_ID = to_bed where Stu_ID = ID;
end
DELIMITER ;

-- 查询学生
DELIMITER $
create procedure select_dom(in flag varchar(15), in profession varchar(15))
begin
	if profession = '' then
			select * from DORMITORY where state = flag;
	else 
		if flag is null then
			select distinct DORMITORY.Dom_ID, state, occupied from DORMITORY, STUDENT
			where
			STUDENT.Dom_ID = DORMITORY.Dom_ID and STUDENT.major = profession;
		else 
			select distinct DORMITORY.Dom_ID, state, occupied from DORMITORY, STUDENT
			where
			STUDENT.Dom_ID = DORMITORY.Dom_ID and STUDENT.major = profession and DORMITORY.state = flag;
		end if;
	end if;
end$
DELIMITER ;
