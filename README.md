# MemoryMatch-Game-CUI-Version (Academic Project)

**IDE:** NetBeans 8.2<br/>

This is a Command-line User Interface (CUI) version of an intellectual, yet fun, game that is intended to train concentration, patience, and short-term memory as the player attempts to match tiles on a displayed tile board, using commands from the options provided. The program consists of 6 classes and uses text files to allot each tile a "face-up" value and, if a player successfully matches all tiles on a tileboard, to store the game date, the player's username, and score. As the number of tiles on the board can change, an ArrayList is used to store each "Tile" object. The tileboard is represented on the console as a 2D matrix with rows and columns. Each "tile" has a unique integer that represents its "face-down" value. Two tiles have the same "face-up" value represented by a 4 letter word. The player uses the unique integer of each tile to choose 2 tiles to flip. The player also has various options to choose from during the game, like changing the number of tiles on the board, for example. If the player opts to flip the tiles chosen, the program will check if their "face-up" matches or not. If it is a match, the player gains points. The game ends when all the tiles on the tile board have been matched or if the player chooses to quit the game mid-way. The last score of a player is retrieved from the text file and displayed if the player returns to play the game again.<br/>

**Goal of the game:** With every tile flip, memorise the number and word pairs, and eventually match all the tiles on the tile board.

**Gameplay:**<br/>
**=======**<br/>

1. A player will be prompted for a username and the number of tiles he/she wishes to play the game with.<br/>
2. If the player has played this game before, using the same username, the last score will be displayed.<br/>
3. The player is able to choose 20, 30, or 40 tiles for the tile board, which will then be displayed before the game begins.<br/>
4. Each tile on the tile board has a unique number as its "face down" value and a word as its "face up" value.<br/>
5. In each move, the player can turn any 2 tiles by typing the unique number of the tiles. 
6. The player then has the option to flip the chosen tiles and view the words associated with them, or to change the options.<br/>
7. If the player chooses to flip the tiles, the tile board, with the chosen tiles flipped, will be displayed.<br/>
8. The player will be notified if the "face up" values of the flipped tiles match or not and the game score will be displayed.<br/>
9. If the tiles do not match, they will flip again to display their number if the player continues to play. Matched tiles will not flip back.<br/>
10. During the game, the player will also have the option to play with a different tile board.<br/>
11. The game will end when all the tiles on the tile board are matched successfully or, if the player chooses to quit the game.<br/>

**Scoring:**<br/>
**======**<br/>

1. Every match, will increase score by 20 points and the "number of matches" by 1.<br/>
2. Every miss will reduce score by 5 points and increase the "number of misses" by 1.<br/>
3. If a player chooses to change the tile board mid-way through the game, the current game will end and the score will be reset to 0 before a new game can begin.<br/>
4. A player's score will be displayed throughout the game.<br/>
5. At the end of the game, the final score will be displayed along with the tile board with all tiles flipped, and the username, score, and date will saved in the text file.<br/>
6. If a player successfully matches all the tiles on the tile board, 50 bonus points will be awarded.<br/>

**Screenshots:** <br/>
**========**<br/>

**Welcome / Introduction text:**<br/><br/>
![image](https://user-images.githubusercontent.com/52112568/70194930-04567000-1769-11ea-9bce-8c1d202a10d2.png)
<br/><br/>

**Output when 20, 30 or 40 tiles are selected for a tileboard:**<br/><br/>
![image](https://user-images.githubusercontent.com/52112568/70194871-d40ed180-1768-11ea-9f1b-cc713553d2c2.png)
<br/><br/>

**Output if a player chooses to flip the 2 chosen tiles on a 20 tile tileboard, that do not match, and the player chooses to continue.(Note: all unmatched tiles flip back and the tiles are shuffled.):**<br/><br/>
![image](https://user-images.githubusercontent.com/52112568/70194684-18e63880-1768-11ea-8bb6-06200370a773.png)
<br/><br/>

**Output when a player chooses to change the tile board.:**<br/><br/>
![image](https://user-images.githubusercontent.com/52112568/70195055-6adb8e00-1769-11ea-85e6-75833af59a64.png)
<br/><br/>

**Output when a player chooses to quit the game:**<br/><br/>
![image](https://user-images.githubusercontent.com/52112568/70195144-b4c47400-1769-11ea-8085-a2f8a7048825.png)
<br/><br/>


