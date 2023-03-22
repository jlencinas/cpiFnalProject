// add hovered class to selected list item
let list = document.querySelectorAll(".navigation li");

function activeLink() {
  list.forEach((item) => {
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
  navigation.classList.toggle("active");
  main.classList.toggle("active");
};





// Open Email Form
function openEmailForm() {
  document.getElementById("emailForm").classList.add("active");
  document.querySelector(".modal-overlay").classList.add("show");
}

// Close Email Form
function closeEmailForm() {
  document.getElementById("emailForm").classList.remove("active");
  document.querySelector(".modal-overlay").classList.remove("show");
}

// Open Password Form
function openPasswordForm() {
  document.getElementById("passwordForm").classList.add("active");
  document.querySelector(".modal-overlay").classList.add("show");
}

// Close Password Form
function closePasswordForm() {
  document.getElementById("passwordForm").classList.remove("active");
  document.querySelector(".modal-overlay").classList.remove("show");
}




