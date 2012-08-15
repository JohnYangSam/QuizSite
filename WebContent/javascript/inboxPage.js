$(document).ready(function() {
    $("td.respond > button").click(function() {
        $("#compose_to").val($(this).attr("data-to"));
        $("#compose_subject").val($(this).attr("data-subject"));
        window.location = "#compose";
    })
    $("#send_button").click(function() {
        $.ajax({
            type: "POST",
            url: "send_message",
            data: { to: $("#compose_to").val(), subject: $("#compose_subject").val(), body: $("#compose_body").val() }
        }).done(function( msg ) {
            $("#send_result").html(msg);
            $("#send_result").show("slow").delay(500).hide("slow");
        });
        return false;
    });
});