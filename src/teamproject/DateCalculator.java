package teamproject;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
/**
 *The DateCalculator class takes date from the user and formats it.
 * it checks pattern of date and perform operations 
 * @author farahmahboob
 */
public class DateCalculator 
{
    //this method takes date from user and returns teh correct pattern and format of date
    public String TakeRegisterationDate()
    {
        String UserDate = TakeDateFromUser();
        
        System.out.println("\n\t To check if date is not a future date..");
        boolean flag = isItFutureDate(UserDate); //calls method isItFutureDate to check if date is not ahead of todays date
        
        while(flag) //Prompts user to enter a new date until correct date is entered
        {
            System.out.println("Please dont add a future date."
                    + "\nRegisteration cannot be of future date.");
            UserDate = TakeDateFromUser();
            flag = isItFutureDate(UserDate);
        }
        return UserDate;//returns a correct patterend and not future date
    }
    
    // @method isItFutureDate to check if date is not ahead of todays date
    public boolean isItFutureDate(String UserDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dateCurrent = LocalDate.now();
        System.out.println("\tToday's date (dd/mm/yyyy): " + dateCurrent.format(format));

        LocalDate date = LocalDate.parse(UserDate, format);
        boolean flag = date.isAfter(dateCurrent);//checks future date or not

        return flag;
    }//end of method isitFutureDate

    //takes date from user this method is used in TakeRegisteration date method
    public String TakeDateFromUser()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("\n\t Enter registeration date (dd/mm/yyyy): ");
        String regDate = input.nextLine();
        
        boolean FormatCheck = checkDateFormat(regDate); //checks format of the date
        
        while(!FormatCheck) //prompts user to enter a date in correct patern
        {
            System.out.println("\n WRONG FORMAT");
            System.out.print("\n Enter the correct format(dd/mm/yyyy): ");
            regDate = input.nextLine();
            FormatCheck =  checkDateFormat(regDate);
        }
        return regDate;
    }
    
    //checksDateFormat the date pattern and return boolean 
    public boolean checkDateFormat(String UserDate)
    {
    //    System.out.print("\nInside checkDateFormat method");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu"); //defines date format
        
//      String UserDate = ("09/07/2018");
        boolean flag = true;
        
            try //catches an exception is date is of worng format
            {
                LocalDate date = LocalDate.parse(UserDate, format); //parsing string date into local date format
                flag = true;
            }
            catch(Exception e)
            {
                System.out.println("Wrong format");
                flag = false;
            }
        
    //        System.out.println("Exiting checkDateFormat method. returning: " + flag);
            return flag;
    }//end of checkDateFormat
 
    //calculates the days lapsed between todays date and user entered date
    public void CalculateHowLong(String UserDate)
    {
    //    System.out.println("in CalculateHowLong method");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/uuuu"); //formats the date on a pattern 
        
        LocalDate dateCurrent = LocalDate.now(); //LocalDate class used perform comparison on dates using Java API
        System.out.println("Date today: " + dateCurrent.format(format));
   
        LocalDate date = LocalDate.parse(UserDate, format); //parse user date in to LocalDate variable
        
        long daysBetween = ChronoUnit.DAYS.between(dateCurrent, date ); //gives age of date in no of days
        long monthsBetween = ChronoUnit.MONTHS.between(date, dateCurrent);//gives age of date in no of months
        long yearsBetween = ChronoUnit.YEARS.between(date, dateCurrent);//gives age of date in no of years
        
        if(daysBetween < 0)
        {
            daysBetween *= -1;
        }
        
        System.out.print("\n\t Team created: " + daysBetween + " days ago.");

        
        if(yearsBetween > 0)//so that only output years is its more than 0
        {
            System.out.print("\n\t Team created: " + yearsBetween + " years ago.");
        }
        
        if(monthsBetween > 0)//so that only output months is its more than 0
        {
            System.out.print("\n\t Team created: " + monthsBetween + " months ago.");
        }
     }//end of method CalculateHowLong
}//end of class DateCalculator
