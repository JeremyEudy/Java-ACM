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
		FileInputStream fIn = null;									//Use File IO to store user account info
		FileOutputStream fOut = null;
        System.out.printf("###################################\n");
        System.out.printf("#                                 #\n");
        System.out.printf("# Java Access Control Matrix Tool #\n");
        System.out.printf("#                                 #\n");
        System.out.printf("###################################\n");

        ACM newACM = new ACM();
        boolean persist = true;
		/*
        while(true){
			System.out.printf("Please input your username:\n>");
			String userName = in.nextLine();
			System.out.printf("Please input your password:\n>");
			char[] passString = Console.readPassword();
			String password = new String(passString);
			//If valid info, then approve
		}
		*/

		int userRole = 0;

		while(persist){
			System.out.printf("\nWhich role do you belong to?\n0 - User\n1 - Security Officer\n2 - Administrator\n>");
			userRole = in.nextInt();
			switch(userRole){
				case 0:
					System.out.printf("\nRole set to 'User'");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				case 1:
					System.out.printf("\nRole set to 'Security Officer'");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				case 2:
					System.out.printf("\nRole set to 'Administrator'");
					newACM.setUserRole(userRole);
					persist = false;
					break;
				default:
					System.out.printf("\nInvalid choice.");
					break;
			}
		}

		persist = true;

        while(persist){
            System.out.printf("\nPlease select an option.\n1 - View ACM\n2 - Add a new subject\n3 - Delete a subject\n4 - Autenticate user\n5 - View user authentication table\n6 - Manipulate database\n0 - Quit\n>");
            int choice = in.nextInt();
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
                    newACM.printACM();
                    break;

                case 2:
					in = new Scanner(System.in);
                    roleList = newACM.getRoles();
                    System.out.printf("\nWhat is the name of the subject?\n>");
                    name = in.nextLine();
                    name = name.toUpperCase();
                    ID = newACM.getSubjectsNum()+1;
                    System.out.printf("\nWhat role does this subject have?\n");
                    for(int i=0;i<roleList.size();i++){
                        System.out.printf("%d - %s\n", i, roleList.get(i));
                    }
                    System.out.printf("\n> ");
                    int roleInput = in.nextInt();
					try{
	                    String roleName = roleList.get(roleInput);
						role = roleList.indexOf(roleName);
					}
				    catch(Exception e){
						role = roleList.indexOf("USER");
				    }
                    newACM.addSubject(name, ID, role);
                    break;

                case 3:
					subjects = newACM.getSubjects();
                    System.out.printf("\nWhat is the ID of the subject?\n");
					for(int i=0;i<subjects.size();i++){
						ACMObject one = subjects.get(i);
						System.out.printf("%d - %s\n", one.getID(), one.getName());
					}
                    ID = in.nextInt();
                    newACM.removeSubject(ID);
                    break;

				case 4:
					if(userRole>0 & newACM.getSubjects().size()>0){
						subjects = newACM.getSubjects();
						objects = newACM.getObjects();
						System.out.printf("\nWhich subject would you like to authenticate?\n");
						for(int i=0;i<subjects.size();i++){
							ACMObject one = subjects.get(i);
							System.out.printf("%d - %s\n", i, one.getName());
						}
						System.out.printf(">");
						int subjectChoice = in.nextInt();
						ACMObject subject = subjects.get(subjectChoice);
						
						System.out.printf("\nWhich object would you like them to have access to?\n");
						for(int i=0;i<objects.size();i++){
							ACMObject one = objects.get(i);
							System.out.printf("%d - %s\n", i, one.getName());
						}
						System.out.printf(">");
						int objectChoice = in.nextInt();
						ACMObject object = objects.get(objectChoice);
						System.out.printf("\nWhat privileges should they have?\n0 - Execute\n1 - Control\n2 - Owner\n>");
						int controlChoice = in.nextInt();
						object.authenticate(subject.getName(), controlChoice);
						System.out.printf("\nAuthentication complete.");
						break;
					}

					else if(userRole>0 & newACM.getSubjects().size()<=0){
						System.out.printf("There are no subjects!");
						break;
					}

					else{
						System.out.printf("Invalid clearance.");
						break;
					}
				
				case 5:
					newACM.printUAuth();
					break;

                case 6:
					subjects = newACM.getSubjects();
					objects = newACM.getObjects();
					boolean dbPersist = true;
					while(dbPersist){
						System.out.printf("\n>");
						in = new Scanner(System.in);
						String command = in.nextLine();
						command = command.toLowerCase();
						if(command.contains("grant") | command.contains("revoke") | command.contains("commit") | command.contains("rollback")){
							if(userRole>0){
								System.out.printf("Success.");
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("create") | command.contains("drop")){
							if(userRole>1){
								System.out.printf("Success.");
							}
							else{
								System.out.printf("Authentication failure.");
							}
						}
						else if(command.contains("exit")){
							dbPersist = false;
							break;
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
