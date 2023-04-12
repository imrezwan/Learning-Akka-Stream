name := "Learning-Akka-Stream"

version := "0.1"

scalaVersion := "2.13.10"

val AkkaVersion = "2.8.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % AkkaVersion % Test
)
