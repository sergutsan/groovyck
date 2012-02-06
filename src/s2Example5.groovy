int num1, num2
String s
print "Please key in a number: "
s = System.console().readLine()
num1 = Integer.parseInt(s)
print "And another: "
s = System.console().readLine()
num2 = Integer.parseInt(s)
if (num1 == num2) {
    println "They are the same"
}
