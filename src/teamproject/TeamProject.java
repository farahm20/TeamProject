import java.util.ArrayList;
import java.io.*; // Java API library for creating a file
import java.util.Scanner; //Java API library for the scanner class



/**
 *This program consist of class TeamProject. 
 * this class holds the main attributes of TeamObject
 * @author farahmahboob
 */
/**
* The class Team is the main class of this program. 
**/

        
public class TeamProject 
{
	// Team class fields 
        private int TeamIndex; //team index used in arraylist
        private String TeamName; // team name
        private String TeamDate; //team date
	private double TeamEntryFee; //team entry fees        
        private int TeamMembers; // number of team members in a team
        private String TeamNote; // team discription
        Scanner input = new Scanner(System.in); // Scanner class object to take data from user
            
        /**
	*Constructor
        *@param TeamIndex holds array index
	*@param TeamName holds user entered team name
        *@param TeamDate hold team date as a string
	*@param TeamNote holds user entered team discription
	*@param EntryFee hold team paid entry fee
	*@param TeamMembers holds the number of team  members
	**/

        public TeamProject(int TeamIndex, String TeamName, String TeamDate, int TeamMembers, double TeamEntryFee, String TeamNote)
        {
            this.TeamIndex = TeamIndex;
            this.TeamName = TeamName;
            this.TeamDate = TeamDate;
            this.TeamEntryFee = TeamEntryFee;
            this.TeamMembers = TeamMembers;
            this.TeamNote = TeamNote;
        }
        
        /**
         * The PrintTeamArrayList displays all the contents or TeamProject
         * @param TeamIndex the index value of Arraylist
         * @param TeamName the team name saved in TeamProject object
         * @param TeamDate string formated and saved in TeamProject object
         * @param TeamEntryFee double saved in TeamProject object
         * @param TeamMembers int saved in TeamProject object
         * @param TeamNote string saved in TeamProject object
         */
        
        public void PrintTeamArrayList()
        {
            System.out.print("Team index: " + TeamIndex);
            System.out.print("Team name: " + TeamName);
            System.out.print("Team date: " + TeamDate);
            System.out.print("Team fee:  " + TeamEntryFee);
            System.out.print("Team members:  " + TeamMembers);
            System.out.print("Team note:  " + TeamNote);
        }
        
        /**
         * sets index value
         * @param Teamindex saved as TeamIndex
         */
        public void setTeamIndex(int index)
        {
           this.TeamIndex = index;
        }
        
        /**
         * sets Name as Name
         * @param TeamName 
         */
        public void setTeamName(String Name)
        {
           this.TeamName = Name;
        }
        
        /**
         * sets date as Date
         * @param TeamDate 
         */
        public void setTeamDate(String Date)
        {
             this.TeamDate = Date;
        }
        /**
         * sets fee as teamFee
         * @param TeamFee 
         */
        public void setTeamFee(double teamFee)
        {
            this.TeamEntryFee = teamFee;
        }
        /**
         * sets teamMembers in
         * @param TeamMembers 
         */
        
        public void setTeamMembers(int teamMembers)
        {
        //    System.out.println(" inside set team number method: members number " + teamMembers );
            this.TeamMembers = teamMembers;
        }
        /**
         * sets note as 
         * @param TeamNote 
         */
        public void setTeamNote(String Note)
        {
            this.TeamNote = Note;
        }
        /**
         * the get methods
         * @returns TeamIndex 
         */
        public int getTeamIndex()
        {
            return TeamIndex;
        }
        /**
         * get method 
         * @returns TeamName
         */
    
        public String getTeamName()
        {
            return TeamName;
        }
        /**
         * get method
         * @return TeamDate
         */
        public String getTeamDate()
        {
            return TeamDate;
        }
        /**
         * getmethod returns TeamMembers
         * @return 
         */
        public int getTeamMembers()
        {
            return TeamMembers;
        }
        
        /**
         * get method
         * @return TeamFee
         */
 
        public double getTeamFee()
        {    
            return TeamEntryFee;
        }
        /**
         * get method
         * @return TeamNote
         */
        
