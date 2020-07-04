function start_wait() {
    $('#wait-dialog').modal({
        backdrop: 'static',
        keyboard: false
    });
}
function stop_wait() {
    $('#wait-dialog').modal('hide');
}

$(document).ready(function () {

    //Logout
    $('#logout-li').click(function () {
        bootbox.confirm("<p class='text-center'>Are you sure?</p>", function (result) {
            if (result)
            {
                $.ajax({
                    url: "logout",
                    type: 'post',
                    data: null,
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
    });


    //POST LOGIN
    $('#form-sign-in').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your Username!!'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your password'
                    }
                }
            }
        }
    })
            .on('success.form.bv', function (e) {
                start_wait();
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                var form = "form-sign-in";
                $.ajax({
                    url: 'login',
                    type: 'post',
                    data: {
                        username: $('#username').val(),
                        password: $('#password').val()
                    },
                    dataType: 'json',
                    success: function (json) {
                        stop_wait();
                        console.log(json);
                        if (json.success)
                        {
                            $("form#" + form)[0].reset();
                            setTimeout(function () {
                                window.location.href = $('#base_url').val() + 'students/getStudents';
                            }, 2000);
                        }
                        bootbox.alert('<p class="text-center">' + json.message + '</p>');
                    }
                });
            });


    //POST NEW USER
    $('#form-sign-up').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: {
            fullname: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your full name!!'
                    }
                }
            },
            username: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your Username!!'
                    }
                }
            }, password: {
                validators: {
                    notEmpty: {
                        message: 'Please enter your password'
                    },
                    identical: {
                        field: 'password2',
                        message: 'Your passwords must match'
                    },
                    callback: {
                        message: 'Invalid password entered',
                        callback: function (value, validator, $field) {
                            if (value === '') {
                                return true;
                            }

                            // Check the password strength
                            if (value.length < 5 && 5 > 0) {
                                return {
                                    valid: false,
                                    message: 'It must be at least ' + 5 + ' characters long'
                                };
                            }

                            // The password doesn't contain any digit
                            if (value.search(/[0-9]/) < 0) {
                                return {
                                    valid: false,
                                    message: 'It must contain at least one digit'
                                }
                            }

                            return true;
                        }
                    }
                }
            },
            password2: {
                validators: {
                    notEmpty: {
                        message: 'Please confirm your password'
                    },
                    identical: {
                        field: 'password',
                        message: 'Your passwords must match'
                    },
                    callback: {
                        message: 'Invalid password entered',
                        callback: function (value, validator, $field) {
                            if (value === '') {
                                return true;
                            }

                            // Check the password strength
                            if (value.length < 5 && 5 > 0) {
                                return {
                                    valid: false,
                                    message: 'It must be at least ' + 5 + ' characters long'
                                };
                            }

                            // The password doesn't contain any digit
                            if (value.search(/[0-9]/) < 0) {
                                return {
                                    valid: false,
                                    message: 'It must contain at least one digit'
                                }
                            }

                            return true;
                        }
                    }
                }
            }
        }
    })
            .on('success.form.bv', function (e) {
                start_wait();
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                var form = "form-sign-up";
                $.ajax({
                    url: 'doSignup',
                    type: 'post',
                    data: {
                        fullname: $('#fullname').val(),
                        username: $('#username').val(),
                        password: $('#password').val()
                    },
                    dataType: 'json',
                    success: function (json) {
                        stop_wait();
                        console.log(json);
                        if (json.success)
                        {
                            $("form#" + form)[0].reset();
                            setTimeout(function () {
                                window.location.href = $('#base_url').val() + 'students/getStudents';
                            }, 2000);
                        }
                        bootbox.alert('<p class="text-center">' + json.message + '</p>');
                    }
                });
            });

    //POST NEW STUDENT
    $('#form-newstudent-form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: ':disabled',
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: 'Please enter Student Full Name!!'
                    }
                }
            },
            gender: {
                validators: {
                    notEmpty: {
                        message: 'Please enter gender '
                    }
                }
            },
            subject: {
                validators: {
                    notEmpty: {
                        message: 'Please enter favourite subject'
                    }
                }
            },
            city: {
                validators: {
                    notEmpty: {
                        message: 'Please enter city '
                    }
                }
            },
            country: {
                validators: {
                    notEmpty: {
                        message: 'Please enter country'
                    }
                }
            }
        }
    })
            .on('success.form.bv', function (e) {
                start_wait();
                // Prevent form submission
                e.preventDefault();
                // Get the form instance
                var form = "form-newstudent-form";
                $.ajax({
                    url: 'doAction',
                    type: 'post',
                    data: {
                        student_id : $('#student_id').val(),
                        ACTION: ($('#student_id').val() == null || $('#student_id').val() == '') ? 'NEW_STUDENT' : 'EDIT_STUDENT',
                        name: $('#name').val(),
                        gender: $('#gender').val(),
                        subject: $('#subject').val(),
                        city: $('#city').val(),
                        country: $('#country').val()
                    },
                    dataType: 'json',
                    success: function (json) {
                        stop_wait();
                        console.log(json);
                        if (json.success)
                        {
                            $("form#" + form)[0].reset();
                            setTimeout(function () {
                                location.reload();
                            }, 2000);
                        }
                        bootbox.alert('<p class="text-center">' + json.message + '</p>');
                    }
                });
            });

});