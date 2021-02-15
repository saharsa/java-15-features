package com.java.concurrency;

/*
A thread is the smallest unit of execution that can be scheduled by the operating system.
A process is a group of associated threads that execute in the same, shared environment.
It follows, then, that a single‐threaded process is one that contains exactly one thread, whereas a multithreaded process is one that contains one or more threads.

By shared environment, we mean that the threads in the same process share the same memory space and can communicate directly with one another

A system thread is created by the JVM and runs in the background of the application. For example, the garbage collection is managed by a system thread that is created by the JVM and runs in the background, helping to free memory that is no longer in use. For the most part, the execution of system‐defined threads is invisible to the application developer. When a system‐defined thread encounters a problem and cannot recover, such as running out of memory, it generates a Java Error, as opposed to an Exception.

Alternatively, a user‐defined thread is one created by the application developer to accomplish a specific task.

a daemon thread is one that will not prevent the JVM from exiting when the program finishes. A Java application terminates when the only threads that are running are daemon threads. For example, if garbage collection is the only thread left running, the JVM will automatically shut down. Both system and user‐defined threads can be marked as daemon threads.

The property of executing multiple threads and processes at the same time is referred to as concurrency. Of course, with a single‐core CPU system, only one task is actually executing at a given time. Even in multicore or multi‐CPU systems, there are often far more threads than CPU processors available

Operating systems use a thread scheduler to determine which threads should be currently executing
a thread scheduler may employ a round‐robin schedule in which each available thread receives an equal number of CPU cycles with which to execute, with threads visited in a circular order. If there are 10 available threads, they might each get 100 milliseconds in which to execute, with the process returning to the first thread after the last thread has executed.

When a thread's allotted time is complete but the thread has not finished processing, a context switch occurs. A context switch is the process of storing a thread's current state and later restoring the state of the thread to continue execution. Be aware that there is often a cost associated with a context switch by way of lost time saving and reloading a thread's state. Intelligent thread schedules do their best to minimize the number of context switches, while keeping an application running smoothly.

a thread can interrupt or supersede another thread if it has a higher thread priority than the other thread. A thread priority is a numeric value associated with a thread that is taken into consideration by the thread scheduler when determining which threads should currently be executing.

 */

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Test {

  private static int counter = 0;

  public static void main(String[] args)
      throws InterruptedException, TimeoutException, ExecutionException {
    Runnable sloth = () -> System.out.println("Hello World");
    Runnable snake = () -> {
      int i = 10;
      i++;
    };
    Runnable beaver = () -> {
      return;
    };
    Runnable coyote = () -> {
    };

    new Thread(() -> {
      for (int i = 0; i < 500; i++) {
        Test.counter++;
      }
    }).start();
    while (Test.counter < 100) {
      System.out.println("Not reached yet");
      Thread.sleep(1000); // 1 SECOND
    }
    System.out.println("Reached!");

    ExecutorService service = null;
    Runnable task1 = () ->
        System.out.println("Printing zoo inventory");
    Runnable task2 = () -> {
      for (int i = 0; i < 3; i++) {
        System.out.println("Printing record: " + i);
      }
    };
    try {
      service = Executors.newSingleThreadExecutor();
      System.out.println("begin");
      /*
      submit() methods to the ExecutorService interface, which, like execute(), can be used to complete tasks asynchronously. Unlike execute(), though, submit() returns a Future instance that can be used to determine whether the task is complete. It can also be used to return a generic result object after the task has been completed.
       */
      service.execute(task1);
      service.execute(task2);
      service.execute(task1);
      System.out.println("end");

      Future<?> future = service.submit(() -> System.out.println("Hello"));
      Future<?> result = service.submit(() -> {
        for (int i = 0; i < 500; i++) {
          Test.counter++;
        }
      });
      result.get(10, TimeUnit.SECONDS);
      System.out.println("Reached!");

      Future<Integer> callableResult = service.submit(() -> 30 + 11);
      System.out.println(callableResult.get());   // 41

    } finally {
      /*
      shutdown() does not actually stop any tasks that have already been submitted to the thread executor
      hutdownNow(), which attempts to stop all running tasks and discards any that have not been started yet
       */
      if (service != null) {
        service.shutdown();
      }
    }

    if (service != null) {
      service.awaitTermination(1, TimeUnit.NANOSECONDS);

      // Check whether all tasks are finished
      if (service.isTerminated()) {
        System.out.println("Finished!");
      } else {
        System.out.println("At least one task is still running");
      }
    }

    /*
    invokeAll() method will wait indefinitely until all tasks are complete, while the invokeAny() method will wait indefinitely until at least one task completes. The ExecutorService interface also includes overloaded versions of invokeAll() and invokeAny() that take a timeout value and TimeUnit parameter.
     */
    ExecutorService service1 = Executors.newSingleThreadExecutor();
    System.out.println("begin");
    Callable<String> task = () -> "result";
    List<Future<String>> list = service1.invokeAll(List.of(task, task, task));
    for (Future<String> future : list) {
      System.out.println(future.get());
    }
    System.out.println("end");
    service1.shutdown();

    ExecutorService service2 = Executors.newSingleThreadExecutor();
    System.out.println("begin");
    Callable<String> task3 = () -> "result";
    String data = service2.invokeAny(List.of(task3, task3, task3));
    System.out.println(data);
    System.out.println("end");
    service2.shutdown();

    /*
    Oftentimes in Java, we need to schedule a task to happen at some future time. We might even need to schedule the task to happen repeatedly, at some set interval.
    ScheduledExecutorService

    schedule(Callable<V> callable, long delay, TimeUnit unit)	Creates and executes a Callable task after the given delay
    schedule(Runnable command, long delay, TimeUnit unit)	Creates and executes a Runnable task after the given delay
    scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)	Creates and executes a Runnable task after the given initial delay, creating a new task every period value that passes
    scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)	Creates and executes a Runnable task after the given initial delay and subsequently with the given delay between the termination of one execution and the commencement of the next

     While these tasks are scheduled in the future, the actual execution may be delayed. For example, there may be no threads available to perform the task, at which point they will just wait in the queue. Also, if the ScheduledExecutorService is shut down by the time the scheduled task execution time is reached, then these tasks will be discarded.
     */

    ScheduledExecutorService scheduledExecutorService = Executors
        .newSingleThreadScheduledExecutor();
    Runnable stask1 = () -> System.out.println("Hello Zoo");
    Callable<String> stask2 = () -> "Monkey";
    ScheduledFuture<?> r1 = scheduledExecutorService.schedule(stask1, 10, TimeUnit.SECONDS);
    ScheduledFuture<?> r2 = scheduledExecutorService.schedule(stask2, 8,  TimeUnit.MINUTES);
    System.out.println(r1.get());
    System.out.println(r2.get());
    scheduledExecutorService.shutdown();

    /*
    A thread pool is a group of pre‐instantiated reusable threads that are available to perform a set of arbitrary tasks
    ExecutorService newSingleThreadExecutor()	Creates a single‐threaded executor that uses a single worker thread operating off an unbounded queue. Results are processed sequentially in the order in which they are submitted.
    ScheduledExecutorService newSingleThreadScheduledExecutor()	Creates a single‐threaded executor that can schedule commands to run after a given delay or to execute periodically
    ExecutorService newCachedThreadPool()	Creates a thread pool that creates new threads as needed but will reuse previously constructed threads when they are available
    ExecutorService newFixedThreadPool(int)	Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue
    ScheduledExecutorService  newScheduledThreadPool(int)	Creates a thread pool that can schedule commands to run after a given delay or to execute periodically
     */

    System.out.println(Runtime.getRuntime().availableProcessors());
  }

}

