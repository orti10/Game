# Pacman Game
Codding by: Tomer Maabari ,Ortal Hanoch and Avichay Nega.
* Exercise number 4 on Computer Science and Mathematics , Ariel University 2018.

The game is about the classic Pacman game who eat fruits and gets corse.
The game board is a picture of a map with pixels who from Google Earth coordinates.
The goal of this game is finishing the fruits as fast as possible.
The player can eat pacmans and fruits as well and to run away from the scary ghoset.
The player can load a .csv file.


## Built With
* [Project site](https://github.com/benmoshe/OOP_EX2-EX4)
* [Algorithm idea](https://neos-guide.org/content/multiple-traveling-salesman-problem-mtsp)
* [Reference source](https://coderanch.com/t/338737/java/draw-points-Java)
* [Reference source](https://javatutorial.net/display-text-and-graphics-java-jframe)

## Game options
1) Automatic game. The only decition is where to locate the main player.
2) Mouse follow game. The palyer decide where to locate the main player and also responsible where to move him for the intare game.

## Java Jars
1) Ex4_v0.2.jar - this jar is the main game from the * [Project site](https://github.com/benmoshe/OOP_EX2-EX4)
2) Graph_v0.1.jar
3) mysql-connector-java-5.1.47.jar

## Packages
* Algorithms --> Recursive scan of a folder. For each file in "CSV" format and creates a data structure of information.
* Coords --> GPS coordinate.
* File_format --> Convert "scv" and "path" format to kml file format.
* Geom --> convert pixel to coordinate and contrary.
* GIS --> The main objects for this game (Pacman,Fruits,Path,MyPlayer,Direction and etc.).
* GUI --> The board (Frame) of the game incloud picture of the map and an update Pacmans and Fruits for each game.
* Threads --> Standard thread. map

Excample of the board game:
![map1](https://user-images.githubusercontent.com/44768171/50381661-28728b80-0695-11e9-8ba3-bd64330dc541.png)
The map on Google Earth earth:
![earth](https://user-images.githubusercontent.com/44768171/50277567-6bdab900-044d-11e9-97e5-4dd8a4dbc5b5.png)
The Class Diagram:
![diagram](https://user-images.githubusercontent.com/44768171/50381640-b69a4200-0694-11e9-803e-56ec3703ba8c.png)
