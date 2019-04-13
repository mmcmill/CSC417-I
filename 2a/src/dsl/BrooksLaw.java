import java.util.HashMap;

public class BrooksLaw extends Model {
	HashMap<String, Thing> params;

	public BrooksLaw (HashMap<String, Thing> params) {
		super(params);
		this.params = params;
	}

	@Override
	public Things have() {
		HashMap<String, Thing> things = new HashMap<String, Thing>();
		
		Flow assimilationRate = 			new Flow("assimilationRate");
		things.put("assimilationRate", assimilationRate);
		Percent communicationOverhead = 	new Percent("communicationOverhead");
		things.put("communicationOverhead", communicationOverhead);
		Stock developedSoftware = 			new Stock(params.get("d").init, params.get("d").lo, params.get("d").hi, params.get("d").txt);
		things.put("d", developedSoftware);
		Stock experiencedPeople = 			new Stock(params.get("ep").init, params.get("ep").lo, params.get("ep").hi, params.get("ep").txt);
		things.put("ep", experiencedPeople);
		Auxiliary experiencedPeopleNeededToTrain =	new Auxiliary("experiencedPeopleNeededToTrain");
		things.put("experiencedPeopleNeededToTrain", experiencedPeopleNeededToTrain);
		Auxiliary nominalProductivity = 			new Auxiliary(params.get("nprod").init, params.get("nprod").lo, params.get("nprod").hi, params.get("nprod").txt);
		things.put("nprod", nominalProductivity);
		Stock newPersonnel = 				new Stock(params.get("np").init, params.get("np").lo, params.get("np").hi, params.get("np").txt);
		things.put("np", newPersonnel);
		Flow personnelAllocationRate = 		new Flow("personnelAllocationRate");
		things.put("personnelAllocationRate", personnelAllocationRate);
		Auxiliary plannedSoftware = 				new Auxiliary("plannedSoftware");
		things.put("plannedSoftware", plannedSoftware);
		Flow softwareDevelopmentRate = 		new Flow("softwareDevelopmentRate");
		things.put("softwareDevelopmentRate", softwareDevelopmentRate);
		Auxiliary teamSize = 						new Auxiliary(params.get("ts").init, params.get("ts").lo, params.get("ts").hi, params.get("ts").txt);
		things.put("ts", teamSize);
		Percent trainingOverhead = 			new Percent(params.get("to").init, params.get("to").lo, params.get("to").hi, params.get("to").txt);
		things.put("to", trainingOverhead);
		Stock requirements = 				new Stock(params.get("r").init, params.get("r").lo, params.get("r").hi, params.get("r").txt);
		things.put("r", requirements);
		
		// When missing, cause NPE in step()
		// Values are placeholders - are these constants?
		things.put("pomposity", new Stock("pomposity", 20));
		things.put("learning_curve", new Stock("learning_curve", 20));
		things.put("optimism", new Stock("optimism", 20));
		things.put("atleast", new Stock("learning_curve", 20));
		things.put("done_percent", new Stock("learning_curve", 20));
		
		
		return new Things(things);
	} 

	public Double commOverhead(Double x) {
		// Talk to everyone on my team
		Double myTeam = this.params.get("ts").getInit() - 1;
		// Talk to every other team
		Double others = x / (this.params.get("ts")).getInit() - 1;

		return (this.params.get("pomposity").getInit() * (Math.pow(myTeam, 2) + Math.pow(others, 2)));
	}
	
	@Override
	public void step(int dt, int t, HashMap<String, Thing> i, HashMap<String, Thing> j) {
		j.put("assimilationRate", new Flow("assimilationRate", i.get("np").getInit() / this.params.get("learning_curve").getInit()));
		j.put("plannedSoftware", new Auxiliary("plannedSoftware", this.params.get("optimism").getInit() * t));
		j.put("communicationOverhead", new Percent("communicationOverhead", commOverhead(i.get("ep").getInit() + i.get("np").getInit())));
		j.put("personnelAllocationRate", new Flow ("personnelAllocationRate", (i.get("plannedSoftware").getInit() - i.get("d").getInit() < this.params.get("atleast").getInit())  && (t < (this.params.get("done_percent").getInit() * t / 100 )) ? 6 : 0));
		// These lookups don't appear to be keys in the map
		// I'm not sure what softwareDevelopmentRate 1 and 2 are but those aren't keyed in, not sure where they're gotten from 
//		j.put("softwareDevelopmentRate", new Flow ("softwareDevelopmentRate", i.get("nprod").getInit() * (1 - i.get("communicationOverhead").getInit() / 100) * (this.params.get("sDR_param1").getInit() * i.get("np").getInit() + this.params.get("sDR_param2").getInit() * (i.get("ep").getInit() - i.get("experiencedPeopleNeededToTrain").getInit()))));
		j.put("experiencedPeopleNeededToTrain", new Auxiliary("experiencedPeopleNeededToTrain", i.get("np").getInit() * i.get("to").getInit() / 100));
		j.put("ep", new Stock("ep", j.get("ep").getInit() +  i.get("assimilationRate").getInit() * dt));
		j.put("np", new Stock("np", j.get("np").getInit() + (i.get("personnelAllocationRate").getInit() - i.get("assimilationRate").getInit() * dt)));
		j.put("d", new Stock("d", j.get("d").getInit() + i.get("softwareDevelopmentRate").getInit() * dt));
		j.put("r", new Stock("r", j.get("r").getInit() - i.get("softwareDevelopmentRate").getInit() * dt));
	}
}