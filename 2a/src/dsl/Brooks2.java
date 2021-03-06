import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Brooks2 {

	private static boolean verbose = false;
	private static final int DT = 1;
	private static final int TIME_MAX = 30;

	public static void main(String[] args) {
		HashMap<String, Thing> thingMap;
		try {
			thingMap = inputThings();
			
			Logger.log("Successfully loaded data from Stdin.");
			Model model = new Model(thingMap);
			Logger.log("Running model for dt, timeMax: " + DT +", " + TIME_MAX);
			ArrayList<String> outputList = model.run(DT, TIME_MAX, verbose);
			Logger.log("Model completed for dt, timeMax: " + DT + ", " + TIME_MAX);
			
			outputThings(outputList);
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
	}

	public static HashMap<String, Thing> inputThings() throws FileNotFoundException {

		HashMap<String, Thing> thingMap = new HashMap<String, Thing>();
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double pomposity = scan.nextDouble();
		thingMap.put("pomposity", new Thing("pomposity", pomposity));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double learning_curve = scan.nextDouble();
		thingMap.put("learning_curve", new Thing("learning_curve", learning_curve));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double optimism = scan.nextDouble();
		thingMap.put("optimism", new Thing("optimism", optimism));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double atleast = scan.nextDouble();
		thingMap.put("atleast", new Thing("atleast", atleast));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double done_percent = scan.nextDouble();
		thingMap.put("done_percent", new Thing("done_percent", done_percent));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_new = scan.nextDouble();
		thingMap.put("productivity_new", new Thing("productivity_new", productivity_new));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_exp = scan.nextDouble();
		thingMap.put("productivity_exp", new Thing("exp", productivity_exp));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double d = scan.nextDouble();
		thingMap.put("d", new Thing("d", d));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ep = scan.nextDouble();
		thingMap.put("ep", new Thing("ep", ep));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double nprod = scan.nextDouble();
		thingMap.put("nprod", new Thing("nprod", nprod));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double np = scan.nextDouble();
		thingMap.put("np", new Thing("np", np));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ts = scan.nextDouble();
		thingMap.put("ts", new Thing("ts", ts));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double to = scan.nextDouble();
		thingMap.put("to", new Thing("to", to));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double r = scan.nextDouble();
		thingMap.put("r", new Thing("r", r));
		scan.useDelimiter("[T|F]");
		scan.next();
		scan.useDelimiter("[}]");
		setVerbose(scan.nextBoolean());
		scan.useDelimiter("[0-9]");
		scan.nextLine();

		scan.close();
		return thingMap;
	}
	public static void outputThings(ArrayList<String> outputList) {
		for (int i = 0; i < outputList.size(); i++) {
			System.out.println(
					"?t, $atleast, >d, $done_percent, <ep, $learning_curve, <np, $nprod, $optimism, $pomposity, $productivity_exp, $productivity_new, $r, $to, $ts, ?verbose");
			System.out.println(outputList.get(i));
		}
	}
	public static void printHashMap(HashMap<String, Thing> map) {
		for (String key : map.keySet()) {
			Thing thing = map.get(key);
			System.out.println(
					"Text: " + thing.getTxt() + "\tLow: " + thing.getLo() + "\tHigh: " + thing.getHi() + "\tInit: " + thing.getInit());
		}
	}

	public static boolean getVerbose() {
		return verbose;
	}

	public static void setVerbose(boolean verbose) {
		Brooks2.verbose = verbose;
	}
}