#!/bin/bash

# Export $JBOSS_HOME when not set in environment variables.
# For cygwin set he windows path because it java from windows.
#export JBOSS_HOME=X:/SDKs/wildfly-8.1.0

# Check JBOSS_HOME environment variable.
if [ "x$JBOSS_HOME" == "x" ]; 
	then echo "ERROR: Need to set JBOSS_HOME environment variable, alternative open this script and export JBOSS_HOME with our path"; 
	exit 1; 
fi

# Start h2 tcp database.
java -cp $JBOSS_HOME/modules/system/layers/base/com/h2database/h2/main/h2*.jar org.h2.tools.Server