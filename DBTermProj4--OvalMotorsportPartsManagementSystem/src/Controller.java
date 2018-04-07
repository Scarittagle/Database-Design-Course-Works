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
	////Level 2 Operation
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
					chkcatOp.browseAllInventory();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Browse specific parts
			else if(cmd.getCommand().equals("filter")) {
    			chkcatFilter();
    			//indicate level after leave
    			lv2.chkcatMenu();
			}
			//STD UI code within loop
	    	else if (cmd.getCommand().equals("")) {
	        	
    		} else {
    			System.out.println("Invalid Command, try again!");
               	}             

                System.out.print("> "); 
        
	    
		}
		
	}
	
	////Level 3 Operation
	////chkcat() - Browse Specific Parts
	public void chkcatFilter() {
		CommandParser parser = new CommandParser();
		mySQLAccess chkcatOp = new mySQLAccess();
		Info lv3 = new Info();
		
		//Show options
		lv3.chkcatFilterMenu();
		System.out.print("> "); 

		//command line
		Command cmd = null;
		while ((cmd = parser.fetchCommand()) != null) {
			//back to previous level
			if(cmd.getCommand().equals("back")) {
				break;
			}
			//browse all parts
			else if(cmd.getCommand().equals("sF")) {
	            //Take arguments
	            String[] arr = cmd.getParameters();
	            
	            //Assign arguments into values
	            int year = Integer.valueOf(arr[0]);
	            String brand = arr[1];
	            String model = arr[2];
	            String category = arr[3];
	            
	            //Push arguments into Database OP
	            try {
					chkcatOp.browseFilteredInventory(year, brand, model, category);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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