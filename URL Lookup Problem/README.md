# URL Lookup problem     
Problem statement is to create a speaking URL from a parameter URL and reverse lookup of the Speaking URL. Use cases for this mapping table are the lookup of a parameter combination as soon as you request a speaking URL and a reverse lookup of the speaking URL, whenever you are building a link.
   
## Input data    
Since the dataset was not given the problem, 200,000+ data rows has been generated for testing the project.   

## Idea     
Since it was asked to do the lookups without looking in the database, a copy of the map is stored in memory. I'm using Google's BiMap (or "bidirectional map") for this project which is a map that preserves the uniqueness of its values as well as that of its keys. This constraint enables bimaps to support an "inverse view", which is another bimap containing the same entries as this bimap but with reversed keys and values. According to the wiki:     

```
A BiMap<K, V> is a Map<K, V> that

- allows you to view the "inverse" BiMap<V, K> with inverse()
- ensures that values are unique, making values() a Set
```     

The URL is broken into smaller parameters which are then mapped into the BiMap instead of whole URL which saves memory and is more efficient.   

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