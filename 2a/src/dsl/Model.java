import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public void step(int dt, int t, HashMap<String, Thing> i, HashMap<String, Thing> j) {
		throw new UnsupportedOperationException("step must be implemented in subclass");
	}

	public Things have() {
		throw new UnsupportedOperationException("have must be implemented in subclass");
	} 

	public void run(int dt, int timeMax, boolean printHead, boolean verbose) {
		
		printHead = true;
		verbose = false;
		boolean changeable = true;
		int t = 0;
		
		BrooksLaw brooksLaw = new BrooksLaw(params);

		Things have = brooksLaw.have();
		HashMap<String, Thing> b4 = have.payload(have.things);
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

			//an arraylist of all the "init"s in the "Things" : an arraylist of doubles
			List<Double> vals = new ArrayList<Double>();
			now.values().forEach(Thing -> {vals.add(Thing.getInit());});
			vals.add(0, (double) i);     		
			
			b4 = now;

			if (verbose || i == timeMax) {
				// starts at 1 because time is at 0
				for (int j = 1; j <= vals.size(); j++) {
					System.out.printf("%.2f, ", vals.get(j));				
				}
			}
		}
	}
}