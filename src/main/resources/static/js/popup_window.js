/*
    Acknowledgement
    This pop-up-window script comes from Jack Moore, http://www.jacklmoore.com/notes/jquery-modal-tutorial/
*/

var modal = (function(){
    var
    method = {},
    $overlay,
    $modal,
    $pcontent,
    $close;

// Center the modal in the viewport
method.center = function () {
    var top, left;

    top = Math.max($(window).height() - $modal.outerHeight(), 0) / 2;
    left = Math.max($(window).width() - $modal.outerWidth(), 0) / 2;

    $modal.css({
        top:top + $(window).scrollTop(),
        left:left + $(window).scrollLeft()
    });
};

// Open the modal
method.open = function (settings) {
    $pcontent.empty().append(settings.pcontent);

    $modal.css({
        width: settings.width || 'auto',
        height: settings.height || 'auto'
    });

    method.center();
    $(window).bind('resize.modal', method.center);
    $modal.show();
    $overlay.show();
};

// Close the modal
method.close = function () {
    $modal.hide();
    $overlay.hide();
    $pcontent.empty();
    $(window).unbind('resize.modal');
};

    // Generate the HTML and add it to the document
    $overlay = $('<div id="overlay"></div>');
    $modal = $('<div id="modal"></div>');
    $pcontent = $('<div id="pcontent"></div>');
    $close = $('<a id="close" href="#">close</a>');

    $modal.hide();
    $overlay.hide();
    $modal.append($pcontent, $close);

    $(document).ready(function(){
        $('body').append($overlay, $modal);
    });

    $close.click(function(e){
        e.preventDefault();
        method.close();
    });

    return method;
}());
