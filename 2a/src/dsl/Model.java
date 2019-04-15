import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Model {
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
		BrooksLaw brooksLaw = new BrooksLaw(params);
		Things have = brooksLaw.have();
		HashMap<String, Thing> b4 = have.payload(null);

		for (int t = 0; t < timeMax; t += dt) {
			Logger.log("Starting iteration for time t = " + t);
			HashMap<String, Thing> now = have.payload(b4);
			brooksLaw.step(dt, t, b4, now);

			List<Double> vals = new ArrayList<Double>();
			
			TreeMap<String, Thing> blTree = new TreeMap<String, Thing>();
			blTree.putAll(params);
			List<String> removedThings = Arrays.asList("experiencedPeopleNeededToTrain", "plannedSoftware",
					"personnelAllocationRate", "assimilationRate", "softwareDevelopmentRate", "communicationOverhead");
			blTree.values().forEach(Thing -> {
				if (!removedThings.contains(Thing.getTxt())) {
					System.out.println(Thing.getTxt() + " " + Thing.getInit());
					vals.add(Thing.getInit());
				}
				});
			if (t + 1 == timeMax)
				vals.add(0, 1.0);
			else
				vals.add(0, 0.0);
			if (verbose)
				vals.add(1.0);
			else
				vals.add(0.0);
			b4 = now;

			if (verbose || t + 1 == timeMax) {
				System.out.println(
						"?t, $atleast, >d, $done_percent, <ep, $learning_curve, <np, $nprod, $optimism, $pomposity, $productivity_exp, $productivity_new, $r, $to, $ts, ?verbose");
				for (int j = 0; j < vals.size(); j++) {
					if (j == 0)
						System.out.printf("%d", vals.get(j).intValue());
					else
						System.out.printf(", %.2f", vals.get(j));				
				}
				System.out.println();
			}
		}
	}
}