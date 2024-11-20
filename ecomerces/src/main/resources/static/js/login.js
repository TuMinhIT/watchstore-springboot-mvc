document.addEventListener("DOMContentLoaded", function () {
    var forms = document.querySelector("#submit_reg");
    forms.addEventListener("submit", function (event) {
        if (forms.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
            document.querySelector("#submit_btn").disabled = !true;
        }
        forms.classList.add("was-validated");
    });
});




