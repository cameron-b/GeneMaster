Takes in results file that follows format:

    [Bot#] [Wins] [Turns]
    ...
    (should be sorted by bot# in ascending order)

scores each bot based on total wins/turns and sorts by score
* currently, score is: wins + (100/turns) * 100
* turns is: turns(won) - turns(lost)
* can be changed under the ScoreBot method in the Bot class
* turns is calculated in ResultsParser but could potentially be handled in Bot instead

GeneController currently does nothing
* later

to run the example

    java main.RunMaster exampleResults.txt exampleBot
                        | results file     | format for bot text files (e.g. exampleBot1.txt)

