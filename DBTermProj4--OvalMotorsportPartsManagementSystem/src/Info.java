//Store system info & menu option here.
//Created by WEIWEI SU
//UNIV OF SOUTH FLORIDA @ 2018

	public class Info {
		//System information
		public void systemINFO() {
			//Version
			System.out.println("PMS V0.1 Alpha");
			System.out.println("MYSQL ver. 5.7");
			//Info board
			System.out.println("**********************************");
			System.out.println("************INFORMAION************");
			System.out.println("**********************************");
			System.out.println("* SYSTEM IS IN ALPHA TEST, NOT   *");
			System.out.println("* ALL THE FUNCTION IS IMPLEMENTED*");
			System.out.println("* DOES NOT REFLECT FINAL PRODUCT *");
			System.out.println("* PLEASE REPORT ALL BUGS TO OUR  *");
			System.out.println("* DEV TEAM, THANKS               *");
			System.out.println("**********************************");
		}
		
		//LV1
		//Main Menu 1st Level Options
		public void mainMenu() {
			System.out.println("");
			System.out.println("Please select following option to begin.");
			System.out.println("chkcat - Check the latest Inventory catalog.");
			System.out.println("order  - Check your current active Orders");
			System.out.println("cart   - Your shopping cart.");
			System.out.println("acinfo - Your account information");
			System.out.println("");
		}
		
		//Options - chkcat
		//LV2
		//Check inventory cat 2nd level Options
		public void chkcatMenu() {
			System.out.println("");
			System.out.println("Current Level - CHECK CATEGORY");
			System.out.println("OPTION:");
			System.out.println("bA     - Browse all parts.");
			System.out.println("filter - Browse specific parts");
			System.out.println("back   - Go back to previous menu.");
			System.out.println("");
		}
		
		//LV3
		//Check inventory cat 3rd level Options - Filter op
		public void chkcatFilterMenu() {
			System.out.println("");
			System.out.println("Current Level - BROWSE SPECIFIC PARTS");
			System.out.println("OPTION:");
			System.out.println("sF % % % %  - Set filter (ONLY supports 'sF year, brand, model, category' criteria");
			System.out.println("b         - Browse");
			System.out.println("back      - Go back to previous menu.");
			System.out.println("");
			System.out.println("****INSTRUCTION ON SET FILTER*****");
			System.out.println("**********************************");
			System.out.println("% means the category you put in,  ");
			System.out.println("for example search body parts for ");
			System.out.println("1986 Honda Accord, should enter   ");
			System.out.println("sF 86 Honda Accord Body         ");
			System.out.println("**********************************");
		}
		
		
		//Options - order
		//LV2
		//order 2nd level Options
		public void orderMenu() {
			System.out.println("");
			System.out.println("Current Level - ORDER");
			System.out.println("Below is your current active order.");
			System.out.println("OPTION:");
			System.out.println("back   - Go back to previous menu.");
			System.out.println("");
		}
		
	}