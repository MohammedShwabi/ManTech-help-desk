// to collapse side bar
document.addEventListener("DOMContentLoaded", function() {
    // Get all the collapsible buttons
    const buttons = document.querySelectorAll(".side-header");

    // Add a click event listener to each button
    buttons.forEach(function(button) {
        button.addEventListener("click", function() {
            // Find the associated icon elements within this section
            const openIcon = this.querySelector(".open-icon");
            const closeIcon = this.querySelector(".close-icon");

            // Toggle the visibility of the icons
            openIcon.style.display = openIcon.style.display === "none" ? "inline-block" : "none";
            closeIcon.style.display = closeIcon.style.display === "none" ? "inline-block" : "none";
        });
    });
});

let delModelbox=document.getElementById('del-model-box');
function displayModel(){
    delModelbox.style.display="block";
}
function closeModel(){
    delModelbox.style.display="none";
}


const customSelect = document.querySelector(".custom-select");
const selectBtn = document.querySelector(".select-button");

const selectedValue = document.querySelector(".selected-value");
const optionsList = document.querySelectorAll(".select-dropdown li");

// add click event to select button
selectBtn.addEventListener("click", () => {
  // add/remove active class on the container element
  customSelect.classList.toggle("active");
  // update the aria-expanded attribute based on the current state
  selectBtn.setAttribute(
    "aria-expanded",
    selectBtn.getAttribute("aria-expanded") === "true" ? "false" : "true"
  );
});

optionsList.forEach((option) => {
  function handler(e) {
    // Click Events
    if (e.type === "click" && e.clientX !== 0 && e.clientY !== 0) {
      selectedValue.textContent = this.children[1].textContent;
      customSelect.classList.remove("active");
    }
    // Key Events
    if (e.key === "Enter") {
      selectedValue.textContent = this.textContent;
      customSelect.classList.remove("active");
    }
  }

  option.addEventListener("keyup", handler);
  option.addEventListener("click", handler);
});