import java.util.HashMap;

public class BrooksLaw extends Model {
	HashMap<String, Thing> params;

	public BrooksLaw (HashMap<String, Thing> params) {
		super(params);
		this.params = params;
	}

	@Override
	public Things have() {	
		Flow assimilationRate = 			new Flow("assimilationRate");
		params.put("assimilationRate", assimilationRate);
		Percent communicationOverhead = 	new Percent("communicationOverhead");
		params.put("communicationOverhead", communicationOverhead);
		Stock developedSoftware = 			new Stock(0, params.get("d").lo, params.get("d").hi, params.get("d").txt);
		params.put("d", developedSoftware);
		Stock experiencedPeople = 			new Stock(30, params.get("ep").lo, params.get("ep").hi, params.get("ep").txt);
		params.put("ep", experiencedPeople);
		Auxiliary experiencedPeopleNeededToTrain =	new Auxiliary("experiencedPeopleNeededToTrain");
		params.put("experiencedPeopleNeededToTrain", experiencedPeopleNeededToTrain);
		Auxiliary nominalProductivity = 	new Auxiliary(0.1, params.get("nprod").lo, params.get("nprod").hi, params.get("nprod").txt);
		params.put("nprod", nominalProductivity);
		Stock newPersonnel = 				new Stock(0, params.get("np").lo, params.get("np").hi, params.get("np").txt);
		params.put("np", newPersonnel);
		Flow personnelAllocationRate = 		new Flow("personnelAllocationRate");
		params.put("personnelAllocationRate", personnelAllocationRate);
		Auxiliary plannedSoftware = 		new Auxiliary("plannedSoftware");
		params.put("plannedSoftware", plannedSoftware);
		Flow softwareDevelopmentRate = 		new Flow("softwareDevelopmentRate");
		params.put("softwareDevelopmentRate", softwareDevelopmentRate);
		Auxiliary teamSize = 				new Auxiliary(5, params.get("ts").lo, params.get("ts").hi, params.get("ts").txt);
		params.put("ts", teamSize);
		Percent trainingOverhead = 			new Percent(25, params.get("to").lo, params.get("to").hi, params.get("to").txt);
		params.put("to", trainingOverhead);
		Stock requirements = 				new Stock(500, params.get("r").lo, params.get("r").hi, params.get("r").txt);
		params.put("r", requirements);

		return new Things (params);
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
		j.put("softwareDevelopmentRate", new Flow ("softwareDevelopmentRate", i.get("nprod").getInit() * (1 - i.get("communicationOverhead").getInit() / 100) * (0.74 * i.get("np").getInit() + 1.28 * (i.get("ep").getInit() - i.get("experiencedPeopleNeededToTrain").getInit()))));
		j.put("experiencedPeopleNeededToTrain", new Auxiliary("experiencedPeopleNeededToTrain", i.get("np").getInit() * i.get("to").getInit() / 100));
		j.put("ep", new Stock("ep", j.get("ep").getInit() +  i.get("assimilationRate").getInit() * dt));
		j.put("np", new Stock("np", j.get("np").getInit() + (i.get("personnelAllocationRate").getInit() - i.get("assimilationRate").getInit() * dt)));
		j.put("d", new Stock("d", j.get("d").getInit() + i.get("softwareDevelopmentRate").getInit() * dt));
		j.put("r", new Stock("r", j.get("r").getInit() - i.get("softwareDevelopmentRate").getInit() * dt));
	}
}