Scanner sc = new Scanner(System.in)
boolean finished = false
int total
while (!finished) {
    total = 0
    int num
    if (sc.hasNext()) {
        num = sc.nextInt()
        total = total + num
    }
    else
        finished = true
}
println "Total is " + total