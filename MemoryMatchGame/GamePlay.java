/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the GamePlay object, the game will be played in.  It implements a single main method with core playable functionality
 * code to create TileBoard and GamePlay objects objects and invoke a play() method.
 * @author AmritaSen 18048443
 */
public class GamePlay 
{
    private boolean gameInProgress;                     // A private variable to store a boolean value determining if a game is in progress. 
    private TileBoard currentBoard;                     // A private instance of the TileBoard object.  
    private Player player;                              // A private instance of the Player object.
    private ArrayList<Integer> userMatchedTileOptions;  // A private ArrayList to store Integer objects representing face down values already matched by the user.
    private PlayerRecords records;                      // A private instance of the PlayerRecords object.
    
    /**
     * This is a class constructor that takes a TileBoard object and initialises the class variables.
     * Objects this.currentBoard, this.player and this.records are initialised with new instances of the object.
     * @param currentBoard, a TileBoard object. 
     */
    public GamePlay(TileBoard currentBoard) 
    {
	this.currentBoard = currentBoard;	
        this.player = new Player();
        this.gameInProgress = true;
        this.userMatchedTileOptions = new ArrayList<>();
        this.records = new PlayerRecords();
    }
    
    Scanner keyboard = new Scanner(System.in); // new scanner object created to handle user input.
    
    /**
     * This method is called by the main method of this class. When called, it displays the introduction text, 
     * main menu options and prompts and processes the user input. If the player chooses to play the game, 
     * the while loop will be executed until the player matches all tiles on the tile board or chooses to quit 
     * the game. Once the game has ended, the player's username and the score will be printed to a text file.
     * The text file will also store the the game has been played on against the player's details.
     * 
     */
    private void play()   
    {   
        displayIntroText();
        System.out.println();
        displayMainMenuCommands();
        System.out.println();         
        userCommandPrompt();
        processMainMenuUserInput();

        while(gameInProgress)
        {   
            promptAndProcessUserTileOptionsToMatch();
        } 
        
        PlayerRecords.writeRecords(records, this.player);  // Write player's username and score to the text file.
                
        keyboard.close();
    }
    
    //--------------------------------------------------------------------------------------
    /**
     * This method is called by the play method of this class. When called, it prints text to the console
     * introducing the game.
     * 
     */
    private void displayIntroText()
    {
        System.out.println("Welcome to Memory Match! This intellectual, yet fun, game is intended to train");
        System.out.println("concentration, patience, and short-term memory as you challenge yourself to match");
        System.out.println("tiles on a displayed tile board, using commands from the options provided.");
        System.out.println();
        System.out.println("If you would like to proceed, and already know how the game works, enter option");
        System.out.println("1 to begin playing. For game instructions, enter option 2. If you wish to come");
        System.out.println("back later to play, enter option 3.");
    }
    
    /**
     * This private method prints the command options available to a player before the player has started playing.
     * These options are displayed with the introduction text and called if the player provides an invalid input.
     * This method is invoked by play method, after the introduction text is displayed, and by the 
     * promptAndProcessMainMenuUserInput method.
     * 
     */
    private void displayMainMenuCommands()
    {
        System.out.println("1 = Play | 2 = Help | 3 = Quit");
    }
    
    /**
     * This private method prompts the user to input a command from the options displayed to the console. This 
     * method is called by the 3 methods that process user input.
     * 
     */
    private void userCommandPrompt()
    {
        System.out.println("What would you like to do? ");
        System.out.print("?> ");
    }
    
    /**
     * This private method is called by the play method and if the user provides an invalid input. When called,
     * the method uses a switch case statement to validate and process input the player provides based on the 
     * main menu command options displayed by the displayMainMenuCommands method. 
     */
    private void processMainMenuUserInput()
    {
        //try catch block to deal with exceptions.
        try
        {
            String option = keyboard.next();
            Integer numOption = Integer.parseInt(option);
            System.out.println();

            switch(numOption)
            {
                case 1:
                    beginPlay();
                    break;
                case 2:
                    displayGameInstructions();
                    displayHelpMenuCommands(); 
                    System.out.println();
                    userCommandPrompt();
                    processHelpMainMenuUserInput();
                    break;
                case 3:
                    quit();
                    break;
                default:
                    System.out.println("Your command is invalid! Please choose one from the given options.");
                    displayMainMenuCommands();
                    System.out.println();
                    processMainMenuUserInput();
                    break;
            }
        } catch(NumberFormatException e)
        {
            System.out.println();
            System.out.println("Your command is invalid! Please choose one from the given options.");
            displayMainMenuCommands();
            System.out.println();
            processMainMenuUserInput();
        }
    }
    
