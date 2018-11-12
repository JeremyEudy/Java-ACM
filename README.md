# Java-ACM
## Introduction
The goal of this project is to design a working Access Control Matrix (ACM) in an OOP language (in this instance Java was used). The scope of this ACM is as follows:
- It must be able to add and remove users
- It must be able to authenticate users
- It must be able to create and drop databases and tables
- It must be able to parse SQL commands input by users
- It must implement some kind of role system in order to manage users
## Requirements
This project runs on Java 8, so you must have JRE 1.8.x installed, and JDK 1.8.x is necessary to edit this project.
## Project Documentation
### The ACM Class
- Variables
  - `int rolesNum`
    - Keeps track of the number of roles
  - `int userRole`
    - Keeps track of the users role
  - `int index`
    - Variable that is used in several methods so scope was widened
  - `int updateCounter`
    - Stores the amount of changes made to the ACM from 0 (For commits)
  - `ArrayList<ACMObject> subjects`
    - An arraylist of all subjects in the ACM
  - `ArrayList<ACMObject> objects`
    - An arraylist of all objects in the ACM
  - `ArrayList<String> roles`
    - A list of all the roles by name (index is almost always used)
- Methods (not including get and set methods)
  - `updateCount()`
    - Increments `updateCounter` by 1
  - `addSubject(String name, int ID, int role)`
    - Passes parameters into `ACMObject` constructor and then adds the object to `subjects` and increments `updateCounter`
  - `removeSubject(int ID)`
    - Iterate through `subjects` and remove the subject with a matching ID, then increment `updateCounter`
  - `addObject(String name)`
    - Creates an object with the given name, add it to `objects` and then increment `updateCounter`
  - `removeObject(String name)`
    - Removes the object with the given name from `objects` and then increments `updateCounter`
  - `printUsers()`
    - Prints a list of subjects and their IDs and roles
  - `printACM()`
    - Prints the ACM as it currently stands
### The ACMObject Class
- Variables
  - `String name`
    - The name of the subject/object
  - `int ID`
    - The ID of the subject (not used by objects)
  - `int role`
    - The role of the subject (not used by objects)
  - `int index`
    - Variable that is used in several places so the scope increased
  - `boolean objType`
    - defines objects from subjects (true = subject, false/null = object)
  - `ArrayList<Integer> owners`
    - A list of the IDs of subjects that own the object
  - `ArrayList<Integer> controllers`
    - A list of the IDs of the subjects that control the object
  - `ArrayList<Integer> executors`
    - A list of the IDs of the subjects that can execute the object
  - `ArrayList<String> data`
    - The data in the objects (objects are tables, the data is in the tables)
- Methods (not including get and set methods)
  - `removeOwner(int ID)`
    - Iterates through `owners` and deletes all matching IDs
  - `removeController(int ID)`
    - Iterates through `controllers` and deletes all matching IDs
  - `removeExecutor(int ID)`
    - Iterates through `executors` and deletes all matching IDs
  - `authenticate(int ID, int controlChoice)`
    - Adds the ID to the list that corresponds to `controlChoice` (0 = executors, 1 = controllers, 2 = owners)
### The ACMTest Class
This is the main class so most of this will be UI
- The program first asks the user for their role
- Then the option for a test bench is given (generate sample users)
- The majority of this program is managed by a `switch()`
  - `case 0:` exits the program
  - `case 1:` prints the ACM
  - `case 2:` prints the user list
  - `case 3:` allows the user to add a subject
  - `case 4:` allows the user to delete a subject
  - `case 5:` authenticates a subject (adds a subject to owners, controllers, or executors)
  - `case 6:` gives the user a command line so they can input SQL commands
    - Command help:
      - `GRANT permission_type ON object_name TO subject_id` - Grant permissions
				 `EVOKE permission_type ON object_name FROM subject_id` - Revoke permissions
				 `COMMIT` - Update objects list and commit work
				 `ROLLBACK update_counter` - revert to specific commit
				 `CREATE table_name` - Creates an object with the specified name
				 `DROP table_name` - Deletes an object with the specified name
				 `SELECT data FROM table_name` - Selects data from the specified table
				 `INSERT INTO table_name VALUES value1 value2` - Inserts values into table
				 `DELETE FROM table_name value1 value2` - Deletes data fitting condition
				 `ROLE` - Prints the users current role
				 `HISTORY` - Prints out commit history
				 `EXIT` - Exits the database interface

### Roles
The roles are as listed:
- 0 = User
  - No rights outside of using SELECT, INSERT, and DELETE commands
- 1 = Security Officer
  - Has control over subjects, and can use COMMIT, and ROLLBACK SQL commands
- 2 = Root
  - Has all rights and privileges

## This ACM
This ACM is an RBAC ACM in regards to subject control, and it is a DAC ACM in regards to object control since roles are used to give authority over users and ID numbers are used to give authority over objects.

### Author
Jeremy Eudy
