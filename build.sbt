name := "myutils"

version := "0.1"

scalaVersion := "2.13.1"

organizationName := "Earl Dombowsky"
startYear := Some(2020)
licenses += ("Apache-2.0", new URL("https://www.apache.org/licenses/LICENSE-2.0.txt"))

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  //"-Xfatal-warnings",
  "-Xlint",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-dead-code",
  "-Ywarn-unused"
)

libraryDependencies += "com.github.scopt" % "scopt_2.11" % "4.0.0-RC2"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.1" % Test
libraryDependencies += "com.typesafe" % "config" % "1.4.0"
libraryDependencies += "io.monix" %% "monix" % "3.1.0"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
