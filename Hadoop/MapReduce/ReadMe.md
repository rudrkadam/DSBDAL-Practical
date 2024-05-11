## Steps

1. Create a poject 
2. Add external jar files - hadoop core and hadoop cli
3. Write a map reduce program
4. Export the project as a jar file
5. Transfer the data (.csv or .txt) file to hdfs using `hadoop fs -put source_filename destination_filename`
6. Execute the program using `hadoop jar jar_file package_name.class_name csv_filename direc_name`
7. View the result using `hadoop fs -cat direc_name/part-r-00000`