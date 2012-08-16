<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% if (request.getAttribute("failureMessage") != null) { %>
<div class="alert-failure">
	<%= request.getAttribute("failureMessage") %>
</div>
<% } %>

<% if (request.getAttribute("successMessage") != null) { %>
<div class="alert-success">
	<%= request.getAttribute("successMessage") %>
</div>
<% } %>