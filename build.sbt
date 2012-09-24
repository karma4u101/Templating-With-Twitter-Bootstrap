
organization := "Lift"

name := "Templating With Twitter Bootstrap"

version := "0.4.3-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

// If using JRebel
//scanDirectories in Compile := Nil

logLevel := Level.Info

EclipseKeys.withSource := true

transitiveClassifiers := Seq("sources")

resolvers ++= Seq(
  "Sonatype snapshots"             at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype releases"              at "http://oss.sonatype.org/content/repositories/releases",
  "Java.net Maven2 Repository"     at "http://download.java.net/maven/2/",
  "Media4u101 Repository"          at "http://www.media4u101.se:8081/nexus/content/repositories/releases/",
  "Media4u101 Snapshot Repository" at "http://www.media4u101.se:8081/nexus/content/repositories/snapshots/"
)

libraryDependencies ++= {
  val liftVersion = "2.5-M1" // Put the current/latest lift version here
  Seq(
    "net.liftweb"      %% "lift-webkit"          % liftVersion          % "compile->default" withSources(),
    "net.liftweb"      %% "lift-mapper"          % liftVersion          % "compile->default",
    "net.liftweb"      %% "lift-squeryl-record"  % liftVersion          % "compile->default",
    "net.liftweb"      %% "lift-wizard"          % liftVersion          % "compile->default",
    "net.liftweb"      %% "lift-testkit"         % liftVersion          % "compile->default",
    "net.liftmodules"  %% "lift-jquery-module"   % (liftVersion+"-1.0") % "compile->default",
    "net.liftmodules"  %% "fobo"                 % (liftVersion+"-0.7.2-SNAPSHOT") withSources(),
    "com.h2database"    % "h2"                  % "1.3.167"
    )
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  "org.eclipse.jetty"        % "jetty-webapp"   % "8.0.3.v20111011"  % "container",
  "com.jolbox"               % "bonecp"         % "0.7.1.RELEASE"    % "compile->default",
  "javax.servlet"            % "servlet-api"    % "2.5"              % "provided->default",
  "org.slf4j"                % "slf4j-log4j12"  % "1.6.1"            % "compile->default"
)


