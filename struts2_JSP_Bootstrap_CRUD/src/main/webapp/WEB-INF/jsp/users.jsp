<%-- 
    Document   : users
    Created on : May 17, 2020, 11:07:57 PM
    Author     : simiyu
--%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/header.jsp" />

<div class="main-content">

    <div class="container">

        <div class="col-md-6 col-md-offset-3">
            <div class="login-form">

                <form class="register-form" method="post" id="form-sign-up">
                    <fieldset>
                        <legend class="text-center">Please Create An Account</legend>
                        <img class="profile-img-card" src="<%=request.getContextPath()%>/static/images/avatar_2x.png">

                        <div class="form-group">
                            <label class="control-label">Full Name</label> <input
                                class="form-control" type="text"
                                placeholder="Full Name" name="fullname" id="fullname">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Username</label> <input
                                class="form-control" type="text"
                                placeholder="Username" name="username" id="username">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Password</label> <input
                                class="form-control" type="password" placeholder="Password"
                                name="password" id="password">
                        </div>

                        <div class="form-group">
                            <label class="control-label">Confirm Password</label> <input
                                class="form-control" type="password" placeholder="Confirm Password"
                                name="password2" id="password2">
                        </div>
                        <button class="btn btn-warning btn-block" type="submit">
                            <strong>Submit</strong>
                        </button><br />
                    </fieldset>
                </form>

            </div>
        </div>

    </div>

</div>
<jsp:include page="/includes/footer.jsp" />