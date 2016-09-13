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

function getWateringJson(callback) {
    $.ajax({
        type: "GET",
        dataType: "json",
        url: "/control/status",
        success: function(data) {
            callback(data)
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
        success: function(result) {
            $('#watering_control_button').text(wateringStatus ? "Stop Watering" : "Start Watering");
            alert("Switched the watering " + (wateringStatus ? "on" : "off"));
            wateringStatus = !wateringStatus;
        }
    });
}

function getDummyJson() {
    return '{"nextWateringDate":1473855868000,"status":"on"}';
}

function updateNextWatering() {
    var json = getWateringJson(function(obj){
        var date = new Date(parseInt(obj.settings.nextWatering));
        //FIXME fetch wattering status periodically in different call
        if(obj.deviceData.status === "RUNNING"){
            wateringStatus = true;
        } else {
            wateringStatus = false;
        }

        startCountDown(date);
        $("#watering_status").text("Watering: " + obj.deviceData.status);
    });
    

};


$(document).ready(updateNextWatering());