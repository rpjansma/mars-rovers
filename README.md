#### MARS Rovers

## HOW TO RUN THE PROJECT

```Download this repo's zip or clone it;```

__You will need JDK installed in your machine! Project developed with Java 11__

```Open the project directory in your favorite IDE```

```Run the Main Class```

__*You can choose another source for the test file changing the path from line 19 of Main Class*__

__*Unit tests can be tested in the class RoversDeployTests*__


### MARS Rovers

###### __**Rules**__ 

###### There must be a way to supply the application with the input data via text file

###### The application must run

###### You should provide sufficient evidence that your solution is complete by, as a minimum, indicating that it works correctly against the supplied test data

#### Problem Description

A squad of robotic rovers are to be landed by NASA on a plateau on Mars. This plateau, which is curiously rectangular, must be navigated by the rovers so that their on-board cameras can get a complete view of the surrounding terrain to send back to Earth.

A rover’s position and location is represented by a combination of x and y co-ordinates and a letter representing one of the four cardinal compass points. The plateau is divided up into a grid to simplify navigation. An example position might be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, NASA sends a simple string of letters. The possible letters are ‘L’, ‘R’ and ‘M’. ‘L’ and ‘R’ makes the rover spin 90 degrees left or right respectively, without moving from its current spot. ‘M’ means move forward one grid point, and maintain the same heading.

Assume that the square directly North from (x, y) is (x, y+1).

INPUT:

The first line of input is the upper-right coordinates of the plateau, the lower-left coordinates are assumed to be 0,0.

The rest of the input is information pertaining to the rovers that have been deployed. Each rover has two lines of input. The first line gives the rover’s position, and the second line is a series of instructions telling the rover how to explore the plateau.

The position is made up of two integers and a letter separated by spaces, corresponding to the x and y co-ordinates and the rover’s orientation.
Each rover will be finished sequentially, which means that the second rover won’t start to move until the first one has finished moving.

OUTPUT
The output for each rover should be its final co-ordinates and heading.
INPUT AND OUTPUT


Test Input Example:

5 5

1 2 N

LMLMLMLMM

3 3 E

MMRMMRMRRM

Expected Output:

1 3 N

5 1 E
