
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
function refreshWateringStatus(){
    if(wateringData.deviceData !== undefined){
        $("#watering_status").text(wateringData.deviceData.status);
        console.log("refeshing status");

    }
    setTimeout(refreshWateringStatus, 1000);
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

    refreshWateringData();
    refreshWateringStatus();
});