/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryMatchGame;

/**
 * This Tile class represents a Tile object, the building block of the Memory Match game, and maintains 2 private variables, a faceUpvalue of 
 * type String and a faceDownValue of type int. It also maintains a default class constructor and getter and setter methods for the variables.
 * The TileBoard class maintains a list of Tile objects.
 * 
 * @author AmritaSen 18048443
 */
public class Tile 
{
    private String faceUpValue; // Private String variable to store the value representing the face up value of a Tile.
    private int faceDownValue;  // Private String variable to store the value representing the face down value of a Tile.
    
    /**
     *  This is a default class constructor that initialises the variables with default values.
     */
    public Tile ()
    {
        this.faceUpValue = " ";
        this.faceDownValue = 0;
    }  
    
    /**
     * This public getter method returns the faceDownValue of type int. It does not take in any parameters.
     * 
     * @return this.faceDownValue, of type int.
     */
    public int getFaceDownValue() 
    {
        return this.faceDownValue;
    }

    /**
     * This public setter method, takes in an int value representing the face down value of a Tile and assigns
     * it to the private faceDownValue variable of this class. It does not return any value.
     * 
     * @param faceDownValue of type int.
     */
    public void setFaceDownValue(int faceDownValue) 
    {
        this.faceDownValue = faceDownValue;
    }
    
    /**
     * This public getter method returns the faceUpValue of type String. It does not take in any parameters.
     * 
     * @return this.faceUpValue, of type String.
     */
    public String getFaceUpValue() 
    {
        return this.faceUpValue;
    }
    
    /**
     * This public setter method, takes in an String value representing the face up value of a Tile and assigns
     * it to the private faceUpValue variable of this class. It does not return any value.
     * 
     * @param faceUpValue of type String.
     */
    public void setFaceUpValue(String faceUpValue) 
    {
        this.faceUpValue = faceUpValue;
    }   
}
