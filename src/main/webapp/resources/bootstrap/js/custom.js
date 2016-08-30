/* 
 * When Hymnn Radio Button is selected
 */
$("#hymnLinkRadio*").click(function () {
    var number = $(this).attr("num");
    var text = $(this).closest('td').next("td").html();
    $("#pTitle").html(text);
    $("#pTitle").attr('class', 'titlePostSel');
    $("#playHymn*").attr('num', number);
    $("#PDFScore*").attr('num', number);
    $("#MusScore*").attr('num', number);
    $("#downloadHymn*").data('num', number);
    $("#topButton*").toggleClass('disabled', false);
    $("div.hymns-tabsShow").css("visibility", "visible");
    $("div.hymns-tabHide").css("display", "none");
    $("div.hymns-tabHide").css("visibility", "hidden");
    $(".audioControlsShow").css("visibility", "visible");
    $(".audioControlsShow").css("display", "block");
    $(".controlsTable").css("visibility", "visible");
    $(".controlsTable").css("display", "block");
});

$(".audioControlsShow").click(function () {
    var toggleValue = $(".audioControlsShow").attr("toggle");

    if (toggleValue == "off") {//Not being displayed
        $(".audioControlsShow").attr("toggle", "on");
        $(".audioControlsShow").text("Hide Controls").append("&nbsp;&nbsp;").append("<i class='fa fa-caret-up fa-3'></i>");
        $(".audioControlsShow").prepend("<i class='fa fa-caret-up fa-3'></i>&nbsp;&nbsp;")
        $("#controlsTable").css("visibility", "visible");
        $("#controlsTable").css("display", "block");
    } else { // on // Are being displayed
        $(".audioControlsShow").attr("toggle", "off");
        $(".audioControlsShow").text("Show Controls").append("&nbsp;&nbsp;").append("<i class='fa fa-caret-down fa-3'></i>");
        $(".audioControlsShow").prepend("<i class='fa fa-caret-down fa-3'></i>&nbsp;&nbsp;")
        $("#controlsTable").css("visibility", "hidden");
        $("#controlsTable").css("display", "none");
    }
});

$("#playHymn*").click(function () {
    var number = $(this).attr('num');
    var voice = $(this).attr('voice');
    var type = $(this).attr('type');
    var action = $(this).attr('action');

    //Build Path
    var path = "mp3s/" + number + "/" + getType(type) + "/" + getVoice(voice) + ".mp3";
    //var path = "includes/mp3s" + "/" + getVoice(voice);
    if (action == "p") {
        var player = document.getElementById('audioPlayer');
        player.src = path;

        player.load(); //just start buffering (preload)
        player.play(); //start playing
    } else {
        $(this).attr('href', path);
    }
});

$("#PDFScore*").click(function () {
    var number = $(this).attr('num');
    var voice = $(this).attr('voice');

    //Build Path
    var path = "scores/" + number + "/PDF/" + getVoice(voice) + ".pdf";
    //var path = "includes/mp3s" + "/" + getVoice(voice);
    $(this).attr('href', path);
});

$("#MusScore*").click(function () {
    var number = $(this).attr('num');
    var voice = $(this).attr('voice');

    //Build Path
    var path = "scores/" + number + "/MuseScore/";
    //var path = "includes/mp3s" + "/" + getVoice(voice);
    $(this).attr('href', path);
});

function getVoice(voice) {
    if (voice == 's')
        return "Soprano";
    else if (voice == "a")
        return "Alto";
    else if (voice == "t")
        return "Tenor";
    else
        return "Bass";
}

function getType(type) {
    if (type == 's')
        return "Solo";
    else if (type == "n")
        return "Normal";
    else
        return "Emph"
}

$(function () {
    $(".dropdown-menu > li > a.trigger").on("click", function (e) {
        var current = $(this).next();
        var grandparent = $(this).parent().parent();
        if ($(this).hasClass('left-caret') || $(this).hasClass('right-caret'))
            $(this).toggleClass('right-caret left-caret');
        grandparent.find('.left-caret').not(this).toggleClass('right-caret left-caret');
        grandparent.find(".sub-menu:visible").not(current).hide();
        current.toggle();
        e.stopPropagation();
    });
    $(".dropdown-menu > li > a:not(.trigger)").on("click", function () {
        var root = $(this).closest('.dropdown');
        root.find('.left-caret').toggleClass('right-caret left-caret');
        root.find('.sub-menu:visible').hide();
    });
});

