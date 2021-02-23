/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import model.Contact;

/**
 *
 * @author ianotto
 */

public class ContactDao  {

	protected SessionFactory sessionFactory;

	public void setup() {
		// code to load Hibernate Session factory
		String classpath = System.getProperty("java.class.path");
		String[] classpathEntries = classpath.split(File.pathSeparator);
		for (int i = 0; i < classpathEntries.length; i++)
			System.out.println(classpathEntries[i]);
		String path = "D:\\Documents\\SUPELEC\\eleves\\NouveauCursus\\SG8\\Cours\\JavaEE_Eclipse\\WebApplicationCarnetAdresses3";
		String hibernatePropsFilePath = path + "\\src\\hibernate.cfg.xml";



		File hibernatePropsFile = new File(hibernatePropsFilePath);
		Configuration configuration = new Configuration(); 
		configuration.configure(hibernatePropsFile);

		configuration.addAnnotatedClass(Contact.class);

		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);


	}

	public void exit() {
		// code to close Hibernate Session factory
		sessionFactory.close();
	}

	public void addContact(Contact c) {
		// code to save a Contact

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(c);

		session.getTransaction().commit();
		session.close();
	}

	public Contact getContact(Integer ContactId) {
		// code to get a Contact
		Session session = sessionFactory.openSession();


		Contact Contact = session.get(Contact.class, ContactId);

		System.out.println("Id : " + Contact.getId());
		System.out.println("Nom : " + Contact.getNom());
		System.out.println("Prénom : " + Contact.getPrenom());
		System.out.println("Adresse : " + Contact.getAdresse());
		System.out.println("Téléphone : " + Contact.getTelephone());
		session.close();
		return Contact;

	}

	public void editContact(Contact c) {
		// code to modify a Contact
		

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.update(c);

		session.getTransaction().commit();
		session.close();
	}

	public void deleleContact(int contactId) {
		// code to remove a Contact
		Contact c = new Contact();
		c.setId(contactId);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.delete(c);

		session.getTransaction().commit();
		session.close();
	}

	public List<Contact> getAllContacts() {
		
		List<Contact> l;
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		l =  session.createQuery("SELECT b FROM Contact b", Contact.class).getResultList(); 
		//List<Contact> l = session.createNamedQuery("Contact.findAll").getResultList();

		session.close();
		return l;
	}
}
