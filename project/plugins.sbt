//libraryDependencies <+= sbtVersion(v => v match {
//  case "0.11.0" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.0-0.2.8"
//  case "0.11.1" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.1-0.2.10"
//  case "0.11.2" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.2-0.2.11"
//  case "0.11.3" => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.3-0.2.11.1"
//  case x if x.startsWith("0.12") => "com.github.siasia" %% "xsbt-web-plugin" % "0.12.0-0.2.11.1"
//})

addSbtPlugin("com.earldouglas" % "xsbt-web-plugin" % "0.7.0")

//Uncoment this line to enable the sbt idea plugin
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.1")

//Uncoment this line to enable the sbt eclipse plugin
//sbt 0.12.x
//addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.1.2")
//sbt 0.13.x
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

//Uncoment this line to enable the sbt ensime plugin
addSbtPlugin("org.ensime" % "ensime-sbt-cmd" % "0.1.2")
