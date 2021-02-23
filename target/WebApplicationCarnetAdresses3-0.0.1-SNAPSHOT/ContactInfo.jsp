<%-- 
    Document   : ContactInfo
    Created on : 22 juin 2020, 10:04:45
    Author     : ianotto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="style.css"/>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="styles.css">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Information Page</title>
    </head>
    <body>
        <h1> Contact informations </h1>
        <form method="Post" action="./ContactServlet" > 
            <table>
                <tr>
                    <%
                        /*
                         Tout ce qui est situé entre les balises % et %  
                         correspond a du code JAVA
                         */
                        // on récupère la valeur du paramètre Param1 envoyée par la servlet 
                        // User
                        String attribut = (String) request.getAttribute("MessageErreur");
                        // On envoie la chaine de caractères attribut dans le flux de sortie.
                        // Cette chaîne de caractères sera traduite en HTML puis envoyée 
                        // au navigateur et affichée par le navigateur
                        if (attribut != null) {
                            out.println(attribut);
                        } else {
                            out.println(" ");
                        }
                    %>
                </tr>
                <tr>
                    <td> Id </td>
                    <td> <input type="text" name = "ContactId" value = "${Contact.id}" placeholder="Enter the Id"/> </td>
                </tr>  
                <tr>
                    <td> Nom </td>
                    <td> <input type="text" name = "ContactNom" value = "${Contact.nom}" /> </td>
                </tr>  
                <tr>
                    <td> Prénom </td>
                    <td> <input type="text" name = "ContactPrenom" value = "${Contact.prenom}" /> </td>
                </tr>  
                <tr>
                    <td> Addresse </td>
                    <td> <input type="text" name = "ContactAdresse" value = "${Contact.adresse}" /> </td>
                </tr>  
                <tr>
                    <td> Numéro de téléphone </td>
                    <td> <input type="text" name = "ContactTelephone" value = "${Contact.telephone}" />  </td>
                </tr>  
            </table>
            <table>
                <tr>
                    <td> 
                        <input type ="submit" name = "action"  value ="Rechercher" />
                    </td>
                    <td> 
                        <input type ="submit" name = "action"  value ="Ajouter" />
                    </td>
                    <td> 
                        <input type ="submit" name = "action"  value ="Supprimer" />
                    </td>
                    <td> 
                        <input type ="submit" name = "action"  value ="Modifier" />
                    </td>
                </tr>  
            </table>
            <br>
            <table border="1">
                <th> ID </th>
                <th> Nom </th>
                <th> Prenom </th>
                <th> Adresse </th>
                <th> Téléphone </th>
                <c:forEach items = "${TousLesContacts}" var ="c">
                    <tr>
                        <td>
                            ${c.id}
                        </td>
                        <td>
                            ${c.nom}
                        </td>
                        <td>
                            ${c.prenom}
                        </td>
                        <td>
                            ${c.adresse}
                        </td>
                        <td>
                            ${c.telephone}
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </form>

    </body>
</html>
