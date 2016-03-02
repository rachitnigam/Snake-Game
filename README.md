##Snake

This is a re-make of the classic game [Snake](https://en.wikipedia.org/wiki/Snake_%28video_game%29). 

![Snake Game](http://cdn2.ubergizmo.com/wp-content/uploads/2011/12/13-Snake.jpg)

###The Game 

- Goal: Get the maximum "points", by eating as many insects as possible without hitting
the snake's own body or the walls.

- Failure State: When the snake hits one of its own nodes or hits a wall, the game is
over.


###Ideas behind the implementation 
- The snake is a series of "nodes" which moves on a 2D plane. The board can be represented by a 2D array. Since the project    was initially made for Turbo C++ console, I made it with bounds for a standard 25 X 80 console window. 
 
- The nodes follow the path taken by the head. This makes it incredibly easy to think of it as linked list. The movement is    of a node is simply the changing it with the coordinates of the node in front of it. This reduces the problem of updating    the position of the snake to just updating the position of the head and propogating the change to the nodes behind it. 

- The snake "eats" the insects to grow in size. Growing the size of the snake is simply linking a new node to the tail node    of the snake.

- The repainting and checking insect collisions are simple event listeners. I created two different event listeners because I   have heard it is good practice to not bind the frame refreshing part of a game with the logic of the game. So if you have    the right equipment, you run this game in glorious 144 Fps.


####To Do List
1. Hide the event listener button
2. Make it more feature complete
3. Add walls.
4. Make it more general, rather than only working on 25 X 80 windows
5. Port it out of java? 
