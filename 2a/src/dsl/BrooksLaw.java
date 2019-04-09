public class BrooksLaw extends Model {
	HashMap<String, Double> params;

	public BrooksLaw (HashMap<String, Double> params) {
		super(params);
		this.params = params;
	}

	public Things have(Model i) {
		Flow assimilationRate = 			new Flow("assimilationRate");
		Percent communicationOverhead = 	new Percent("communicationOverhead");
		Stock developedSoftware = 			new Stock("developedSoftware", i.getParams().get("d").intValue());
		Stock experiencedPeople = 			new Stock("experiencedPeople", (i.getParams().get("ep")).intValue());
		Aux experiencedPeopleNeeded2Train =	new Aux("experiencedPeopleNeeded2Train");
		Aux nominalProductivity = 			new Aux("nominalProductivity", i.getParams().get("nprod").intValue());
		Stock newPersonnel = 				new Stock("newPersonnel", (int)(i.getParams().get("np")).intValue());
		Flow personnelAllocationRate = 		new Flow("personnelAllocationRate");
		Aux plannedSoftware = 				new Aux("plannedSoftware");
		Flow softwareDevelopmentRate = 		new Flow("softwareDevelopmentRate");
		Aux teamSize = 						new Aux("teamSize", i.getParams().get("ts").intValue());
		Percent trainingOverhead = 			new Percent("trainingOverhead", i.getParams().get("to").intValue());
		Stock requirements = 				new Stock("requirements", i.getParams().get("r").intValue());
	}

	public Double commOverhead(Double x) {
		// Not sure what 'x' is supposed to be yet

		// Talk to everyone on my team
		Double myTeam = this.params.get("ts") - 1;
		// Talk to every other team
		Double others = x / (this.params.get("ts")) - 1;

		return (this.params.get("pomposity") * (Math.pow(myTeam, 2) + Math.pow(others, 2)));
	}

	public void step(int dt, int t, HashMap<String, Double> i, HashMap<String, Double> j) {
		j.put("aR", i.get("np") / this.params.get("learning_curve"));
		j.put("ps", this.params.get("optimism") * t);
		j.put("co", commOverhead(i.get("ep") + i.get("np")));
		j.put("paR", ((i.get("ps") - i.get("d") < this.params.get("atleast"))  && (t < (int)self.params.get("done_percent") * t / 100 )) ? 6 : 0);
		j.put("sdR", i.get("nprod") * (1 - i.get("co") / 100) * (this.params.get("sDR_param1" * i.get("np") + this.params.get("sDR_param2") * (i.get("ep") - i.get("ept")))));
		j.put("ept", i.get("np") * i.get("to") / 100);
		j.put("ep", j.get("ep") + i.get("aR") * dt);
		j.put("np", j.get("np") + (i.get("paR") - i.get("aR") * dt));
		j.put("d", j.get("d") + i.get("sdR") * dt);
		j.put("r", j.get("r") - i.get("sdR") * dt);
	}
}
