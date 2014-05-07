#!/bin/bash

ARRAY=()

function get_index {
    for ((i = 0; i < ${#ARRAY[@]}; i++)); do
        if [ "${ARRAY[$i]}" = $1 ]; then
            index=$(($i+1))
            echo "${index} - ${ARRAY[$i]}"
            break
        fi
    done
}

while read line; do
    ARRAY+=("$(echo $line | awk -F"|" '{print $1}')")
done < $1

for ix in ${!ARRAY[*]}
do
    get_index "${ARRAY[$ix]}"
done
