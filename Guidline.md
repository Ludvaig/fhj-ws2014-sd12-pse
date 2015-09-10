**Guidline:**

---


**Note:**
To test the environment for all envolved developers we add a jboss example project source to it. This makes it easy to ensure it runs on all developer clients when the deploy it. The example package is "org.jboss.as.quickstart.hibernate4". Please don't add real project classes to it, we work only in the "at.fhj.swd" package and the "hibernate4" example is going to be deleted in a further development step. Keep in mind this is only an example for test your envirnoment and give you a short access tutorial.

**Tutorials:**
Extensive turorials and examples package for JBoss:
https://codeload.github.com/jboss-developer/jboss-eap-quickstarts/zip/6.3.0.GA

**What next:**
When development environment is ready for all we change all the configuration from the example "org.jboss.as.quickstart.hibernate4" to our own package sources "at.fhj.swd" and remove the example.

**Best practice:**
  * Please don't commit anything witout a log entry!
  * Do not commit directories like "target", "bin", ".settings" or any other eclipse project files. Everybod should manage its IDE environment by himself and can have differnt contents in it.
  * Please feel free to add content to the readme.txt file or create wiki entries for special implementations.
  * Store important informations and documents in the "doc" directory!
  * Source code documentations (JavaDoc) in English.
  * Authors are responsible for their classes.
  * Do not change code formating of an existing file for proper SVN merge views.
  * No task definitions in the issues section.