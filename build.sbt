name := "home_security_system"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.12.3"

/*
libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.h2database" % "h2" % "1.4.196"
*/


libraryDependencies ++= {

  Seq(
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,

    "com.pi4j" % "pi4j-core" % "1.1",
    "com.pi4j" % "pi4j-core" % "1.1",
    "com.pi4j" % "pi4j-gpio-extension" % "1.1",
    "com.pi4j" % "pi4j-device" % "1.1",
    guice
  )
}