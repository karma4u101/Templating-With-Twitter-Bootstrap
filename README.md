Templating With Twitter Bootstrap
=================================
 
The intention of this project is to serve as a starter template project for Lift'ers that wish to get a quick start using Lift with 
the front-end toolkit [Twitter Bootstrap](http://twitter.github.com/bootstrap/). 

The [Twitter Bootstrap](http://twitter.github.com/bootstrap/) toolkit is provided as a **Lift module** by the [FoBo - Lift Module](https://github.com/karma4u101/FoBo) making maintenance, upgrade and fall-back quick and easy (typically a one liner change in lift boot class of the BootstrapXXX option and a version bump of Fobo module in build.sbt). 
  
One of Lift's many great features is it's clean and flexible templating system. For convenience and to give some styling 
to Lift and it's templates Lift comes by default, at this writing, bundled with [blueprint](http://blueprintcss.org/) as hooked 
up CSS framework, here you will see blueprint replaced by another very popular and feature rich toolkit. 

Contributions
-------------
Improvements, contributions and suggestions are welcome! Please see the [Contribution Document](https://github.com/karma4u101/Templating-With-Twitter-Bootstrap/blob/master/CONTRIBUTING.md). You can also leave a issue report or drop a question/suggestion to my priv. e-mail or on [Lift's mailing list](http://groups.google.com/group/liftweb/) 


best regards Peter Petersson 

Update log
----------

**Latest Module Version:** 
- **2012-12-29** -- 0.4.6-SNAPSHOT

**Latest Updates:**
- **2012-12-29** -- 0.4.6-SNAPSHOT updated build to dep. on FoBo v0.7.7 and using FoBo.JQuery182

Quick Start
-----------
The only prerequisites for running this Lift example is that you have Git and Java installed and configured on the target computer.
You don't need to use it but the project also includes Eclipse, IDEA and ENSIME plug-in for browsing and following/working with the code, see the [Scala IDE Support] section bellow.   
 

1) Clone this project

	git clone git@github.com:karma4u101/Templating-With-Twitter-Bootstrap.git
	cd Templating-With-Twitter-Bootstrap

2) Update & Run Jetty

The following commands will update and fetch the projects dependancys then start the jetty server and load the lift application. 
There is also a sbt.bat for windows users so if you are on a windows machine just substitute the ./sbt command with sbt.bat.

	./sbt update ~container:start

3) Launch Your Browser

Launch your favorite browser and type in the following address to bring up the application.
	
	http://localhost:8080/

###Database backend

For demonstration purpose and easy setup this example uses a in memory database. 

Scala IDE Support 
-----------------

###Eclipse 

Sbteclipse provides SBT command to create Eclipse project files

1) Usage

	project$ ./sbt
	> eclipse 

2) In eclipse do: 

	File ==> Import...
	Select General ==> Existing Project into Workspace 
	Use "Brows" to look up the project root ....

###Others

There is also support for IDEA and ENSIME ... 
**Note:** Can someone with insite in how to use thouse modules please contribute a updat to this document.

User powered basic example 
==========================
**(*)** This is a _unofficial_ Lift user powered basic Lift assembly which means it is a work based on the 
sound foundation of Lift and done by a developer who uses Lift for development ;), sharing it with others.
