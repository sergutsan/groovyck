Scanner sc = new Scanner(System.in)
int total = 0
boolean finished = false

while (!finished) {
    if (sc.hasNext()) {
        int num
        num = sc.nextInt()
        total = total + num
    }
    else
        finished = true
}
println "Total is " + total
