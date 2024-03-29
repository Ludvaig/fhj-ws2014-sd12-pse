Build and Deploy with maven
---------------------------

Description to add wildfly servers to maven for use it with the wildfly maven plugin.

This directory contains a customized copy of the template file from the maven installation.
In the section profiles we can define several active servers which can be used by maven.

Copy the settings.xml file to your ${USER_HOME}/.m2 folder!

Open it and change wildfly-home to your local wildfly home directory.
You find this tag at the end of the file.
<profile>
			<id>wildfly-local</id>
			<properties>
				<wildfly-home>X:/SDKs/</wildfly-home>
        .....
        
If you want to add a remote server as well customize the "wildfly-remote" profile and
activate it under "activeProfiles" at the end of the file.
  

Maven goals:
------------

war pugin
---------

http://maven.apache.org/plugins/maven-war-plugin/

war:war is the default goal invoked during the package phase for projects with a packaging type of war. It builds a WAR file.
war:exploded is generally used to speed up testing during the developement phase by creating an exploded webapp in a specified directory.
war:inplace another variation of war:explode where the webapp is instead generated in the web application source directory, which is src/main/webapp by default.
war:manifest generates a manifest for this webapp. The manifest file is created in the web application source directory.

wildfly pugins
--------------

https://docs.jboss.org/wildfly/plugins/maven/latest/

wildfly:add-resource adds a resource.
wildfly:deploy deploys the application to the application server.
wildfly:deploy-only deploys the application to application server invoking no other goals by default.
wildfly:deploy-artifact deploys an arbitrary artifact to the server.
wildfly:redeploy redeploys the application.
wildfly:redeploy-only redeploys the application invoking no other goals by default.
wildfly:undeploy undeploys the application.
wildfly:run runs the application server and deploys your application.
wildfly:start starts the application server and shuts it down at last when the maven process ends unless an explicit shutdown from a management client or the shutdown goal is executed.
wildfly:shutdown shuts down a running application server.
wildfly:execute-commands executes commands on the running server.