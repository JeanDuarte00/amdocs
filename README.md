# Code Challenge from Amdocs

## To run this application

### For this version you can't pass a new graph, it uses the one that has been provided.
<h6>Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7</h6>

First of all:
- Open the folder where this project is located in your machine.

First Step:
- Build it by running: <b>mvn compile package</b>

Second Step:
- Run application: <b>java -jar target/amdocs-1.0-SNAPSHOT.jar [params] </b>
	* Params must be two arguments separeted by white space
	* You can use [distance(param:A-B-C), trips(param:AC3), shortest(param:AC)]
  * For example: DISTANCE A-B-C
