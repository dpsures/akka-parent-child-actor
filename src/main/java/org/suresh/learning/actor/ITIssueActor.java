package org.suresh.learning.actor;

import org.suresh.learning.model.Employee;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class ITIssueActor extends AbstractActor {	
	
	private LoggingAdapter log = Logging.getLogger(getContext().system(), "ITIssueActor");
	
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Employee.class, emp -> {
					log.info("IT Issue Received : {}",emp.getEmpId()+" --- "+emp.getIssue());
					emp.setIssue(emp.getIssue()+ " : Resolved");
					emp.setIssueStatus(2);
					getSender().tell(emp, self());
				})
				.build();
	}

}
