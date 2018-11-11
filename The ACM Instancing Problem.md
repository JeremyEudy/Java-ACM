# The ACM Instancing Problem
## Short Description
In order to maintain a proper log of history for changes to the ACM and database, I am storing instances of the ACM into an ArrayList\<ACM\> called commitHistory. At all points there are 2 ACM instances, one called newACM and one called workingACM. However, all changes that occur in workingACM are reflected in newACM despite not committing changes (the only way to update newACM to workingACM).
## Example
In this code snippet, user input is parsed for the "CREATE" keyword, and then an object is created with the specified name, and added to workingACM. However as shown in the output, despite no commits being made, the change is passed to newACM. Another interesting note to make, is the "DROP" command does not behave the same way.
[The ACM Instancing Problem](:note:89b67f7a-efbf-41dc-b9bb-de6157a30130)
```java
else if(command.contains("CREATE")){
			if(userRole>2){				//Access restricted to Admins
						System.out.printf("Success.");
						splitCommand = command.split(" ");
						String tableName = splitCommand[splitCommand.length-1];
						workingACM.addObject(tableName);
			}
			else{
						System.out.printf("Authentication failure.");
			}
}
```
```
DATABASE INPUT:

>CREATE SAMPLE
Success.
>EXIT

Please select an option:
...
>6                   //View user authentication table

DML
     Owners | [2]
Controllers | []
  Executors | [1]
...
SAMPLE
     Owners | []
Controllers | []
  Executors | []
...
```
```java
else if(command.contains("DROP")){
			if(userRole>2){
						System.out.printf("Success.");
						splitCommand = command.split(" ");
						String tableName = splitCommand[splitCommand.length-1];
						workingACM.removeObject(tableName);
			}
			else{
						System.out.printf("Authentication failure.");
			}
}
```
```java
else if(command.contains("COMMIT")){
			if(userRole>1){				//Access restricted to Security Officers and Admins
  					System.out.printf("Success.");
	  				commitHistory.add(workingACM);
		  			newACM = workingACM;
			}
			else{
			    	System.out.printf("Authentication failure.");
			}
}
else if(command.contains("ROLLBACK")){
			if(userRole>1){				//Access restricted to Security Officers and Admins
						splitCommand = command.split(" ");
						String commitNum = splitCommand[splitCommand.length-1];
						for(int i=0;i<commitHistory.size();i++){
									if(commitHistory.get(i).getUpdateCounter() == Integer.parseInt(commitNum)){
										  newACM = commitHistory.get(i);
                                    }
						}
			}
			else{
						System.out.printf("Authentication failure.");
			}
}
```