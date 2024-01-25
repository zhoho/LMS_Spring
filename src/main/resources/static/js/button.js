document.addEventListener("DOMContentLoaded", function () {
  var loginBtn = document.querySelector(".login-btn");

  loginBtn.addEventListener("click", function () {
    window.location.href = "dashboard";
  });
});
