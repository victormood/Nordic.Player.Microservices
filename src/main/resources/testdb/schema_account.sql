drop table T_ACCOUNT if exists;

create table T_ACCOUNT (ID bigint identity primary key, NUMBER varchar(9),
                        USERNAME varchar(50) not null, PASSWORD varchar(50) not null, BALANCE decimal(8,2), unique(NUMBER));
                        
ALTER TABLE T_ACCOUNT ALTER COLUMN BALANCE SET DEFAULT 0.0;
ALTER TABLE T_ACCOUNT ALTER COLUMN PASSWORD SET DEFAULT '123456';