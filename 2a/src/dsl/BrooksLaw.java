import java.util.ArrayList;
import java.util.HashMap;

public class BrooksLaw extends Model {
	HashMap<String, Thing> params;

	public BrooksLaw (HashMap<String, Thing> params) {
		super(params);
		this.params = params;
	}

	public Things have(HashMap<String, Thing> i) {
		HashMap<String, Thing> things = new HashMap<String, Thing>();
		
		Flow assimilationRate = 			new Flow("assimilationRate");
		things.put("assimilationRate", assimilationRate);
		Percent communicationOverhead = 	new Percent("communicationOverhead");
		things.put("communicationOverhead", communicationOverhead);
		Stock developedSoftware = 			new Stock(i.get("d").init, i.get("d").lo, i.get("d").hi, i.get("d").txt);
		things.put("d", developedSoftware);
		Stock experiencedPeople = 			new Stock(i.get("ep").init, i.get("ep").lo, i.get("ep").hi, i.get("ep").txt);
		things.put("ep", experiencedPeople);
		Aux experiencedPeopleNeededToTrain =	new Aux("experiencedPeopleNeededToTrain");
		things.put("experiencedPeopleNeededToTrain", experiencedPeopleNeededToTrain);
		Aux nominalProductivity = 			new Aux(i.get("nprod").init, i.get("nprod").lo, i.get("nprod").hi, i.get("nprod").txt);
		things.put("nprod", nominalProductivity);
		Stock newPersonnel = 				new Stock(i.get("np").init, i.get("np").lo, i.get("np").hi, i.get("np").txt);
		things.put("np", newPersonnel);
		Flow personnelAllocationRate = 		new Flow("personnelAllocationRate");
		things.put("cpersonnelAllocationRate", personnelAllocationRate);
		Aux plannedSoftware = 				new Aux("plannedSoftware");
		things.put("plannedSoftware", plannedSoftware);
		Flow softwareDevelopmentRate = 		new Flow("softwareDevelopmentRate");
		things.put("softwareDevelopmentRate", softwareDevelopmentRate);
		Aux teamSize = 						new Aux(i.get("ts").init, i.get("ts").lo, i.get("ts").hi, i.get("ts").txt);
		things.put("ts", teamSize);
		Percent trainingOverhead = 			new Percent(i.get("to").init, i.get("to").lo, i.get("to").hi, i.get("to").txt);
		things.put("to", trainingOverhead);
		Stock requirements = 				new Stock(i.get("r").init, i.get("r").lo, i.get("r").hi, i.get("r").txt);
		things.put("r", requirements);
		
		return new Things(things);
	}

	//look into commOverhead
	//co #1
	public Double commOverhead(Double x) {
		// Not sure what 'x' is supposed to be yet

		// Talk to everyone on my team
		Double myTeam = this.params.get("ts") - 1;
		// Talk to every other team
		Double others = x / (this.params.get("ts")) - 1;

		return (this.params.get("pomposity") * (Math.pow(myTeam, 2) + Math.pow(others, 2)));
	}

	//change names in step
	//take doubles out of thing instead of having the hashmap string-thing which was
	//the case when this was implemented
	public void step(int dt, int t, HashMap<String, Thing> i, HashMap<String, Thing> j) {
		j.put("aR", i.get("np") / this.params.get("learning_curve"));
		j.put("ps", this.params.get("optimism") * t);
		j.put("co", commOverhead(i.get("ep") + i.get("np")));
		j.put("paR", ((i.get("ps") - i.get("d") < this.params.get("atleast"))  && (t < this.params.get("done_percent") * t / 100 )) ? 6 : 0);
		j.put("sdR", i.get("nprod") * (1 - i.get("co") / 100) * (this.params.get("sDR_param1" * i.get("np") + this.params.get("sDR_param2") * (i.get("ep") - i.get("ept")))));
		j.put("ept", i.get("np") * i.get("to") / 100);
		j.put("ep", j.get("ep") + i.get("aR") * dt);
		j.put("np", j.get("np") + (i.get("paR") - i.get("aR") * dt));
		j.put("d", j.get("d") + i.get("sdR") * dt);
		j.put("r", j.get("r") - i.get("sdR") * dt);
	}
	
	
	public static void main( String[] args) {
		ArrayList<Object> inputList = inputThings();
		HashMap<String, Thing> initMap = createInitHashMap(inputList);
	}
}
