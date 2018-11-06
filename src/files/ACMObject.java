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
    private int role;
	private ArrayList<String> owners = new ArrayList<String>();
	private ArrayList<String> controllers = new ArrayList<String>();
	private ArrayList<String> executors = new ArrayList<String>();

    public ACMObject(String name, int ID){
        this.name = name;
        this.ID = ID;
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

	public ArrayList<String> getOwners(){
		return owners;
	}

	public ArrayList<String> getControllers(){
		return controllers;
	}

	public ArrayList<String> getExecutors(){
		return executors;
	}

	public void authenticate(String subject, int controlChoice){
		if(controlChoice == 0){
			executors.add(subject);
		}
		else if(controlChoice == 1){
			controllers.add(subject);
		}
		else if(controlChoice == 2){
			owners.add(subject);
		}
	}

}
