<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
            <meta content="width=device-width, initial-scale=1, maximum-scale=1"
                  name="viewport" />
            <title>Sample CRUD</title>
            <meta content="Simiyu" name="author" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/bootstrap-3.3.5/css/bootstrap.min.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/bootstrap-3.3.5/css/bootstrap-multiselect.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/bootstrap-3.3.5/css/bootstrap-datetimepicker.min.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/font-awesome-4.3.0/css/font-awesome.min.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/custom/css/styles.css" />
            <link rel="stylesheet" type="text/css"
                  href="<%=request.getContextPath()%>/static/custom/css/bootstrap.vertical-tabs.css" />
            <link rel="icon" href="<%=request.getContextPath()%>/static/images/favicon.ico">

                <style type="text/css">
                    body {
                        font-family: '${theme.font}';
                        font-size: 11px;
                    }
                    .modal.in .modal-dialog {
                        top: 30%;
                    }
                </style>
                </head>

                <body oncontextmenu="return false">
                    <input type="hidden" name="message" id="message" value="${ message }" />
                    <nav id="main-nav" class="navbar navbar-default navbar-fixed-top" role="imageBanner">
                        <div class="container">

                            <div class="collapse navbar-collapse navbar-right">
                                <div class="row">
                                    <div class="col-md-9">
                                        <ul class="nav navbar-nav">
                                            <li><a href="<%=request.getContextPath()%>/students/getStudents">Home</a></li>
                                        </ul>
                                    </div>
                                    <div class="col-md-3 calls-to-action hidden-sm hidden-xs">
                                        <c:if test="${loggedIn == 'FALSE'}">
                                            <a class="btn btn-sm btn-warning"  href="<%=request.getContextPath()%>/users/gologin"><strong>Sign In</strong></a> 
                                            <a class="btn btn-sm btn-success"  href="<%=request.getContextPath()%>/users/goSignup"><strong>Register</strong></a>
                                        </c:if>
                                        <c:if test="${loggedIn == 'TRUE'}">
                                            <a class="btn btn-sm btn-toolbar"  id="logout-li"><strong>Logout</strong></a>
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/.container-->
                    </nav>
                </body>
                <!--/nav-->