public class AdultPerson implements Person {
    private int situation;
    private int energy;
    private Leg leftLeg;
    private Leg rightLeg;
    
    /**
     * Move a distance in a straight line, given in meters
     */
    public void move(int distance) {
	  if (rightLeg.isHealthy() && leftLeg.isHealthy()) {
		run(distance);
	  } else {
		walk(distance);
	  }
    }

    /**
     * Say something
     */
    public void say(String message) {
	  System.out.println(message);
    }

    private void run(int distance) {
	  situation = situation + distance;
	  energy--;
    }

    private void walk(int distance) {
        for (int i = 0; i < distance; i++) {
		situation++;
	  }		
    }
    // ...other methods, including constructors, come here...
}