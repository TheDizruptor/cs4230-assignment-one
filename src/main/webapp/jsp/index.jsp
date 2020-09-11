<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
            <th>Address 1</th>
            <th>Address 2</th>
        </tr>
    <c:forEach var = "contact" items="${contacts}">
        <c:set var = "numAddresses" value = "${fn:length(contact.addresses)}"/>
        <tr>
            <td><c:out value = "${contact.firstName}" /></td>
            <td><c:out value = "${contact.lastName}" /></td>
            <td><c:out value = "${contact.phoneNumber}" /></td>
            <td><c:out value = "${numAddresses > 0 ? contact.addresses[0].combinedAddress : \"N/A\"}" /></td>
            <td><c:out value = "${numAddresses > 1 ? contact.addresses[1].combinedAddress : \"N/A\"}" /></td>
        </tr>
    </c:forEach>
    </table>
    <br /><hr /><br />

    <div class="p-3">
        <h5 class="display-5">Add New Contact</h5>
        <br /><hr />
        <p class="text-danger"><c:out value="${error}" /></p>
        <form action="${pageContext.request.contextPath}/" method="post">
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <h6 class="display-6">Personal Info</h6>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="sr-only" for="fname">First Name: </label>
                    <input class="form-control mb-2" type="text" name="fname" id="fname" placeholder="First Name" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="lname">Last Name: </label>
                    <input class="form-control mb-2" type="text" name="lname" id="lname" placeholder="Last Name" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="phone">Phone Number: </label>
                    <input class="form-control mb-2" type="text" name="phone" id="phone" placeholder="Phone Number" />
                </div>
                <br />
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <h6 class="display-6">Address 1 (required): </h6>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="sr-only" for="type1">Type</label>
                    <select name="type1" id="type1" class="form-control">
                        <option selected value="Home">Home</option>
                        <option value="Business">Business</option>
                    </select>
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="address1">Street Address</label>
                    <input class="form-control mb-2" type="text" name="address1" id="address1" placeholder="Street Address" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="city1">City</label>
                    <input class="form-control mb-2" type="text" name="city1" id="city1" placeholder="City" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="state1">State</label>
                    <input class="form-control mb-2" type="text" name="state1" id="state1" placeholder="State" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="postal1">Postal Code</label>
                    <input class="form-control mb-2" type="text" name="postal1" id="postal1" placeholder="Postal Code" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="country1">Country</label>
                    <input class="form-control mb-2" type="text" name="country1" id="country1" placeholder="Country" />
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <h6 class="display-6">Address 2 (optional): </h6>
                </div>
            </div>
            <div class="form-row align-items-center">
                <div class="col-auto">
                    <label class="sr-only" for="type2">Type</label>
                    <select name="type2" id="type2" class="form-control">
                        <option value="Home">Home</option>
                        <option selected value="Business">Business</option>
                    </select>
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="address2">Street Address</label>
                    <input class="form-control mb-2" type="text" name="address2" id="address2" placeholder="Street Address" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="city2">City</label>
                    <input class="form-control mb-2" type="text" name="city2" id="city2" placeholder="City" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="state2">State</label>
                    <input class="form-control mb-2" type="text" name="state2" id="state2" placeholder="State" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="postal2">Postal Code</label>
                    <input class="form-control mb-2" type="text" name="postal2" id="postal2" placeholder="Postal Code" />
                </div>
                <div class="col-auto">
                    <label class="sr-only" for="country2">Country</label>
                    <input class="form-control mb-2" type="text" name="country2" id="country2" placeholder="Country" />
                </div>
            </div>
            <div class="form-row align-items-center">
                <button class="btn btn-primary btn-lg" type="submit">Add</button>
            </div>
        </form>
    </div>
</body>
</html>