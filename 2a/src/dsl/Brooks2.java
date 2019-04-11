import java.util.HashMap;
import java.util.Scanner;

public class Brooks2 {
	
	private static boolean verbose = false; 
	
	public static void main (String[] args) {
		HashMap<String, Thing> inputMap = inputThings();
		printHashMap(inputMap);
	}
	
	public static HashMap<String, Thing> inputThings() {
		
		HashMap<String, Thing> list = new HashMap<String, Thing>();
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double np = scan.nextDouble();
		list.put("np", new Thing("np", np));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_exp = scan.nextDouble();
		list.put("exp", new Thing("exp", productivity_exp));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ep = scan.nextDouble();
		list.put("ep", new Thing("ep", ep));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double atleast = scan.nextDouble();
		list.put("atleast", new Thing("atleast", atleast));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double productivity_new = scan.nextDouble();
		list.put("productivity_new", new Thing("productivity_new", productivity_new));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double r = scan.nextDouble();
		list.put("r", new Thing("r", r));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double learning_curve = scan.nextDouble();
		list.put("learning_curve", new Thing("learning_curve", learning_curve));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double nprod = scan.nextDouble();
		list.put("nprod", new Thing("nprod", nprod));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double done_percent= scan.nextDouble();
		list.put("done_percent", new Thing("done_percent", done_percent));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double to = scan.nextDouble();
		list.put("to", new Thing("to", to));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double pomposity = scan.nextDouble();
		list.put("pomposity", new Thing("pomposity", pomposity));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double ts = scan.nextDouble();
		list.put("ts", new Thing("ts", ts));
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[,]");
		double d = scan.nextDouble();
		list.put("d", new Thing("d", d));
		scan.useDelimiter("[T|F]");
		scan.next();
		scan.useDelimiter("[,]");
		setVerbose(scan.nextBoolean());
		scan.useDelimiter("[0-9]");
		scan.next();
		scan.useDelimiter("[}]");
		double optimism = scan.nextDouble();
		list.put("optimism", new Thing("optimism", optimism));
		scan.useDelimiter("[0-9]");
		scan.nextLine();
		
		scan.close();
		return list;
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