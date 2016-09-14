//refreshing watering data every 2 seconds
var wateringData = {};

function refreshWateringData(){
    $.ajax({
        dataType: "json",
        url: "/control/status"
    }).success(function(data) {
        wateringData = data;
        //updateNextWatering();
        setIrrigationData();
        setTimeout(refreshWateringData, 1000);
    });
}

function setIrrigationData(){
    $("#td_11").text(wateringData.settings.nextWateringDateText);
    $("#td_12").text(wateringData.settings.delay);
    $("#td_13").text(wateringData.settings.wateringDuration);
    $("#td_14").text(wateringData.settings.forceIrrigation);
    $("#td_15").text(wateringData.settings.minHumidityThreshold);
}


$(document).ready(refreshWateringData());