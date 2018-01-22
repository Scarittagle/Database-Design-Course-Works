// Modified By WEIWEI SU
// U17420699
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class P1 {
	
	/* Define data structures for holding the data here */
	private List<coach>listofCoaches;
	private List<team>listofTeams;
	
    public P1() {
        /* initialize the data structures */
    	listofCoaches=new ArrayList<coach>();
    	listofTeams=new ArrayList<team>();
    }
    
    public void run() {
        CommandParser parser = new CommandParser();

        System.out.println("The mini-DB of NBA coaches and teams");
        System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
        System.out.println();
        System.out.print("> "); 
        
        Command cmd = null;
        while ((cmd = parser.fetchCommand()) != null) {
            //System.out.println(cmd);
            
            boolean result=false;
            
            if (cmd.getCommand().equals("help")) {
                result = doHelp();

		/* You need to implement all the following commands */
            } else if (cmd.getCommand().equals("add_coach")) {
            //Take arguments
            String[] arr = cmd.getParameters();
            //Assign arguments into values
            String ID = arr[0];
            int season = Integer.valueOf(arr[1]);
            String firstName = arr[2];
            String lastName = arr[3];
            int seasonWin = Integer.valueOf(arr[4]);
            int seasonLoss = Integer.valueOf(arr[5]);
            int playoffWin = Integer.valueOf(arr[6]);
            int playoffLoss = Integer.valueOf(arr[7]);
            String team = arr[8];
            //Assign Values into new Object
            coach newCoach = new coach(ID, season, firstName, lastName, seasonWin, seasonLoss, playoffWin, playoffLoss, team);
            //more to go...
            //Add Object into the list
            listofCoaches.add(newCoach);
            
            //System.out.println(ID);
	    } else if (cmd.getCommand().equals("add_team")) {
	    	//Take arguments
            String[] arr = cmd.getParameters();
            //Assign arguments into values
            String team = arr[0];
            String location = arr[1];
            String name = arr[2];
            String league = arr[3];
            //more to go...
            //Assign Values into new Object
            team newTeam = new team(team, location, name, league);
            //more to go...
            //Add Object into the list
            listofTeams.add(newTeam);
		} else if (cmd.getCommand().equals("print_coaches")) {
			//Advanced Steam?
			//listofCoaches.forEach((temp) -> { //Not sure Why it doesn't compile in javac
			//	System.out.println(temp);
			//});
			System.out.printf("%-10s%-7s%-10s%-10s%-5s%-5s%-5s%-5s%-10s", "ID", "Season", "FName", "LName", "SW", "SL", "PW", "PL", "Team");
			System.out.println();
			for (coach temp:listofCoaches) {
				System.out.println(temp);
			}
	   	} else if (cmd.getCommand().equals("print_teams")) {
			//Same as above
			//listofTeams.forEach((temp) -> {
			//	System.out.println(temp);
			//});
	   		System.out.printf("%-10s%-20s%-10s%-5s", "Team", "Location", "Name", "League");
	   		System.out.println();
			for (team temp:listofTeams) {
				System.out.println(temp);
			}
		} else if (cmd.getCommand().equals("coaches_by_name")) {
			//Take arguments
			String[] arr = cmd.getParameters();
			//Assign, sign removal and Search
			String name = arr[0];
			String LastName = name.replace("+", " ");
			//listofCoaches.forEach((temp) -> {
			for (coach temp:listofCoaches) {
				if(temp.getLastName().equals(LastName)) {
					System.out.println(temp);
				}
			};
		} else if (cmd.getCommand().equals("teams_by_city")) {
			//Take arguments
			String[] arr = cmd.getParameters();
			//Assign and Search
			String name = arr[0];
			//listofTeams.forEach((temp) -> {
			for (team temp:listofTeams) {
				if(temp.getlocation().equals(name)) {
					System.out.println(temp);
				}
			};
		} else if (cmd.getCommand().equals("load_coaches")) {
			//Take argument
			String filename = cmd.getParameters()[0];
			//Open file & Init Buffer
			File f = new File(filename);
			BufferedReader reader = null;
			//READ
			try {
			    reader = new BufferedReader(new FileReader(f));
			    String line = null;
			    reader.readLine();			    
			    while((line = reader.readLine()) != null) {
			    	String[] arr = line.split(",");
			    	for(int i = 0; i<arr.length;i++) {
			    		arr[i]=arr[i].trim();
			    	}
			    	listofCoaches.add(new coach(arr[0],Integer.valueOf(arr[1]),arr[3],arr[4],Integer.valueOf(arr[5]),Integer.valueOf(arr[6]),Integer.valueOf(arr[7]),Integer.valueOf(arr[8]),arr[9]));
			    }			    
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(reader != null) {
						reader.close();
					}
				} catch (IOException e) {					
				}
			}
        } else if (cmd.getCommand().equals("load_teams")) {
        	//Take argument
			String filename = cmd.getParameters()[0];
			//Open file & Init Buffer
			File f = new File(filename);
			BufferedReader reader = null;
			//READ
			try {
			    reader = new BufferedReader(new FileReader(f));
			    String line = null;
			    reader.readLine();			    
			    while((line = reader.readLine()) != null) {
			    	String[] arr = line.split(",");
			    	for(int i = 0; i<arr.length;i++) {
			    		arr[i]=arr[i].trim();
			    	}
			    	listofTeams.add(new team(arr[0],arr[1],arr[2],arr[3]));
			    }			    
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			} catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if(reader != null) {
						reader.close();
					}
				} catch (IOException e) {					
				}
			}		
		} else if (cmd.getCommand().equals("best_coach")) {
			//Take arguments
			String[] arr = cmd.getParameters();
			int year = Integer.parseInt(arr[0]);
			//Initialize Variable and calculate
			int coachIndex = 0;
			int max = 0;
			int score = 0;
			for(int i=0; i<listofCoaches.size(); i++) { //iter thru coach list to calculate net wins
				coach c = listofCoaches.get(i);
				if(c.getYear() == year) {
					score = c.getSeasonWin()-c.getSeasonLoss()+c.getPlayoffWin()-c.getPlayoffLoss();
					if(score > max) {
						coachIndex = i;
						max = score;
					}
				}							
			}
			//Print
			System.out.println(listofCoaches.get(coachIndex).getFirstName()+" "+listofCoaches.get(coachIndex).getLastName());

		} else if (cmd.getCommand().equals("search_coaches")) {
			//Take argument
			String[] arr = cmd.getParameters();
			//match query results
			for(coach c:listofCoaches){
				boolean check=true;
				for(String parameter:arr){
					 String field=parameter.split("=")[0];
					 String value=parameter.split("=")[1];
					 if(field.equals("coachid")&&!c.getID().equals(value)){
						 check=false;
					 }
					 if(field.equals("year")&&!(c.getYear()==(Integer.parseInt(value)))){
						 check=false;
					 }
					 if(field.equals("first_name")&&!c.getFirstName().equals(value)){
						 check=false;
					 }
					 if(field.equals("last_name")&&!c.getLastName().equals(value)){
						 check=false;
					 }
					 if(field.equals("season_win")&&!(c.getSeasonWin()==(Integer.parseInt(value)))){
						 check=false;
					 }
					 if(field.equals("season_loss")&&!(c.getSeasonLoss()==(Integer.parseInt(value)))){
						 check=false;
					 }
					 if(field.equals("playoff_win")&&!(c.getPlayoffWin()==(Integer.parseInt(value)))){
						 check=false;
					 }
					 if(field.equals("playoff_loss")&&!(c.getPlayoffLoss()==(Integer.parseInt(value)))){
						 check=false;
					 }
					 if(field.equals("team")&&!c.getTeam().equals(value)){
						 check=false;
					 }
				}
				if(check){
					 System.out.println(c);
				 }
			}
		} else if (cmd.getCommand().equals("exit")) {
			System.out.println("Leaving the database, goodbye!");
			break;
		} else if (cmd.getCommand().equals("")) {
		} else {
			System.out.println("Invalid Command, try again!");
           	} 
            
	    if (result) {
                // ...
            }

            System.out.print("> "); 
        }        
    }
    
    private boolean doHelp() {
        System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN "); 
	System.out.println("          EASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
        System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
        System.out.println("print_coaches - print a listing of all coaches");
        System.out.println("print_teams - print a listing of all teams");
        System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
        System.out.println("teams_by_city CITY - list the teams in the specified city");
	    System.out.println("load_coach FILENAME - bulk load of coach info from a file");
        System.out.println("load_team FILENAME - bulk load of team info from a file");
        System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
        System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
		System.out.println("exit - quit the program");        
        return true;
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        new P1().run();
    }
    
    

}
