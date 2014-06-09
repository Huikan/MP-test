MP-test
=======

after clone this repository, cd under it:

usage : **./process.sh input_hdfs output_hdfs**

the final result will be found under your defined output repository named: **{output_hdfs}/finalresult**

This project has been tested under single-node cluster. 

The ./src/main/java/multiposting package contains two parts:
+  common: definition and operation of data structure SingleDirectLinkedList for similar tags 
+  mapreduce: implementations of mapper,reducer 

./src/test/java/multiposting contains all the unit tests and [mrunit tests](https://cwiki.apache.org/confluence/display/MRUNIT/MRUnit+Tutorial).

    
