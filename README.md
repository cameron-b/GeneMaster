Takes in results file that follows format:

    [Bot#] [Wins] [Turns]
    ...
    (should be sorted by bot# in ascending order)

Takes in bot files that follow format:

    [preferred starting regions]
    [test parameter] (for example)
    ... (more as needed)

scores each bot based on total wins/turns and sorts by score
* currently, score is: wins + (100/turns) * 100
* turns is: turns(won) - turns(lost)
* can be changed under the ScoreBot method in the Bot class
* turns is calculated in ResultsParser but could potentially be handled in Bot instead

GeneController takes the top 50% of bots and reproduces them. currently does this by randomly changing parameters and storing the changes in the bots that performed poorly.
to add a new parameter, do the following
* create a boolean for it under the RunMaster constructor
* add it to the arguments of reproduceBots under GeneController
* create a new method for swapping out values under GeneController
* add the method to the reproduceBots loop

currently a second parameter, testParameter, has been added to show how only specific params can be changed if needed

to run the example

                                                      | parameter change enable/disable
    java main.RunMaster exampleResults.txt exampleBot 1 0 ...
                        | results file     | format for bot text files (e.g. exampleBot1.txt)

