int total = 0
boolean finished = false
while (!finished) {
    println "Please enter a number (end with 0):"
    String s = System.console().readLine()
    int num = Integer.parseInt(s)
    if (num != 0) {
        total = total + num
        println "Total is " + total
    } else {
        finished = true
    }
}
