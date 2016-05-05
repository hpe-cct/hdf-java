name := "hdf-java"

description := "HDF-Java library and associated native code loader."

organizationName := "Hewlett Packard Labs"

organizationHomepage := Some(url("http://www.labs.hpe.com"))

licenses ++= Seq(
  ("BSD", url("https://www.hdfgroup.org/ftp/HDF5/hdf-java/current/src/unpacked/COPYING")),
  ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))
)

version := "2.11.0-hpe.1"

organization := "com.hpe.cct"

scalaVersion := "2.11.7"

crossScalaVersions := Seq(scalaVersion.value, "2.10.6")

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.12"

bintrayRepository := "maven"

bintrayOrganization := Some("cogexmachina")