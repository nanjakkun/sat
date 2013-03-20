name := "SAT"

version := "1.0"

scalaVersion := "2.10.0"

scalacOptions ++= Seq("-feature")

libraryDependencies += "com.jsuereth" % "scala-arm_2.10" % "1.3"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.1"


unmanagedSourceDirectories in Compile <<= Seq(scalaSource in Compile).join

unmanagedSourceDirectories in Test <<= Seq(scalaSource in Test).join



