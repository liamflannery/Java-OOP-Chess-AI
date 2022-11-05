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





