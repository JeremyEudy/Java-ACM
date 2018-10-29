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

public class ACMTest{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.printf("###################################\n");
        System.out.printf("#                                 #\n");
        System.out.printf("# Java Access Control Matrix Tool #\n");
        System.out.printf("#                                 #\n");
        System.out.printf("###################################\n");

        ACM newACM = new ACM();
        boolean persist = true;
        while(persist){
            System.out.printf("\nPlease select an option.\n1-View ACM\n2-Add a new subject\n3-Add a new object\n4-Delete a subject\n5-Delete an object\n6-Add a role\n7-Remove a role\n0-Quit\n>");
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
                    newACM.addSubject(name, ID, objType, role);
                    break;
                case 3:
                    System.out.printf("\nWhat is the name of the object? ");
                    name = in.nextLine();
                    name = name.toUpperCase();
                    ID = newACM.getObjectsNum()+1;
                    objType = 0;
                    newACM.addObject(name, ID, objType);
                    break;
                case 4:
                    System.out.printf("\nWhat is the ID of the subject? ");
                    ID = in.nextInt();
                    newACM.removeSubject(ID);
                    break;
                case 5:
                    System.out.printf("\nWhat is the ID of the object? ");
                    ID = in.nextInt();
                    newACM.removeObject(ID);
                    break;
                case 6:
                    System.out.printf("\nWhat is the name of the role? ");
                    roleName = in.nextLine();
                    roleName = roleName.toUpperCase();
                    roleList = newACM.getRoles();
                    for(int i = 0; i < roleList.size(); i++){
                        System.out.println("("+i+")"+": "+roleList.get(i));
                    }
                    System.out.printf("\nWhere would you like this role to fit in the hierarchy? (Note: roles will be shifted down)\n>");
                    int destination = in.nextInt();
                    newACM.addRole(roleName, destination);
                    break;
                case 7:
                    roleList = newACM.getRoles();
                    for(int i = 0; i < roleList.size(); i++){
                        System.out.println("("+i+")"+": "+roleList.get(i));
                    }
                    System.out.printf("\nWhich role would you like to remove?\n>");
                    if(roleID == 0 || roleID == 1){
                        System.out.printf("\nThis role is protected, you are not able to remove it.");
                    }
                    else{
                        int roleID = in.nextInt();
                        role = roleList.get(roleID);
                        roleList.remove(role);
                        newACM.updateRoles(roleList);
                        newACM.purgeRole(roleID);
                    }
                    break;
                default:
                    System.out.printf("\nInvalid choice.");
                    break;
            }
        }
    }
}
