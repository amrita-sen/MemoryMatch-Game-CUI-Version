/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGame;

/**
 * This Player class represents a Player object, representing a user who plays the Memory Match game, and maintains 2 private variables, a username of 
 * type String and an instance of the Score object representing this player's individual score. It also maintains a default class constructor, getter 
 * and setter methods for the userName variable and a getter method for the individualScore variable. 
 * 
 * @author AmritaSen 18048443
 */
public class Player 
{
    private String username;            // Private String variable to store the user's username.
    private Score individualScore;      // Private instance of the Score object representing this player's individual score.

    /**
     *  This is a default class constructor that initialises the variables with default values.
     */
    public Player()
    {
        this.username = " "; 
        this.individualScore = new Score();
    }
 
    /**
     * This public getter method returns the username of type String. It does not take in any parameters.
     * 
     * @return this.username, of type String.
     */
    public String getUsername()
    {
        return this.username;
    }
    
    /**
     * This public setter method, takes in a String value representing a player's username and assigns
     * it to the this.username variable of this class. It does not return any value.
     * 
     * @param username, of type String. 
     */
    public void setUsername(String username)
    {
        this.username = username;
    }   
    
    /**
     * This public getter method returns an instance of the Score object, representing this player's individual score.
     * It does not take in any parameters.
     * 
     * @return this.individualScore, an instance of the Score object.
     */
    public Score getIndividualScore()
    {
        return this.individualScore;
    }
}


