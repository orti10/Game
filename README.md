# Pacman Game
Codding by: Tomer Maabari ,Ortal Hanoch and Avichay Nega.
* Exercise number 4 on Computer Science and Mathematics , Ariel University 2018.

## General
The game is about the classic Pacman game who eat fruits and gets score.

The game board is a picture of a map with pixels who from Google Earth GPS coordinates.

The goal of this game is finishing the fruits as fast as possible.

The player can eat pacmans and fruits as well.

The player blocks by black boxes.

The player needs to run away from the scary ghoset.

The player can load a .csv file.

## Example
For csv file number #8 id:306711633 we got the score 35.0.

## Built With
* [Project site](https://github.com/benmoshe/OOP_EX2-EX4)
* [Algorithm idea](https://neos-guide.org/content/multiple-traveling-salesman-problem-mtsp)
* [Reference source](https://coderanch.com/t/338737/java/draw-points-Java)
* [Reference source](https://javatutorial.net/display-text-and-graphics-java-jframe)
* [Dijkstra source](https://www.baeldung.com/java-dijkstra)

## Game options
1) Automatic game. The only decition is where to locate the main player.
2) Mouse follow game. The palyer decide where to locate the main player and also responsible where to move him for the intare game.

## Java Jars
1) Ex4_v0.2.jar - this jar is the server of the game.
2) Graph_v0.11.jar
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
![ex game](https://user-images.githubusercontent.com/44768171/50734645-8aa9b100-11aa-11e9-984f-d4787c037e8a.png)
The class Diagram:
![gamediagram](https://user-images.githubusercontent.com/44768171/50905690-0cf4d800-142c-11e9-8889-0e3257a3b683.jpg)
