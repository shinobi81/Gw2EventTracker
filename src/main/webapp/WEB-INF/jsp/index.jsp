<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .div-table{
                display:table;         
                width:auto;         
                background-color:#eee;         
                border:1px solid  #666666;         
                border-spacing:5px;/*cellspacing:poor IE support for  this*/
            }
            .div-table-row{
                display:table-row;
                width:auto;
                clear:both;
            }
            .div-table-col{
                float:left;/*fix for  buggy browsers*/
                display:table-column;         
                width:200px;         
                background-color:#ccc;  
            }
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form:form action="index.htm" commandName="eventsCommand">
            <div>
                <form:select path="worldId">
                    <form:options items="${response.worldNames}" />
                </form:select>
            </div>
            <div>
                <form:select path="mapId">
                    <form:options items="${response.mapNames}" />
                </form:select>
            </div>
            <div>
                <button type="submit" value="Search">Search</button>
            </div>
        </form:form>
        <div class="div-table">
            <c:forEach items="${response.events}" var="event">
                <div class="div-table-row">
                    <div class="div-table-col">
                        <c:out value="${response.eventNames[event.eventId]}" />
                    </div>
                    <div class="div-table-col">
                        <c:out value="${event.state}" />
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
