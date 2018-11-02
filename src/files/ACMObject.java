/* ************************************************************************** */
/*                                                                            */
/*                                                            |\              */
/*   ACMObject.java                                     ------| \----         */
/*                                                      |    \`  \  |  p      */
/*   By: jeudy2552 <jeudy2552@floridapoly.edu>          |  \`-\   \ |  o      */
/*                                                      |---\  \   `|  l      */
/*   Created: 2018/09/26 14:38:05 by jeudy2552          | ` .\  \   |  y      */
/*   Updated: 2018/10/15 14:18:37 by jeudy2552          -------------         */
/*                                                                            */
/* ************************************************************************** */
package ACM;

import java.util.ArrayList;

public class ACMObject{

    private String name;
    private int ID;
    private int objType;    //var to check whether this object is a subject or not
    private int role;
	private ArrayList<ACMObject> owners;
	private ArrayList<ACMObject> controllers;
	private ArrayList<ACMObject> executors;

    public ACMObject(String name, int ID, int objType){
        this.name = name;
        this.ID = ID;
        this.objType = objType;
    }

    public void setRole(int role){
        this.role = role;
    }

    public int getRole(){
        return role;
    }

    public String getName(){
        return name;
    }

    public int getID(){
        return ID;
    }

    public int getObjType(){
        return objType;
    }

	public void authenticate(ACMObject subject, int controlChoice){
		if(controlChoice == 0){
			executors.add(subject);
		}
		if(controlChoice == 1){
			controllers.add(subject);
		}
		if(controlChoice == 2){
			owners.add(subject);
		}
	}

}
