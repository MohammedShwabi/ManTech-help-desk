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

