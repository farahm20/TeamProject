import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import teamproject.DateCalculator;


/**
 *The main class consists of the MainMenu. Here user will enter the data and 
 * data will be saved in TeamProject object. and stored in an ArrayList
 * @author farahmahboob
 */
public class TheTeamMenu
{
        //Object of Scanner class
       
     public static void main(String [] args) throws Exception
    {
        PrintWriter outPutFile = new PrintWriter("TeamRecord.txt"); //File to write all data when progrom finishes
        Scanner input = new Scanner(System.in);//Object of Scanner class
        ArrayList <TeamProject> Teams = new ArrayList <TeamProject>(); //NameArray is an Array Object of TeamArrayList class
        DateCalculator TakeDate = new DateCalculator();
        
        
        int menuChoice; //for checking menuchoices in if loop
        
        String name; //for taking team name 
        int members; // for taking team members
        double fees; // taking team fees
        String note; //taking team note
        boolean flag; //to use in chicking team name validity
        String answer; // stores answer whether team will pay or not
       
        int indexforTeam = 0; //shows team index in arraylist
        String formattedDate = ""; // for storing correctly formatted date
        boolean readFromFile = false;
        int tempArraySize = 500;//to define size of the temp array. 
        do
        {
             
                          //Array with this size will be used in MenuChoice 1 and 2
            menuChoice = MainMenu();//method calls the menu 
//*********MENU CHOICE 1 *******************************        
            if(menuChoice == 1)
            {
                System.out.println("\n\t\t Reading data from an already stored file.");
                
                String [] dataFromFile = new String [tempArraySize]; //This is a temp array with a set size. 
                                                           //array is used to store each line data from the file.
                                                           //the data is later parsed into teamProject class attributes
                Scanner inputFromFile; // object of class Scanner 
                inputFromFile = new Scanner(new File("OldTeamRecord.txt")); //initializing 
                int index = 0; //index used for array
                
                 while(inputFromFile.hasNext()) //reads file till ends
                    {
                        String data = inputFromFile.nextLine(); //reads next line
                        dataFromFile[index] = data; //each line saved in array 
                        index ++; //next index in array
                        tempArraySize = index; //increasing size so that array is read till 
                                        //there is data in the array and null values
                                        //are not read. because array size set to 100 
                    }
                //TeamObject temporary fields to send to TeamObject constructor
                 int teamIndex = 0;
                 String teamName;
                 String teamDate;
                 int teamMembers;
                 double teamFees;
                 String teamNote;

                 String temp;
                 int tempSize = 0;
                 int i = 0;
                 while( i< tempArraySize) //while loop taking data from array and formating into TeamObject attribute's data type
                 {
                     System.out.println( "In array: " + dataFromFile[i]);
                     temp = dataFromFile[i];
                     teamIndex = Integer.parseInt(temp);
                     System.out.println(" " + teamIndex);
                     i++;

                     temp = dataFromFile[i];
                     teamName = temp;
                     System.out.println(" " + teamName);
                     i++;

                     temp = dataFromFile[i];
                     teamDate = temp;
                     System.out.println(" " + teamDate);
                     i++;

                     temp = dataFromFile[i];
                     teamMembers = Integer.parseInt(temp);
                     System.out.println(" " + teamMembers);
                     i++;

                     temp = dataFromFile[i];
                     teamFees = Double.parseDouble(temp);
                     System.out.println(" " + teamFees);
                     i++;

                     temp = dataFromFile[i];
                     teamNote = temp;
                     System.out.println(" " + teamNote);
                     i++;        
                     
                     //adding data in TeamProject object
                     Teams.add(new TeamProject(teamIndex, teamName, teamDate, teamMembers, teamFees, teamNote));
                     readFromFile = true;
                      
                       
                 }
                 tempSize = teamIndex + 1;
                 //when user wants to add more teams the arraylist starts from 0 when Menu 2 is prompted
                    if(readFromFile) //indexforTeam = tempSize --- so that ArrayList continues fromthe next index rather than starting again fromzero
                    {
                      indexforTeam = tempSize;
                      System.out.println("" + tempSize);
                    }
            }
     
//*********MENU CHOICE 2 *******************************        
            if(menuChoice == 2)
            {
                System.out.println("\n\t\t ADD A TEAM");
                System.out.print("\n\tIndex is: " + indexforTeam);
               
                System.out.print("\n\tEnter Team Name: ");
                String name1 = input.nextLine();
                name = name1.toLowerCase();
                flag = isValidTeamName(name);
       
                while(flag == false) // checks for name validity
                {
                    System.out.print("\tINVALID TEAM NAME. Enter again: ");
                    name1 = input.nextLine();
                    name = name1.toLowerCase();
                    flag = isValidTeamName(name);
                }//end of while
                
             //Adding team date     
                formattedDate = TakeDate.TakeRegisterationDate();
                System.out.println("Date: " + formattedDate);
                
                
        
        //Adding team members       
                boolean mflag;
                System.out.print("\tEnter members for team - " + name + " : ");
                System.out.print("\n\tMembers should be between 5-15: ");
                members = input.nextInt();
                mflag = isValidTeamMembers(members);
                
                while(mflag == false) // checks for members validity
                {
                    System.out.print("\tINVALID TEAM MEMBERS. Enter again: ");
                    members = input.nextInt();
                    mflag = isValidTeamMembers(members);
                }
                input.nextLine(); //to avoid line consumption by nextInt() method above

        //Adding team fee        
                System.out.print("\tDoes team " + name + " want to pay entry fees (y/n): " );
                answer = input.nextLine();
                boolean entryFeecheck = willTeamPayFee(answer);
                if(entryFeecheck == true)
                {
                    System.out.print("\tEnter fee for team - " + name +
                                      "\n\tEntry fee should be between 500 - 1000: " );
                    fees = input.nextDouble();

                    while(fees <= 500 || fees >= 1000)
                    {
                        System.out.println("\t !!!! Please enter valid fee amount 500 - 1000: ");
                        fees = input.nextDouble();
                    }
                }//end of if
                else
                {
                    fees = 0.0;
                }//end of else
                

        //Adding team note        
                input.nextLine(); //to avoid line consumption by nextInt() method above
                System.out.print("\tEnter note for team - " + name + " : ");
                note = input.nextLine();
                   
                Teams.add(new TeamProject(indexforTeam, name, formattedDate, members,fees,note));
                indexforTeam ++;
//Writing in file
                outPutFile.println(indexforTeam);
                outPutFile.println(name);
                outPutFile.println(formattedDate);
                outPutFile.println(members);
                outPutFile.println(fees);
                outPutFile.println(note);
                
        //      System.out.println("The index at end of if-loop" + indexforTeam);
            }// end of if loop for Menuchoice 1


//*********MENU CHOICE 3 *******************************        
            
            if(menuChoice == 3)
            {
                System.out.println("\n\n\tUPDATE A TEAM");
                
                System.out.print("\n\t The teams are: "  +" \n"); 
                //loop displays only name and index team for user's ease
                for(TeamProject Userteam : Teams)
                {
                    System.out.print("\n\tTeam Name:" + Userteam.getTeamName() + " at Index: " + Userteam.getTeamIndex());
                }//
 
                System.out.print("\n\tEnter the team name to update: ");
                String nameU = input.nextLine(); //taking the team index
                String nameToUpdate = nameU.toLowerCase();
                flag = isValidTeamName(nameToUpdate);
        //Adding team name     
                while(flag == false) // checks for name validity
                {
                    System.out.print("\tINVALID TEAM NAME. Enter again: ");
                    nameU = input.nextLine(); //taking the team index
                    nameToUpdate = nameU.toLowerCase();
                    flag = isValidTeamName(nameToUpdate);
                }//end of while   
                //the for loop finds the index entered by user and calls the update team method for the matched index
                for(TeamProject Userteam : Teams)
                {
                    if(nameToUpdate.equals(Userteam.getTeamName())) //comparing usergiven index with team index
                    {
                        Userteam.TeamProjectUpdate(Userteam);//calling update team method
                    }
                    else
                    {
                        System.out.print("\n\t\tName not found");
                    }
                }
                
                System.out.print("\n\t Displaying Teams after updating"  +" \n");
                //for loop for displaying teams
                for(TeamProject Userteam : Teams)
                {
                    Userteam.DisplayTeam(Userteam); //display team method
                    System.out.println();
                }//
            }//end of if loop for menuChoice 2

//*********MENU CHOICE 4 *******************************        
            
            if(menuChoice == 4)
            {
                System.out.println("\n\t\tREMOVE A TEAM");
             
                System.out.print("\n\t The teams are: "  +" \n"); 
                //loop displays only name and index team for user's ease
                for(TeamProject Userteam : Teams)
                {
                    System.out.print("\n\tTeam Names: " + Userteam.getTeamName() + " at Index: " + Userteam.getTeamIndex());
                }//
                
                System.out.print("\n\tEnter the team index to remove: "); //takes index to comapre
                int indexToremove = input.nextInt();//storing index in indexto remove
                //forloop to compare index
                for(int index = 0; index < Teams.size(); index++)
                {
                    if(indexToremove == index) //comparig index
                    {    
                         System.out.print("\t\t ..... Removing " + index);
                         Teams.remove(index);
    //                     Teams.add(new TeamProject(index, "",0,0.0,""));//adds null value to the arraylist.
                                                                        //so that the index can be updated later
                     }//end of if statemnet that compared index to remove
                }//end of for loop that removes team
               
                System.out.print("\n\t Displaying Teams after removing \n");
                //for loop calling display method
                for(TeamProject Userteam : Teams)
                {
                    Userteam.DisplayTeam(Userteam);
                    System.out.println();
                }//end of for loop for displaying
                
            }//end of if loop for menuChoice 3

//*********MENU CHOICE 5 *******************************        
                        
            if(menuChoice == 5)
            {
                System.out.println("\n\t\tDISPLAYING ALL TEAMS");
                
                for(TeamProject Userteam : Teams)
                {
                    Userteam.DisplayTeam(Userteam);
                    Userteam.PrintTeamInFile(Userteam, outPutFile); //prints all arraylist in a text file teamRecord.txt
                    System.out.println();
                }//end of display for loop
            }// end of if loop for menuChoice 4
            
//*********MENU CHOICE 6 *******************************        
                        
            if(menuChoice == 6)
            {
                System.out.println("\n\t\tDISPLAYING SUM & AVERAGE TEAM FEES");
                double SumOfFees = 0.0;
                int index = 1;
                double teamFees; 
                for(TeamProject Userteam : Teams)
                {
                    teamFees = Userteam.getTeamFee();
                    SumOfFees += teamFees;
                    index ++;
               //     System.out.println();
                }//end of display for loop
                double AverageOfFees = 0.0;
                AverageOfFees = SumOfFees/index;
                System.out.print("\n\t\t Total number of teams: "+ index);
                System.out.print("\n\t\t Sum of Fees: " + SumOfFees);
                System.out.printf("\n\t\t Average of Fees: %.2f", AverageOfFees);
               
            }// end of if loop for menuChoice 4
            
//*********MENU CHOICE 7 *******************************        
            
            if(menuChoice ==7)
            {
                System.out.println("\n\t\tDISPLAYING PERCENTAGE OF TEAM WITH PAID FEES");
                double checkteam;
                int NumofTeamsPaidfee = 0;
                int index = 0;
                boolean check;
                for(TeamProject UserTeam: Teams)
                {
                    index ++;
                    checkteam = UserTeam.getTeamFee();
                    
                    if(checkteam > 0)
                    {
                        NumofTeamsPaidfee ++ ;
                    }
                }
                
                System.out.print("\n\t\t Number of teams that paid fee. " + NumofTeamsPaidfee);
                System.out.print("\n\t\t Total number of teams. " + index);
                float percentage = (float) (NumofTeamsPaidfee *100) / index;
                System.out.printf("\n\t\t Percentage of teams that paid fee %.0f" , percentage);
                System.out.print("%");
            }
            

//*********MENU CHOICE 8 ********************************        
            if(menuChoice == 8)
            {
                System.out.println("\n\t\tCALCULATING TEAM AGE");
                
                String TeamDate;
                int dindex = 0;
                
                for(TeamProject Userteam : Teams)
                {
                    TeamDate = Userteam.getTeamDate();
                    System.out.print("\n\n\t\t Team age for team " + dindex + " ");
                    TakeDate.CalculateHowLong(TeamDate);
                    dindex++;
                }//end of display for loop
            }
            
        }while(menuChoice != 9);//end of while loop for MainMenu Choice
    
        outPutFile.close(); //closing file
    }

