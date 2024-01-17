document.addEventListener("DOMContentLoaded", function () {
  var loginBtn = document.querySelector(".login-btn");

  loginBtn.addEventListener("click", function () {
    window.location.href = "dashboard.html"; // 이동하려는 페이지의 URL
  });
});
