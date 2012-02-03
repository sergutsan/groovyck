// Given a series of words, each on a separate line,
// this program finds the length of the longest word.

String str
int max = 0
Scanner sc = new Scanner(System.in)

while (sc.hasNext()) {
    str = sc.next()
    if (str.length() > max)
        max = str.length()
}

if (max == 0)
    println "There were no words."
else
    println "The longest word was " + max + " characters long."
