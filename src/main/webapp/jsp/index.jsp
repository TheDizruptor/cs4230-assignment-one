<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>

    <h1 class="display-1 text-center">Contacts</h1>
    <table class="table">
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Phone Number</th>
            <th>Address</th>
        </tr>
    <c:forEach var = "contact" items="${contacts}">
        <tr>
            <td><c:out value = "${contact.firstName}" /></td>
            <td><c:out value = "${contact.lastName}" /></td>
            <td><c:out value = "${contact.phoneNumber}" /></td>
            <td><c:out value = "${contact.address.addressLine1}" /></td>
        </tr>
    </c:forEach>
    </table>

    <button type="button">Add Contact</button>

<%--    <c:if test="${addingContact == true}">--%>
        <form>
            <label for="fname">First Name: </label>
            <input type="text" name="fname" id="fname" />
            <br />
            <label for="lname">Last Name: </label>
            <input type="text" name="lname" id="lname" />
            <br />
            <label for="phone">Phone Number: </label>
            <input type="text" name="phone" id="phone" />
            <br />
            <a href="addContact">Add</a>
        </form>
<%--    </c:if>--%>
</body>
</html>