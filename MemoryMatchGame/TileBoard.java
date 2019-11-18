/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a TileBoard object and maintains a private list of Tile objects. It also maintain 3
 * more private integer variables of which one is final and store the final value for number of columns a TileBoard
 * has. It has a public get and set method for the number of tiles only. In addition, there are methods to generate the 
 * tile board, allocate face up and face down values and shuffle the tiles on the board and different methods to
 * display the tile board differently in different conditions. It can have all tiles displaying the face down value,
 * some tiles displaying the face up value or all tiles displaying the face up value.
 * Once a player begins to play the game, a tile board, a 2D grid of rows and columns, will be generated and 
 * printed to the console, based on the choice of the number of tiles. 
 *
 * @author AmritaSen 18048443
 */
public class TileBoard 
{
    private List <Tile> board;               // This is a private list to store Tile objects. 
    private int numOfTiles;                  // This is a private int variable to store the number of tiles on a tileboard.
    private final int NUM_COLUMNS = 5;       // This is a private int variable to store the number of columns of a tileboard.
    private int numOfRows;                   // This is a private int variable to store the number of rows of a tileboard.
    
    /**
     * This is the class constructor that takes an integer representing the number of tiles as a parameter
     * and intitialises the variables.
     * 
     * @param numOfTiles, of type integer 
     */
    public TileBoard(int numOfTiles)
    {
        this.board = new ArrayList<>();           // this.board is initialised with a new ArrayList
        this.numOfTiles = numOfTiles;    
        this.numOfRows = numOfTiles/NUM_COLUMNS;
    }  

    /**
     * This public getter method returns the number of tiles as an integer value. It does not take in any parameters.
     * 
     * @return this.numOfTiles, of type integer
     */
    public int getNumOfTiles()
    {
        return this.numOfTiles;
    } 

    /**
     * This public setter method, takes in an integer value representing the number of tiles and assigns it
     * to the this.numOfTiles variable of this class. Based on the if-else statements specified, this.numOfTiles 
     * can be set to 0, 20, 30, or 40. It does not return any value.     * 
     * 
     * @param numOfTiles 
     */
    public void setNumOfTiles(int numOfTiles)
    {
        if(numOfTiles == 20 || numOfTiles == 30 || numOfTiles == 40)
        {
            this.numOfTiles = numOfTiles;
        }
        else
        {
            this.numOfTiles = 0;
        }
    }        
  
    /**
     * This method is called by the createAndDisplayNewTileBoardToPlay method in the GamePlay class. When called, 
     * this method generates a tile board (List) of Tile objects based on the integer value representing the 
     * number of tiles, it takes as a parameter. A for loop is used to add Tile objects to the list.
     *  
     * @param theNumOfTiles
     * @return this.board, a List of Tile objects
     */
    public List<Tile> generateTileBoard(int theNumOfTiles) 
    {
        // iterates through the tileboard and adds a new Tile object to the tileboard.
        // number of tiles added to the board is equal to the on the number provided 
        // as a parameter.
        for(int i = 0; i < theNumOfTiles; i++)
        {
          this.board.add(new Tile());
        }   

        allocateFaceDownValues();        // allocates face down values to each tile.
        shuffleTiles();                  // shuffles the tiles in the list.
        allocateFaceUpValues();          // allocates face up values to each tile.
        shuffleTiles();                  // shuffles the tiles in the list again.

        return this.board;
    }
    
    /**
     * This private method is called by the generateTileBoard method. When called, the method 
     * will assign to each Tile object an integer value, between 1 and the number of tiles chosen 
     * by the player, as its face down value.
     * 
     */
    private void allocateFaceDownValues()
    {
        int value = 1; 

        for(Tile aTile : board)
        { 
            aTile.setFaceDownValue(value);
            value++;          
        }
    }
  
