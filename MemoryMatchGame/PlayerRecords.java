/*/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package MemoryMatchGame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is a sub class of HashMap and contains a String as the key and an integer as the value it is paired with or mapped to.
 * It class is used to maintain player details on a text file and maintains a public static method that will write the date, representing 
 * the date this  game is played, the username name of a player, who plays this game, and the number of points scored by the end of the game 
 * to a text file. It also maintains a method that reads the text file and adds the username of the player and the corresponding score to 
 * the playerRecords, a HashMap. 
 * 
 * @author AmritaSen 18048443
 */
public class PlayerRecords extends HashMap<String, Integer>
{
    /**
     * This method will read through the text file that stores the date a game is played, the player's name and 
     * game score.As the text is read, each line is split using white space and only the player's username and 
     * points are added to a new instance of a HashMap<String, Integer>, playerRecords.
     * This method returns the playerRecords with the added data. If  text file is empty, nothing will be
     * read.
     * 
     * @return playerRecords, a HashMap<String, Integer> 
     */
    public static HashMap<String, Integer> readRecords() 
    {
        HashMap<String, Integer> playerRecords = new HashMap(); // A new instance of HashMap.
        BufferedReader bufferedReader = null;                   // new BufferedReader created and initialised with a null value.

        try 
        {
            bufferedReader = new BufferedReader(new FileReader("playerRecords.txt")); 
            String line = "";
            
            while ((line = bufferedReader.readLine()) != null) 
            {
                String[] arr = line.split(" "); //Split a line by using white space.

                playerRecords.put(arr[1], Integer.parseInt(arr[2]));
            }
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PlayerRecords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) 
        {
            Logger.getLogger(PlayerRecords.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ArrayIndexOutOfBoundsException e)         
        { }
        finally 
        {
            if (bufferedReader != null) 
            {
                try 
                {
                    bufferedReader.close();
                } catch (IOException ex) 
                {
                    Logger.getLogger(PlayerRecords.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return playerRecords;
    }
    
    /**
     * This method takes in 2 arguments as parameters, a HashMap<String, Integer> and a Player object)
     * 2 local variables are created to store the String value of the player's username and Integer value
     * of the player's game score. This method adds the record (username and score) to HashMap taken in as a parameter
     * and writes the key word and associated value in the HashMap to the specified text file, playerRecords.txt, using 
     * a PrintWriter that iterates the HashMap.
     * 
     * @param playerRecords, a HashMap<String, Integer>
     * @param aPlayer, a Player object 
     */
    public static void writeRecords(HashMap<String, Integer> playerRecords, Player aPlayer) 
    {
        String playerName = aPlayer.getUsername();
        Integer playerScore = aPlayer.getIndividualScore().getPlayerScore();

        playerRecords.put(playerName, playerScore);//Add the record of the user to the HashMap. The old record would be overwritten if the user has played before.

        PrintWriter printWriter = null;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	Date date = new Date();

        try 
        {
            playerRecords.put(playerName, playerScore);//Add the record of the user to the HashMap. The old record would be overwritten if the user has played before.
            
            printWriter = new PrintWriter(new FileWriter("playerRecords.txt", true)); //Add text to the end of the text file.
            
            // Iterate the HashMap.
            for (Map.Entry<String, Integer> entry : playerRecords.entrySet()) 
            {   
                printWriter.println((dateFormat.format(date)) + " " + entry.getKey() + " " + entry.getValue()); // Write data to the file.
            }
            
            System.out.println("Records saved.");
                 
        } catch (FileNotFoundException ex) 
        {
            Logger.getLogger(PlayerRecords.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) 
        {
            Logger.getLogger(PlayerRecords.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            if(printWriter != null)
            {
                printWriter.close();
            }
        }
    }
} 
