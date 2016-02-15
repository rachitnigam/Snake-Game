## Snake-Game

This is a re-make of the classic snake game. 

####SETUP 
The snake is a series of "nodes" which move on a 2D plane. The nodes follow
the path taken by the head. The snake "eats" the insects to grow in size. Growing  in size is
an abstraction for adding nodes to the tail of the snake.

#####GOAL 
Get the maximum "points", by eating as many insects as possible without hitting
the snake's own body.

####FAILURE STATE
When the snake hits one of its own nodes or hits a "wall", the game is
over.

####IDEAS USED 
Linked Lists, Event Listeners, List traversal
