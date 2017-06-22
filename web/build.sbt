seq(webSettings :_*)

name := "lemon-patterns-web"

version := "1.17.6"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
   "org.scalatest" %% "scalatest" % "2.1.5" % "test",
   "de.jflex" % "jflex" % "1.4.3",
   "org.scala-lang.modules" %% "scala-xml" % "1.0.6",
   "javax.servlet" % "servlet-api" % "2.5" % "provided",
   "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

unmanagedBase := new File("../target")
