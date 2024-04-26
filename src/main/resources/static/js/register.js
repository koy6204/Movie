// let index={
//     init: function() {
//         $("#btn-save").on("click", () => {this.save();});
//
//     },
//
//     save: function(){
//         let data={
//             username: $("#register__username").val(),
//             password: $("#register__password").val(),
//             email: $("#email").val(),
//
//         };
//
//
//         $.ajax({
//             type:"POST",
//             url:"/member/sign_up_proc",
//             data:JSON.stringify(data),
//             contentType:"application/json; charset=utf-8",
//             dataType:"json"
//         }).done(function(resp){
//             if(resp.status == 400) {
//                 alert("회원가입 입력 정보를 다시 확인해주세요")
//             }
//             else {
//                 alert("회원가입이 완료되었습니다.");
//                 location.href = "/member/sign_in";
//             }
//
//         }).fail(function(error){
//             alert(JSON.stringify(error));
//
//         });
//
//     },
//
// }
//
//
// index.init();

// document.addEventListener('DOMContentLoaded', function() {
//     var registerForm = document.querySelector('.form.register');
//
//     registerForm.addEventListener('submit', function(event) {
//         event.preventDefault();
//
//         // 폼 데이터를 JSON 객체로 변환
//         var formData = {
//             username: document.getElementById('register__username').value,
//             password: document.getElementById('register__password').value,
//             email: document.getElementById('email').value
//         };
//
//         // Fetch API를 사용하여 서버에 POST 요청 전송
//         fetch('/member/sign_up_proc', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json; charset=utf-8'
//             },
//             body: JSON.stringify(formData)
//         })
//             .then(function(response) {
//                 if (!response.ok) {
//                     throw new Error('Network response was not ok');
//                 }
//                 return response.json();
//             })
//             .then(function(data) {
//                 console.log('Success:', data);
//
//                 alert("회원가입이 완료되었습니다.");
//                 window.location.href = "/member/sign_in";
//             })
//             .catch(function(error) {
//                 console.error('Error:', error);
//
//                 alert("회원가입 중 오류가 발생했습니다. 다시 시도해주세요.");
//             });
//     });
// });
$(document).ready(function() {

    var registerForm = $('.form.register');

    registerForm.submit(function(event) {

        event.preventDefault();

        var formData = {
            username: $('#register__username').val(),
            password: $('#register__password').val(),
            email: $('#email').val()
        };

        $.ajax({
            type: 'POST',
            url: '/member/sign_up_proc',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(formData),
            dataType: 'json',
            success: function(data) {
                console.log('Success:', data);
                alert('회원가입이 완료되었습니다.');
                window.location.href = '/member/sign_in';
            },
            error: function(error) {
                console.error('Error:', error);
                alert('회원가입 중 오류가 발생했습니다. 다시 시도해주세요.');
            }
        });
    });
});
