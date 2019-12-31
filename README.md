
# Ten-pin bowling challenge

# Overview
Develop a command-line application to score a game of ten-pin bowling
[Wikipedia](https://en.wikipedia.org/wiki/Ten-pin_bowling#Rules_of_play).

## To know

### Gutter Game
When the bowler never hits a pin (20 zero scores)
```sh
Frame           1               2               3               4               5               6               7               8               9               10
Jeff
Pinfalls        0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       0       
Score           0               0               0               0               0               0               0               0               0               0
```
### Perfect Game
when the player rolls 12 strikes (10 regular strikes and 2 strikes for the bonus in the 10th frame). The Perfect Game scores 300 points.
```
Frame           1               2               3               4               5               6               7               8               9               10
Jeff
Pinfalls                X               X               X               X               X               X               X               X               X       X       X       X
Score           30              60              90              120             150             180             210             240             270             300
```


## Building

### General Requirements

You need the following software to build bowling-challenge:

* Java 8(JDK) or later.
* Get code from github repo `git clone https://github.com/josehmaza/bowling-challenge.git` 

## Compiling bowling-challenge 
When you are inside the repo then:
* Run `./gradlew clean build` to generate bowling-challenge-1.0.0-RELEASE.jar, also this command run the test an integrationTest tasks.
* Compiled library are placed in build/libs .

### Run jar

```sh
java -jar bowling-challenge-1.0.0-RELEASE.jar
```
Output will be according to original code that has a file text as input the same scores of challenge  [sample-input](src/main/resources/sample-input.txt)
```sh
Frame           1               2               3               4               5               6               7               8               9               10
Jeff
Pinfalls                X       7       /       9       0               X       0       8       8       /       F       6               X               X       X       8       1
Score           20              39              48              66              74              84              90              120             148             167
John
Pinfalls        3       /       6       3               X       8       1               X               X       9       0       7       /       4       4       X       9       0
Score           16              25              44              53              82              101             110             124             132             151
```

If you want to build with zero or pefect score you will change the name of file(`List<Result> results = textInputService.getResults("sample-input.txt");`) in  [Main.java](src/main/java/com/jobsity/challenge/view/Main.java) for your respectives file text(as input). This files are for zero and perfect score. Finally you can build an execute executable jar.

```sh
java -jar bowling-challenge.jar
```


## Testing bowling-challenge
### Tests
* Run `./gradlew test` to run tests. Tests reports will be in `build/reports/tests/test//index.html`

The test to run will be: 
* When is last frame 3 rolls are allowed
* When a bowler has 2 rolls and its knocked down pins sum is more than 10. expected BreakRuleException
* When incorrect format line throw BadInputException.
* When score is not betweeen 0 to 10 throw BadInputException	
* When score is a string, it must be F

### Integration tests
* Run `./gradlew integrationTest` to run integration tests. Integration Tests reports will be in `build/reports/tests/integrationTest/index.html`
* The test integration files(inputs) are located in [integration test resources](src/integrationTest/resources/)
The test to run will be: 
* Test for 1 player with zero score: Expected 0
* Test for 1 player with perfect score: Expected 300	
* Test for sample input: Expected score 167 for Jeff and 151 score for John. Also will not throw exception	
* When last frame has less than 3 rolls throw BreakRuleBowlingException

## Author
[Hernan Maza S.](mailto:josehmaza@gmail.com)
## Note
All bonus tasks were developed. 