/*
Runnable interface is commonly used to define the task or work a thread will execute, separate from the main application thread

Defining the task that a Thread instance will execute can be done two ways in Java:

Provide a Runnable object or lambda expression to the Thread constructor.
Create a class that extends Thread and overrides the run() method.

Object.wait(), Object.notify(), Thread.join()

Even though multithreaded programming allows you to execute multiple tasks at the same time, one thread often needs to wait for the results of another thread to proceed. One solution is to use polling. Polling is the process of intermittently checking data at some fixed interval.

Java includes the Concurrency API to handle the complicated work of managing threads for you. The Concurrency API includes the ExecutorService interface, which defines services that create and manage threads for you.

void execute(Runnable command)	Executes a Runnable task at some point in the future
Future<?> submit(Runnable task)	Executes a Runnable task at some point in the future and returns a Future representing the task
<T> Future<T> submit(Callable<T> task)	Executes a Callable task at some point in the future and returns a Future representing the pending results of the task
<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException	Executes the given tasks and waits for all tasks to complete. Returns a List of Future instances, in the same order they were in the original collection
<T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException	Executes the given tasks and waits for at least one to complete. Returns a Future instance for a complete task and cancels any unfinished tasks

The java.util.concurrent.Callable functional interface is similar to Runnable except that its call() method returns a value and can throw a checked exception.

 */