    /**
     * This private method prints the command options available to a player once the player has started playing.
     * These options are displayed during play until the game ends. This method is invoked by processInPlayMenuCommands 
     * method and the matchUserTileOptions method.     * 
     */
    private void displayInPlayMenuCommands()
    {
        System.out.println("1 = Continue | 2 = Change my tile board | 3 = Help | 4 = Quit");
    }
    
    /**
     * This private method is invoked by the matchUserTileOptions method and when the player provides an invalid input. When called,
     * the method uses a switch case statement to validate and process input the player provides based on the menu command options
     * displayed by the displayInPlayMenuCommands method if the player chooses to go ahead and play the game. This method takes a 
     * TileBoard object as a parameter.
     * 
     * @param aTileBoard, a TileBoard object. 
     */
    private void processInPlayMenuCommands(TileBoard aTileBoard)
    {        
        //try catch block to deal with exceptions.
        try
        {
            String option = keyboard.next();
            Integer numOption = Integer.parseInt(option);
            System.out.println();

            switch(numOption)
            {
                case 1:
                    System.out.println("Your tile board:");
                    this.currentBoard.showOnlyMatches();
                    System.out.println();
                    break;
                case 2:
                    System.out.println("You have chosen to change your tile board. Your current score will be reset to 0.");
                    this.player.getIndividualScore().setNumOfMatches(0);
                    this.player.getIndividualScore().setNumOfMisses(0);
                    this.player.getIndividualScore().setPlayerScore(0);  
                    
                    if(!this.userMatchedTileOptions.isEmpty())
                    {
                       this.userMatchedTileOptions.clear();
                    }

                    promptAndProcessUserNumOfTiles();
                    System.out.println();
                    
                    System.out.println("Your tile board:");
                    createAndDisplayNewTileBoardToPlay(this.currentBoard.getNumOfTiles());

                    System.out.println();
                    break;
                case 3:
                    displayGameInstructions();
                    displayHelpInPlayMenuCommands(); 
                    System.out.println();
                    userCommandPrompt();
                    processHelpInPlayMenuUserInput();
                    break;
                case 4:
                    quit();
                    System.out.println("Your tile board:");
                    this.currentBoard.displayFaceUpValues();
                    System.out.println(); 
                    break;
                default:
                    System.out.println("Your command is invalid! Please choose one from the given options.");
                    displayInPlayMenuCommands();
                    System.out.println();
                    userCommandPrompt();
                    processInPlayMenuCommands(aTileBoard);
                    break;
            }
        } catch(NumberFormatException e)
        {
            System.out.println();
            System.out.println("Your command is invalid! Please choose one from the given options.");
            displayInPlayMenuCommands();
            System.out.println();
            userCommandPrompt();
            processInPlayMenuCommands(aTileBoard);
        }
    }
    
    /**
     * This private method prints the command options available to a player before the player has started playing and chooses
     * the Help command. These options are displayed with the game instructions. This method is invoked by the 
     * processInPlayMenuCommands method.
     * 
     */
    private void displayHelpMenuCommands()
    {
        System.out.println("1 = Play | 2 = Quit");
    }
    
