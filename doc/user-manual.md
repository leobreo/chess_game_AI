## Comand Line Interface 

This manual will illustrate how to access, control and use this chess game. It will guide you step by step through the entire terminal-based management of this software.


### Getting started
In order to start the game, you need to open your terminal and navigate to the folder the game was saved in. Assuming the required environment and tools have been installed, you will be able to open it by inserting `./schach --no-gui` into the terminal. Now the game will start automatically and show the initial game state. The chessboard consists of eight rows and eight lines. In order for you to be able to navigate through these squares on the board, there is a vertical line of numbers from zero to eight and a horizontal line with the letters from a to h. Therefore you are now ready to access each individual square through the combination of these two dimensions. As an example: Your queen will have the initial position `d1`.
Having said that, we will now take a look at the board and its pieces. The pieces are divided into two sets: white and black, represented by filled pieces and blank pieces. The initial game state is built according to the internationally agreed-upon rules. 

### Making a move
When looking at the process of inserting a move-command it is important to stick to the following pattern. A move consists of a start position and a target position - both accessible through letterNUMBER. Therefore we will describe a move by stating the current position and the target position with a hyphen in between. The first move of your pawn at `a2` could be `a2-a3`. Correct inputs will be confirmed by the exact same statement with an exclamation point at the beginning. If your command is not in accordance with that structure, you will receive a response message saying: *!Invalid move*. In that case, the system will wait for another command. 
Since there are a few special moves, these need to be accessed differently. By conducting a castling move the program is going to detect that move only by seeing the kings move. Assuming the rook is at the according square, the castling move will automatically be performed. If you want to carry out a promotion move, meaning moving a pawn to the other end of the board thus exchanging it with any other piece, you need to add an upper case letter to the end of your input statement, for example: `a7-a8Q`. If the letter at the end is missing, however, your pawn still reaches the mentioned square it will immediately turn into a queen.
After inserting a valid command it will then be checked whether it is conforming to the rules of procedure. If that should not be the case you will receive a response saying *!Move not allowed*.
At the beginning of every move, the chessboard including the current state will be shown.

### During the game
While playing against another human, this is all you need to know so far. You will use one terminal and execute one move after the other. In case you beat a piece of your enemy (according to the chess rules) the piece will disappear and your peace will take its place. If you want to check which pieces have already been taken by your opponent, just type "beaten" and they will be shown to you. 

### Ending of the game
There are a few options for how the game can end. Obviously, one player can be put in checkmate, meaning there is no possible move for the king which will keep it from getting beat. In that case, you will get a notification informing you. Another possibility would be a tie. Then the 50-moves rule applies, meaning after 50 moves whereafter no pieces have been beaten nor any pawn has been moved, the game will end in a tie. Either way, the game will automatically be ended.


