var wateringStatus = false;

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

function wateringControl() {
    controlURL = "";

    if (wateringStatus) {
        controlURL = "/control/on";
    } else {
        controlURL = "/control/off";
    }

    $.ajax({
        type: "PUT",
        dataType: "json",
        url: controlURL,
        success: function() {
            $('#watering_control_button').text(watering ? "Stop Watering" : "Start Watering");
            alert("Switched the watering " + (watering ? "on" : "off"));
            wateringStatus = !wateringStatus;
        }
    });
}

function getDummyJson() {
    return '{"nextWateringDate":1473855868000,"status":"on"}';
}

function updateNextWatering() {
    var json = getWateringJson();
    
    obj = JSON.parse(json);
    
    var date = new Date(parseInt(obj.nextWateringDate));
    if(obj.status === "running"){
        wateringStatus = true;
    } else {
        wateringStatus = false;
    }

    startCountDown(date);
    $("#watering_status").text("Watering: " + obj.status);
};


$(document).ready(updateNextWatering());