Scanner sc = new Scanner(System.in)
boolean finished = false
int total = 0
while (!finished) {
    int num
    if (sc.hasNext()) {
        num = sc.nextInt()
        total = total + num
        println "Total is " + total
    }
    else
        finished = true;
}

