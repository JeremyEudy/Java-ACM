/* ************************************************************************** */
/*                                                                            */
/*                                                            |\              */
/*   ACM.java                                           ------| \----         */
/*                                                      |    \`  \  |  p      */
/*   By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o      */
/*                                                      |---\  \   `|  l      */
/*   Created: 2018/09/26 14:23:11 by jeudy2552          | ` .\  \   |  y      */
/*   Updated: 2018/11/09 11:29:11 by jeudy2552          -------------         */
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
	private int index;
	private int updateCounter;
    private ArrayList<ACMObject> subjects = new ArrayList<ACMObject>();
    private ArrayList<ACMObject> objects = new ArrayList<ACMObject>();
    private ArrayList<String> roles = new ArrayList<String>();

    public ACM(){
		//Populate roles list
        roles.add("USER");
		roles.add("SECURITY");
        roles.add("ADMIN");
		//Populate objects
		ACMObject object1 = new ACMObject("DML", 1);
		ACMObject object2 = new ACMObject("DCL", 2);
		ACMObject object3 = new ACMObject("TCL", 3);
		objects.add(object1); objects.add(object2); objects.add(object3);
    }

	public void updateCount(){
		this.updateCounter++;
	}

	public int getUpdateCounter(){
		return this.updateCounter;
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
        ACMObject newSubject = new ACMObject(name, ID);			//Generate new ACMObject from params
        newSubject.setRole(role);
        subjects.add(newSubject);
        System.out.printf("\nSubject %s added\n", newSubject.getName());
		this.updateCount();
    }

    public void removeSubject(int ID){
		//Search subjects for goal
		for(int i=0;i<subjects.size();i++){
			if(subjects.get(i).getID() == ID){
				index = i;
			}
		}
        subjects.remove(index);									//Remove subject
		this.updateCount();
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

	public void updateObjects(ArrayList<ACMObject> objects){
		this.objects = objects;
		this.updateCount();
	}

    public void addObject(String name){
        int objectID = objects.get(objects.size()-1).getID()+1;
        ACMObject newObject = new ACMObject(name, objectID);
        this.objects.add(newObject);
		this.updateCount();
    }

    public void removeObject(String name){
        int index = 0;
        for(int i=0;i<objects.size();i++){
            if(objects.get(i).getName() == name){
                index = i;
            }
        }
	    objects.remove(index);
		this.updateCount();
    }
	
	public void printUsers(){
		//Prints dynamic user list
		System.out.printf("\nUSERS:\n");
		for(int i=0;i<subjects.size();i++){
			System.out.printf("%d - %s | ID: %d | Role: %s\n", i+1, subjects.get(i).getName(), subjects.get(i).getID(), subjects.get(i).getRole());
		}
	}

    public void printACM(){
		//Prints static ACM
        System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "", "ADMIN", "SECURITY", "USER", "DML", "DCL", "TCL");
		System.out.printf("\n%8s %52s", "", "+------------------------------------------------------------------");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "ADMIN |", "Control", "Owner", "Owner/Control", "Control", "Control", "Control");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "SECURITY |", "", "Control", "Control", "", "Execute", "Execute");
		System.out.printf("\n%10s %8s %9s %14s %8s %8s %8s", "USER |", "", "", "", "Execute", "", "\n");
    }

	public void printUAuth(){
		//Prints dynamic user authentication table
		for(int i=0;i<objects.size();i++){
			System.out.printf("\n%s", objects.get(i).getName());
			System.out.printf("\n%11s | %s", "Owners", objects.get(i).getOwners());
			System.out.printf("\n%11s | %s", "Controllers", objects.get(i).getControllers());
			System.out.printf("\n%11s | %s\n", "Executors", objects.get(i).getExecutors());
		}
		System.out.printf("Users:\n");
		for(int i=0;i<subjects.size();i++){
			System.out.printf("%d - %s | ID: %d\n", i+1, subjects.get(i).getName(), subjects.get(i).getID());
		}
	}
}
