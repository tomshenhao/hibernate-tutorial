package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents=session.createQuery("from Student").list();
			
			// display the students
			displayStudents(theStudents);
			
			// query student: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").list();
			
			//display the students
			System.out.println("Student who has last name as Doe");
			displayStudents(theStudents);
			
					
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
		}
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
