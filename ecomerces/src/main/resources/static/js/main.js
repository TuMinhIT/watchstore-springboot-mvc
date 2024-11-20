let slideIndex = 0;
showSlides();

function showSlides() {
  let i;
  let slides = document.getElementsByClassName("Slides");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {
    slideIndex = 1;
  }
  slides[slideIndex - 1].style.display = "block";
  setTimeout(showSlides, 5000);
}

let currentSlide = 0;
const slides = document.querySelector(".ps-slides");
const totalSlides = document.querySelectorAll(".ps-slide").length;

function moveSlide(direction) {
  currentSlide = (currentSlide + direction + totalSlides) % totalSlides;
  const offset = -currentSlide * (totalSlides + 8);
  slides.style.transform = `translateX(${offset}%)`;
}

setInterval(() => {
  moveSlide(1);
}, 3500);


window.addEventListener("load", function () {
  var successMessage = document.getElementById("successAlert");

  if (successMessage && successMessage.style.display !== "none") {
    successMessage.classList.add("show");

    setTimeout(function () {
      successMessage.classList.remove("show");
    }, 3000);
  }
});




