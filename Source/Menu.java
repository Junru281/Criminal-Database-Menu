/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author ANDREW.ABEL, Junru
 */
public class Menu {

    // ArrayList for id
    List<Person> ids = new ArrayList<>();
    
    public void mainMenu(String fileName) {
        
        // Display a welcome message, get option and input
        
        // Do not Change
        
        System.out.println("\nCriminal Database Menu \n");
        System.out.println("Choose an option: ");
        System.out.println("1. Load Crime File");
        System.out.println("2. List all Criminals");
        System.out.println("3. Search for a Criminal");
        System.out.println("4. Generate a Wanted Poster");
        System.out.println("5. Generate Stats");
        System.out.println("6. Check Criminal for ID Fraud");
        System.out.println("7. Exit");

        // Get input from method
        int command = getIntInput();
        System.out.println("You entered " + command);
        switch (command) {
            case 1:
                System.out.println("Load Database");
                ids = loadFiles(fileName, ids);
                break;
            case 2:
                System.out.println("Listing all Criminals");
                listPeople(ids);
                break;
            case 3:
                System.out.println("Search for a Criminal with partial match");
                String input = getStringInput("Please input a partial match for the ID file");      
                searchIDs(ids, input);
                break;
            case 4:
                System.out.println("Generate a Wanted Poster");
                input = getStringInput("Please input a exact match for the ID code"); 
                
                generatePoster(ids, input);
                
                break;
            case 5:
                System.out.println("\nGenerate Stats");
                generateStats(ids);
                break;
            case 6:
                System.out.println("Check for Fraud");
                input = getStringInput("Please input a exact match for the ID code");                
                // Get a boolean of whether valid
                boolean valid = checkFraud(ids, input);
                if(valid){
                    System.out.println("Valid ID");
                } else {
                    System.out.println("Invalid ID or not found in system");
                }
                
                break;
            case 7:

                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice, please re-enter between 1 and 6");
            //  showMenu();
        }
        // Loop through menus again
        mainMenu(fileName);
    }
    
    
    private List<Person> loadFiles(String fileName, List<Person> ids) {
        // Method to handle loading of file and writing into array
        // Completed method, do not change
        
        System.out.println("Loading file " + fileName);
        ids = FileUtils.readFile(fileName);
        return ids;
    }
    
    // Generate Stats
    // This method is pre-completed
    private void generateStats(List<Person> ids) {
        // Method to generate stats
        // Completed method, do not change
        
        System.out.println("Number of criminals in System: " + statsIdNumber(ids));
        System.out.println("Number of Nationalities in System: " + statsNatNumber(ids));
        System.out.println("Average age of people: " + statsAvgAge(ids));
        System.out.println("Average reward level: " + avgReward(ids));
    }
    

    
    public static int getIntInput() {
        // Method to check for input
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        String input = kb.nextLine();

        int cmd = 6;
        try {
            cmd = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // If not a number
            System.out.println("Invalid number option chosen");
            cmd = getIntInput();
        }

        return cmd;
    }
    
    public static String getStringInput(String inputMessage) {
        // Method to receive a message to display, and get a string input
        // from keyboard
        // Completed method, do not change
        
        Scanner kb = new Scanner(System.in);
        
        System.out.println(inputMessage);
        String input = kb.nextLine();
        return input;
    }
   
    

    
    private void listPeople(List<Person> ids) {
        // Display list of Peoople using toString method
        for(int i = 0; i< ids.size(); i++)
            try{
                Person P = ids.get(i);
                System.out.println(P.toString());
            } catch (NullPointerException e){
                System.out.println("This is null!");
            }
    }

    
    private void searchIDs(List<Person> ids, String input) {
        // Method to search for people and display using toString
        // Search by full or partial match of first name, family name, nationality,
        // id or nickname, not case sensitive
        int notFound = 0;
        try {
            for (int i = 0; i < ids.size(); i++) {
                Person P = ids.get(i);
                String id = ids.get(i).getFirstName() + " " + ids.get(i).getFamilyName() + " " + ids.get(i).getIdCode() + " " + ids.get(i).getNationality();
                // deal with case sensitive
                String newid = id.toLowerCase();
                String inputlow = input.toLowerCase();
                // found out!
                int found = newid.indexOf(inputlow);
                //print when found
                if (found != -1)
                    System.out.println(P.toString());
                else 
                    notFound++; 
            }

            // if no one found then print
            if (notFound == ids.size()) {
                System.out.println("Not found !");
            }

        } catch (NullPointerException e) {
            System.out.println("This is null!");
        }
        
        
    }

