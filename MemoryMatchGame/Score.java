/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGame;

/**
 * This Score class keeps a track of a player's game score and maintains 6 private variables of type int, 3 of which are final 
 * with fixed values. It also maintains a default class constructor and getter and setter methods for the variables. In addition, 
 * there are methods to handle unsuccessful tile matches (misses), successful tile matches (matches), and apply bonus points to 
 * the player score. This class also has a toString method that returns a String representation of the class.
 * 
 * @author AmritaSen 18048443
 */
public class Score 
{
    private final int POINTS_PER_MATCH = 20;       // Private final int variable to store the points earned per tile match.
    private final int POINTS_LOST_PER_MISS = 5;    // Private final int variable to store the points lost per missed tile match.
    private final int BONUS_POINTS = 50;           // Private final int variable to store the points awarded as a bonus for matching all tiles.
    private int numOfMatches;                      // Private int variable to store the number of successfull tile matches.
    private int numOfMisses;                       // Private int variable to store the number of unsuccessful tile matches.
    private int playerScore;                       // Private int variable to store the points scored by a player.
    
    /**
     *  This is a default class constructor that initialises the variables with default values.
     */
    public Score()
    {
        this.numOfMatches = 0;
        this.numOfMisses = 0;
        this.playerScore = 0;
    }
    
    /**
     * This public method is called if the face down value of the 2 tiles, chosen by the player, do not match.
     * When called, this method will increase the player's number of misses value by one and decrease the player 
     * score by the POINTS_LOST_PER_MISS value.
     */
    public void handleMisses()
    {
        this.numOfMisses++;
        this.playerScore -= POINTS_LOST_PER_MISS;
    }
    
    /**
     * This public method is called if the face down value of the 2 tiles, chosen by the player, match.
     * When called, this method will increase the player's number of matches value by one and increase the player 
     * score by the POINTS_PER_MATCH value.
     */
    public void handleMatches()
    {
        this.numOfMatches++;
        this.playerScore += POINTS_PER_MATCH;
    }
    
    /**
     * This public method is called if the player is able to match all the tiles on the tileboard.
     * When called, this method will increase the player score by the BONUS_POINTS value.    
     */
    public void applyBonus()
    {
        this.playerScore += BONUS_POINTS;
    }
    
    /**
     * This public getter method returns this.numOfMisses, an int value representing the number of unsuccessful tile matches by the player.
     * 
     * @return this.numOfMisses of type int.
     */
    public int getNumOfMisses()
    {
        return this.numOfMisses;
    }
    
    /**
     * This public setter method takes in an integer, representing a player's number of unsuccessful tile matches, as a parameter and assigns the value
     * to the variable this.numOfMisses. It does not return any value.
     * 
     * @param numOfMisses of type int.
     */
    public void setNumOfMisses(int numOfMisses)
    {
        this.numOfMisses = numOfMisses;
    }
    
    /**
     * This public getter method returns this.numOfMatches, an int value representing the number of successful tile matches by the player.
     * 
     * @return this.numOfMatches of type int.
     */
    public int getNumOfMatches()
    {
        return this.numOfMatches;
    }
    
    /**
     * This public setter method takes in an integer, representing a player's number of successful tile matches, as a parameter and assigns the value
     * to the variable this.numOfMatches. It does not return any value.
     * 
     * @param numOfMatches of type int.
     */
    public void setNumOfMatches(int numOfMatches)
    {
        this.numOfMatches = numOfMatches;
    }
    
    /**
     * This public getter method returns this.playerScore, an int value representing the player's score.
     * 
     * @return this.playerScore of type int.
     */
    public int getPlayerScore()
    {
        return this.playerScore;
    }
    
    /**
     * This public setter method takes in an integer, representing a player's score, as a parameter and assigns the value
     * to the variable this.playerScore. It does not return any value.
     * 
     * @param playerScore of type int.
     */
    public void setPlayerScore(int playerScore)
    {
        this.playerScore = playerScore;
    }
    
    /**
     * This public getter method returns this.POINTS_PER_MATCH, a final int value representing the points awarded per tile match.
     * 
     * @return this.POINTS_PER_MATCH of type int.
     */
    public int getPointsScoredPerMatch()
    {
        return this.POINTS_PER_MATCH;
    }
    
    /**
     * This public getter method returns this.POINTS_LOST_PER_MISS, a final int value representing the points lost per tile match.
     * 
     * @return this.POINTS_LOST_PER_MISS of type int.
     */
    public int getPointsLostPerMiss()
    {
        return this.POINTS_LOST_PER_MISS;
    } 
    
    /**
     * This public getter method returns this.BONUS_POINTS, a final int value representing the points that could be awarded to a player.
     * 
     * @return this.BONUS_POINTS of type int.
     */
    public int getBonusPoints()
    {
        return this.BONUS_POINTS;
    } 
    
    /**
     * This public method returns a String representation of the Score object.
     * 
     * @return String representation of the Score object.
     */
    public String toString()
    {
        return "Score: " +  this.playerScore  + " points | Number of matches: " + this.getNumOfMatches() + " | Number of misses: " +  this.getNumOfMisses();
    }
}