    /**
     * This private method is called by the processMainMenuUserInput method when a player chooses the Help option from the
     * main menu. When called, the method uses a switch case statement to validate and process input the player provides 
     * based on the help menu command options displayed by the displayHelpMenuCommands method before the player chooses to 
     * go ahead and play the game. The player can choose to begin playing or quit the game.
     */
    private void processHelpMainMenuUserInput()
    {   
        //try catch block to deal with exceptions
        try
        {
            String option = keyboard.next();
            Integer numOption = Integer.parseInt(option);

            switch(numOption)
            {
                case 1:
                    System.out.println();
                    beginPlay();
                    break;
                case 2:
                    quit();
                    break;
                default:
                    System.out.println("Your command is invalid! Please choose one from the given options.");
                    displayGameInstructions();
                    displayHelpMenuCommands(); 
                    processHelpMainMenuUserInput();
                    break;
            }
        } catch(NumberFormatException e)
        {
            System.out.println("Your command is invalid! Please choose one from the given options.");
            displayGameInstructions();
            displayHelpMenuCommands(); 
            processHelpMainMenuUserInput();
        }
    }
    
    /**
     * This private method prints the command options available to a player once the player has started playing and chooses
     * the Help command. These options are displayed with the game instructions.     *
     * This method is invoked by the processInPlayMenuCommands method.
     * 
     */
    private void displayHelpInPlayMenuCommands()
    {
        System.out.println("1 = Continue Play | 2 = Quit");
    }
    
    /**
     * This private method is called by the processInPlayMenuCommands method when a player chooses the Help option from the
     * menu after the player has started playing the game. When called, the method uses a switch case statement to validate 
     * and process input the player provides based on the help menu command options displayed by the displayHelpInPlayMenuCommands 
     * method after the player chooses to go ahead and play the game. The player can choose to continue playing or quit the game.
     * If the player chooses to continue playing, the current tile board in the current state will be displayed. 
     */
    private void processHelpInPlayMenuUserInput()
    {   
        //try catch block to deal with exceptions
        try
        {
            String option = keyboard.next();
            Integer numOption = Integer.parseInt(option);

            switch(numOption)
            {
                case 1:
                    System.out.println();
                    System.out.println("Your tile board:");
                    this.currentBoard.showOnlyMatches();
                    System.out.println();
                    return;
                case 2:
                    quit();
                    break;
                default:
                    System.out.println("Your command is invalid! Please choose one from the given options.");
                    System.out.println();
                    displayGameInstructions();
                    displayHelpMenuCommands(); 
                    processHelpInPlayMenuUserInput();
                    break;
            }
        } catch (NumberFormatException e)
        {
            System.out.println("Your command is invalid! Please choose one from the given options.");
            System.out.println();
            displayGameInstructions();
            displayHelpMenuCommands(); 
            processHelpInPlayMenuUserInput();
        }
    }
    
    /**
     *  This private method is called by the begin play method and prompts the player to input a username
     *  and sets the username as the player's username.
     * 
     */
    private void promptAndProcessUsernameInput()
    {
        System.out.println("Please enter a username: ");
        System.out.print("?> ");
        keyboard.nextLine();
        
        String aUsername = keyboard.nextLine();
        
        this.player.setUsername(aUsername);
        System.out.println();
    }
    
    /**
     *  This private method is called by the begin play method and prompts the player to input a valid number (20, 30, or 40)
     *  as the number of tiles on the tile board. The player will be prompted until a valid input is obtained. If input is valid,
     *  it will be set the tile board's number of tiles.
     * 
     */
    private void promptAndProcessUserNumOfTiles()
    {
        System.out.println("Please choose the number of tiles you would like to play this game with (20, 30, or 40): ");
        
        try
        {
            System.out.print("?> ");
            String tiles = keyboard.next();
            Integer numTiles = Integer.parseInt(tiles);
            
            if(numTiles == 20 || numTiles == 30 || numTiles == 40)
            {
                this.currentBoard.setNumOfTiles(numTiles);
            }
            else
            {
                System.out.println();
                System.out.println("Invalid input! You must choose 20, 30 or 40 tiles for your tile board.");
                promptAndProcessUserNumOfTiles();
            }            
        }
        catch(NumberFormatException e)
        {
            System.out.println();
            System.out.println("Invalid input! You must choose 20, 30 or 40 tiles for your tile board.");
            promptAndProcessUserNumOfTiles();
        }
    }
    
