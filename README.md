Templating With Twitter Bootstrap
=================================
 
The intention of this project is to serve as a starter template project for Lift'ers that wish to get a quick start using Lift with 
the front-end toolkit [Twitter Bootstrap](http://twitter.github.com/bootstrap/). 

The [Twitter Bootstrap](http://twitter.github.com/bootstrap/) toolkit is provided as a **Lift module** by the [FoBo - Lift Module](https://github.com/karma4u101/FoBo) making maintenance, upgrade and fall-back quick and easy (typically a one liner change in lift boot class of the BootstrapXXX option). 
  
One of Lift's many great features is it's clean and flexible templating system. For convenience and to give some styling 
to Lift and it's templates Lift comes by default, at this writing, bundled with [blueprint](http://blueprintcss.org/) as hooked 
up CSS framework, here you will see blueprint replaced by another very popular and feature rich toolkit. 

Contributions
------
Improvements, contributions and suggestions are welcome! Please see the [Contribution Document](https://github.com/karma4u101/Templating-With-Twitter-Bootstrap/blob/master/CONTRIBUTING.md). You can also leave a issue report or drop a question/suggestion to my priv. e-mail or on [Lift's mailing list](http://groups.google.com/group/liftweb/) 


best regards Peter Petersson 

Quick Start
-----------
The only prerequisites for running this Lift example is that you have Git and Java installed and configured on the target computer.
You don't need to use it but the project also includes a Eclipse plug-in for browsing and following/working with the code, see the Scala IDE section.   

1) If you wish you can fetch the FoBo module and build it yourself but as of FoBo >= v0.5.0 it will be fetched for you.  

If you fetched the FoBo module follow the instructions at [FoBo - Lift Front-End Toolkit Module](https://github.com/karma4u101/FoBo). Basically this step involves a one liner command for local publish of the FoBo module.  

2) Get the examples

	git clone git@github.com:karma4u101/Templating-With-Twitter-Bootstrap.git
	cd Templating-With-Twitter-Bootstrap

3) Update & Run Jetty

There is also a sbt.bat for windows users.

	./sbt update ~container:start

4) Launch Your Browser
	
	http://localhost:8080/

###Database backend

For demonstration purpose and easy setup this example uses a in memory database. 

Scala IDE for Eclipse
---------------------
Sbteclipse provides SBT command to create Eclipse project files

1) Usage

	project$ ./sbt
	> eclipse 

2) In eclipse do: 

	File ==> Import...
	Select General ==> Existing Project into Workspace 
	Use "Brows" to look up the project root ....

User powered basic example 
==========================
**(*)** This is a _unofficial_ Lift user powered basic assembly example which means it is a work based on the 
sound foundation of Lift and done by a developer who uses Lift for development ;), sharing it with others.
