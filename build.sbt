
organization := "Lift"

name := "Templating With Twitter Bootstrap"

version := "0.4.2-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

// If using JRebel
//scanDirectories in Compile := Nil

logLevel := Level.Info

EclipseKeys.withSource := true

transitiveClassifiers := Seq("sources")

resolvers ++= Seq(
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Scala Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Scala" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Media4u101 Repository" at "http://www.media4u101.se:8081/nexus/content/repositories/releases/",
  "Media4u101 SNAPSHOT Repository" at "http://www.media4u101.se:8081/nexus/content/repositories/snapshots/"  
)

libraryDependencies ++= {
  val liftVersion = "2.4" // Put the current/latest lift version here
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default" withSources(),
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-squeryl-record" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-testkit" % liftVersion % "compile->default",
    "net.liftmodules" %% "lift-jquery-module" % (liftVersion+"-1.0") % "compile->default",
    "net.liftmodules" %% "fobo" % (liftVersion+"-0.5.4-SNAPSHOT") withSources()
    )
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  "org.hoisted" %% "hoisted" % "0.1-SNAPSHOT" ,
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.3.v20111011" % "container",
  //"org.mortbay.jetty" % "jetty" % "6.1.22" % "container", // For Jetty 7
  "com.jolbox" % "bonecp" % "0.7.1.RELEASE" % "compile->default",
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "org.slf4j" % "slf4j-log4j12" % "1.6.1" % "compile->default", // Logging
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "org.scala-tools.testing" %% "specs" % "1.6.9" % "test",
  "org.specs2" %% "specs2" % "1.6.1" % "test"
)


