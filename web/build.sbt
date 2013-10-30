seq(webSettings :_*)

name := "lemon-patterns-web"

version := "1.12.9"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "1.8" % "test",
   "de.jflex" % "jflex" % "1.4.3",
   "javax.servlet" % "servlet-api" % "2.5" % "provided",
   "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

unmanagedBase := new File("../target")
