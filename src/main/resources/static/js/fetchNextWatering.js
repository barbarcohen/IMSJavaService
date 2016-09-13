function startCountDown(watering_date) {
    $('#example').countdown({
        date: watering_date,
        offset: -8,
        day: 'Day',
        days: 'Days'
    }, function() {
        alert('Done!');
    });
}

function getWateringJson() {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/control/status",
        success: function() {
            return data;
        }
    });
}

function wateringControl(watering) {
    controlURL = "";

    if (watering) {
        controlURL = "/control/on";
    } else {
        controlURL = "/control/off";
    }

    $.ajax({
        type: "PUT",
        dataType: "json",
        url: controlURL,
        success: function() {
            alert("Switched the watering " + (watering ? "on" : "off"));
        }
    });
    
    alert("blbl");
}

function getDummyJson() {
    return '{"nextWateringDate":1473855868000,"status":"stopped"}';
}

function updateNextWatering() {
    var json = getDummyJson();
    obj = JSON.parse(json);
    var date = new Date(parseInt(obj.nextWateringDate));
    startCountDown(date);
    $("#watering_table tr:last td:first").before("<td><h3>Watering: " + obj.status + "</h3></td>");
};


$(document).ready(updateNextWatering());