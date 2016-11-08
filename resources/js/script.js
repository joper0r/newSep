var fab;
var clicker = 0;

$(function () {
//   alert("ja"); 
    fab = new Fab();
    fab.init();



    $('#logo').click(function () {
        clicker++;
        if (clicker == 6) {
            $("body").addClass('easteregg');
            $('.fullheightwidth').addClass('easteregg');
        }
    });
    
    $('.changebg').click(function() {
        $("body").removeClass("karlsruhe miami dresden");
        if ($(this).attr('data-bg')  != "")
        $("body").addClass($(this).attr('data-bg'));
        
        $('[data-remodal-id=settingsmodal]').remodal().close();
    });
});


function Fab() {
    button = $("#addwidget");
    menu = $("#widgetmenu");


    this.init = function () {
        button.click(function () {
            fab.toggle();
        });

        $("#unusedwidgets, #quicklinks").sortable({connectWith: ".sortable", start: function () {
                fab.hide();
            }, stop: function () {
                fab.show();
            }}).disableSelection();
        $("#left").sortable();
        $("#middle").sortable();
    }

    this.toggle = function () {
        if (button.hasClass("active"))
            fab.close();
        else
            fab.open();
    }

    this.open = function () {
        button.addClass("active");
        menu.addClass("active");
    }

    this.close = function () {
        button.removeClass("active");
        menu.removeClass("active");
    }

    this.hide = function () {
//        menu.css("display", "none");
    }

    this.show = function () {
//        menu.css("display", "block");
    }
}




angular.module('dashboard', [])
        .controller('dashboardController', function ($scope, $http) {
            var dashboard = this;

            $http.get('json/appsunused.json').
                    then(function (response) {
                        dashboard.unusedapps = response.data;
                    });

            $http.get('json/appsused.json').
                    then(function (response) {
                        dashboard.usedapps = response.data;
                    });

            $http.get('json/news.json').
                    then(function (response) {
                        dashboard.news = response.data;
                    });

            $http.get('json/dates.json').
                    then(function (response) {
                        dashboard.dates = response.data;
                    });
        });

