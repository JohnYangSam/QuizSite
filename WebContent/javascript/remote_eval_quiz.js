$(document).ready(function() {
    $("#send_button").click(function() {
    	var data_form = $("#question_form").serialize();
        $.ajax({
            type: "POST",
            url: "eval_quiz",
            data: data_form ,//{ to: $("#compose_to").val(), subject: $("#compose_subject").val(), body: $("#compose_body").val() }
        }).done(function( msg ) {
        	$("#send_result").hide();
            $("#send_result").html(msg);
            $("#send_result").show("slow");
        });
        return false;
    });
    
    });