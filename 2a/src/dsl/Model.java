import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Model {
	// "Genereic" just for the time being
	// private ArrayList<Object> params;
	private HashMap<String, Thing> params;

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

	public void run(int dt, int timeMax, boolean verbose) {
		int t = 0;
		
		BrooksLaw brooksLaw = new BrooksLaw(params);

		Things have = brooksLaw.have();
		HashMap<String, Thing> b4 = have.payload(have.things);

//    experiencedPeopleNeededToTrain
//    plannedSoftware
//    personnelAllocationRate
//    assimilationRate
//    softwareDevelopmentRate
//    CommunicationOverhead
		for (int i = t; i < timeMax; i++) {
			Logger.log("Starting iteration for time t = " + i);
			HashMap<String, Thing> now = have.payload(b4);
			brooksLaw.step(dt, i, b4, now);

			//an arraylist of all the "init"s in the "Things" : an arraylist of doubles
			List<Double> vals = new ArrayList<Double>();
			
			TreeMap<String, Thing> blTree = new TreeMap<String, Thing>();
			blTree.putAll(params);
			blTree.values().forEach(Thing -> {
				vals.add(Thing.getInit());
				});
			vals.add(0, (double) i);     		
			
			b4 = now;

			if (verbose || i == (timeMax - 1)) {
				System.out.println(
						"?t, $atleast, >d, $done_percent, <ep, $learning_curve, <np, $nprod, $optimism, $pomposity, $productivity_exp, $productivity_new, $r, $to, $ts, ?verbose");
				for (int j = 0; j < vals.size(); j++) {
					System.out.printf("%.2f, ", vals.get(j));				
				}
			}
		}
	}
}