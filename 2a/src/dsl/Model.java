import java.util.ArrayList;

public class Model {
	// "Genereic" just for the time being
	private ArrayList<Object> params;
	private Object head;

	public Model(ArrayList<Object> params) {
		this.params = params;
	}

	public void step() {
		throw new UnsupportedOperationException("step must be implemented in subclass");
	}

	public void have() {
		throw new UnsupportedOperationException("have must be implemented in subclass");
	}

	public void run(int dt, int timeMax, boolean printHead, boolean verbose) {
		printHead = true;
		verbose = false;
		changeable = true;

		// Do lots of confusing stuff here
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
			vals = i;

      // unsure what this "theRest" section is for - mmcmill
			theRest = have.asList(now);
			for (int j = 0; j < theRest.length; j++) {
				vals.append(theRest[j]);
			}
      
			b4 = now;

			if (vals[2] >= 100) System.out.println("vals rounded to 2 decimals places");
			if (verbose) System.out.println("vals rounded to 2 decimal places");

			if (i == timeMax) System.out.println("vals rounded to 2 decimal places");
		}
	}
}
