## Hive

1. Create, Drop and Alter tables - 
    - create db -  
    `create database mydb;`
    
    - change db -  
    `use mydb`
    
    - Normal create table -  
    `create table tablename(var type, var type);`
    
    - Create table for insert from txt file -  
    `create table tablename (var type, var type) row format delimited fields terminated by ',' lines terminated by '\n' stored as textfile;`

    - Normal insert -  
    `insert into tablename values (val1,  val2); `

    - Insert from txt file -  
    `load data local inpath 'filename.txt'overwrite into table tablename`
    
    - Drop table -  
    `drop table tablename;`

    - Alter Table to rename -  
    `alter table tablename1 rename to tablename2;`
    
    - Alter Table to add columns -  
    `alter table tablename add columns(var type);`

2. Join tables -  
    - `select a.var1, a.var2, b.var1 from table1 a join table2 b on (a.var1 == b.var1) `

3. Create Index - 
    - `create index indexname on table tablename(var1) as 'org.apache.hadoop.hive.ql.index.compact.compactIndexHandler' WITH DEFERRED REBUILD`

4. Create External Table - 
    - `create external table tablename(var1 type, var2 type) stored by 'org.apache.hadoop.hive.hbase.HbaseStorageHandler' with serdeproperties ("hbase.columns.mapping"=":key,var1:attr1, var1attr2, var2:attr1) tblproperties("hbase.table.name"="tablename");` 

## HBase

1. Create table -  
    - `create 'tablename', 'var1', 'var2'`

2. Insert Values - 
    - `put 'tablename', '1','var1:attr1','value'`

3. View table - 
    - `scan tablename`