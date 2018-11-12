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

public class ACM implements Cloneable{

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
    }
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		ACM cloned = (ACM) super.clone();
		cloned.updateObjects((ArrayList<ACMObject>)cloned.getObjects().clone());
		return cloned;
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

	public ArrayList<ACMObject> getObjects(){
		return this.objects;
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
            if(objects.get(i).getName().contains(name)){
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
		//Prints ACM
		ArrayList<ACMObject> headerObj = new ArrayList<ACMObject>();
		ArrayList<String> header = new ArrayList<String>();
		int maxSize = 0;
		for(int i=0;i<subjects.size();i++){
			headerObj.add(subjects.get(i));
			header.add(subjects.get(i).getName());
			if(subjects.get(i).getName().length() > maxSize){
				maxSize = subjects.get(i).getName().length();
			}
		}
		for(int i=0;i<objects.size();i++){
			headerObj.add(objects.get(i));
			header.add(objects.get(i).getName());
			if(objects.get(i).getName().length() > maxSize){
				maxSize = objects.get(i).getName().length();
			}
		}
		String formatString = "\n" + String.format("%"+maxSize+"s", " ") + " | ";
		String headerString = String.join(" | ", header);
		headerString = formatString + headerString + " | ";
		int titleCenter = headerString.length()/2 - 3;
		String title = "\n" + String.format("%"+titleCenter+"s", " ") + "ACM\n";
		System.out.printf(title);
		System.out.printf(headerString);
		for(int i=0;i<subjects.size();i++){
			int columnMax = 0;
			String rowString = "\n" + String.format("%"+maxSize+"s", subjects.get(i).getName()) + " | ";
			for(int j=0;j<headerObj.size();j++){
				columnMax = headerObj.get(j).getName().length();
				if(headerObj.get(j).getObjType()){
					if(subjects.get(i).getRole() > 1){
						rowString = rowString + String.format("%"+columnMax+"s", "O") + " | ";
					}
					else if(subjects.get(i).getRole() > 0 && subjects.get(i).getRole() < 2){
						rowString = rowString + String.format("%"+columnMax+"s", "C") + " | ";
					}
					else{
						rowString = rowString + String.format("%"+columnMax+"s", " ") + " | ";
					}
				}
				else{
					if(headerObj.get(j).getOwners().contains(subjects.get(i).getID())){
						rowString = rowString + String.format("%"+columnMax+"s", "O") + " | ";
					}
					else if(headerObj.get(j).getControllers().contains(subjects.get(i).getID())){
						rowString = rowString + String.format("%"+columnMax+"s", "C") + " | ";
					}
					else if(headerObj.get(j).getExecutors().contains(subjects.get(i).getID())){
						rowString = rowString + String.format("%"+columnMax+"s", "E") + " | ";
					}
					else{
						rowString = rowString + String.format("%"+columnMax+"s", " ") + " | ";
					}
				}
			}
			System.out.printf(rowString);
		}
	}
}
