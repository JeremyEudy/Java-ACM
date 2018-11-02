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

//This doesn't print right now since my method of autogenerating var names doesn't work.

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
            System.out.printf("\nPlease select an option.\n1 - View ACM\n2 - Add a new subject\n3 - Delete a subject\n4 - Autenticate user\n5 - Manipulate database\n0 - Quit\n>");
            int choice = in.nextInt();
            int ID = 0;
            String name = "";
            int objType;
            String roleName = "";
            ArrayList roleList;
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
                    roleList = newACM.getRoles();
                    System.out.printf("\nWhat is the name of the subject? ");
                    name = in.nextLine();
                    name = name.toUpperCase();
                    ID = newACM.getSubjectsNum()+1;
                    objType = 1;
                    System.out.printf("\nWhat role does this subject have? ");
                    for(int i=0;i<roleList.size();i++){
                        System.out.println(roleList.get(i));
                    }
                    System.out.printf("\n> ");
                    roleName = in.nextLine();
                    roleName = roleName.toUpperCase();
                    role = roleList.indexOf(roleName);
				    if(role == -1){
						role = roleList.indexOf("USER");
				    }
                    newACM.addSubject(name, ID, objType, role);
                    break;

                case 3:
                    System.out.printf("\nWhat is the ID of the subject? ");
                    ID = in.nextInt();
                    newACM.removeSubject(ID);
                    break;

				case 4:
					if(userRole>0 & newACM.getSubjects().size()>0){
						ArrayList subjects = newACM.getSubjects();

						for(int i=0;i<subjects.size();i++){
							ACMObject one = subjects.get(i);
							System.out.printf("%d - %s", i, one.getName());
						}

						System.out.printf("\nWhich subject would you like to authenticate?");
						int subjectChoice = in.nextInt();
						ACMObject subject = subjects.get(subjectChoice);
						
						System.out.printf("\nWhich object would you like them to have access to?");
						int objectChoice = in.nextInt();
						ACMObject object = objects.get(objectChoice);
						System.out.printf("\nWhat privileges should they have?\n0 - Execute\n1 - Control\n2 - Owner\n>");
						int controlChoice = in.nextInt();
						object.authenticate(subject, controlChoice);
						System.out.printf("\nAuthentication complete.");
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
					boolean dbPersist = true;
					while(dbPersist){
						System.out.printf("\n>");
						String command = in.nextLine();
						command = command.toLowerCase();
						if(command.contains("grant") | command.contains("revoke") | command.contains("commit") | command.contains("rollback")){
							if(userRole>0){
								System.out.printf("\nSuccess.");
							}
							else{
								System.out.printf("\nAuthentication failure.");
							}
						}
						else if(command.contains("create") | command.contains("drop")){
							if(userRole>1){
								System.out.printf("\nSuccess.");
							}
							else{
								System.out.printf("\nAuthentication failure.");
							}
						}
						else{
							System.out.printf("\nSuccess.");
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
