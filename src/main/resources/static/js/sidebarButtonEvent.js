document.addEventListener("DOMContentLoaded", function () {
  feather.replace();

  document
    .getElementById("file-text-icon")
    .addEventListener("click", function () {
      var sidebar = document.getElementById("lecture-sidebar");
      var mainContent = document.querySelector(".main-content");

      if (sidebar.style.left === "60px") {
        sidebar.style.left = "-420px";
        mainContent.classList.remove("content-reduced");
      } else {
        sidebar.style.left = "60px";
        mainContent.classList.add("content-reduced");
      }
    });
});

document.addEventListener("DOMContentLoaded", function () {
  feather.replace();

  var sidebarIcons = document.querySelectorAll(".nav-item.sidebar-icon");
  sidebarIcons.forEach(function (icon) {
    icon.addEventListener("click", function () {
      // 모든 아이콘에서 active 클래스 제거
      sidebarIcons.forEach(function (i) {
        i.classList.remove("active");
      });

      // 현재 클릭된 아이콘에 active 클래스 추가
      icon.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", function () {
  var writeBtn = document.querySelector(".course-title");

  writeBtn.addEventListener("click", function () {
    window.location.href = "/lms/course"; // 이동하려는 페이지의 URL
  });
});

document.addEventListener("DOMContentLoaded", function () {
  var writeBtn = document.querySelector(".write-btn");

  writeBtn.addEventListener("click", function () {
    window.location.href = "/lms/write"; // 이동하려는 페이지의 URL
  });
});

document
  .getElementById("dashboard-icon")
  .addEventListener("click", function () {
    window.location.href = "/lms/dashboard";
  });

document.getElementById("handong-icon").addEventListener("click", function () {
  window.location.href = "/lms/login";
});
