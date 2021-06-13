    $("#acc dd").hide();
    $("#acc dt").click(function () {
        $(this).next("#acc dd").slideToggle(300);
        $(this).toggleClass("expanded");
    });