    /**
     * This private method is called by the beginPlay method and the processInPlayMenuCommands when a player decides
     * to play the game and later if the player chooses to change the tile board. It takes integer value representing the 
     * number of tiles and when called, creates a new TileBoard object using the parameter. It then generates a new tile board 
     * by calling the TileBoard's generateTileBoard method using the parameter and then displays the tile board to the 
     * player by invoking TileBoard's displayStartingBoard.
     * 
     * @param tilesNum, of type int
     */
    private void createAndDisplayNewTileBoardToPlay(int tilesNum)
    {
        this.currentBoard  = new TileBoard(tilesNum);
        this.currentBoard.generateTileBoard(tilesNum);
        this.currentBoard.displayStartingBoard();
        System.out.println();
    }
    
    /**
     * This private method is called by the play method after a player chooses to play the game and has input the username and the preferred
     * number of tiles. When called, this method will prompt the player to choose 2 tiles by providing a number for each tile. The method
     * will validate the input and process it accordingly. If the player provides a valid input, the promptAndProcessFlipTilesOptionsToMatch
     * will be invoked and if the chosen tiles are a match, the tile options are added to the userMatchedTileOptions array list. If the player
     * inputs the same number for each tile, 5 points are reduced from the score. If a player inputs are number that was already a match, text
     * prompting the user to change the input is displayed.
     */
    private void promptAndProcessUserTileOptionsToMatch()
    {            
        System.out.println("Choose 2 tiles to match (Input the number corresponding to the tile you want to choose on your tile board): ");
        
        try
        {
            System.out.print("First tile: ");
            String option1 = keyboard.next();
            Integer numOption1 = Integer.parseInt(option1);            
            
            if(numOption1 >= 1 && numOption1 <= this.currentBoard.getNumOfTiles() && !this.userMatchedTileOptions.contains(numOption1))
            {
                System.out.print("Second tile: ");
                String option2 = keyboard.next();
                Integer numOption2 = Integer.parseInt(option2);
                
                if(numOption2 >= 1 && numOption2 <= this.currentBoard.getNumOfTiles() && !this.userMatchedTileOptions.contains(numOption2))
                {
                    if(numOption1.compareTo(numOption2) != 0)
                    {
                        System.out.println();
                        promptAndProcessFlipTilesOptionsToMatch(numOption1, numOption2);
                        
                        if(this.currentBoard.checkMatch(numOption1, numOption2) == true)
                        {
                            this.userMatchedTileOptions.add(numOption1);
                            this.userMatchedTileOptions.add(numOption2);
                        }
                    }
                    else
                    {
                        System.out.println();
                        System.out.println("Oops.. Please enter a different number for each tile. You cannot match a tile with itself! You lose 5 points!");
                        this.player.getIndividualScore().handleMisses();
                        displayStats();
                        System.out.println();
                        promptAndProcessUserTileOptionsToMatch();
                    }
                }
                else
                {
                    System.out.println();
                    System.out.println("Invalid input! Please choose a number from the ones displayed on your tile board.");
                    System.out.println();
                }
            }
            else
            {
                System.out.println();
                System.out.println("Invalid input! Please choose a number from the ones displayed on your tile board.");
                System.out.println();
                promptAndProcessUserTileOptionsToMatch();
            }              
        }
        catch(NumberFormatException e)
        {
            System.out.println();
            System.out.println("Invalid input! You must choose 20, 30 or 40 tiles for your tile board.");
            promptAndProcessUserTileOptionsToMatch();
        }
    }
    
    /**
     * This private method takes in 2 integer values as parameters and is called by the promptAndProcessUserTileOptionsToMatch method.
     * once the player chooses 2 different tiles to match. It displays text to the console asking the player whether or not
     * to flip the chosen tiles and processes user input accordingly. If the player chooses to not flip the tiles and change the
     * tile option, the promptAndProcessUserTileOptionsToMatch will be invoked prompting the user to input tile options again. If
     * the player chooses to flip the chosen tiles, the player's tile board will be displayed with the chosen tiles face up. The 
     * player will also be informed whether or not the tiles match with the score displayed, by invoking the matchUserTileOptions 
     * method. 
     * 
     * @param tile1, of type int
     * @param tile2, of type int 
     */
    private void promptAndProcessFlipTilesOptionsToMatch(int tile1, int tile2)
    {
        char flipTiles;
        System.out.print("Flip and match chosen tiles? \"Y\" or \"y\" (Yes please!) / \"N\" or \"n\" (No, I would like to change my options): ");
        flipTiles = keyboard.next().charAt(0);
        System.out.println();

        switch(flipTiles)
        {
            case 'N':
            case 'n':
                promptAndProcessUserTileOptionsToMatch();
                break;
            case 'Y':
            case 'y':
                System.out.println("Your tile board:");
                this.currentBoard.showPlayerBoard(tile1, tile2);
                System.out.println();
                matchUserTileOptions(tile1, tile2);
                break;
            default:
                System.out.println("Invalid input! Please enter a \"Y\" or a \"y\" to indicate a yes or a \"N\" or a \"n\" to indicate a no.");  
                System.out.println();
                promptAndProcessFlipTilesOptionsToMatch(tile1, tile2);
                break;
        }
    }
    
