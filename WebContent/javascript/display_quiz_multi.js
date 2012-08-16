$(document).ready(function() {
  $("div.question").css("display", "none"); // Hide all
  $("div#question_num_0").css("display","block"); // Display 0th
  // Set click function for buttons
  $("button.button_next").click(function() {
    alert($(this).attr('data-next-question'));
    var next = parseInt($(this).attr('data-next-question'));
    $("div.question").css("display", "none"); // Hide all
    $("div#question_num_" + next).css("display","block");
    return false;
  });
});

