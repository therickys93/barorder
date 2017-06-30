#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
java -cp $DIR/build/libs/barorder.jar it.therickys93.barorder.database.DatabaseInfo
java -cp $DIR/build/libs/barorder.jar it.therickys93.barorder.server.Server