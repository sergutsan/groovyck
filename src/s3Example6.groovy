int m = 0
boolean finished = false
while (!finished) {
    print "Enter another number (0 to finish): "
    String s = System.console().readLine()
    int num = Integer.parseInt(s)    
    if (num != 0) {
        if (num > m) {
            m = num
	}
    } else {
        finished = true
    }
}
println m
