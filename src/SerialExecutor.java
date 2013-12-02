import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 */
public class MyExecutor implements Executor{

    private final Queue<WrappedTask> taskQueue = new ArrayDeque<WrappedTask>();
    private final Executor innerExecutor = new ForkJoinPool();
    private Runnable active;
    @Override
	  public synchronized void execute(final Runnable r) {
		WrappedTask newTask = new WrappedTask(this, r);
		taskQueue.offer(newTask);
		if (active == null) {
		    scheduleNext();
		}
	  }
    protected synchronized void scheduleNext() {
	  active = taskQueue.poll();
	  if (active  != null) {
		innerExecutor.execute(active);
	  }
    }
}

class WrappedTask implements Runnable {
    private Runnable innerTask = null;
    private MyExecutor myExecutor = null;
    public WrappedTask(MyExecutor e, Runnable r) {
	  this.innerTask = r;
	  this.myExecutor = e;
    }
    public void run() {
	  try {
		innerTask.run();
	  } finally {
		this.myExecutor.scheduleNext();
	  }
    }
}