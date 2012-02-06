int examMark
String s
print "Please key in your exam mark: "
s = System.console().readLine()
examMark = Integer.parseInt(s)
if (examMark >= 40) {
    println "A satisfactory result!"
}
