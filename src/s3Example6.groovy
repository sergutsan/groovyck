Scanner sc = new Scanner(System.in)
boolean finished = false
int m = 0
while (!finished) {
    int num
    if (sc.hasNext()) {
        num = sc.nextInt()
        if (num > m)
            m = num
    } else
        finished = true
}
println m