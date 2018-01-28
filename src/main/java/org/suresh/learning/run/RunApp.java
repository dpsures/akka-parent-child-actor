package org.suresh.learning.run;

import org.suresh.learning.actor.EmployeeActor;
import org.suresh.learning.model.Employee;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class RunApp {

	public static void main(String[] args) {
		
		ActorSystem system = ActorSystem.create("company-system");
		
		ActorRef empRef = system.actorOf(Props.create(EmployeeActor.class), "EmployeeActor");
		
		Employee emp = new Employee();
		emp.setEmpId(1);
		emp.setDept("it");
		emp.setIssue("computer not working");
		emp.setIssueStatus(1);
		empRef.tell(emp, Actor.noSender());
		
		emp = new Employee();
		emp.setEmpId(2);
		emp.setDept("eng");
		emp.setIssue("card reader not working");
		emp.setIssueStatus(1);
		empRef.tell(emp, Actor.noSender());
		
	}

}
