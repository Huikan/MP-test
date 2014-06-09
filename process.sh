#!/bin/bash

EXPECTED_ARGS=2
E_BADARGS=65

if [ $# -lt $EXPECTED_ARGS ]
then
  echo "Usage: `basename $0` {input} {output}"
  exit $E_BADARGS
fi

echo "hdfs://input $1"
echo "hdfs://output $2"
mvn compile 
mvn exec:java -Dexec.mainClass=multiposting.mapreduce.SimTagDriver -Dexec.args="$1 $2"

awk '{split($0,a,"\t"); print a[1],'\t',a[2],'\t',a[3],'\t',a[4],'\t',a[5],'\t',a[6],'\t',a[7],'\n' }' /$2/part-r-00000 > finalresult
