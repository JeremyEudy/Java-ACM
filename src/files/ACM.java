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
        roles.add("ROOT");
    }

    public void addRole(String role, int destination){
        roles.add(destination, role);
    }

    public ArrayList getRoles(){
        return roles;
    }

    public void removeRole(int role){
        roles.remove(role);
    }

    public void updateRoles(ArrayList roles){
        this.roles = roles;

    }

    public void purgeRole(int roleID){
        for(int i = 0; i < subjects.size(); i++){
            if(subjects.get(i).getRole() == roleID){
                subjects.get(i).setRole(0);
            }
        }
    }

    public void addSubject(String name, int ID, int objType, int role){
        ACMObject newSubject = new ACMObject(name, ID, objType);
        newSubject.setRole(role);
        subjects.add(newSubject);
        System.out.printf("\nSubject %s added", newSubject.getName());
    }

    public void addObject(String name, int ID, int objType){
        ACMObject newObject = new ACMObject(name, ID, objType);
        objects.add(newObject);
        System.out.printf("\nObject %s added", newObject.getName());
    }

    public void removeSubject(int ID){
        subjects.remove(ID);
    }

    public void removeObject(int ID){
        objects.remove(ID);
    }

    public int getSubjectsNum(){
        return this.subjectsNum;
    }

    public int getObjectsNum(){
        return this.objectsNum;
    }

    public void printACM(){
        System.out.printf("\n%10s %5s %15s", "Name", "ID", "Object Type\n");
        for(int i=0; i<subjectsNum; i++){
            System.out.format("%10s %5d %15s\n", subjects.get(i).getName(), subjects.get(i).getID(), subjects.get(i).getObjType());
        }
        System.out.printf("\n%10s %5s %15s", "Name", "ID", "Object Type\n");
        for(int i=0; i<objectsNum; i++){
            System.out.format("%10s %5d %15s\n", objects.get(i).getName(), objects.get(i).getID(), objects.get(i).getObjType());
        }
    }
}
