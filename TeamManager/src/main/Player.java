package main;

@SuppressWarnings("serial")
public class Player extends FileHandler{
	private String name;
	private String position;
	private int goals;
	private int assists;

	
	Player(String name, String position){
		this.name = name;
		this.position = position;
	}
	
	Player(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGoals() throws Exception {
		for (Goal g : getGoalArray()) {
			if(g.getPlayer().equals(name)) {
				goals++;
			}
		}
		return goals;
	}


	public int getAssists() throws Exception {
		for (Goal g : getGoalArray()) {
			if(g.getAssist().equals(name)) {
				assists++;
			}
		}
		return assists;
	}


	
	public int exportGoals() {
		return goals;
	}
	
	public int exportAssists() {
		return assists;
	}

	public int getPoints() throws Exception {
		return getGoals() + getAssists();
	}
	
	
	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


}

