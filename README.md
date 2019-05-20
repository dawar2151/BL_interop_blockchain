# Intreop-blockchain - an interop blockchain implementation

### What is blockchain
[From Wikipedia](https://en.wikipedia.org/wiki/Blockchain_(database)) : Blockchain is a distributed database that maintains a continuously-growing list of records called blocks secured from tampering and revision.



### HTTP API
##### Get blockchain

Coming soon...

### Test

export a jar file in every node folder

First Peer:

java -jar interop-bl.jar 8080 7001 node_name

Second Peer:

java -jar interop-bl.jar 8081 7003 node_name ws://localhost:7001

Using rest Api to add messages.

Create block:

### HTTP API
##### Get blockchain
```
curl http://localhost:8080/blocks
```
##### Create block
```
curl -H "Content-type:application/json" --data '{"data" : "Some data to the first block"&user=publicKey}' http://localhost:8080/mineBlock
``` 
##### Add peer
```
curl -H "Content-type:application/json" --data '{"peer" : "ws://localhost:7003"}' http://localhost:8080/addPeer
```
#### Query connected peers
```
curl http://localhost:8080/peers
```

