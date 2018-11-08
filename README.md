takes in results file that follows format:
    [Bot#] [Wins] [Turns]
    ...

results file should be sorted by bot# in ascending order

scores each bot based on total wins/turns and sorts by score
    - currently, score is: wins + (100/turns) * 100
        - can be changed under the ScoreBot method in the Bot class
    - should eventually change how turns get added (i.e. if a bot loses, maybe - the turns instead)
        - handled in the ResultsParser class

GeneController currently does nothing
    - later

to run the example
    java main.RunMaster exampleResults.txt exampleBot
                        | results file     | format for bot text files (e.g. exampleBot1.txt)

