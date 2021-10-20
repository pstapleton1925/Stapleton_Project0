val scalaTest = "org.scalatest" %% "scalatest" % "3.2.7"

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "pstapleton1925"

val playJson  = "com.typesafe.play" %% "play-json" % "2.9.2"

lazy val main = (project in file("."))
  .settings(
    name := "Main",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.8.0",
  )