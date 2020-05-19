//https://www.yumpu.com/en/document/read/38210438/oraoop-11-user-guide-cloudera-blog
//
//mysqlHostname=ip-172-31-2-124.us-west-2.compute.internal
//mysqlDBName=employees
//mysqlUsername=hive
//
//
//sqoop import \
//--connect jdbc:mysql://$mysqlHostname/$mysqlDBName \
//  --table salaries \
//  --username $mysqlUsername \
//  -P \
//--hcatalog-table orc_salaries \
//--create-hcatalog-table \
//  --map-column-hive  from_date=date,to_date=date\
//  --hcatalog-storage-stanza 'stored as orcfile tblproperties ("orc.compress"="ZLIB")'


// ORC+Zlib has better performance than Paqruet + Snappy

// https://www.yumpu.com/en/document/read/38210438/oraoop-11-user-guide-cloudera-blog
//http://bdlabs.edureka.co/static/help/topics/impala_bigint.html
//https://impala.apache.org/docs/build/html/topics/impala_timestamp.html
//https://docs.cloudera.com/documentation/enterprise/5-8-x/topics/impala_datetime_functions.html
//https://docs.cloudera.com/documentation/enterprise/6/6.3/topics/impala_config_options.html
//http://106.75.162.218:8180/static/help/topics/impala_config_options.html
//https://dwgeek.com/commonly-used-impala-shell-command-line-options.html/
//  https://cm.brandwire.tv/static/help/topics/impala_install.html
//https://docs.cloudera.com/documentation/enterprise/5-12-x/topics/impala_timestamp.html
//https://docs.cloudera.com/documentation/enterprise/5-12-x/topics/impala_timestamp.html

// https://orc.apache.org/docs/types.html

//Validating avro schema and json file
//https://community.cloudera.com/t5/Community-Articles/Validating-avro-schema-and-json-file/ta-p/245033

//
//25.8.8.3. Confirm The Data Connector for Oracle and Hadoop Can Initialize The Oracle Session
//  If the Sqoop output includes feedback such as the following then the configuration properties contained within
//  oraoop-site-template.xml and
//  oraoop-site.xml have been loaded by Hadoop and can be accessed by the Data Connector for Oracle and Hadoop.
//
//14/07/08 15:21:13 INFO oracle.OracleConnectionFactory: Initializing Oracle session with SQL
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//hive-import command:
//hive-import commands automatically populates the metadata for the populating tables in hive metastore. If the table in
// Hive does not exist yet,
//Sqoop will simply create it based on the metadata fetched for your table or query. If the table already exists,
// Sqoop will import data into the existing table.
//If you’re creating a new Hive table, Sqoop will convert the data types of each column from your source table to a
// type compatible with Hive.
//
//  create-hive-table command:
//  Sqoop can generate a hive table (using create-hive-tablecommand) based on the table from an existing relational
//  data source. If set, then the job will fail
//if the target hive table exists. By default this property is false.
//
//  Using create-hive-table command
//  involves three steps: importing data into HDFS, creating hive table and then loading the HDFS data into Hive. This
//  can be shortened to one step by
//  using hive-import.
//
//During a hive-import,
//Sqoop will first do a normal HDFS import to a temporary location. After a successful import, Sqoop generates two queries:
//  one for creating a table and another one for loading the data from a temporary location. You can specify any temporary
//  location using either the
//--target-dir or --warehouse-dir parameter.
//
//Added a example below for above description
//
//Using create-hive-table command:
//  Involves three steps:
//
//Importing data from RDBMS to HDFS
//  sqoop import --connect jdbc:mysql://localhost:3306/hadoopexample --table employees --split-by empid -m 1;
//
//Creating hive table using create-hive-table command
//  sqoop create-hive-table --connect jdbc:mysql://localhost:3306/hadoopexample --table employees --fields-terminated-by ',';
//
//Loading data into Hive
//  hive> load data inpath "employees" into table employees;
//Loading data to table default.employees
//Table default.employees stats: [numFiles=1, totalSize=70]
//OK
//Time taken: 2.269 seconds
//  hive> select * from employees;
//OK
//1001    emp1    101
//1002    emp2    102
//1003    emp3    101
//1004    emp4    101
//1005    emp5    103
//Time taken: 0.334 seconds, Fetched: 5 row(s)
//
//Using hive-import command:
//
//
//
//Thanks Gergely. The approaches that we followed to overcome this issue was to sqoop import the date fields as
// Strings type when sqooped into hdfs.
//  This was achieve using
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//sqoop --option-file $OPTION_FILE_NAME \ --table $TABLE_NAME \ --map-column-java DAY_END_DTE=String \
// --target-dir $TARGET_DIR \ --as-avrodatafile
//
//This would cause the timestamp information to be sqooped as string of 'yyyy-mm-dd hh:mm:ss.f'
// format which could be casted into a date field.

/////////////////////////////////////////////////////////////
// serde in hive
//////////////////////////////////////////////////////////
// HDFS files –> InputFileFormat –> <key, value> –> Deserializer –> Row object
// Row object –> Serializer –> <key, value> –> OutputFileFormat –> HDFS files
///////////////////////////////////////////////////////////////////
// how to truncate external table
////////////////////////////////////////
// ALTER TABLE abc SET TBLPROPERTIES('EXTERNAL'='FALSE');
//Then truncate:
//
//  truncate table abc;
// And finally you can make it external again:
//
//  ALTER TABLE abc SET TBLPROPERTIES('EXTERNAL'=
//////////////////////////////////////////////

