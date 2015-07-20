import AssemblyKeys._

assemblySettings

name := "lemon-patterns"

version := "1.12.9"

scalaVersion := "2.10.2"

sbtVersion := "0.13.1"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "1.9.2" % "test",
   "de.jflex" % "jflex" % "1.4.3"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

compileOrder := CompileOrder.JavaThenScala

mainClass := Some("net.lemonmodel.patterns.ConvertPatterns")

test in assembly := {}
