/* ************************************************************************** */
/*                                                                            */
/*                                                            |\              */
/*   ACMTest.java                                       ------| \----         */
/*                                                      |    \`  \  |  p      */
/*   By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o      */
/*                                                      |---\  \   `|  l      */
/*   Created: 2018/09/26 14:22:00 by jeudy2552          | ` .\  \   |  y      */
/*   Updated: 2018/10/19 12:46:57 by jeudy2552          -------------         */
/*                                                                            */
/* ************************************************************************** */
package ACM;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ACMTest{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.printf("###################################\n");
        System.out.printf("#                                 #\n");
        System.out.printf("# Java Access Control Matrix Tool #\n");
        System.out.printf("#                                 #\n");
        System.out.printf("###################################\n");

        ACM newACM = new ACM();				//Initialize the ACM
        boolean persist = true;

		/*Login system framework
        while(true){
			System.out.printf("Please input your username:\n>");
			String userName = in.nextLine();
			System.out.printf("Please input your password:\n>");
			char[] passString = Console.readPassword();
			String password = new String(passString);
			//If valid info, then approve
		}
		*/
		/*
		File f = new File("SaveACM.txt");
		if(f.exists() && !f.isDirectory()){
			System.out.printf("\nSave file found.\nWould you like to load from it?\n1 - yes\n2 - no\n>");
			int choice = in.nextInt();
			if(choice == 1){
				
			}
		}
		*/

		int userRole = 0;

		while(persist){
			//Prompt user for the role they want to act as
			System.out.printf("\nWhich role do you belong to?\n1 - User\n2 - Security Officer\n3 - Administrator\n>");
			userRole = in.nextInt();
			switch(userRole){				//Listed lowest to highest in hierarchy
				case 1:
					System.out.printf("\nRole set to 'User'\n");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				case 2:
					System.out.printf("\nRole set to 'Security Officer'\n");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				case 3:
					System.out.printf("\nRole set to 'Administrator'\n");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				default:
					System.out.printf("\nInvalid choice.");
					break;
			}
		}
		
		//Generates 2 users, JOHN DOE and JANE DOE
		//Both are users, and they are on the command list of each object
		System.out.printf("\nWould you like to generate a test set of users?\n1 - Yes\n2 - No\n>");
		int testBench = in.nextInt();

		if(testBench == 1){
			newACM.addSubject("JOHN DOE", 1, 0);
			newACM.addSubject("JANE DOE", 2, 0);
			for(int i=0;i<newACM.getObjects().size();i++){
				int j = i*-1 + 2;				//j increments down while i increments up for permissions
				ACMObject one = newACM.getObjects().get(i);
				one.authenticate(1, i);
				one.authenticate(2, j);
			}
		}

		persist = true;

        while(persist){
            System.out.printf("\nPlease select an option.\n1 - View ACM\n2 - View users\n3 - Add a new subject\n4 - Delete a subject\n5 - Autenticate user\n6 - View user authentication table\n7 - Manipulate database\n0 - Quit\n>");
            int choice = in.nextInt();
			//Vars initialized for future use in switch()
            int ID = 0;
            String name = "";
            ArrayList<String> roleList;
 			ArrayList<ACMObject> subjects;
			ArrayList<ACMObject> objects;	
            int role;
            switch(choice){
                case 0:
                    persist = false;
                    System.out.printf("\nGoodbye!\n");
                    break;

                case 1:
					//Prints a table for the ACM
                    newACM.printACM();
                    break;

				case 2:
					//Prints a list of users and information about them
					newACM.printUsers();
					break;

                case 3:
					//Adds a subject
					if(userRole>0){								//Only accessible by Security Officers and Admins
						in = new Scanner(System.in);
    	                roleList = newACM.getRoles();
        	            System.out.printf("\nWhat is the name of the subject?\n>");
            	        name = in.nextLine();
                	    name = name.toUpperCase();
                    	ID = newACM.getSubjectsNum()+1;			//ID is equal to index()+1
	                    System.out.printf("\nWhat role does this subject have?\n");
    	                for(int i=0;i<roleList.size();i++){		//Prints list of roles
        	                System.out.printf("%d - %s\n", i+1, roleList.get(i));
            	        }
                	    System.out.printf("\n> ");
                    	int roleInput = in.nextInt();
						//Prompt user for a choice, if none then role = USER
						try{
		                    String roleName = roleList.get(roleInput-1);
							role = roleList.indexOf(roleName);
						}
					    catch(Exception e){
							role = roleList.indexOf("USER");
					    }
    	                newACM.addSubject(name, ID, role);		//Add the subject to newACM
        	            break;
					}
					else{
						System.out.printf("\nAccess denied.");
						break;
					}

                case 4:
					//Build lists of ACM subjects and objects
					subjects = newACM.getSubjects();
					objects = newACM.getObjects();
                    System.out.printf("\nInput the ID of the subject you would like to delete.\n");
					for(int i=0;i<subjects.size();i++){	//Print subjects and info
						ACMObject one = subjects.get(i);
						System.out.printf("%s | ID = %d\n", one.getName(), one.getID());
					}
					System.out.printf(">");
                    ID = in.nextInt();
					int index = 0;						//Define index and removedSubjects
					ACMObject removedSubject;
					//Search through subjects to get the goal subject and its index
					for(int i=0;i<subjects.size();i++){
						if(subjects.get(i).getID() == ID){
							removedSubject = subjects.get(i);
							index = subjects.indexOf(removedSubject);
						}
					}
					//Iterate through objects and remove the ID wherever it appears in control lists
					for(int i=0;i<objects.size();i++){
						ACMObject one = objects.get(i);
						if(one.getOwners().contains(ID)){
							one.removeOwner(ID);
						}
						else if(one.getControllers().contains(ID)){
							one.removeController(ID);
						}
						else if(one.getExecutors().contains(ID)){
							one.removeExecutor(ID);
						}
						newACM.updateObjects(objects);
					}
					newACM.removeSubject(ID);			//Finally remove the subject
					System.out.printf("\nSubject deleted.");
                    break;

				case 5:
					//Authenticates users to objects
					if(userRole>0 & newACM.getSubjects().size()>0){					//Restricted to Security Officers and Admins
						//Generate list of subjects and objects from newACM
						subjects = newACM.getSubjects();
						objects = newACM.getObjects();
						System.out.printf("\nWhich subject would you like to authenticate?\n");
						//Prints list of subjects
						for(int i=0;i<subjects.size();i++){
							ACMObject one = subjects.get(i);
							System.out.printf("%d - %s\n", i+1, one.getName());
						}
						System.out.printf(">");
						int subjectChoice = in.nextInt();
						ACMObject subject = subjects.get(subjectChoice-1);			//Get goal subject
						
						System.out.printf("\nWhich object would you like them to have access to?\n");
						//Prints list of objects
						for(int i=0;i<objects.size();i++){
							ACMObject one = objects.get(i);
							System.out.printf("%d - %s\n", i+1, one.getName());
						}
						System.out.printf(">");
						int objectChoice = in.nextInt();
						ACMObject object = objects.get(objectChoice-1);				//Get goal object
						System.out.printf("\nWhat privileges should they have?\n0 - Execute\n1 - Control\n2 - Owner\n>");
						int controlChoice = in.nextInt();
						object.authenticate(subject.getID(), controlChoice);		//Passes it to the object to record
						System.out.printf("\nAuthentication complete.");
						break;
					}

					else if(userRole>0 & newACM.getSubjects().size()<=0){			//Just in case there aren't subjects
						System.out.printf("There are no subjects!");
						break;
					}

					else{
						System.out.printf("Invalid clearance.");
						break;
					}
				
				case 6:
					//Prints User Authentication chart
					newACM.printUAuth();
					break;

                case 7:
					//Allows the user a looping input line that can be exitted by typing "exit"
					//The text is parsed based on role level to dissallow access to lower level users
					subjects = newACM.getSubjects();
					objects = newACM.getObjects();
					System.out.printf("\nDATABASE INPUT:\n");
					boolean dbPersist = true;
					while(dbPersist){
						System.out.printf("\n>");
						in = new Scanner(System.in);
						String command = in.nextLine();
						command = command.toUpperCase();
						ArrayList<String> splitCommand = new ArrayList<String>();
						if(command.contains("GRANT")){
							if(userRole>1){				//Access restricted to Security Officers and Admins
								System.out.printf("Success.");
								splitCommand = command.split(" ");
								String permission = splitCommand.get(1);
								String objectName = splitCommand.get(3);
								String subjectName = splitCommand.get(5);
								ACMObject object;
								ACMObject subject;
								for(int i=0;i<objects.size();i++){
									if(objects.get(i).getName() == objectName){
										object = objects.get(i);
									}
								}
								for(int i=0;i<subjects.size();i++){
									if(subjects.get(i).getName() == subjecName){
										subject = subjects.get(i);
									}
								}
								if(object == null){
									System.out.printf("Could not find object.");
								}
								if(subject == null){
									System.out.printf("Could not find subject.");
								}
								if(permission == "EXECUTE"){
									object.authenticate(subject.getID(), 0);
								}
								else if(permission == "CONTROL"){
									object.authenticate(subject.getID(), 1);
								}
								else if(permission == "OWN"){
									object.authenticate(subject.getID(), 2);
								}
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("REVOKE"){
							if(userRole>1){				//Access restricted to Security Officers and Admins
								System.out.printf("Success.");
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("COMMIT"){
							if(userRole>1){				//Access restricted to Security Officers and Admins
								System.out.printf("Success.");
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("ROLLBACK"){
							if(userRole>1){				//Access restricted to Security Officers and Admins
								System.out.printf("Success.");
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("CREATE")){
							if(userRole>2){				//Access restricted to Admins
								System.out.printf("Success.");
								splitCommand = command.split(" ");
								String tableName = splitCommand.get(splitCommand.size()-1);
								newACM.addObject(tableName);
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("DROP")){
							if(userRole>2){
								System.out.printf("Success.");
								splitCommand = command.split(" ");
								String tableName = splitCommand.get(splitCommand.size()-1);
								newACM.removeObject(tableName);
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command == "ROLE"){
							System.out.print(userRole);
						}
						else if(command == "EXIT"){
							dbPersist = false;
							break;
						}
						else if(command == "HELP"){
							System.out.printf("Commands:\nGRANT permission_type ON object_name TO subject_name - Grant permissions\n"
									"REVOKE permission_type ON object_name FROM subject_name - Revoke permissions\n"
									"COMMIT - Update objects list and commit work\n"
									"ROLLBACK - Undo last commit\n"
									"CREATE table_name - Creates an object with the specified name\n"
									"DROP table_name - Deletes an object with the specified name\n"
									"SELECT data FROM table_name - Selects data from the specified table\n"
									"INSERT INTO table_name VALUES value1, value2 - Inserts values into table\n"
									"DELETE FROM table_name WHERE condition - Deletes data fitting condition\n"
									"ROLE - prints the users current role\n"
									"EXIT - exits the database interface\n"
									);
						}
						else{
							System.out.printf("Success.");
						}
					}
					break;

	            default:
                    System.out.printf("\nInvalid choice.");
                    break;
            }
        }
    }
}
