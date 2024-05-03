$(document).ready(function() {
    $("#login-btn").click(function(event) {
        event.preventDefault();

        var username = $("#login__username").val();
        var password = $("#login__password").val();

        $.ajax({
            url: '/member/sign_in_proc',
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                username: username,
                password: password
            }),
            dataType: 'json',
            success: function(response) {
                console.log(response);
                alert('로그인성공')
            },
            error: function(error) {
                console.error(error);
                alert('로그인실패')
            }
        });
    });
});