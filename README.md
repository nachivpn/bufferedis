Bufferedis
==========

Buffererdis is an asynchronous write buffer for Redis, built on top of the  Jedis client. This wrapper around Jedis can be typically used for high latency client-server environments. The current Jedis client (v2.6), has a relatively poor implementation of redis pipeline. Bufferedis attempts to be faster than the current implementation of Jedis pipeline. Bufferedis exploits the MTU capability of the network in addition to cutting down on network latency troubles. 

Pipeline vs Bufferedis
----------------------
Pipeline is a client side feature which attempts to cut down on the time spent over the network on paying for the latency. More on pipelining internals [here](http://nachivpn.blogspot.in/2014/11/redis-pipeline-explained.html).
Bufferedis is also a client side feature where the redis commands are buffered and flushed asynchronously once a specified limit has been reached. This way, the time spent over the network latency is reduced, and also, the bandwidth is utilized as more commands are sent over a single packet and executed in bulk on the server side. In simple words, the performance gain can be noticed as it sends more commands in a single packet than one packet per command. It doesn't do anything ground-breakingly cool, but the speed-up of this approach is insane!

Usage examples:

Set
---
```java
Set setBufferedis = new Set("host", 6379, "password");
for (Map.Entry<String, String> entry : everGrowingHashMap.entrySet()){
	String key = entry.getKey(); 
	String value = entry.getValue();
	setBufferedis.add(key, value);
}
setBufferedis.exec();
```

Del
---
```java
Del delBufferedis = new Del("host", 6379, "password");
for(String key : everGrowingKeysArray)
	delBufferedis.add(key);
delBufferedis.exec();
```

HDel
----
```java
HDel hdelBufferedis = new HDel("host", 6379, "password", "hash");
for(String field : everGrowingFieldArray)
	hdelBufferedis.add(field);
hdelBufferedis.exec();
```

Benchmark
---------
It is advisable to use pipelining for relatively smaller number of commands like 10K. For larger number of commands, like >= 100K, Bufferedis could yield very good results (more than 100% speedup!). Have a look at this [post](http://nachivpn.blogspot.in/2014/10/bufferedis-faster-than-redis-pipeline.html) for some quick benchmarking results.

Limitations
------------
* Bufferedis may end up consuming a huge amount of heap space if the buffer limit is not configured properly. Hence it must be configured depending on the use case (data size & amount of data). It exploits space to performance trade-off.
* Currently it is available only for a very limited set of read commands. Feel free to fork and extend it if it works for you.
 
Contact
-------
nachivpn@gmail.com