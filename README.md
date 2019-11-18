# MemoryMatch-Game-CUI-Version
Command-line User Interface version of a tile matching game.
This is a an intellectual, yet fun, game that is intended to train concentration, patience, and short-term memory as the player 
attempts to match tiles on a displayed tile board, using commands from the options provided.
The program consists of 6 classes and uses text files to allot each tile a "face-up" value and to store a the date, player's username, 
and test score. As the number of tiles on the board is not constant, an ArrayList is used to store each "Tile" object.
The tileboard is represented on the console as a 2D matrix with rows and columns. Each "tile" has a unique integer that represents its "face-down"
value. Two tiles have the same "face-up" value represented by a 4 letter word. The player uses the unique integer of each tile to choose
2 tiles to flip. The player also has various options to choose from during the game, like changing the number of tiles on the board, for
example.
If the player opts to flip the tiles chosen, the program will check if their "face-up" matches or not. If it is a match, the player gains points.
The game ends when all the tiles on the tile board have been matched or if the player chooses to quit the game mid-way.
