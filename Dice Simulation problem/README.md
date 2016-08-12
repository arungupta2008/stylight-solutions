# Dice Simulation problem

We should check if we have a good chance of winning in a game with two dices. The 
values of the dices are added. We start the game with 50c and profit is calculated based on what is rolled. We try to roll dice 1000 times to see if we lose or win in the long run. 

## Assumption: Win vs Loss

A game is considered to be won when any of these numbers -> 7, 8, 9, 10, 11, 12 occur.    
A game is considered to be lost when any of these numbers -> 2,3,4,5,6 occur.     

## Running locally    
Compile the java program by running following command:
```
javac DiceSimulation.java
```   

Run the program by running:
```
java DiceSimulation
```    

## Output     

As we see in the image below, number of wins are greater than loss but overall we are making loss in net profit. So it is not a good idea to take part in this game.        

![alt tag](http://g.recordit.co/2c5SC5RW7D.gif)  