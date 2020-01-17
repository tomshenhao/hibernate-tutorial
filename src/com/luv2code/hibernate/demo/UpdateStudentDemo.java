package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;
			
			//now get a new session and start transaction
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("Getting the student with id: "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			
			//commit the transaction
			session.getTransaction().commit();			
			
			System.out.println("Done!");
			
			//new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update email 
			System.out.println("Update email for all students");
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			factory.close();
		}
		
	}

}
