//Data Structure for the Coaches.
//WEIWEI SU
//U17420699

public class coach {
	//Define Object Structure
	private String ID;
    private int season;
    private String firstName;
    private String lastName;
    private int seasonWin;
    private int seasonLoss;
    private int playoffWin;
    private int playoffLoss;
    private String team;

    //Init Constructor
    public coach(String ID, int season, String firstName, String lastName, int seasonWin, int seasonLoss, int playoffWin, int playoffLoss, String team) {
    	//super(); //Call Superclass
    	this.ID = ID;
    	this.season = season;
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.seasonWin = seasonWin;
    	this.seasonLoss = seasonLoss;
    	this.playoffWin = playoffWin;
    	this.playoffLoss = playoffLoss;
    	this.team = team;
    }
	
    //getters
    public String getID() {
    	return ID;
    }
    
    public int getYear() {
    	return season;
    }    
    
    public String getFirstName() {
    	return firstName;
    }
    
    public String getLastName() {
    	return lastName;
    }
    
    public int getSeasonWin() {
    	return seasonWin;
    }
    
    public int getSeasonLoss() {
    	return seasonLoss;
    }
    
    public int getPlayoffWin() {
    	return playoffWin;
    }
    
    public int getPlayoffLoss() {
    	return playoffLoss;
    }
    
    public String getTeam() {
    	return team;
    }
    
    //In order to print out the result properly, I have to override this method to print info in format.
    @Override
	public String toString() {
		return String.format("%-10s%-7s%-10s%-10s%-5s%-5s%-5s%-5s%-10s",
				ID, season, firstName, lastName, seasonWin, seasonLoss, playoffWin, playoffLoss, team);
	}
}
