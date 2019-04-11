import java.util.HashMap;
import java.util.Scanner;

public class Brooks2 {
	
	private static boolean verbose = false; 
	
	public static void main (String[] args) {
		HashMap<String, Thing> thingMap = inputThings();
		//printHashMap(thingMap);
		BrooksLaw brooksLaw = new BrooksLaw(thingMap);
		Things things = brooksLaw.have(thingMap);
		things.printOrder();
		//printHashMap(things.things);
	}
	
	public static HashMap<String, Thing> inputThings() {
		
		HashMap<String, Thing> thingMap = new HashMap<String, Thing>();
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double np = scan.nextDouble();
		thingMap.put("np", new Thing("np", np));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_exp = scan.nextDouble();
		thingMap.put("exp", new Thing("exp", productivity_exp));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ep = scan.nextDouble();
		thingMap.put("ep", new Thing("ep", ep));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double atleast = scan.nextDouble();
		thingMap.put("atleast", new Thing("atleast", atleast));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_new = scan.nextDouble();
		thingMap.put("productivity_new", new Thing("productivity_new", productivity_new));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double r = scan.nextDouble();
		thingMap.put("r", new Thing("r", r));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double learning_curve = scan.nextDouble();
		thingMap.put("learning_curve", new Thing("learning_curve", learning_curve));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double nprod = scan.nextDouble();
		thingMap.put("nprod", new Thing("nprod", nprod));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double done_percent= scan.nextDouble();
		thingMap.put("done_percent", new Thing("done_percent", done_percent));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double to = scan.nextDouble();
		thingMap.put("to", new Thing("to", to));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double pomposity = scan.nextDouble();
		thingMap.put("pomposity", new Thing("pomposity", pomposity));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ts = scan.nextDouble();
		thingMap.put("ts", new Thing("ts", ts));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double d = scan.nextDouble();
		thingMap.put("d", new Thing("d", d));
		scan.useDelimiter("[T|F]");
		scan.next();
		scan.useDelimiter("[,]");
		setVerbose(scan.nextBoolean());
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[}]");
		double optimism = scan.nextDouble();
		thingMap.put("optimism", new Thing("optimism", optimism));
		scan.useDelimiter("[0-9]");
		scan.nextLine();
		
		scan.close();
		return thingMap;
	}
	
	public static void printHashMap(HashMap<String, Thing> map) {
		for (String key : map.keySet()) {
			Thing thing = map.get(key);
			System.out.println("Text: " + thing.txt + "\tLow: " + thing.lo + "\tHigh: " + thing.hi + "\tInit: " + thing.init);
			}
	}

	public static boolean getVerbose() {
		return verbose;
	}

	public static void setVerbose(boolean verbose) {
		Brooks2.verbose = verbose;
	}
}