    private void generatePoster(List<Person> ids, String input) {
        
        // Method will receive a String input and a list of persons.  Will
        // Look for an exact match with the id and if a match is found, will
        // Display a wanted poster
        int notFound = 0;
        for(int i = 0; i< ids.size(); i++){
            String id = ids.get(i).toString();
            int found = id.indexOf(input);
            if(found != -1){
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800,900);
                frame.setTitle("Criminal Poster");
                
                        
                ImagePanel drawing = new ImagePanel(ids.get(i),0, 0, 800, 900);
                frame.add(drawing);
                frame.setVisible(true);
            }
            else notFound++;
            
            if(notFound == ids.size())
                System.out.println("Not found !");
        }
        
       

    }
    
    private int statsIdNumber(List<Person> ids){
        // method to calculate the number of ids in the system
        int totalID = 0;
        for(int i = 0; i< ids.size(); i++){
            if(ids.get(i) != null)
            totalID ++;
        }
        return totalID;
    }
    
    private int statsNatNumber(List<Person> ids){
        // Method to return the number of nationalities present in the system
        String Nat = null;
        ArrayList<String> Nation = new ArrayList<String>();
        for(int i = 0; i< ids.size(); i++){
            Nat = ids.get(i).getNationality().trim();
            Nation.add(Nat);

            for(int j = 0; j< Nation.size()-1; j++){
                if(Nat.equals(Nation.get(j)))
                    Nation.remove(Nat);
            }
        }               
        return Nation.size();
    }
    
    private double statsAvgAge(List<Person> criminals){
        // Calculate age of every person in the system and return as double
        double avg = 0;
        double totalage = 0;
        for(int i = 0; i< criminals.size(); i++)
            totalage = totalage + criminals.get(i).getAgeinYears();
        
        avg = totalage / statsIdNumber(criminals);
        return avg;
    }
    
    private double avgReward(List<Person> ids){
        // Method to calculate average reward amount of all people in system
        double avg = 0;
        double totalReward = 0;
        for(int i = 0; i< ids.size(); i++)
            totalReward = totalReward + ids.get(i).getReward();
        
        avg = totalReward / statsIdNumber(ids);
        return avg;
    }
    
    private boolean checkFraud(List<Person> ids, String input){
        // Method to check if an ID is valid or invalid.  Receives an ID string 
        // and a list of people.  Valid ID if:
        
        // when the user inputs an ID code, a matching ID is found in the system
        // The ID code is 8 characters in length
        // The code begins with an “A”, “B”, or “C” (case sensitive)
        // case sensitive!!!!!!
        // The third character matches the last number of their year of birth
        // The final 2 characters are a checksum, and should add up to 7
        // check length
        if(input.length() != 8){
            System.out.println("Not satisfy requirement 1");
            return false;
        }
        // check begining
        char firstLetter = input.charAt(0);
        //Character.toUpperCase(firstLetter);
        
        if((firstLetter < 65)||(firstLetter > 67)){
            System.out.println("Not satisfy requirement 2");
            return false;
        }
        //check the third character
        int unthird = 0;
        for(int i = 0; i<ids.size(); i++){
            String third = String.valueOf(ids.get(i).getDob().getYear());
            if(!input.substring(2, 3).equals(third.substring(3)))
                unthird++;
            
            if(unthird == ids.size()){    
                System.out.println("Not satisfy requirement 3");
                return false;}
        }
        
        try{
        // check the final 2 characters
        int lastsec = Integer.parseInt(input.substring(6,7));
        int last = Integer.parseInt(input.substring(7));

        if(last + lastsec != 7){
            System.out.println("Not satisfy requirement 4");
            return false;}
        }
        catch(NumberFormatException err){
           return false;   
        }
        return true;
    }

}

