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

	public HashMap<String, Thing> step(int dt, int t, HashMap<String, Thing> i, HashMap<String, Thing> j) {
		throw new UnsupportedOperationException("step must be implemented in subclass");
	}

	public Things have() {
		throw new UnsupportedOperationException("have must be implemented in subclass");
	} 

	public ArrayList<String> run(int dt, int timeMax, boolean verbose) {
		BrooksLaw brooksLaw = new BrooksLaw(params);
		Things have = brooksLaw.have();
		HashMap<String, Thing> b4 = have.payload(null);
		//printHashMap(b4);
		ArrayList<String> outputList = new ArrayList<String>();

		for (int t = 0; t < timeMax; t += dt) {
			Logger.log("Starting iteration for time t = " + t);
			HashMap<String, Thing> now = have.payload(b4);
			//printHashMap(now);
			now = brooksLaw.step(dt, t, b4, now);
			
			List<Double> vals = new ArrayList<Double>();
			
			TreeMap<String, Thing> blTree = new TreeMap<String, Thing>();
			blTree.putAll(params);
			List<String> removedThings = Arrays.asList("experiencedPeopleNeededToTrain", "plannedSoftware",
					"personnelAllocationRate", "assimilationRate", "softwareDevelopmentRate", "communicationOverhead");
			blTree.values().forEach(Thing -> {
				if (!removedThings.contains(Thing.getTxt())) {
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
			StringBuilder outputLine = new StringBuilder("");
			if (verbose || t + 1 == timeMax) {
				for (int j = 0; j < vals.size(); j++) {
					if (j == 0)
						outputLine.append(String.format("%d",  vals.get(j).intValue()));
					else
						outputLine.append(String.format(", %.2f", vals.get(j)));			
				}
			}
			if (!outputLine.toString().equals(""))
				outputList.add(outputList.size(), outputLine.toString());
		}
		return outputList;
	}
	public static void printHashMap(HashMap<String, Thing> map) {
		for (String key : map.keySet()) {
			Thing thing = map.get(key);
			System.out.println(
					"Text: " + thing.getTxt() + "\tLow: " + thing.getLo() + "\tHigh: " + thing.getHi() + "\tInit: " + thing.getInit());
		}
	}
}