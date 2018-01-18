-- 员工表
CREATE TABLE employees (
  emp_id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工ID',
  name varchar(100) NOT NULL COMMENT '员工姓名',
  password varchar(20) NOT NULL COMMENT '密码',
  power_id int(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (emp_id)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='员工表';

-- 添加外键
alter table employees add constraint emp_fk foreign key(power_id) REFERENCES powers(power_id);
#   alter table employees add FOREIGN key (power_id) references powers(power_id)
#   on delete set null # 在删除外键时，将从表的外键值设置为null
#   on update cascade;

drop TABLE employees;
# 初始化员工数据
INSERT INTO employees (emp_id ,name, password, power_id)
VALUES
	(1000, '龙小平','111111', 1),
	(1001, '张启辉','111111', 2),
	(1002, '徐俊','111111', 2),
	(1003, '汪彬芳','111111', 3);

-- 创建权限表
CREATE TABLE powers (
  power_id int(20) NOT NULL COMMENT '权限ID',
#   emp_id bigint(20) NOT NULL COMMENT '员工ID',
  power_name VARCHAR (20) NOT NULL COMMENT '权限名称',
  PRIMARY KEY (power_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

# ALTER TABLE powers DROP emp_id;

INSERT INTO powers (power_id, power_name)
VALUES
  (1,'管理员'),
  (2,'经理'),
  (3,'普通员工');


# drop TABLE powers;
