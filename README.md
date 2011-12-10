SEO Friendly Menu Params
========================

This Scala/Lift basic assembly example project demonstrates Lift's menu param:s (menu.param/menu.params) usage combined with "SEO" (search engine optimization) -friendly url:s.
There are two examples, the "ParamTest" menu entry point contains a "bare-bone" menu.param/menu.params usage example wile the "Countries" menu entry point also utilizes "Search Engine Optimization" -friendly url:s.
To make things easy to follow the examples has extensive logging. 

This _unofficial*_ example is intended for those of you who need some compact and concise running and mostly ;) simple to follow example of a particular 
feature or concept in Lift.

After many years of Java EE development I started look at Scala/Lift in May 2011 so there may be some none "best practice" stuff in there 
and if you find something you think could be done in a more Scala/Lift fashion please let me know.

Improvements and suggestions are welcome!

best regards Peter Petersson 

Quick Start
-----------
The only prerequisites for running this Lift example is that you have Git and Java installed and configured on the target computer.
You don't need to use it but the project also includes a Eclipse plug-in for browsing and following/working with the code, see the Scala IDE section.   

1) Get the examples

	git clone git@github.com:karma4u101/SEO-Friendly-Menu-Params.git
	cd SEO-Friendly-Menu-Params

2) Update & Run Jetty

There is also a sbt.bat for windows users.

	./sbt update ~container:start

3) Launch Your Browser
	
	http://localhost:8080/

###Database backend

For demonstration purpose and easy setup this example uses a in memory database. 

Scala IDE for Eclipse
---------------------
Sbteclipse provides SBT command to create Eclipse project files

1) Usage

	project$ ./sbt
	> eclipse create-src

2) In eclipse do: 

	File ==> Import...
	Select General ==> Existing Project into Workspace 
	Use "Brows" to look up the project root ....

User powered basic example 
==========================
**(*)** This is a _unofficial_ Lift user powered basic assembly example which means it is a work based on the 
sound foundation of Lift and done by a developer who uses Lift for development ;), sharing it with others.