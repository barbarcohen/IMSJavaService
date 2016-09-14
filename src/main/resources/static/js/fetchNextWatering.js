var wateringData = {};

//refreshing watering data every 2 seconds
function refreshWateringData(){
    $.ajax({
        dataType: "json",
        url: "/control/status"
    }).success(function(data) {
        wateringData = data;
        //updateNextWatering();
        setTimeout(refreshWateringData, 1000);
    });
}

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


$(document).ready(function(){
    //updateNextWatering();
    refreshWateringData();
});