#!/bin/bash
while read line;
do
	if echo "$line" | java RDPExample1 | grep -q 'Successful'; then 
		echo "Success $line"
	else 
		echo "FAIL $line" 
	fi

done <inputs.txt
