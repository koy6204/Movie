// $(document).ready(function() {
//
//     var loginForm = $('.form.login');
//
//     loginForm.submit(function(event) {
//
//         event.preventDefault();
//
//         var formData = {
//             username: $('#login__username').val(),
//             password: $('#login__password').val(),
//         };
//
//         $.ajax({
//             type: 'POST',
//             url: '/member/sign_in_proc',
//             contentType: 'application/json; charset=utf-8',
//             data: JSON.stringify(formData),
//             dataType: 'json',
//             success: function(data) {
//                 console.log('Success:', data);
//                 alert('로그인 완료되었습니다.');
//                 window.location.href = '/';
//             },
//             error: function(error) {
//                 console.error('Error:', error);
//                 alert('로그인에 실패했습니다. 입력 정보를 다시 확인해주세요');
//             }
//         });
//     });
// });

