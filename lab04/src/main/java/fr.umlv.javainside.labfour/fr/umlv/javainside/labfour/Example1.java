package fr.umlv.javainside.labfour;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 6. 
 * hello continuation
	|  Exception java.lang.IllegalStateException: Continuation terminated
	|        at Continuation.run (Continuation.java:158)
	|        at Example1.main (#14:9)
	|        at (#15:1)
	
 * 
 * 9.
 * java.lang.IllegalStateException: Pinned: 2
		at java.base/java.lang.Continuation.onPinned(Continuation.java:321)
		at java.base/java.lang.Continuation.onPinned0(Continuation.java:311)
		at java.base/java.lang.Continuation.yield0(Continuation.java:281)
		at java.base/java.lang.Continuation.yield(Continuation.java:241)
		at REPL.$JShell$11C$Example1.lambda$main$0($JShell$11C.java:11)
		at java.base/java.lang.Continuation.enter0(Continuation.java:223)
		at java.base/java.lang.Continuation.enter(Continuation.java:208)
		at java.base/java.lang.Continuation.run(Continuation.java:172)
		at REPL.$JShell$11C$Example1.main($JShell$11C.java:16)
		at REPL.$JShell$27.do_it$($JShell$27.java:5)
		at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
		at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
		at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
		at java.base/java.lang.reflect.Method.invoke(Method.java:566)
		at jdk.jshell/jdk.jshell.execution.DirectExecutionControl.invoke(DirectExecutionControl.java:209)
		at jdk.jshell/jdk.jshell.execution.RemoteExecutionControl.invoke(RemoteExecutionControl.java:116)
		at jdk.jshell/jdk.jshell.execution.DirectExecutionControl.invoke(DirectExecutionControl.java:119)
		at jdk.jshell/jdk.jshell.execution.ExecutionControlForwarder.processCommand(ExecutionControlForwarder.java:144)
		at jdk.jshell/jdk.jshell.execution.ExecutionControlForwarder.commandLoop(ExecutionControlForwarder.java:262)
		at jdk.jshell/jdk.jshell.execution.Util.forwardExecutionControl(Util.java:76)
		at jdk.jshell/jdk.jshell.execution.Util.forwardExecutionControlAndIO(Util.java:137)
		at jdk.jshell/jdk.jshell.execution.RemoteExecutionControl.main(RemoteExecutionControl.java:70)
	|  Exception java.lang.IllegalStateException: Pinned: 2
	|        at Continuation.onPinned (Continuation.java:321)
	|        at Continuation.onPinned0 (Continuation.java:311)
	|        at Continuation.yield0 (Continuation.java:281)
	|        at Continuation.yield (Continuation.java:241)
	|        at Example1.lambda$main$0 (#22:7)
	|        at Continuation.enter0 (Continuation.java:223)
	|        at Continuation.enter (Continuation.java:208)
	|        at Continuation.run (Continuation.java:172)
	|        at Example1.main (#22:12)
	|        at (#23:1)

 * 
 * 
 * 11.
 * Le main est un thread mais n'est pas une continuation
 * Le thread peut utiliser plusieurs coeurs
 * 
 * 
 */


public class Example1 {
	public static void main() {
		Object lock = new Object();
		
		Scheduler schedule = new Scheduler(SchedulerMode.RANDOM);
		
		ContinuationScope continuationScope = new ContinuationScope("hello1");
		Runnable runnable = () -> {
			schedule.enqueue(continuationScope);
			System.out.println(Continuation.getCurrentContinuation(continuationScope));
			//synchronized(lock) {
				Continuation.yield(continuationScope);
			//}
			System.out.println("hello continuation");
		};
		Runnable runSecond = () -> {
			schedule.enqueue(continuationScope);
			Continuation.yield(continuationScope);
			System.out.println("second continuation");
			Continuation.yield(continuationScope);
		};
		Continuation continuationFirst = new Continuation(continuationScope, runnable);
		Continuation continuationSecond = new Continuation(continuationScope, runSecond);
		
		
		continuationFirst.run();
		continuationSecond.run();
		
		schedule.runLoop();
		
		
		/*while(!suite.isDone() || !second.isDone()) {
			suite.run();
			second.run();
		}*/
	}
	
	enum SchedulerMode {
		STACK,
		FIFO,
		RANDOM
	}
	
	static class Scheduler {
		private final Collection<Continuation> schedulerConti;
		private final SchedulerMode schedulerMode;
		
		public Scheduler(SchedulerMode schedulerMode) {
			if(schedulerMode == SchedulerMode.RANDOM) {
				this.schedulerConti = new ArrayList<>();
			} else {
				this.schedulerConti = new ArrayDeque<>();
			}
			this.schedulerMode = schedulerMode;
		}
		
		public void enqueue(ContinuationScope cs) {
			Continuation tmp = Continuation.getCurrentContinuation(cs);
			if(tmp == null) {
				return;
			}
			schedulerConti.add(tmp);
			Continuation.yield(cs);
		}
		
		public void runLoop() {
			while(!schedulerConti.isEmpty()) {
				switch(schedulerMode) {
					case STACK:
						((ArrayDeque<Continuation>) schedulerConti).removeLast().run();
						break;
					case FIFO:
						((ArrayDeque<Continuation>) schedulerConti).removeFirst().run();
						break;
					case RANDOM:
						var conti = ThreadLocalRandom.current().nextInt(schedulerConti.size());
						((ArrayList<Continuation>) schedulerConti).remove(conti).run();
						break;
				}
				
			}
		}
	}
}
