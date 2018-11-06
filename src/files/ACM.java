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
	private int userRole;
    private ArrayList<ACMObject> subjects = new ArrayList<ACMObject>();
    private ArrayList<ACMObject> objects = new ArrayList<ACMObject>();
    private ArrayList<String> roles = new ArrayList<String>();

    public ACM(){
        roles.add("USER");
		roles.add("SECURITY");
        roles.add("ADMIN");
		ACMObject object1 = new ACMObject("DML", 1);
		ACMObject object2 = new ACMObject("DCL", 2);
		ACMObject object3 = new ACMObject("TCL", 3);
		objects.add(object1); objects.add(object2); objects.add(object3);
    }

    public void addRole(String role, int destination){
        roles.add(destination, role);
    }

    public ArrayList<String> getRoles(){
        return this.roles;
    }

	public void setUserRole(int roleInt){
		userRole = roleInt;
	}

	public int getUserRole(){
		return this.userRole;
	}

    public void addSubject(String name, int ID, int role){
        ACMObject newSubject = new ACMObject(name, ID);
        newSubject.setRole(role);
        subjects.add(newSubject);
        System.out.printf("\nSubject %s added", newSubject.getName());
    }

    public ACMObject removeSubject(int ID){
		ACMObject backup = subjects.get(ID-1);
        subjects.remove(ID);
		return backup;
    }

	public ArrayList<ACMObject> getSubjects(){
		return this.subjects;
	}

    public int getSubjectsNum(){
        return this.subjectsNum;
    }

	public ArrayList<ACMObject> getObjects(){
		return this.objects;
	}

    public int getObjectsNum(){
        return this.objectsNum;
    }

    public void printACM(){
        System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "", "ADMIN", "SECURITY", "USER", "DML", "DCL", "TCL");
		System.out.printf("\n%8s %52s", "", "+------------------------------------------------------------------");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "ADMIN |", "Control", "Owner", "Owner/Control", "Control", "Control", "Control");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "SECURITY |", "", "Control", "Control", "", "Execute", "Execute");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "USER |", "", "", "", "Execute", "", "\n");
    }

	public void printUAuth(){
		for(int i=0;i<objects.size();i++){
			System.out.printf("\n%s", objects.get(i).getName());
			System.out.printf("\n%11s | %s", "Owners", objects.get(i).getOwners());
			System.out.printf("\n%11s | %s", "Controllers", objects.get(i).getControllers());
			System.out.printf("\n%11s | %s\n", "Executors", objects.get(i).getExecutors());
		}
	}
}
