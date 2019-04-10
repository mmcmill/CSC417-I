import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Model {
	// "Genereic" just for the time being
	// private ArrayList<Object> params;
	private HashMap<String, Double> params;
	private Object head;

	public Model(ArrayList<Double> params) {
		this.params = params;
	}

	public HashMap<String, Double> getParams() {
		return params;
	}

	public void step() {
		throw new UnsupportedOperationException("step must be implemented in subclass");
	}

	public void have() {
		throw new UnsupportedOperationException("have must be implemented in subclass");
	}
	
	public static ArrayList<Object> inputThings() {
		
		ArrayList<Object> list = new ArrayList<Object>();
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double np = scan.nextDouble();
		list.add(np);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_exp = scan.nextDouble();
		list.add(productivity_exp);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ep = scan.nextDouble();
		list.add(ep);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double atleast = scan.nextDouble();
		list.add(atleast);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_new = scan.nextDouble();
		list.add(productivity_new);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double r = scan.nextDouble();
		list.add(r);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double learning_curve = scan.nextDouble();
		list.add(learning_curve);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double nprod = scan.nextDouble();
		list.add(nprod);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double done_percent= scan.nextDouble();
		list.add(done_percent);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double to = scan.nextDouble();
		list.add(to);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double pomposity = scan.nextDouble();
		list.add(pomposity);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ts = scan.nextDouble();
		list.add(ts);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double d = scan.nextDouble();
		list.add(d);
		scan.useDelimiter("[T|F]");
		scan.next();
		scan.useDelimiter("[,]");
		boolean verbose = scan.nextBoolean();
		list.add(verbose);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[}]");
		double optimism = scan.nextDouble();
		list.add(optimism);
		scan.useDelimiter("[0-9]");
		scan.nextLine();
		
		scan.close();
		return list;
	}

	public void run(int dt, int timeMax, boolean printHead, boolean verbose) {
		printHead = true;
		verbose = false;
		boolean changeable = true;

		Things have = this.have();
		int t = 0;
		HashMap<String, Thing> b4 = have.payload(/*current payload*/);
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
		
		//need to add input operations to handle the dictionary input from beforebrooks2.txt
		//send all input to the for loop for processing
		ArrayList<Object> inputList = inputThings();

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
      		*/
			
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
}