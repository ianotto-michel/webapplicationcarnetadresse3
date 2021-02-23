/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContactDao;
import model.Contact;

/**
 *
 * @author ianotto
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {


	private ContactDao contactDao;

	public void init() {


		System.out.println("Init : ControllerServlet");
		//bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
		contactDao = new ContactDao();
		
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String idStr = null;
		String nomStr;
		String prenomStr;
		String adresseStr;
		String telephoneStr;
		Contact c = null;

		// Rechercher un contact
		contactDao.setup();
		if (action != null) {
			if (action.equals("Rechercher")) {
				idStr = request.getParameter("ContactId");
				if (idStr.equals("") == false && idStr.matches("\\p{Digit}+") == true) {
					int contactId = Integer.valueOf(idStr);
					
					c = contactDao.getContact(contactId);
					
					if (c == null) {
						request.setAttribute("MessageErreur", "Id incorrect");
					} else {
						request.setAttribute("Contact", c);
					}
				} else {
					request.setAttribute("MessageErreur", "L'id doit �tre un entier");
				}
			}
			// Ajouter un nouveau contact
			if (action.equals("Ajouter")) {
				idStr = request.getParameter("ContactId");
				nomStr = request.getParameter("ContactNom");
				prenomStr = request.getParameter("ContactPrenom");
				adresseStr = request.getParameter("ContactAdresse");
				telephoneStr = request.getParameter("ContactTelephone");
				if (idStr.equals("") == false && idStr.matches("\\p{Digit}+") == true
						&& nomStr.equals("") == false
						&& prenomStr.equals("") == false
						&& adresseStr.equals("") == false
						&& telephoneStr.equals("") == false) {
					c = new Contact();
					c.setId(Integer.valueOf(idStr));
					c.setNom(nomStr);
					c.setPrenom(prenomStr);
					c.setAdresse(adresseStr);
					c.setTelephone(telephoneStr);
					
					contactDao.addContact(c);
					
				} else {
					request.setAttribute("MessageErreur", "Tous les champs doivent �tre remplis");
				}

			}
			// Supprimer un contact
			if (action.equals("Supprimer") == true) {
				idStr = request.getParameter("ContactId");
				if (idStr.equals("") == false && idStr.matches("\\p{Digit}+") == true) {
					int contactId = Integer.valueOf(idStr);
					
					contactDao.deleleContact(contactId);
					
				} else {
					request.setAttribute("MessageErreur", "L'id doit �tre un entier");
				}
			}

			// Modifier un contact
			if (action.equals("Modifier") == true) {
				idStr = request.getParameter("ContactId");
				nomStr = request.getParameter("ContactNom");
				prenomStr = request.getParameter("ContactPrenom");
				adresseStr = request.getParameter("ContactAdresse");
				telephoneStr = request.getParameter("ContactTelephone");
				if (idStr.equals("") == false && idStr.matches("\\p{Digit}+") == true
						&& nomStr.equals("") == false
						&& prenomStr.equals("") == false
						&& adresseStr.equals("") == false
						&& telephoneStr.equals("") == false) {
					c = new Contact();
					c.setId(Integer.valueOf(idStr));
					c.setNom(nomStr);
					c.setPrenom(prenomStr);
					c.setAdresse(adresseStr);
					c.setTelephone(telephoneStr);
					
					contactDao.editContact(c);
					
				}
			}
		}
		// Lister tous les contacts
		System.out.println("processRequest");
		List<Contact> l = contactDao.getAllContacts();
		request.setAttribute("TousLesContacts", l);

		// on renvoie le message � la page jsp
		request.getRequestDispatcher("ContactInfo.jsp").forward(request, response);
		contactDao.exit();
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
