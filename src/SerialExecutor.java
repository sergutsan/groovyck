import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * An alternative implementation of the SerialExecutor class found on
 * the documentation of Executor. 
 * 
 * This implementation does not use anonymous classes and includes
 * comments. 
 */
public class MyExecutor implements Executor{
    /** 
     * A queue of wrapped tasks. 
     *
     * Tasks are added to this queue and then retrieved to be
     * executed. 
     *
     * Note that the name of the class is not important. The only 
     * thing we know (and care) about is that 'taskQueue' is a Queue
     * (i.e. has methods for adding elements on one end and retrieving
     * them from the other end). In other words, any class that
     * implements the Queue interface is in principle good enough. 
     */ 
    private final Queue<WrappedTask> taskQueue = new ArrayDeque<WrappedTask>();

    /**
     * An internal executor to actually execute tasks. 
     *
     * Note that this class (SerialExecutor) does not really execute
     * tasks, i.e. it never calls their run() method. That method is
     * called from run() of the WrappedTask object, which in turn is
     * called by the internal executor (method scheduleNext() below). 
     *
     * Note also that the name of the class is not important. The only 
     * thing we know (and care) about is that 'executor' is an Executor
     * (i.e. has methods to execute() tasks i.e. Runnables). In other
     * words, any class that implements the Executor interface is in 
     * principle good enough. 
     */
    private final Executor innerExecutor = new ForkJoinPool();

    /** 
     * The current active task. 
     *
     * Note that this Runnable will always be a WrappedTask (check the code!)
     */
    private Runnable active;

    @Override
    public synchronized void execute(final Runnable r) {
	  WrappedTask newTask = new WrappedTask(this, r);
	  taskQueue.offer(newTask);
	  if (active == null) {
		scheduleNext();
	  }
    }

    /** 
     * Gets the next tasks from the queue and executes it. 
     */
    protected synchronized void scheduleNext() {
	  active = taskQueue.poll();
	  if (active != null) {
		innerExecutor.execute(active);
	  }
    }
}

/**
 * A class that wraps a Runnable to create another Runnable with a
 * run() method that has two steps: 
 *   1. Execute the run() method of the original runnable (task)
 *   2. Schedule the next task from the queue. 
 */
class WrappedTask implements Runnable {
    /**
     * The tasks wrapped inside
     */
    private Runnable innerTask = null;
    /**
     * The executor that will schedule the next task from the queue
     */
    private MyExecutor myExecutor = null;

    /**
     * A new Wrapped Task
     *
     * @param e the executor that will schedule the next task from the queue
     * @param r the task to be run
     */
    public WrappedTask(MyExecutor e, Runnable r) {
	  this.innerTask = r;
	  this.myExecutor = e;
    }
    @Override
    public void run() {
	  try {
		innerTask.run();
	  } finally {
		this.myExecutor.scheduleNext();
	  }
    }
}