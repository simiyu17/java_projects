<%@page import="com.crudsample.model.UserInfo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/includes/header.jsp" />

<div class="container-fluid section">
    <div class="col-md-12">
        <h3 class=" main-title" align="center">
            <i class="glyphicon glyphicon-user"></i>&nbsp;&nbsp;STUDENTS PROFILES
        </h3>
    </div>
    <div class="col-md-12"> 
        <div class="right"><button onclick="open_student_form();" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-plus-sign"></i>&nbsp;Add New</button></div>
        <table class="table table-responsive table-striped" id="search-results">
            <thead>
                <tr><th>Name</th><th>Gender</th><th>Favourite Subject</th><th>City</th><th>Country</th><th>ACTIONS</th></tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr><td>${ student.name }</td><td>${ student.gender }</td><td>${student.favouriteSubject }</td>
                        <td>${ student.city }</td><td>${ student.country }</td><td class="right">
                            <button class="btn btn-sm btn-warning" onclick="edit_student('${student.id}');">
                                <i class="glyphicon glyphicon-pencil"></i>&nbsp;Edit</button>&nbsp;
                            <button onclick="remove_student('${student.id}');" class="btn btn-sm btn-danger">
                                <i class="glyphicon glyphicon-remove"></i>&nbsp;Delete</button></td></tr>
                            </c:forEach>
            </tbody>
        </table>
    </div>


</div>


<div class="modal fade" id="student_form" tabindex="-1" role="dialog" aria-labelledby="myModalLabelOther" aria-hidden="true">
    <form role="form" id="form-newstudent-form">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myModalLabelMenu">
                        <i class="glyphicon glyphicon-cog"></i>&nbsp;&nbsp;STUDENT DETAILS
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="student_id" id="student_id" value="${ student.id}"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="name">Student Name:</label>
                        <input type="text" class="form-control" name="name" id="name" value="${ student.name}"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="gender">Select Gender:</label>
                        <select name="gender" id="gender" class="form-control" value="${ student.gender}">
                            <option value="MALE" >Male</option>
                            <option value="FEMALE">Female</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="subject">Favourite Subject:</label>
                        <select name="subject" id="subject" class="form-control" value="${ student.favouriteSubject}">
                            <option value="MATHS" >Mathematics</option>
                            <option value="ARTS">Arts</option>
                            <option value="LANGUAGES">Foreign Languages</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="city">City:</label>
                        <input type="text" class="form-control" name="city" id="city" value="${ student.city }"/>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="country">Country:</label>
                        <input type="text" class="form-control" name="country" id="country" value="${ student.country}"/>
                    </div>

                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-warning" data-dismiss="modal">Cancel</a>
                    <input class="btn btn-primary" type="submit" value="Save Changes" id="btn-menu">
                </div>
            </div>
        </div>
    </form>
</div>

<div class="modal fade" id="modal-student-form" tabindex="-1" role="dialog" aria-labelledby="myModalLabelPermission" aria-hidden="true">
    <form role="form" id="form-permission">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title" id="myModalLabelPermission">
                        <i class="glyphicon glyphicon-picture"></i>&nbsp;&nbsp;EDIT STUDENT DETAILS
                    </h4>
                </div>
                <div class="modal-body" id="student-form-content">

                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-warning" data-dismiss="modal">Cancel</a>
                    <input class="btn btn-primary" type="submit"
                           value="Save Permissions">
                </div>
            </div>
        </div>
    </form>
</div>


<h2><%
    UserInfo user = (UserInfo) request.getSession().getAttribute("user");
    if (user == null) {
        response.sendRedirect("users/gologin");
    }
    %></h2>

<script type="text/javascript">


    function open_student_form()
    {
        var form = "form-newstudent-form";
        $("form#" + form)[0].reset();
        $('#student_form').modal('show');
    }


    function edit_student(id)
    {
        start_wait();
        $.ajax({
            url: 'doAction',
            type: 'post',
            data: {ACTION: 'GET_STUDENT', student_id: id},
            dataType: 'json',
            success: function (json) {
                stop_wait();
                var std = JSON.parse(json.data);
                console.log('Student*************>'+std.favouriteSubject)
                $('#student_id').val(std.id);
                $('#name').val(std.name);
                $('#gender').val(std.gender);
                $('#subject').val(std.favouriteSubject);
                $('#city').val(std.city);
                $('#country').val(std.country);

                $('#student_form').modal('show');
            }
        });

    }


    function remove_student(id)
    {
        bootbox.confirm("<p class='text-center'>Are you sure?</p>", function (result) {
            if (result)
            {
                $.ajax({
                    url: "doAction",
                    type: 'post',
                    data: {ACTION: 'DELETE_STUDENT', student_id: id},
                    dataType: 'json',
                    success: function (json) {
                        if (json.success)
                        {
                            setTimeout(function () {
                                location.reload();
                            }, 2000);

                        }
                        bootbox.alert('<p class="text-center">' + json.message + '</p>');
                    }
                });
            }
        });
    }



</script>
<jsp:include page="/includes/footer.jsp" />