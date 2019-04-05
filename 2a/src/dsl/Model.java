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

	public void payload() {
		// TODO
	}

	public void run(Object o, int dt, int timeMax, boolean printHead, boolean verbose) {
		printHead = true;
		verbose = false;
		changeable = true;

		// Do lots of confusing stuff here

		if (printHead) {
			// Probably will change to user Logger
			System.out.println(head);
		}

		for (int i = 0; i < timeMax; i++) {
			now = o.have().payload(b4);
			now = o.step(dt, i, b4, now);
			now = o.have().payload(now);
			vals = i;

			theRest = o.have().asList(now, o.params, changeable);
			for (int j = 0; j < theRest.length; j++) {
				vals.append(theRest[j]);
			}

			i += dt;
			b4 = now;

			if (vals[2] >= 100) System.out.println("vals rounded to 2 decimals places");
			if (verbose) System.out.println("vals rounded to 2 decimal places");

			if (i == timeMax) System.out.println("vals rounded to 2 decimal places");
		}
	}
}
