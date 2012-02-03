println "Given a series of words, each on a separate line,"
println "this program finds the length of the longest word."
println "Please enter several sentences, one per line. "
println "Finish with a blank line."
max = 0
String str = "."
while (str.length() > 0) {
  str = System.console().readLine()
  if (str.length() > max) {
    max = str.length()
  }
}
if (max == 0)
    println "There were no words."
else
    println "The longest sentence was " + max + " characters long."

