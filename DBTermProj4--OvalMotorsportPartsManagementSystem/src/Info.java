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
	}