    /**
     * This private method is called by the generateTileBoard method. When called, the method will read 
     * string values from the specified text file containing a list of words, and set it as a face down 
     * value for each tile. A scanner object is used to read the text file.
     * 
     */
    private void allocateFaceUpValues() 
    {   
        File textFile = new File("faceUpValuesList.txt");  // new File object created.
        int tiles = 0;

        try (Scanner sc = new Scanner(new FileReader(textFile)))
        {
            while (sc.hasNext() && tiles != this.numOfTiles) 
            {
                for(Tile aTile : board)
                {
                    aTile.setFaceUpValue(sc.nextLine());
                    tiles++;
                }
            }

          sc.close();
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
    }
  
    /**
     * This private method is called by the generateTileBoard method and the showOnlyMatches method.
     * When called, the method will apply the shuffle operation on the tile board this.board and 
     * return this.board with shuffled tile objects (the position of each object will change to a 
     * random position).
     * 
     * @return this.board, a List of Tile objects.
     */
    private List<Tile> shuffleTiles()
    {
        Collections.shuffle(this.board);
        return this.board;
    }
    
    /**
     * This public method is called by the createAndDisplayNewTileBoardToPlay method in the GamePlay class.
     * When called, using for loops and if-else statements, a tile board will be printed to the console
     * as a 2D grid of rows and columns with all tile face down (face down values displayed).
     * 
     */
    public void displayStartingBoard()
    {
        int value = 0;
      
        for(int k = 0; k < (this.NUM_COLUMNS * 4) + 6; k++)
        {
           System.out.print("-");
        }
        
        System.out.println(); 
       
        for(int i = 0; i < this.numOfRows; i++)
        {
           for(int j = 0; j < this.NUM_COLUMNS; j++)
           {
              if(this.board.get(value).getFaceDownValue() >= 1 && this.board.get(value).getFaceDownValue() <= 9)
              {
                  System.out.format("| " + "%02d " , this.board.get(value).getFaceDownValue());
              }
              else
              {
                  System.out.print("| " + this.board.get(value).getFaceDownValue() + " ");
              }
              
              value++;
           }

            System.out.print("|");
            System.out.println();
                      
            for(int j = 0; j < (this.NUM_COLUMNS * 4) + 6; j++)
            {
                System.out.print("-");
            } 
            
            System.out.println();
       }
    }
    
    /**
     * This public method is called by the promptAndProcessFlipTilesOptionsToMatch method in the 
     * GamePlay class if a player chooses to flip the chosen tiles. When called, the method sets 
     * the face down value of the tiles with face down integer values option1 and option2 (taken 
     * as parameters) to -1. Using for loops and if-else statements, the tile board will be printed 
     * to the console as a 2D grid of rows and columns, where if the face down value of a tile is 
     * set to -1, means the tile was a match, the face up value will be displayed. All other tiles 
     * will have their face down value displayed.
     * 
     * @param option1
     * @param option2 
     */
    public void showPlayerBoard(int option1, int option2)
    {
        setFaceDownValueForMatchedTiles(option1, option2);
        int value = 0;
       
        for(int k = 0; k < (this.NUM_COLUMNS * 4) + 6; k++)
        {
           System.out.print("-");
        }
        
        System.out.println();
        
        for(int i = 0; i < this.numOfRows; i++)
        {
            for(int j = 0; j < this.NUM_COLUMNS; j++)
            { 
                if(option1 == this.board.get(value).getFaceDownValue() || option2 == this.board.get(value).getFaceDownValue()|| 
                   this.board.get(value).getFaceDownValue() == -1)
                {
                  System.out.print("|" + this.board.get(value).getFaceUpValue().trim());
                }  
                else 
                {
                    if(this.board.get(value).getFaceDownValue() >= 1 && this.board.get(value).getFaceDownValue() <= 9)
                    {
                        System.out.format("| " + "%02d ", this.board.get(value).getFaceDownValue());
                    }
                    else
                    {
                        System.out.print("| " + this.board.get(value).getFaceDownValue() + " ");
                    }
                }

                 value++;
            }  

            System.out.print("|");
            System.out.println();
            
            for(int j = 0; j < (this.NUM_COLUMNS * 4) + 6; j++)
            {
                System.out.print("-");
            } 
            
            System.out.println();
        }
    }
    
    /**
     * This public method is called by the displayInPlayMenuCommands and the displayHelpInPlayMenuCommands methods
     * in the GamePlay class. When called,it simply shuffles the tiles by calling the shuffleTiles method and 
     * then prints a player's tile board to the console during play. Using for loops and if-else statements, the 
     * tile board will be printed to the console as a 2D grid of rows and columns, where if a 
     * the face down value of a tile is set to -1, means the tile was a match in a previous round, 
     * the face up value will be displayed. All other tiles will have their face down value displayed. 
     * 
     */
    public void showOnlyMatches()
    {    
        shuffleTiles();
        int value = 0;
        
        for(int j = 0; j < (this.NUM_COLUMNS * 4) + 6; j++)
        {
           System.out.print("-");
        }
        
        System.out.println();
      
        for(int i = 0; i < this.numOfRows; i++)
        {
            for(int j = 0; j < this.NUM_COLUMNS; j++)
            { 
                if(this.board.get(value).getFaceDownValue() == -1)
                {
                    System.out.print("|" + this.board.get(value).getFaceUpValue().trim());
                }  
                else
                {
                    if(this.board.get(value).getFaceDownValue() >= 1 && this.board.get(value).getFaceDownValue() <= 9)
                    {
                        System.out.format("| " + "%02d" + " ", this.board.get(value).getFaceDownValue());
                    }
                    else
                    {
                        System.out.print("| " + this.board.get(value).getFaceDownValue() + " ");
                    }
                }

                value++;
            }  
            
            System.out.print("|");
            System.out.println();
            
            for(int k = 0; k < (this.NUM_COLUMNS * 4) + 6; k++)
            {
                System.out.print("-");
            } 
            
            System.out.println();
        }
    }
  
    /**
     * This public method is called by the setFaceDownValueForMatchedTiles method of this class and the
     * promptAndProcessUserTileOptionsToMatch and matchUserTileOptions methods in the GamePlay class.
     * This method takes in 2 takes in two integer values, representing the tile face up values chosen 
     * by the player, and checks if their face up string values are equal.
     * 
     * @param option1, an integer representing a tile face up value chosen by the player
     * @param option2, an integer representing another tile face up value chosen by the player
     * @return true if the string values on both tiles match, else false.
     */
    public boolean checkMatch(int option1, int option2)
    {   
        // local variables to store Sting values representing 2 face up values.
        String faceUpValue1 = "";
        String faceUpValue2 = "";

        // iterates through the array list of tile objects, gets the index of the tile with face down value option1 
        // and assigns the associated face up value to variable faceUpValue1. Similar operation for option2.
        for(int i = 0; i < this.numOfTiles; i++)
        {
            if(this.board.get(i).getFaceDownValue() == option1)
            {
                faceUpValue1 = this.board.get(i).getFaceUpValue().trim();
            }

            if(this.board.get(i).getFaceDownValue() == option2)
            {
                faceUpValue2 = this.board.get(i).getFaceUpValue().trim();
            }
        }

        return faceUpValue1.equalsIgnoreCase(faceUpValue2);  
    }
    
    /**
     * This private method is called by the showPlayerBoard method. It takes in two integer values, 
     * representing the tile face up values chosen by the player, checks if they were a match by calling 
     * the checkMatch method, and sets the face down value of each tile to -1, if the method evaluated to 
     * be true and the tiles were a match.
     * 
     * @param option1, an integer representing a tile face up value chosen by the player
     * @param option2, an integer representing another tile face up value chosen by the player
     */
    private void setFaceDownValueForMatchedTiles(int option1, int option2)
    {   
        // local variables to store integer values representing the indices of 2 tile objects.
        int index1 = 0;  
        int index2 = 0; 
        
        // if the tiles with face down value option1 and option2 are a match.
        if(checkMatch(option1, option2) == true)   
        {
            // iterates through the tile board, gets the index of the tile with face down value option1
            // and assigns the index value to variable index1. Similar operation for option2.
            for(int i = 0; i < this.numOfTiles; i++)
            {
                if(this.board.get(i).getFaceDownValue() == option1)   
                {
                    index1 = i;                                       
                }
                
                if(this.board.get(i).getFaceDownValue() == option2)   
                {
                    index2 = i;                                        
                }
            }
            
            // set the face down value of the tile at index1 and index2 to -1
            this.board.get(index1).setFaceDownValue(-1);              
            this.board.get(index2).setFaceDownValue(-1);               
        }
   }
  
    /**
     * This public method will be called after a player chooses to quit a game or after a player succeeds in matching all
     * the tiles on the tile board and the game ends. When called, the player's tile board will be printed to the console 
     * as a 2D grid of rows and columns with the face up value of all the tiles on the tile board visible. A for loop is 
     * used to iterate through the list of tile objects (tile board).
     * This is a representation of the tile board with all tiles flipped to reveal the face up value.
     * 
     */
    public void displayFaceUpValues()
    {
        int index = 0;

        for(int j = 0; j < (this.NUM_COLUMNS * 4) + 6; j++)
        {
            System.out.print("-");
        }
        
        System.out.println();

        for(int i = 0; i < this.numOfRows; i++)
        {
            for(int j = 0; j < this.NUM_COLUMNS; j++)
            {
                System.out.print("|" + this.board.get(index).getFaceUpValue().trim());
                index++;
            }

            System.out.print("|");
            System.out.println();

            for(int k = 0; k < (this.NUM_COLUMNS * 4) + 6; k++)
            {
                System.out.print("-");
            } 

            System.out.println();
        }
    }
}
