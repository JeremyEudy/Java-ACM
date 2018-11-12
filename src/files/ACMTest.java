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

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class ACMTest{
    public static void main(String[] args) throws CloneNotSupportedException{
        Scanner in = new Scanner(System.in);
        System.out.printf("###################################\n");
        System.out.printf("#                                 #\n");
        System.out.printf("# Java Access Control Matrix Tool #\n");
        System.out.printf("#                                 #\n");
        System.out.printf("###################################\n");

        ACM newACM = new ACM();				//Initialize the ACM
		ArrayList<ACM> commitHistory = new ArrayList<ACM>();	//Make arraylist to keep track of commits
		commitHistory.add(newACM);
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
			System.out.printf("\nWhich role do you belong to?\n1 - User\n2 - Security Officer\n3 - Root\n>");
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
					System.out.printf("\nRole set to 'Root'\n");
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

			/*Deprecated code
			for(int i=0;i<newACM.getObjects().size();i++){
				int j = i*-1 + 2;				//j increments down while i increments up for permissions
				ACMObject one = newACM.getObjects().get(i);
				one.authenticate(1, i);
				one.authenticate(2, j);
			}
			*/
		}

		persist = true;
	
        while(persist){
            System.out.printf("\nPlease select an option.\n1 - View ACM\n2 - View users\n3 - Add a new subject\n4 - Delete a subject\n5 - Authenticate user\n6 - Manipulate database\n0 - Quit\n>");
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
					if(userRole>0){								//Only accessible by Security Officers and Root
						in = new Scanner(System.in);
						subjects = newACM.getSubjects();
    	                roleList = newACM.getRoles();
        	            System.out.printf("\nWhat is the name of the subject?\n>");
            	        name = in.nextLine();
                	    name = name.toUpperCase();
						ID = 0;
						for(int i=0;i<subjects.size();i++){
							if(subjects.get(i).getID() > ID){
	                    		ID = subjects.get(i).getID();
							}
						}
						ID += 1;
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
					//Deletes a subject
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
					try{
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
					}catch(Exception e){}
					newACM.removeSubject(ID);			//Finally remove the subject				
					System.out.printf("\nSubject deleted.");
                    break;

				case 5:
					//Authenticates users to objects
					if(userRole>0 & newACM.getSubjects().size()>0 & newACM.getObjects().size()>0){					//Restricted to Security Officers and Root 
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

					else if(userRole>0 & newACM.getObjects().size()<=0){
						
						System.out.printf("There are no objects!");
						break;
					}

					else{
						System.out.printf("Invalid clearance.");
						break;
					}
				
				case 6:
					//Allows the user a looping input line that can be exitted by typing "exit"
					//The text is parsed based on role level to dissallow access to lower level users
					ACM workingACM = (ACM) newACM.clone();
					//System.out.printf("\nIf this is true, then workingACM = newACM : %s", workingACM == newACM);
					System.out.printf("\nNote: table names must not contain spaces.");
					commitHistory.add(newACM);
					System.out.printf("\nDATABASE INPUT:\n");
					boolean dbPersist = true;
					while(dbPersist){
						System.out.printf("\n>");
						in = new Scanner(System.in);
						String command = in.nextLine();
						command = command.toUpperCase();
						String[] splitCommand;
						try{
							if(command.contains("GRANT")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>1){				//Access restricted to Security Officers and Root
									splitCommand = command.split(" ");
									String permission = splitCommand[1];
									String objectName = splitCommand[3];
									String subjectID = splitCommand[5];
									ACMObject object = objects.get(0);
									ACMObject subject = subjects.get(0);
									//System.out.printf("\nInput permission: %s\nInput object name: %s\nInput subject ID: %s", permission, objectName, subjectID);
									for(int i=0;i<objects.size();i++){
										if(objects.get(i).getName().contains(objectName)){
											object = objects.get(i);
										}
									}
									//System.out.printf("\nSelected object: %s", object.getName());
									for(int i=0;i<subjects.size();i++){
										if(subjects.get(i).getID() == Integer.parseInt(subjectID)){
											subject = subjects.get(i);
										}
									}
									//System.out.printf("\nSelected subject: %s", subject.getName());
									//System.out.printf("\nSelected permission: %s", permission);
									if(permission.contains("EXECUTE")){
										object.authenticate(subject.getID(), 0);
										System.out.printf("\nSubject authenticated for %s", permission);
									}
									else if(permission.contains("CONTROL")){
										object.authenticate(subject.getID(), 1);
										System.out.printf("\nSubject authenticated for %s", permission);
									}
									else if(permission.contains("OWN")){
										object.authenticate(subject.getID(), 2);
										System.out.printf("\nSubject authenticated for %s", permission);
									}
									else{
										System.out.printf("\nAuthentication failed.");
									}
									objects.set(object.getID()-1, object);
									workingACM.updateObjects(objects);
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("REVOKE")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>1){				//Access restricted to Security Officers and Root
									splitCommand = command.split(" ");
									String permission = splitCommand[1];
									String objectName = splitCommand[3];
									String subjectID = splitCommand[5];
									ACMObject object = objects.get(0);
									ACMObject subject = subjects.get(0);
									for(int i=0;i<objects.size();i++){
										if(objects.get(i).getName().contains(objectName)){
											object = objects.get(i);
										}
									}
									for(int i=0;i<subjects.size();i++){
										if(subjects.get(i).getID() == Integer.parseInt(subjectID)){
											subject = subjects.get(i);
										}
									}
									if(permission.contains("EXECUTE")){
										object.removeExecutor(subject.getID());
										System.out.printf("\nSubject permission removed");
									}
									else if(permission.contains("CONTROL")){
										object.removeController(subject.getID());
										System.out.printf("\nSubject permission removed");
									}
									else if(permission.contains("OWN")){
										object.removeOwner(subject.getID());
										System.out.printf("\nSubject permission removed");
									}
									else{
										System.out.printf("\nRevoke failed.");
									}
									objects.set(object.getID()-1, object);
									workingACM.updateObjects(objects);
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("COMMIT")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>1){				//Access restricted to Security Officers and Root
									System.out.printf("Updates saved.\n%d - Last update\n%d - Current update", newACM.getUpdateCounter(), workingACM.getUpdateCounter());
									commitHistory.add(workingACM);
									newACM = (ACM) workingACM.clone();
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("ROLLBACK")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>1){				//Access restricted to Security Officers and Root
									splitCommand = command.split(" ");
									String commitNum = splitCommand[splitCommand.length-1];
									for(int i=0;i<commitHistory.size();i++){
										if(commitHistory.get(i).getUpdateCounter() == Integer.parseInt(commitNum)){
											newACM = (ACM) commitHistory.get(i).clone();
										}
									}
									System.out.printf("\nReverted to update %s", commitNum);
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("CREATE")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>2){				//Access restricted to Root
									splitCommand = command.split(" ");
									String tableName = splitCommand[splitCommand.length-1];
									workingACM.addObject(tableName);
									System.out.printf("\nObject %s added.", tableName);
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("DROP")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								if(userRole>2){
									splitCommand = command.split(" ");
									String tableName = splitCommand[splitCommand.length-1];
									workingACM.removeObject(tableName);
									System.out.printf("\nObject %s removed.", tableName);
								}
								else{
									System.out.printf("Authentication failure.");
								}
							}

							else if(command.contains("SELECT")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								ACMObject object = objects.get(0);
								splitCommand = command.split(" ");
								String tableName = splitCommand[splitCommand.length-1];
								String[] dataInput = Arrays.copyOfRange(splitCommand, 1, splitCommand.length-2);
								for(int i=0;i<objects.size();i++){
									if(objects.get(i).getName().contains(tableName)){
										object = objects.get(i);
									}
								}
								if(Arrays.asList(dataInput).contains("*")){
									ArrayList<String> objectData = object.getData();
									for(int i=0;i<objectData.size();i++){
										System.out.printf("%s\n", objectData.get(i));
									}
								}
								else{
									ArrayList<String> objectData = object.getData();
									for(int i=0;i<objectData.size();i++){
										for(int j=0;j<dataInput.length;j++){
											if(objectData.get(i).contains(dataInput[j])){
												System.out.printf("%s\n", objectData.get(i));
											}
										}
									}
								}
							}

							else if(command.contains("INSERT")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								ACMObject object = objects.get(0);
								splitCommand = command.split(" ");
								String tableName = splitCommand[2];
								String[] dataInput = Arrays.copyOfRange(splitCommand, 4, splitCommand.length);
								for(int i=0;i<objects.size();i++){
									if(objects.get(i).getName().contains(tableName)){
										object = objects.get(i);
									}
								}
								ArrayList<String> objectData = object.getData();
								for(int i=0;i<dataInput.length;i++){
									objectData.add(dataInput[i]);
								}
								object.updateData(objectData);
								objects.set(object.getID()-1, object);
								workingACM.updateObjects(objects);
							}

							else if(command.contains("DELETE")){
								subjects = workingACM.getSubjects();
								objects = workingACM.getObjects();
								ACMObject object = objects.get(0);
								splitCommand = command.split(" ");
								String tableName = splitCommand[2];
								String[] dataInput = Arrays.copyOfRange(splitCommand, 3, splitCommand.length);
								for(int i=0;i<objects.size();i++){
									if(objects.get(i).getName().contains(tableName)){
										object = objects.get(i);
									}
								}
								ArrayList<String> objectData = object.getData();
								for(int i=0;i<dataInput.length;i++){
									objectData.remove(dataInput[i]);
								}
								object.updateData(objectData);
								objects.set(object.getID()-1, object);
								workingACM.updateObjects(objects);
							}

							else if(command.contains("ROLE")){
								System.out.printf("%d\n", userRole);
							}

							else if(command.contains("HISTORY")){
								System.out.printf("Update Counters:\n");
								for(int i=0;i<commitHistory.size();i++){
									System.out.printf("%d\n", commitHistory.get(i).getUpdateCounter());
								}
							}

							else if(command.contains("EXIT")){
								dbPersist = false;
								break;
							}

							else if(command.contains("HELP")){
								System.out.printf("Commands:\nGRANT permission_type ON object_name TO subject_id - Grant permissions\n");
								System.out.printf("REVOKE permission_type ON object_name FROM subject_id - Revoke permissions\n");
								System.out.printf("COMMIT - Update objects list and commit work\n");
								System.out.printf("ROLLBACK update_counter - revert to specific commit\n");
								System.out.printf("CREATE table_name - Creates an object with the specified name\n");
								System.out.printf("DROP table_name - Deletes an object with the specified name\n");
								System.out.printf("SELECT data FROM table_name - Selects data from the specified table\n");
								System.out.printf("INSERT INTO table_name VALUES value1 value2 - Inserts values into table\n");
								System.out.printf("DELETE FROM table_name value1 value2 - Deletes data fitting condition\n");
								System.out.printf("ROLE - Prints the users current role\n");
								System.out.printf("HISTORY - Prints out commit history\n");
								System.out.printf("EXIT - Exits the database interface\n");
							}

							else{
								System.out.printf("Invalid input.");
							}

						}

						catch(Exception e){
							System.out.printf("Error");
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
