Version history:
----------------

v1.2:
Refactoring for: Layered enterprise architectures, Logging, Business and technical code encapsulation,
Persistence and Entities, View usability, Exception handling. 

v1.1-SNAPSHOT:
Snapshot version for previous project structure. Further versions consists of refactored project and
packages structure.

v1.1:
Iteration 1 user stories.

v1.0-SNAPSHOT:
Create project maven envirnoment. Get it working for all developers!


Guidline:
---------

Note: 
To test the environment for all envolved developers we add a jboss example project source to
it. This makes it easy to ensure it runs on all developer clients when the deploy it. The example package
is "org.jboss.as.quickstart.hibernate4". Please don't add real project classes to it, we work only in the 
"at.fhj.swd" package and the "hibernate4" example is going to be deleted in a further development step.
Keep in mind this is only an example for test your envirnoment and give you a short access tutorial.

Tutorials:
Extensive turorials and examples package for JBoss:
https://codeload.github.com/jboss-developer/jboss-eap-quickstarts/zip/6.3.0.GA

What next:
When development environment is ready for all we change all the configuration from the example 
"org.jboss.as.quickstart.hibernate4" to our own package sources "at.fhj.swd" and remove the example.

Best practice:
- Please don't commit anything witout a log entry!
- Do not commit directories like "target", ".settings" (or any other eclipse project files). Everybod should manage its
  IDE environment by himself and can have differnt contents in it.
- Try to compile using command line of maven. Because the pom.xml file is for all of us the same we will get the same result!
- Please feel free to add content to the readme file or create wiki entries for special implementations.
  

Project description:
--------------------

TODO!

Authors: Students SWD12 FH-Joanneum Kapfernberg
Supervisors: Christian Krenn, Egon Teiniker


Requirements:
------------

- JDK 1.7 or later
- wildfly-8.1.0
To set up the build environment:
- apache-maven-3.2.3

See maven pom.xml for source dependencies! 


Compilation and Deployment:
---------------------------

To be able to build and deploy with maven please follow this steps:
- Download and start wildfly server (8.1.0), see section "Starting a Standalone Server"
  http://download.jboss.org/wildfly/8.1.0.Final/wildfly-8.1.0.Final.zip
- Download and set up maven (3.2.3), see section "Build and Deploy with maven"
  http://tweedo.com/mirror/apache/maven/maven-3/3.2.3/binaries/apache-maven-3.2.3-bin.zip
- Open browser "localhost:8080/fhj-ws2014-sd12-pse" to see your results

Import the project to eclipse using the eclipse maven plugin!
Or use maven eclipse plugin to create eclipse project files:
http://scriptime.blogspot.in/2014/08/how-to-convert-maven-project-to-eclipse.html


Starting and configure wildfly standalone Server
----------------------------

A WildFly standalone server runs a single instance.

<JBOSS_HOME>/bin/standalone.sh      (Unix / Linux)
<JBOSS_HOME>\bin\standalone.bat     (Windows)

Run the wildfly command line tool (Graphical user interface with option --gui). 

<JBOSS_HOME>/bin/jboss-cli.sh				(Unix / Linux)
<JBOSS_HOME>\bin\jboss-cli.bat			(Windows)

Run the cli files in src/main/resources/. We need to configure the security domain to use 
wildfly server security with our datasource. Open a new command line, navigate to the root 
directory of this project, and run the following command. This script adds the project domain 
to the security subsystem in the server configuration and configures authentication access.

<JBOSS_HOME>/bin/jboss-cli.sh --connect --file=src/main/resources/configure-security-domain.cli 

Available scripts:
src/main/resources/configure-security-domain.cli
src/main/resources/remove-security-domain.cli

Build and Deploy with maven
---------------------------

Description to add wildfly servers to maven for use it with the wildfly maven plugin.

"maven/.m2" directory contains a customized copy of the template file from the maven installation.
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