Version history:
----------------

v1.3:
Add Selenium UI Tests. Prepare the project for Jenkins CI, alle build, test and report goals must be 
available by command line maven tool. Some final refactoring.

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

Install the package into the local repository, which can be used as a dependency in other projects locally.

mvn install

To enable selenium test the first deploy must skip the test to have a deployed application on the server
when tests start. By default they are disable to enable them do:

mvn install -DskipTests=false 

war pugin
---------

http://maven.apache.org/plugins/maven-war-plugin/

war:war is the default goal invoked during the package phase for projects with a packaging type of war. It builds a WAR file.
war:exploded is generally used to speed up testing during the developement phase by creating an exploded webapp in a specified directory.
war:inplace another variation of war:explode where the webapp is instead generated in the web application source directory, which is src/main/webapp by default.
war:manifest generates a manifest for this webapp. The manifest file is created in the web application source directory.

wildfly pugin
-------------

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

Maven Site Plugin
-----------------

http://maven.apache.org/plugins/maven-site-plugin/

site:site is used generate a site for a single project. Note that links between module sites in a multi module build will not work, since local build directory structure doesn't match deployed site.

Maven Project Info Reports Plugin
---------------------------------

http://maven.apache.org/plugins/maven-project-info-reports-plugin/

Jacoco code coverage plugin
---------------------------

http://www.eclemma.org/jacoco/trunk/doc/maven.html

jacoco:report generate code coverage report

Findbugs plugin
---------------

http://mojo.codehaus.org/findbugs-maven-plugin/plugin-info.html

findbugs:check fail the build if there were any FindBugs violations in the source code. An XML report is put out by default in the target directory with the errors.
findbugs:findbugs	generates a FindBugs Report when the site plugin is run. The HTML report is generated for site commands only.
findbugs:gui launch the Findbugs GUI. It will use all the parameters in the POM file.

Maven 2 JavaNCSS Plugin
-----------------------

http://mojo.codehaus.org/javancss-maven-plugin/index.html

javancss:report Generates a report of quantity and complexity metric on your code.
javancss:check Check if your source code has a CCN or NCSS value too high, fails the build if so.

Apache Maven PMD Plugin
-----------------------

http://maven.apache.org/plugins/maven-pmd-plugin/

pmd:pmd creates a PMD site report based on the rulesets and configuration set in the plugin. It can also generate a pmd output file aside from the site report in any of the following formats: xml, csv or txt.
pmd:cpd generates a report for PMD's Copy/Paste Detector (CPD) tool. It can also generate a cpd results file in any of these formats: xml, csv or txt.

Javadoc plugin
--------------

http://maven.apache.org/plugins/maven-javadoc-plugin/

javadoc:javadoc generates the Javadoc files for the project. It executes the standard Javadoc tool and supports the parameters used by the tool.
javadoc:test-javadoc generates the test Javadoc files for the project. It executes the standard Javadoc tool and supports the parameters used by the tool.
javadoc:jar creates an archive file of the generated Javadocs. It is used during the release process to create the Javadoc artifact for the project's release. This artifact is uploaded to the remote repository along with the project's compiled binary and source archive.
javadoc:test-jar creates an archive file of the generated Test Javadocs.