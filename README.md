What is RHLP?
=============

Daily menu offerings for restaurants near the Brno Red Hat office.

You can find a staged instance at http://rhlp-oskutka.8a09.starter-us-east-2.openshiftapps.com/

How to run it locally (if you are experienced with web development)
=====================
To run rhlp locally, execute `mvn package`. That will create `rhlp-1.0.war` file, which you can deploy onto a server (E.g. you can copy it into WildFly's `standalone/deployments` directory). For Zomato restaurants you need their api-key, which you can get at `https://developers.zomato.com`. Set it as a system property named ZOMATO_API_KEY.


How to run it locally (if you're new to the java world)
=====================

If you have no experience with Java web application development, this is a quick guide for you. You'll install Developer Studio along with Enterprise Application Server and run RHLP application there. 

Prerequisites:
--------------

Have a JDK installed 

Install Red Hat JBoss Developer Studio
-------------------

First, download Red Hat JBoss Developer Studio with EAP (from [Red Hat Brno mirror (VPN)](http://download.eng.brq.redhat.com/released/jbdevstudio/10.0.0/devstudio-10.0.0.GA-installer-eap.jar) or [JBoss.org](https://www.jboss.org/download-manager/file/devstudio-10.0.0.GA-installer-eap.jar))

Now run the installer

    java -jar devstudio-10.0.0.GA-installer-eap.jar

Make sure Red Hat JBoss Enterprise Application Platform is also installed (should be by default).

Get the code
------

Import the RHLP project into Developer Studio:

    File -> Import -> Git -> Projects from Git (with smart import)

Select `Clone URI`

Type `git://github.com/oskutka/rhlp` into the URI field

You can leave default options in the rest of the wizard.

Run it
------

To run the application on server, right click rhlp project -> Run As -> Run on Server

Your project should be running at `http://localhost:8080/rhlp-1.0/`

Note that the Developer Studio's internal browser probably wrongly points to `http://localhost:8080/rhlp/`.
