String s
int num1, num2, num3, num4

println "Please key in four numbers: "
print "> "
s = System.console().readLine()
num1 = Integer.parseInt(s)
print "> "
s = System.console().readLine()
num2 = Integer.parseInt(s)
print "> "
s = System.console().readLine()
num3 = Integer.parseInt(s)
print "> "
s = System.console().readLine()
num4 = Integer.parseInt(s)
int total = num1 + num2 + num3 + num4
println "Total: " + total