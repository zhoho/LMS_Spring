document.addEventListener("DOMContentLoaded", function () {
  var cancelBtn = document.querySelector(".cancel-btn");

  cancelBtn.addEventListener("click", function () {
    var userResponse = confirm(
      "All information you've created so far will disappear. Do you want to continue?"
    );
    if (userResponse) {
      window.location.href = "course.html";
    }
  });
});