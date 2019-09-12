Adrenalina
==========

### "Adrenalina" by Cranio Creations, project by Gadolini, Mombelli, Nicolò
 
 This is the second part of the final course of Software Engineering I for IT Engineering at Politecnico di Milano, year 2018-19,
 where students have to design a software adaptation for a board game.
 
 The General Purpose
 ----------------------
 
 The purpose of this project is to develop the basic skills of team working, software design and problem solving needed by a 
 junior engineer to be able to work under precise specifications and deadlines.
 This was achieved during the semester by having a couple of professors that on a weekly basis commissioned a task and evaluated
 the result the next week.
 
 
 The Project
 -----------
 
 Cranio Creations has been a partner of Politecnico di Milano in this course for years. Previous students had to design the 
 software version of their board games such as Sagrada and Lorenzo Il Magnifico. This year the assignment was the game "Adrenalina" (Adrenaline).
 
 ### The Game and the Rules
 
 The board game is designed to be played by 3 to 5 players. It's a turn based game, where players engage themselves into a deathmatch confined 
 into a building made of different rooms connected by doors, with plenty of weapons, power-up's and ammunitions.
 
The rules of the game are quite extended, but can be summarized in a couple of fundamentals:

1. Each player has 10 hitpoints. After a player loses all of them, he dies and respawns.
2. In a single match, respawns are finite. After that, the game ends and the player with highest score wins.
3. Players can travel from room to room, and can shoot or hit only visible players.
4. If either a weapon or a power-up says so, rule 3 can be deceived :+1:.
5. Each player can have only three weapons simultaneously.
6. Each player can have a maximum of three of each ammo type.
7. Each player can have a maximum of three power-ups.

The game is simple to play, but has far more complicated rules, explained exhaustively in the game manual.

The Team
--------
During the first lab meeting, students where asked to form teams of three people.
Knowing each other for years and living close enough one another, we chose to work together.

The Assignment and Development
--------------------------------

This exam had strict rules about the implementation of the game, both for a practical simulation of a real work environment 
and for the final grade. 

The requirements stated that the minimum specifications needed to pass the exam were to implement a simplified set of rules,
being able to play the game onto a Command Line Interface, and have a basic LAN connectivity allowing to play on different
machines concurrently. 

The grade could be increased by adding a Graphic User Interface, improving the connectivity and adding a specific
_Advanced Functionality_.

The development process started with the initial design of the Model-View-Controller principle applied to this board game.

The Model in this project consists of all the dataset needed to play the game, such as Board Class, Player Class, Weapons Classes,
Power-Ups and so on, building a specific type for each piece of the game.

The Controller part is the implementation of all the ruleset, such as weapons' behavior, turns management, score system etc...

The View is responsible of message handling, managing interaction between players and CLI/GUI updates.

After this process of planning the coding started.

We decided that we wanted to implement all the specifications needed to score a final grade of 30/30. This decision was meaning that our 
software had to have a complete set of rules, an interchangeable CLI/GUI mode, a cross-capable connection between clients connected
to the server via Remote Method Invocation (RMI) or via Socket implementation, and one advanced functionality, that in our case was
the implementation of an extra game mode, the Terminator.


IDE and Verification Software
------------------------------

All the code was written in Java 8 using JetBrain's IntelliJ as the main Integrated Developement Environment.
The code comes also with a JavaDoc Library written for almost all the classes.
The requirements regarding testing and _coding principles_ (duplications, bugs, weaknesses, test coverage) were all handled
by Sonar's SonarQube and SonarLint plugin.
Libraries imports were managed via Maven Plugin.


Conclusions
-----------

Our Software scored a final grade of 28/30, because we had a slightly higher than normal percentage of duplication, coming from
the similar behaviour of some weapons that resulted in functions duplication, and a minor bug that occurred during our final interview
that resulted in a network unhandled event.

Extra Notes
------------

All the provided material is intended for educational purpose, it's not intended for commercial use, it's not designed to work
via Internet and has no security features. This code will not be mantained further or optimized/fixed and is provided AS IS to the general public.
For any kind of questions about this material you can contact us via our github emails, but we can't guarantee an answer.

Any kind of material concerning specifications, detailed rules, developement or laboratories is under the Resources folder (rsc).

Contributors
------------

Lorenzo Gadolini ---> *[@TehLowLow](https://github.com/TehLowLow)*.

Gioele Mombelli ---> *[@lolepls](https://github.com/lolepls)*.

Daniele Nicolò --->*[@Dany97](https://github.com/Dany97)*.


















