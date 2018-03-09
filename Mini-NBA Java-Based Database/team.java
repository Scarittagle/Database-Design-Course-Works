//Data Structure for the Teams.
//WEIWEI SU
//U17420699

public class team {
	//Define Object Structure
	private String teamName;
	private String location;
	private String name;
	private String league;
	
	//Constructor
	public team(String teamName, String location, String name, String league) {
		this.teamName = teamName;
		this.location = location;
		this.name = name;
		this.league = league;
	}
	
	
	//getters
	public String getTeamName() {
		return teamName;
	}
	
	public String getlocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLeague() {
		return league;
	}
	
	//In order to print out the result properly, I have to override this method to print info in format.
	@Override
	public String toString() {
		return String.format("%-10s%-20s%-10s%-5s",
				teamName, location, name, league);
	}

}
