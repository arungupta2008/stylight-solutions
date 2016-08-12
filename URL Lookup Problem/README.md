# URL Lookup problem     

We should check if we have a good chance of winning in a game with two dices. The 
values of the dices are added. We start the game with 50c and profit is calculated based on what is rolled. We try to roll dice 1000 times to see if we lose or win in the long run. 
   
## Input data    
Since the dataset was not given the problem, 200,000+ data rows has been generated for this project.     


## Idea     
Since it was asked to do the lookups without looking in the database, a copy of the map is stored in memory. I'm using Google's BiMap (or "bidirectional map") for this project which is a map that preserves the uniqueness of its values as well as that of its keys. This constraint enables bimaps to support an "inverse view", which is another bimap containing the same entries as this bimap but with reversed keys and values. According to the wiki:     

```
A BiMap<K, V> is a Map<K, V> that

- allows you to view the "inverse" BiMap<V, K> with inverse()
- ensures that values are unique, making values() a Set
```     

The URL is broken into smaller parameters which are then mapped into the BiMap instead of whole URL which in result is more efficient and saves memory.   

## Running locally    
Compile the java program by running following command:
```
javac -cp '.:guava-19.0.jar' LookupURL.java
```   

Run the program by running:
```
java -cp '.:guava-19.0.jar' LookupURL
```    

## Output     

As we see in the image below, it works seamlessly for bi-directional lookups.         

![alt tag](http://g.recordit.co/s4HIU9Dfap.gif)  