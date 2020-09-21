import CompilerFlags._

name := "myutils"
version := "0.1"
scalaVersion := "2.13.3"

organizationName := "Earl Dombowsky"
startYear := Some(2020)
licenses += ("Apache-2.0", new URL(
  "https://www.apache.org/licenses/LICENSE-2.0.txt"
))

scalacOptions ++= compilerFlags
scalacOptions in (Compile, console) ~= filterExcludedReplOptions

// scalafmt: { align.preset = most, danglingParentheses.preset = false }
libraryDependencies += "com.github.scopt"  %% "scopt"        % "4.0.0-RC2"
libraryDependencies += "com.typesafe.akka" %% "akka-actor"   % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-slf4j"   % "2.6.1"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.6.1" % Test
libraryDependencies += "com.typesafe"       % "config"       % "1.4.0"
libraryDependencies += "io.monix"          %% "monix"        % "3.1.0"
libraryDependencies += "org.scalactic"     %% "scalactic"    % "3.1.0"
libraryDependencies += "org.scalatest"     %% "scalatest"    % "3.1.0" % Test
// scalafmt: { align.preset = some, danglingParentheses.preset = true } (back to defaults)
