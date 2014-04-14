import ScalaxbKeys._

name := "aw-scalaxb-test"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.0"
)

// settings
 
scalaxbSettings
 
sourceGenerators in Compile <+= scalaxb in Compile

packageName in scalaxb in Compile := "aw"