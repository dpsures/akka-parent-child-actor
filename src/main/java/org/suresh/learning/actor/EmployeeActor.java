package org.suresh.learning.actor;

import org.suresh.learning.model.Employee;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class EmployeeActor extends AbstractActor{

	private LoggingAdapter log = Logging.getLogger(getContext().system(), "EmployeeActor");
	private ActorRef itIssueRef = null;
	private ActorRef engIssueRef = null;
	
	@Override
	public void preStart() throws Exception {
		itIssueRef = getContext().actorOf(Props.create(ITIssueActor.class), "ITIssueActor");
		engIssueRef = getContext().actorOf(Props.create(EngIssueActor.class), "EngIssueActor");
	}
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Employee.class, emp -> {
					if(emp.getIssueStatus() == 1){
						if(emp.getDept().equals("it")){
							itIssueRef.tell(emp, self());
						}else if(emp.getDept().equals("eng")){
							engIssueRef.tell(emp, self());
						}	
					}else if(emp.getIssueStatus() == 2){
						log.info("Issue resolved : {}",emp.getEmpId()+ " --- "+emp.getIssue());
					}
				})
				.matchAny( obj -> log.info("Unknow Type Found : {}", obj))
				.build();
	}

}