    private static int MainMenu()
        {
            Scanner input = new Scanner(System.in);
            int UserMainMenuAns;
            System.out.println("\n\n");
            System.out.println("********** THE SPORTS EVENT TEAM MENU **********");
            System.out.println("\nPlease enter a valid number between 1-9 for the following menu.");
            System.out.println("\n To read/work with data from existing file    1: ");
            System.out.println(  " To add a new Team press                      2: ");
            System.out.println(  " To update a Team press                       3: ");
            System.out.println(  " To remove a Team press                       4: "); 
            System.out.println(  " To display Team  press                       5: ");
            System.out.println(  " To get sum & average of all Team fees press  6: ");
            System.out.println(  " To get Percentage of Teams that paid press   7: ");
            System.out.println(  " To get teams age in days                     8: ");
            System.out.print(    " To quit the menu press                       9: ");


        UserMainMenuAns = input.nextInt();
        return UserMainMenuAns;       
    }
//printing is file 
       
    
    
private static int UpdateMenu()
{
    Scanner input = new Scanner(System.in);
    int UserUpdateMenuAns;

    System.out.println("********** THE UPDATE MENU **********");
    System.out.println("\nPlease enter a valid number between 1-5 for the following menu.");
    System.out.println("\n To add a new Team press      1: ");
    System.out.println(  " To update Team member press  2: ");
    System.out.println(  " To update Team fee press     3: "); 
    System.out.println(  " To update Team note press    4: ");
    System.out.println(  " To update Team date          5: ");
    System.out.print(    " To quit the menu press       6: ");

    UserUpdateMenuAns = input.nextInt();
    return UserUpdateMenuAns;       
}

//************************** BOOLEAN CHECK METHOD FOR TEAM FEE *******************************        
  

