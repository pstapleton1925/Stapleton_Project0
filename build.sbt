ThisBuild / scalaVersion := "2.13.6"
ThisBuild / organization := "pstapleton1925"


lazy val main = (project in file("."))
  .settings(
    name := "Main",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.7" % Test,
  )