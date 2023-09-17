// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();

$('.hamburger').click(function (e) {
    $(this).parent().find('ol').toggleClass('active');
    $(this).toggleClass('active');
    $('.dimmer').toggleClass('active');
    $('body').toggleClass('no-scrolling');
});

$('li .icon-mobileMenu').click(function () {
    $(this).parent().toggleClass('active');
});