    private static boolean willTeamPayFee(String answer)
    {
        boolean returnFlag;
        if(answer.charAt(0)== 'y' || answer.charAt(0)== 'Y')
        {
            returnFlag = true;
        }
        else
        {
            returnFlag = false;
        }
        return returnFlag;
    }//end of willTeamPayfee method
    
//************************** ISVALIDTEAMMEMBERS METHOD TEAM NAME VALIDITY *******************************        
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
    
    
//************************** ISVALIDTEAMNAME METHOD TEAM NAME VALIDITY *******************************        
    private static boolean isValidTeamName(String Name)
    {
        boolean flag = false;
        int stringLength = Name.length();

        if(Name.length() == 6)
        {
          //  System.out.println("\n\nThe name length: " + stringLength);

            flag = true;
            for(int index = 0; index < stringLength; index++)
            {
                if(Character.isLetter(Name.charAt(index)) || (!Character.isDigit(Name.charAt(index))))
                {
                    flag = true;
                //    System.out.println("Charaters are: " + Name.charAt(index));
                }
                else 
                {
                 //   System.out.println("These are not characters");
                    flag = false;
                }   
            }
        }  
        else
        {
            System.out.println("Please enter a valid name.\nPress option 1 again. \n");
        }

        return flag;
    }//isValidTeamName method end
}//TheMainMenu class end
  