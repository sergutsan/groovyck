public class KidPerson implements Person {
    private int position;

    /**
     * Move a distance in a straight line, given in meters
     */
    void move(int distance) {
        crawl(distance);
    }

    /**
     * Say something
     */
    void say(String message) {
	  String finalMsg = getUnderstoodWords(message);
	  System.out.println(finalMsg);
    }

    private void crawl(int distance) {
        for (int i = 0; i < distance; i++) {
		position++;
		waitALot();
	  }		
    }

    private String getUnderstoodWords(String text) {
	  String result = "";
	  String[] words = divideIntoWords(text);
	  for (int i = 0; i < words.length; i++) {
		if (this.isKnown(word)) {
		    result = result + word; // if not, ignore it
		}
	  }
	  return result;
    }
    // ... other methods, including constructors, come here...
}