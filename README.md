# BSC-ideas_TestBackend
<b>Payment Tracker</b></br>
Write a program that keeps a record of payments. Each payment includes a currency and an amount. The program should output a 
list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally
also be loaded from a file when starting up.

<b>Sample input:</b></br>
· USD 1000</br>
· HKD 100</br>
· USD -100</br>
· RMB 2000</br>
· HKD 200</br>

<b>Sample output:</b></br>
· USD 900</br>
· RMB 2000</br>
· HKD 300</br>

<b>Detailed requirements</b></br>
When your Java program is run, a filename can be optionally specified. The format of the file will be one or more lines with Currency Code
Amount like in the Sample Input above, where the currency may be any uppercase 3 letter code, such as USD, HKD, RMB, NZD, GBP etc. The user
can then enter more lines into the console by typing a currency and amount and pressing enter. Once per minute, the output showing the net 
amounts of each currency should be displayed. If the net amount is 0, that currency should not be displayed. When the user types "quit", 
the program should exit.</br>
You may need to make some assumptions. For example, if the user enters invalid input, you can choose to display an error message or quit 
the program. For each assumption you make, write it down in a readme.txt and include it when you submit the project.

<b>Things you may consider using:</b></br>
· Unit testing</br>
· Threadsafe code</br>
· Programming patterns</br>

Please put your code in a bitbucket/github repository. We should be able to build and run your program easily (you may wish to use Maven,
Ant, etc). Include instructions on how to run your program.</br>

<b>Optional bonus question</b></br>
Allow each currency to have the exchange rate compared to USD configured. When you display the output, write the USD equivalent amount 
next to it, for example:</br>
· USD 900</br>
· RMB 2000 (USD 314.60)</br>
· HKD 300 (USD 38.62)</br>
