name := "lemon-patterns"

version := "1.12.9"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "1.8" % "test",
   "de.jflex" % "jflex" % "1.4.3"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

compileOrder := CompileOrder.JavaThenScala

