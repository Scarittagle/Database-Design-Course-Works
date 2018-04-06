//Controller

/////////////
//Controll Level 2 Op
/////////////

/////////////
//DB TERM PROJ
/////////////
//CREATED BY WEIWEI SU
//UNIV OF SOUTH FLORIDA @ 2018

public class Controller{
	////access category
	public void chkcat(){
		CommandParser parser = new CommandParser();
		mySQLAccess chkcatOp = new mySQLAccess();
		Info lv2 = new Info();
		
		//Show options
		lv2.chkcatMenu();
		System.out.print("> "); 
		
		//command line
		Command cmd = null;
		while ((cmd = parser.fetchCommand()) != null) {
			//back to previous level
			if(cmd.getCommand().equals("back")) {
				break;
			}
			//browse all parts
			else if(cmd.getCommand().equals("bA")) {
				try {
					chkcatOp.readAllInventory();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Browse specific parts
			else if(cmd.getCommand().equals("filter")) {
    			System.out.println("Function not implemented yet.");
			}
			//STD UI code within loop
	    	else if (cmd.getCommand().equals("")) {
	        	
    		} else {
    			System.out.println("Invalid Command, try again!");
               	}             

                System.out.print("> "); 
        
	    
		}
		
	}
}