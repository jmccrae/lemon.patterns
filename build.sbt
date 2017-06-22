import AssemblyKeys._

assemblySettings

name := "lemon-patterns"

version := "1.17.6"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "2.1.5" % "test",
   "de.jflex" % "jflex" % "1.4.3",
   "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

compileOrder := CompileOrder.JavaThenScala

mainClass := Some("net.lemonmodel.patterns.ConvertPatterns")

test in assembly := {}
