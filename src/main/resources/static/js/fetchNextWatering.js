
function startCountDown() {
     var watering_date =  new Date(parseInt(wateringData.settings.nextWateringDate));
    console.log(watering_date);
    $('#example').countdown({
        date: watering_date,
        offset: -8,
        day: 'Day',
        days: 'Days'
    }, function() {

    });
}
function refreshWateringStatus(){
    if(wateringData.deviceData !== undefined){
        $("#watering_status").text(wateringData.deviceData.status);
        var text = isRunning() ? "Stop Watering" : "Start Watering";
        $('#watering_control_button').text(text);
        console.log("changing to "+text);
        startCountDown()
    }
    setTimeout(refreshWateringStatus, 1000);
}
function isRunning(){
    wateringData.deviceData.status === "RUNNING";
}

function wateringControl() {
    controlURL = "";

    if (isRunning()) {
        controlURL = "/control/off";
    } else {
        controlURL = "/control/on";
    }

    $.ajax({
        type: "PUT",
        dataType: "json",
        url: controlURL,
        success: function(result) {
            if(result.successful == true){

            }
        }
    });
}

$(document).ready(function(){
    refreshWateringData();
    refreshWateringStatus();
});