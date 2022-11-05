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


## Weekly Progress Report
Week 2:
Game theory based AI simulations

Hawk and Dove Evolution Simulation: https://www.youtube.com/watch?v=YNMkADpvO4w

Hawk and Dove Explanation: https://www.youtube.com/watch?v=RAKjII7xCdk

Ant/Slime Simulation: https://www.youtube.com/watch?v=X-iSQQgOd1A

Week 3:
Made progress on prototype
- Added movement directions
- Did research into weighted random selections (for adding value to movement direction)
- Updated aesthetics of prototype

Week 4:
Clean up code for more understandable and simple rules
https://www.youtube.com/watch?v=UNNKfNqr4kc

- Lose 1 energy every second
- Start with 10 energy
- If 0 energy, die
- If 30 energy - reproduce 
- Eat food for 1 energy
- Reproduction -> change size and speed by random amount (+/- 1_

Week 5:
Worked on research report, looking at developing genetic algorithm further and creating a useable/accessible product
Used the following sources:
SOURCE 1 – Youtube Video by Primer “Simulating Natural Selection” (Natural selection simulation)
SOURCE 2 - Misconceptions about evolution (Wanting to make a game to address/educate about evolution) 
SOURCE 3 – Mice Breeding/Evolution Simulation (How traits are selected (by hawks vision) and how traits are passed down)
SOURCE 4 – Genetic Algorithms tutorial (implementing above into practice)  
SOURCE 5 – Modelling Life textbook (specifically part about predator/prey population dynamics
SOURCE 6 - My prototype

Also worked on different algorithms for pathfinding 

Week 6/7 - Worked on report and presentation 

Week 8 - reevaulating project want to create a Chess AI
https://www.freecodecamp.org/news/simple-chess-ai-step-by-step-1d55a9266977/ 
https://towardsdatascience.com/implementing-a-chess-engine-from-scratch-be38cbdae91 

Mid Semester Break
Over the midsemester break, I initially continued working on my natural simulation project. However, over this time I realised that I lacked direction for the project and did not have an idea of where to take the project next. Over the next few days, I considered and researched other options for a project until I remembered the Chess Engine project I had begun last year. Inspired by another subject, last year I spent a few weeks working on a Chess Engine but as a result of not effectively planning out my project my code became too messy and I was unable to continue. My plan for the rest of the semester was to re-impliment my old Chess Engine now that I had more coding and project management experience. For the rest of the semester break I looked at my old code, as well as beginning a Notion project for planning out how I would go about the project. 

Week 8 (26th Sept - 3rd October) 
	In week 8, I got to work with setting up the initial functionality for my project. I implemented the following features:
	Creating an initial Java/JFrame project.
	Setting up Object-Oriented classes for the squares and pieces
	Creating an 8x8 grid of squares for the chessboard
	Adding pieces to the board as well as loading in images to represent the pieces
	Began functionality to select a piece with the mouse 
Week 9 (3rd-10th October) 
	In week 9 I mostly focused on the movement of pieces with the following features:
	Adding click and drag functionality for each piece, with the piece making a move to the square the mouse was released on
	Implemented a system for handling piece moves, as well as colouring the potential squares the piece could move to
	Implemented correct move functionality for each piece
	Began work on finding checks (i.e. not allowing a piece to move if that would put the king in danger)
Week 10 (10th-17th October)
	In week 10 I finished the game functionality and began work on the Chess engine, with the following:
	Finalised finding check functionality 
	Implemented a turn system (i.e. white moves, then black moves etc.)
	Implemented initial engine and gave it the ability to make a random (legal) move
	Added pawn promotions to a queen 
	Began work on the evaluation of a position (initially just by adding up the value of each piece)
	Illustrated and implemented new pieces and squares 
	Began work on the minimax algorithm for the engine
Week 11 (17th-24th October)
	In this week I kept working on improving the engine with the following:
	Finished a first implementation of the minimax algorithm 
	Gave the ability for the engine to recognise a checkmate
	Improved position evaluation by adding heatmaps for each piece
	Began evaluating the engine against players and other engines (like chess.com)
Week 12 (24th - 31st October)
	In week 12 I continued with evaluating the engine with the following:
	Continued evaluating the engine with chess.com
	Refactored the move system in order to add complex moves (castling and en passant) 
	Began adding castling functionality 
Week 13 (31st October - 6th November)
	I was extremely busy in week 13 so had less time to progress but still worked on the following: 
	Continued work on castling
	Continued work on evaluating the engine with chess.com
	Worked on the final report 



