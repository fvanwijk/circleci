logLevel := Level.Info

name := "backend"

scalaVersion := "2.12.4"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Releases"   at "https://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"         % "10.0.11",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.11" % "test",
  "org.scalatest"     %% "scalatest"         % "3.0.4"   % "test"
)

scalacOptions := Seq("-unchecked","-deprecation", "-feature")