    /**
     * This private method is called by the promptAndProcessFlipTilesOptionsToMatch method if a player chooses to flip the chosen
     * tiles. It takes in 2 integer values representing the player's tile options and checks if they match by invoking the 
     * checkMatch method. Depending on whether they are a match or no, the score is updated and the appropriate text is
     * printed to the console.
     * 
     * @param tile1, of type int
     * @param tile2, of type int 
     */
    private void matchUserTileOptions(int tile1, int tile2)
    {
        if(this.currentBoard.checkMatch(tile1, tile2) != true)
        {
            System.out.println("Oops that's not a match! You lose 5 points.");
            this.player.getIndividualScore().handleMisses();
            displayStats();
            System.out.println();
            displayInPlayMenuCommands();
            System.out.println();
            userCommandPrompt();
            processInPlayMenuCommands(this.currentBoard);
        }
        else
        {
            System.out.println("That's a match! You gain 20 points.");
            this.player.getIndividualScore().handleMatches();
            displayStats();
            System.out.println();

            if(this.player.getIndividualScore().getNumOfMatches() == (this.currentBoard.getNumOfTiles() / 2))
            {
                System.out.println("Well done! You have successfully matched all tiles on your tile board.");
                System.out.println("You are awarded a bonus of " + this.player.getIndividualScore().getBonusPoints() + " points!");
                       
                this.player.getIndividualScore().applyBonus();
                
                System.out.println();
                System.out.println("Your Final Stats:");
                System.out.println("=================");
                displayStats();
                System.out.println();
                this.gameInProgress = false;
            }
            else
            {
                displayInPlayMenuCommands();
                System.out.println();
                userCommandPrompt();
                processInPlayMenuCommands(this.currentBoard);
            }
        }
    }
    
    /**
     * This private method is called by the promptAndProcessMainMenuUserInput method of this class. When called, it
     * will invoke the promptAndProcessUsernameInput method. It then checks if the username input by the player is in
     * the records text file by invoking the readRecords method from the PlayerRecords class. If the records contain the 
     * username, the last score will be displayed to the player. The player is then prompted for the number of tiles and the 
     * tile board is created and displayed by invoking the createAndDisplayNewTileBoardToPlay with the number of tiles
     * as the parameter. Relevant supporting text is also printed to the console.
     * 
     */
    private void beginPlay()
    {
        promptAndProcessUsernameInput();
        
        if(PlayerRecords.readRecords().containsKey(this.player.getUsername()))
        {
            System.out.println("Hello again " + this.player.getUsername() + "! Your last score was " + PlayerRecords.readRecords().get(this.player.getUsername()) + " points.");
        }
        else
        {
            System.out.print("Hello " + this.player.getUsername() + "! ");
        }        
        
        promptAndProcessUserNumOfTiles();
        System.out.println();
        
        System.out.println("Great! You can now begin your game. Good Luck!");
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><><>");
        System.out.println();
        displayStats();
        System.out.println();
        
        System.out.println("Your tile board:");
        createAndDisplayNewTileBoardToPlay(this.currentBoard.getNumOfTiles());
        
        this.gameInProgress = true;
    }
            
    /**
    * This private method when called will display a player's current number of tiles on the tile board, followed by
    * the points scored, the number of matches and number of misses. This information is displayed to the player
    * once the player chooses to play the game and until the game ends.
    * 
    */
    private void displayStats()
    {
        System.out.println("Number of tiles: " + this.currentBoard.getNumOfTiles() + " | " + this.player.getIndividualScore());
    }
    
