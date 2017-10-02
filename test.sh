#!/bin/bash
for FILE in $(find `pwd` -name in*.txt)
do
	if cat $FILE | java RDPExample1 | grep -q 'Successful'; then 
		echo "Success"
	else 
		echo "FAIL"
	fi

done
