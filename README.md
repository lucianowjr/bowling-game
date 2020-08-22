<h1 align="center"> Bowling Game </h1> <br>
<p align="center">
  <img alt="BowlingGame" title="BowlingGame" src="static/bowlingball.png" height="250">
</p>

<p align="center">
  An application to score a game of ten-pin bowling.
</p>

## Table of Contents
- [Introduction](#introduction)
- [Technology stack](#technology-stack)
- [Requirements](#requirements)
- [Build](#build)
- [Run](#run)
- [Sample data files](#sample-data-files)

## Introduction
This project was designed to test my knowledge of back-end web technologies, specifically in Java and assess my ability to create back-end products with attention to details, standards, and reusability.

It was implemented with the MVC pattern in mind.

Although there is no web view to be displayed, the application provides a console output view in order to show the information to the user.

## Technology stack
- [Spring Boot](https://spring.io/projects/spring-boot)
- [jUnit](https://junit.org/junit5/)

## Requirements
In order to compile this application you need to have Java 8+ and Maven 3+ installed and set up in your computer.

For a detailed walkthough on download, install and set up, please refer to the [Java](https://www.oracle.com/java/technologies/javase-downloads.html) and [Maven](https://maven.apache.org/install.html) Documentation

## Build
Clone repository and build the package

```
$ git clone https://github.com/lucianowjr/bowling-game
$ cd bowling-game
$ mvn clean package
```
... or you can donwload the [source code zip file](https://github.com/lucianowjr/bowling-game/archive/master.zip). Then, unzip the source code, open a new command line window, navigate to the unzipped folder `bowling-game` and execute the maven command `mvn clean package`.

## Run
Once the application has been compiled successfully, you can navigate on a command line window to the target folder `bowling-game/target` and run it by passing the input file path as argument of the folowing command: `java -jar bowling-game-0.0.1-SNAPSHOT.jar /file-folder/sample.txt`.

## Sample data files
Sample data files are available to test the application in the test resources folder: `bowling-game/src/test/resources`.

You may use these files to test the end-to-end functionality.

Correct file format:
```
Jeff	10
John	3
John	7
Jeff	7
Jeff	3
...
```