    /**
    * This private method is called by 2 methods in this class that process user input. If a player chooses the help command,
    * this method will display the game instructions to the console.
    * 
    */
    private void displayGameInstructions()
    {
        System.out.println("Gameplay:");
        System.out.println("=========");
        System.out.println("1. When you choose to play the game, you will be prompted for a username and the number of tiles you wish to play the game with.");
        System.out.println("2. If you have played this game before using the same username, your last score will be displayed.");
        System.out.println("3. You are able to choose 20, 30, or 40 tiles for your tile board, which will then be displayed for you to begin playing.");
        System.out.println("4. Each tile on your tile board has a unique number as its \"face down\" value and a word as its \"face up\" value.");
        System.out.println("5. In each move, you can turn any 2 tiles by typing the unique number of the tile you want to flip.");
        System.out.println("6. Once you have typed in the 2 numbers, you have the option to flip the chosen tiles and view the words associated with them,");
        System.out.println("   or you can change the options.");
        System.out.println("7. If you choose to flip the chosen tiles, the tile board, with the chosen tiles flipped, will be displayed.");
        System.out.println("8. You will also know if the 2 tiles have the same word, and are a match, or not, and your game score will be displayed.");
        System.out.println("9. If the tiles do not match, they will flip again to display their number when you continue to play. Matched tiles will not flip back.");
        System.out.println("10.During the game, if you wish to play with a different tile board, you have the option to do so.");
        System.out.println("11.The game will end when you successfully match all the tiles on your tile board or, if you choose to quit the game.");
        System.out.println("12.The goal of the game is to, with every tile flip, memorise the number and word pairs, and eventually match all tiles on your");
        System.out.println("   tile board.");
        System.out.println();
        System.out.println("Scoring:");
        System.out.println("========");
        System.out.println("1. If the words on the face of both tiles match, you will be awarded " + this.player.getIndividualScore().getPointsScoredPerMatch() + " points.");
        System.out.print("2. If the words do not match, or you enter the same number for both tiles, " + this.player.getIndividualScore().getPointsLostPerMiss() + " points.");
        System.out.println(" will be reduced from your score.");
        System.out.println("3. Every match, will increase your \"number of matches\" by 1 and every miss will increase your \"number of misses\" by 1.");
        System.out.println("4. If you choose to change your tile board mid-way through your game, your score will be reset to 0 before you begin play again.");
        System.out.println("5. Once you begin to play the game, your score will be displayed throughout the game.");
        System.out.println("6. At the end of the game, your final score will be displayed along with the tile board with all tiles flipped.");
        System.out.print("7. Bonus: If you manage to match all the tiles on your tile board, you will be awarded " + this.player.getIndividualScore().getBonusPoints());
        System.out.println(" points.");
        System.out.println();
        System.out.println("     <<< Remember, this game is to train your concentration, patience, and memory. So, avoid taking notes. That will be cheating! >>>");
        System.out.println("                                 <<< Also, the tiles on your tile board shuffle! >>>");
        System.out.println();
    }
    
   /**
    * This private method is called by the 3 methods in this class that process user input. If a player chooses to quit
    * the game, this method will display, to the console, relevant text and the final stats by calling the 
    * displayStats method and set this.gameInProgress to false, ending the game.
    * 
    */
    private void quit()
    {
        System.out.println("Oh no! You have ended the game. Goodbye...!");
        System.out.println();
        System.out.println("Your Final Stats:");
        System.out.println("=================");
        displayStats();
        System.out.println();
        this.gameInProgress = false;	
    }
    
    //--------------------------------------------------------------------------------------

    /**
     * This is the public main method of the GamePlay class. This method serves as an entry point or a driver 
     * for this game. The execution of the program will begin from this method.
     * 
     * @param args
     */
    public static void main(String[] args) 
    {         
        TileBoard tileBoard = new TileBoard(0);  
        GamePlay memoryMatch = new GamePlay(tileBoard);
        memoryMatch.play();
    } 
}
