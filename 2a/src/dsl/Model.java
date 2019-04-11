import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Model {
	// "Genereic" just for the time being
	// private ArrayList<Object> params;
	private HashMap<String, Thing> params;
	private Object head;

	public Model(HashMap<String, Thing> params) {
		this.params = params;
	}

	public HashMap<String, Thing> getParams() {
		return params;
	}

	public void step() {
		throw new UnsupportedOperationException("step must be implemented in subclass");
	}

	public void have() {
		throw new UnsupportedOperationException("have must be implemented in subclass");
	} }
/*
	public void run(int dt, int timeMax, boolean printHead, boolean verbose) {
		
		printHead = true;
		verbose = false;
		boolean changeable = true;
		
		//instantiate BrooksLaw

		Things have = this.have();
		int t = 0;
		HashMap<String, Thing> b4 = have.payload();
		ArrayList<String> head = new ArrayList<String>();
    
		head.add("?t");
    
		for (String col : have.order) {
			if (col.equals("d")) {
				head.add(">d");
			} else if (col.equals("ep") || col.equals("np")){
				head.add("<"+col);
			} else {
				head.add("$"+col);
			}
		}
    
		if (printHead) {
			// Probably will change to user Logger
			System.out.println(head);
		}

		for (int i = 0; i < timeMax; i++) {
			HashMap<String, Thing> now = have.payload(b4);
			this.step(dt, i, b4, now);
      
			// what is vals supposed to be? - mmcmill
			
			// I believe this is a list of map values headed by the time t
			// I have no clue why this is useful though. 
			// If the mapping changes from Thing to int this needs to change as well
			// but to Integer instead of int for the List -dsmicken
			
			//an arraylist of all the "init"s in the "Things" : an arraylist of doubles
			List<Thing> vals = new ArrayList<Thing>(now.values());
			vals.add(0, i);

			// unsure what this "theRest" section is for - mmcmill
			
			// not sure where you are getting this. I don't see anything like this
			// on the github page. - dsmicken
			
			/*
			theRest = have.asList(now);
			for (int j = 0; j < theRest.length; j++) {
				vals.append(theRest[j]);
			}
      		
			
			b4 = now;

			// Possible solution for the above statement ^ comma delimeted right now, not sure if it's needed
			if (verbose || i == timeMax) {
				// starts at 1 because time is at 0
				for (int j = 1; j <= vals.size(); j++) {
					System.out.printf("%.2f, ", vals.get(j));				
				}
			}
		}
	}
}*/