        public String getTeamNote()
        {
            return TeamNote;
        }
        /**
         * this method PrintTeamInFiles prints all the teamObject values in a file teamRecord.txt
         * @param Team
         * @param outPutFile
         * @throws Exception 
         */
        public void PrintTeamInFile(TeamProject Team, PrintWriter outPutFile) throws Exception
        {
            Scanner fileInput = new Scanner(System.in);
            outPutFile.println( Team.getTeamIndex());
            outPutFile.println(Team.getTeamName());
            outPutFile.println(Team.getTeamDate());
            outPutFile.println(Team.getTeamMembers());
            outPutFile.println(Team.getTeamFee());
            outPutFile.println(Team.getTeamNote());
        }
        
        /**
         * The display method displays all attributes of TeamProject object
        * @param TeamIndex the index value of Arraylist
         * @param TeamName the team name saved in TeamProject object
         * @param TeamDate string formated and saved in TeamProject object
         * @param TeamEntryFee double saved in TeamProject object
         * @param TeamMembers int saved in TeamProject object
         * @param TeamNote string saved in TeamProject object
         */
        public void DisplayTeam(TeamProject Team)
        {
            System.out.print("\n Team Index: " + getTeamIndex());
            System.out.print("\n Team Name: " + getTeamName());
            System.out.print("\n Team Name: " + getTeamDate());
            System.out.print("\n Team Members: " + getTeamMembers());
            System.out.print("\n Team Fees: " + getTeamFee());
            System.out.print("\n Team Note: " + getTeamNote());
        }
        

        /**
         * The method gets a TeamProject object and then 
         * update and changes the attributes of that object 
         * @param Team 
         */
        
        public void TeamProjectUpdate(TeamProject Team)
        {
            TheTeamMenu Menu = new TheTeamMenu();
            boolean flag = true;
            int choice = 0;
       //     System.out.println("Here is the index: " + TeamIndex);
            System.out.print("\nEnter 1 to change team members: \n"
                    + "Enter 2 to change team fees: \n"
                    + "Enter 3 to change team note: \n"
                    + "Enter 4 to exit: ");
            choice = input.nextInt();
            input.nextLine();//consuming the space after integer entry

            while(flag == true)
            {
                switch(choice)
                {
                    case 1:
                        System.out.print("\n\t Enter new team members no: ");
                        System.out.print("\n\tMembers should be between 5-15: ");
                        int members = input.nextInt();
                        boolean mflag = isValidTeamMembers(members);
                
                        while(mflag == false) // checks for members validity
                        {
                            System.out.print("\tINVALID TEAM MEMBERS. Enter again: ");
                            members = input.nextInt();
                            mflag = isValidTeamMembers(members);
                        }
                        input.nextLine(); //to avoid line consumption by nextInt() method above
                        Team.setTeamMembers(members);
                          flag = false;
                        break;
                        
                    case 2:
                        System.out.print("\n\t Enter new team Fees: " +
                                         "\n\t Entry fee should be between 500 - 1000: " );
                        double newFees = input.nextDouble();
                    
                        while(newFees <= 500 || newFees >= 1000)
                        {
                            System.out.println("\t !!!! Please enter valid fee amount 500 - 1000: ");
                            newFees = input.nextDouble();
                        }

                        Team.setTeamFee(newFees);
                          flag = false;
                        break;
                        
                    case 3:
                        System.out.print("\n\t Enter new team Note: ");
                        String newNote = input.nextLine();
                        Team.setTeamNote(newNote);
                          flag = false;
                        break;
                    
                    case 4:
                        flag = false;
                        break;
                    
                    default:
                        break;
                }
            }
}//end of team object update method
        /**
         * Prompts user to enter team members and check the format 
         * @param members
         * @return boolean after checking validity of the date 
         */
    
    private static boolean isValidTeamMembers(int members)
    {
        boolean flag = false;
       
        if(members < 5 || members > 15)
        {
            System.out.println("\n\tTeam number should be between 5-15.");
            flag = false;
        }
        else
        {
            flag = true;
        }
        
        return flag;
    }//isValidTeamMembers method end

}    

