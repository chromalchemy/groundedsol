// When plain htmx isn't quite enough, you can stick some custom JS here.


// recaptcha callback to submit contact form
function submitContact() {
    // submit form classic style
    // document.getElementById("contact-form").requestSubmit();


    // create new event for :hx-trigger
    // const event = new Event('verified');
    // const elem = document.querySelector("#contact-form");
    // elem.dispatchEvent(event);

    // execute some code after the content has been inserted into the DOM
    htmx.ajax('POST', '/send-contact', {target:'#contact', swap:'outerHTML', source:'#contact-form'}).then(() => {
        // this code will be executed after the 'htmx:afterOnLoad' event,
        // and before the 'htmx:xhr:loadend' event
        console.log('Content inserted successfully!');
    });

    

}


//LIGHTBOX
$(document).ready(function () {
    $('.lightbox').nivoLightbox();

    $('.scroll-to-top').hide();
    //Check to see if the window is top if not then display button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.scroll-to-top').fadeIn();
        } else {
            $('.scroll-to-top').fadeOut();
        }
    });

    //submit contact form (after recaptcha)
    function onSubmit(token) {
        document.getElementById("contact-form").submit();
    }

    new WOW().init();

    //Live.heartbeat();

    //$('ul.slimmenu').slimmenu(
    //    {
    //        resizeWidth: '1024',
    //        collapserTitle: 'Main Menu',
    //        animSpeed: '300',
    //        easingEffect: null,
    //        indentChildren: true,
    //        childrenIndenter: '&nbsp;&nbsp;'
    //    }
    //);


    //SCROLL TO TOP
    $('.scroll-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 800);
        return false;
    });
});

// TICKER www.alexefish.com
(function ($) {
    $.fn.list_ticker = function (options) {
        var defaults = {
            speed: 8000,
            effect: 'slide'
        };
        var options = $.extend(defaults, options);
        return this.each(function () {
            var obj = $(this);
            var list = obj.children();
            list.not(':first').hide();
            setInterval(function () {
                list = obj.children();
                list.not(':first').hide();
                var first_li = list.eq(0)
                var second_li = list.eq(1)
                if (options.effect == 'slide') {
                    first_li.slideUp();
                    second_li.slideDown(function () {
                        first_li.remove().appendTo(obj);
                    });
                } else if (options.effect == 'fade') {
                    first_li.fadeOut(function () {
                        second_li.fadeIn();
                        first_li.remove().appendTo(obj);
                    });
                }
            }, options.speed)
        });
    };
})(jQuery);
$(function () {
    $('#ticker').list_ticker({
        speed: 8000,
        effect: 'fade'
    })
});
