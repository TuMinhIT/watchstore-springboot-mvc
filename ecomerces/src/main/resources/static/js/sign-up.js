document.addEventListener("DOMContentLoaded", function () {
  var form = document.querySelector("#submit_reg");
  var submitButton = document.querySelector("#submit_btn");
  var passwordInput = document.querySelector("#reg_password");
  var passwordAgainInput = document.querySelector("#reg_password_again");
  var errorMessage = document.querySelector(".text-muted");

  form.addEventListener("submit", function (event) {
    // Check form validity
    if (form.checkValidity() === false) {
      event.preventDefault();
      event.stopPropagation();
    } else {
      // Additional check for password match before submitting
      validatePasswords();
    }

    form.classList.add("was-validated");
  });

  passwordAgainInput.addEventListener("input", validatePasswords);

  function validatePasswords() {
    var password = passwordInput.value;
    var passwordAgain = passwordAgainInput.value;

    if (password !== passwordAgain) {
      errorMessage.style.display = "block";
      submitButton.disabled = true;
    } else {
      errorMessage.style.display = "none";
      submitButton.disabled = false;
    }
  }

  function togglePasswordVisibility() {
    var isChecked = document.getElementById("showPasswordCheck").checked;
    passwordInput.type = isChecked ? "text" : "password";
    passwordAgainInput.type = isChecked ? "text" : "password";
  }

  document
      .getElementById("showPasswordCheck")
      .addEventListener("click", togglePasswordVisibility);
});

