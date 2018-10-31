/* ************************************************************************** */
/*                                                                            */
/*                                                            |\              */
/*   ACM.java                                           ------| \----         */
/*                                                      |    \`  \  |  p      */
/*   By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o      */
/*                                                      |---\  \   `|  l      */
/*   Created: 2018/09/26 14:23:11 by jeudy2552          | ` .\  \   |  y      */
/*   Updated: 2018/10/19 12:46:51 by jeudy2552          -------------         */
/*                                                                            */
/* ************************************************************************** */
package ACM;

import java.util.ArrayList;
import java.util.Scanner;

public class ACM{

    private int subjectsNum;
    private int objectsNum;
    private int rolesNum;
    private ArrayList<ACMObject> subjects = new ArrayList<ACMObject>();
    private ArrayList<ACMObject> objects = new ArrayList<ACMObject>();
    private ArrayList<String> roles = new ArrayList<String>();

    public ACM(){
        roles.add("USER");
	roles.add("SECURITY");
        roles.add("ADMIN");
    }

    public void addRole(String role, int destination){
        roles.add(destination, role);
    }

    public ArrayList getRoles(){
        return roles;
    }

    public void addSubject(String name, int ID, int objType, int role){
        ACMObject newSubject = new ACMObject(name, ID, objType);
        newSubject.setRole(role);
        subjects.add(newSubject);
        System.out.printf("\nSubject %s added", newSubject.getName());
    }

    public void removeSubject(int ID){
        subjects.remove(ID);
    }

    public int getSubjectsNum(){
        return this.subjectsNum;
    }

    public int getObjectsNum(){
        return this.objectsNum;
    }

    public void printACM(){
        System.out.printf("\n%10s %8s %9s %14s %8s %8s", "", "ADMIN", "SECURITY", "USER", "DML", "DCL");
		System.out.printf("\n%8s %52s", "", "+-----------------------------------------------------");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s", "ADMIN |", "Control", "Owner", "Owner/Control", "Control", "Control");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s", "SECURITY |", "", "Control", "Control", "", "Execute");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s", "USER |", "", "", "", "Execute", "\n");
    }
}
