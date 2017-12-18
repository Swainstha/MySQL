# MySQL
MySQL Assignments

# Program Flow
    Connecting to database...
    Creating statement...
    Enter the last name whose data you want to query
    *abbas*
    The time taken for  query without index 3398856 ns
    ID: 9, Name: Abbye, Last: abbas, Place: Abingdon
    The time taken for  for query with index 460938 ns
    ID: 9, Name: Abbye, Last: abbas, Place: Abingdon

    Process finished with exit code 0
    
 # Details
    - The sql file is located in path MySQL/Student.sql
    - The file can be imported in localhost/phpmyadmina and it will create the database Student along with table student and insert 10000 data into the table.
    - the jar file is located in path MySQL/out/artifacts/MySQL_jar/Mysql.jar
    - The jar file can be run using "java -jar MySQL.jar".
    - The program creates on index on last name, so query is done based on index in last name.
    - The program will prompt the user to enter the last name. Eg. abbas, diggs, aaron, abbott
