int total
boolean finished = false
while (!finished) {
    total = 0
    println "Please enter a number (end with 0):"
    String s = System.console().readLine()
    int num = Integer.parseInt(s)
    if (num != 0) {
        total = total + num
    } else {
        finished = true
    }
}
println "Total is " + total