var audio;
//jInit is my own site standard which is triggered after aynschronous loading of javascript
//libraries. You can here use $(document).ready instead, in general case.
$(document).ready(function () {

    var windowLoc = $(location).attr('pathname');
    if (windowLoc.indexOf("hymns.php") > -1) {
        (dod)
        audio = $("#audioPlayer");
        addEventHandlers();
    } else if (windowLoc.indexOf("input_") > -1) {
        $("#cancelButton").click(function () {
            alert("We are here")
            /* Single line Reset function executes on click of Reset Button */
            $("#inputForm")[0].reset();
        });
        $('#username').val(' ').val('');
        $('#hashed_password').val(' ').val('');
    }
});


function addEventHandlers() {
    $("#audBtnStart").click(startAudio);
    $("#audBtnStop").click(stopAudio);
    $("#audBtnPause").click(pauseAudio);
    $("#audBtnSlow").click(slowAudio);
    $("#audBtnFast").click(fastAudio);
    $("#audBtnBack").click(backAudio);
    $("#audBtnForward").click(forwardAudio);
    $("#audBtnVolUp").click(volumeUp);
    $("#audBtnVolDown").click(volumeDown);
    $("#audBtnVolToggle").click(toggleMuteAudio);
    $("#audBtnTottleRepeat").click(toggleRepeatAudio);

}

function cancelButton() {
    $.each($('input, select ,textarea', '#calculateform'), function (k) {
        alert(k + ' ' + $(this).attr('name'));
    });

}

function startAudio() {
    //         alert("startAudio");
    audio.trigger('play');
}

function slowAudio() {
    //        alert("slowAudio");
    audio.trigger('pause');
    audio.prop("playbackRate", audio.prop("playbackRate") - .01);
    audio.trigger('play');
}

function fastAudio() {
    //        alert("fastAudio");
    audio.trigger('pause');
    audio.prop("playbackRate", audio.prop("playbackRate") + .01);
    audio.trigger('play');
}

function pauseAudio() {
    //         alert("pauseAudio");
    audio.trigger('pause');
}

function stopAudio() {
    //           alert("stopAudio");
    pauseAudio();
    audio.prop("currentTime", 0);
}

function forwardAudio() {
    //         alert("foward");
    audio.trigger('pause');
    audio.prop("currentTime", audio.prop("currentTime") + 5);
    audio.trigger('play');
}

function backAudio() {
    //           alert("back");
    pauseAudio();
    audio.prop("currentTime", audio.prop("currentTime") - 5);
    startAudio();
}

function volumeUp() {
    //        alert("Volume up");
    var volume = audio.prop("volume") + 0.2;
    if (volume > 1) {
        volume = 1;
    }
    audio.prop("volume", volume);
}

function volumeDown() {
    //          alert("Volume Down");
    var volume = audio.prop("volume") - 0.2;
    if (volume < 0) {
        volume = 0;
    }
    audio.prop("volume", volume);
}

function toggleMuteAudio() {
    if (audio.prop("muted") == true) {
        $("#audBtnVolToggle").text("Volume").append("&nbsp;&nbsp").append($("<i class='fa fa-toggle-on'></i>"));
    } else {
        $("#audBtnVolToggle").text("Volume").append("&nbsp;&nbsp").append($("<i class='fa fa-toggle-off'></i>"));
    }
    audio.prop("muted", !audio.prop("muted"));
}

function toggleRepeatAudio() {
    //          alert("Repeat");

    if (audio.prop("loop") == false) {
        $("#audBtnTottleRepeat").text('').append("<i class='fa fa-refresh'></i>").append("&nbsp;&nbsp").append($("<i class='fa fa-toggle-on'></i>"));
    } else {
        $("#audBtnTottleRepeat").text('').append("<i class='fa fa-refresh'></i>").append("&nbsp;&nbsp").append($("<i class='fa fa-toggle-off'></i>"));
    }
    audio.prop("loop", !audio.prop("loop"));
}