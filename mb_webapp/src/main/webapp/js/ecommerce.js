// Agency Theme JavaScript

(function($) {
    "use strict"; // Start of use strict

    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $('a.page-scroll').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });

    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function(){ 
            $('.navbar-toggle:visible').click();
    });

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

})(jQuery); // End of use strict


$("document").ready(function(){

    $("ul#reful li a").hover(function(){
                                        $(this).css("color", "#fed136");
                                    }, 
                            function(){
                                $(this).css("color", "rgba(254, 209, 54, 0.34)");
                                }
                            );

    $(".col-sm-4 img.img-circle").hover(function(){
                                            $(this).css("border-radius","48%");
                                        },
                                        function(){
                                            $(this).css("border-radius", "50%");
                                        }
                                    );
});

