import java.io.Serializable;

public class TalkingBox implements Serializable {

    private String msg = null;

    public TalkingBox(String msg) {
	  this.msg = msg;
    }

    public String talk() {
	  return msg;
    }
}
