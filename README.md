# Chess Engine

![image](https://user-images.githubusercontent.com/55472933/200106919-83ca71f3-fce1-402d-94e4-239834093b86.png)





## Task Log
https://important-alloy-399.notion.site/dbb6801f28ee48c28d7d58c5ac83a6b5?v=c054c5b8c80f4dd0b4f20162f0de4b85



## Feature Explanation
### Chess Game
The Chess Game portion of the project used an object-oriented structure in Java taking advantage of inheritance. 

#### Board and Piece set up
In my chess program, there are two main factors to the set-up of the board and pieces. These are; the internal representation of the board/pieces which is used to make calculations and the graphical representation of the board/pieces. 
Graphical Representation 
The initial set-up for the board consisted of an 8x8 grid of Squares. Chess Squares are either dark or light and are coloured in an alternative way, but with each player's bottom right square being a light square. This means that the square colour can be calculated based on looking at if the row + the column is even or odd. 
	









The pieces are set up using inheritance, with a piece parent class and each individual piece inheriting from that. These classes are able to store information about the image and position of the chess piece for the graphical representation.

Internal Representation
For the internal representation of the board/pieces, it was important to store the information in the simplest way possible because the speed of the later engine would be dependent on how quickly the system could make calculations with the board. To solve this issue I represent the board as an array of 64 integers.


#### Piece moves
The move of each piece was calculated by a MoveHandler parent class that would take in the above array representing the board, as well as the position of the piece that was to be moved. This class then passes this information to the appropriate child of the PieceMoves class where the moves are calculated for the specific piece. 

The PieceMoves parent class has functions for ‘single moves’ and ‘sliding moves’ in which single moves are those performed by the king and pawns where they only move one square, and sliding moves are performed by the bishops, rooks, and queen where they can move until reaching an obstacle. More specific moves such as the knight's moves and pawns moving two squares on the first move are calculated within each child class. 

#### Checks/checkmates
Checks and checkmates are an essential part of determining the legality of a move. This is because the king can never be taken in chess which means that no piece can move in a way that exposes the king. 
In order to calculate checks, the CheckFinder class takes in each potential move of a piece. The class then looks at each response to this move and if any of those moves will take the king, then the move is discounted.

## Chess Engine
My Chess Engine works by attempting to find the best move possible in any given position using the Minimax algorithm.
Minimax Algorithm 
The Minimax algorithm is an algorithm that is applicable in games where one player is attempting to maximise the score, and the other trying to minimise the score. In my representation of the chess board, the white pieces are assigned positive score values and the black negative. This means that in any given position, white is ultimately trying to increase the score of the board and black is trying to decrease the score of the board (with the ultimate goal being for each player to checkmate their opponent). 

The Minimax algorithm works by recursively searching through a tree of moves to look a certain number of moves ahead in a Chess game, the final position at the end of the tree is evaluated to find the score of the board. This process can be better understood with the following graphical representation:  

Where each square represents a move and the number represents the engine’s evaluation of the position after that move is made. 

### Board Evaluation
The process of evaluating a position is the key for any Chess engine to be able to evaluate the best move in any position. My Chess engine is comparatively basic and only uses two calculations to evaluate a position, these are piece scores and heatmaps. 
Piece Scores
Evaluation of piece scores is the process of assigning a value to each piece and then calculating the total score of the board. Based on values from the website Chessprogramming.org my values were as following (with the positive value being for white pieces and the negative value being for black pieces): 
	Pawn : 100
	Knight : 320
	Bishop : 330
	Queen : 900
	King : 20,000

Based on this evaluation, when all of the values of the pieces are summed, the engine will make a move that either maximises or minimises this sum. 
	
### Heatmaps
There are times in a chess game, particularly in the opening of the game when taking pieces is either not an option or will not produce the most ideal result. In these cases, my engine uses ‘heatmaps’ for each piece in which the piece will preference certain squares to move to, this works by assigning value to each square of the board depending on the piece, and then adding that value to the evaluation score. These values can be seen in Appendix 3.   






