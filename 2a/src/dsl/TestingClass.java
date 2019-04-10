import java.util.ArrayList;
import java.util.Scanner;

public class TestingClass {
	
	public static void main (String[] args) {
		ArrayList<Object> list = inputThings();
		printThings(list);
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
	
	public static void printThings(ArrayList<Object> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
}