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
      // ��� �����ܿ��� active Ŭ���� ����
      sidebarIcons.forEach(function (i) {
        i.classList.remove("active");
      });

      // ���� Ŭ���� �����ܿ� active Ŭ���� �߰�
      icon.classList.add("active");
    });
  });
});

document.addEventListener("DOMContentLoaded", function () {
  var writeBtn = document.querySelector(".course-title");

  writeBtn.addEventListener("click", function () {
    window.location.href = "course.html"; // �̵��Ϸ��� �������� URL
  });
});

document.addEventListener("DOMContentLoaded", function () {
  var writeBtn = document.querySelector(".write-btn");

  writeBtn.addEventListener("click", function () {
    window.location.href = "write.html"; // �̵��Ϸ��� �������� URL
  });
});

document
  .getElementById("dashboard-icon")
  .addEventListener("click", function () {
    window.location.href = "dashboard.html";
  });

document.getElementById("handong-icon").addEventListener("click", function () {
  window.location.href = "login.html";
});
