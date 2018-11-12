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

public class ACMObject implements Cloneable{

    private String name;
    private int ID;			//In order to keep track of subjects
    private int role;		//Subjects and objects are the same class, however subjects are the only one to use this
	private int index;
	private ArrayList<Integer> owners = new ArrayList<Integer>();
	private ArrayList<Integer> controllers = new ArrayList<Integer>();
	private ArrayList<Integer> executors = new ArrayList<Integer>();
	private ArrayList<String> data = new ArrayList<String>();

    public ACMObject(String name, int ID){
        this.name = name;
        this.ID = ID;
    }

	@Override
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
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

	public ArrayList<Integer> getOwners(){
		return owners;
	}

	public ArrayList<Integer> getControllers(){
		return controllers;
	}

	public ArrayList<Integer> getExecutors(){
		return executors;
	}


	public void updateOwners(ArrayList<Integer> owners){
		this.owners = owners;
	}

	public void updateControllers(ArrayList<Integer> controllers){
		this.controllers = controllers;
	}

	public void updateExecutors(ArrayList<Integer> executors){
		this.executors = executors;
	}

	public void removeOwner(int ID){
		for(int i=0;i<this.owners.size();i++){
			if(this.owners.get(i) == ID){
				index = i;
			}
		}
		this.owners.remove(index);
	}

	public void removeController(int ID){
		for(int i=0;i<this.controllers.size();i++){
			if(this.controllers.get(i) == ID){
				index = i;
			}
		}
		this.controllers.remove(index);
	}

	public void removeExecutor(int ID){
		for(int i=0;i<this.executors.size();i++){
			if(this.executors.get(i) == ID){
				index = i;
			}
		}
		this.executors.remove(index);
	}

	public void authenticate(int ID, int controlChoice){
		if(controlChoice == 0){
			executors.add(ID);
		}
		else if(controlChoice == 1){
			controllers.add(ID);
		}
		else if(controlChoice == 2){
			owners.add(ID);
		}
	}

	public void updateData(ArrayList<String> newData){
		this.data = newData;
	}

	public ArrayList<String> getData(){
		return data;
	}

}
