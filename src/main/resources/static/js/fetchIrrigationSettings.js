var dummyData = '{"settings":{"deviceId":null,"nextWatering":1473834845516,"delay":1,"wateringDuration":5,"minHumidityThreshold":10,"forceIrrigation":false,"forecastHours":24},"deviceData":{"status":"STOPPED","humidity":0.0}}';

function getIrrigationSettingsData(){
    return dummyData;
}

function setIrrigationData(){
    var irrSettJSON = JSON.parse(getIrrigationSettingsData());
    var nextWatering = new Date(irrSettJSON.settings.nextWatering);
    $("#td_11").text(nextWatering.toLocaleTimeString());
    $("#td_12").text(irrSettJSON.settings.delay);
    $("#td_13").text(irrSettJSON.settings.wateringDuration);
    $("#td_14").text(irrSettJSON.settings.forceIrrigation);
    $("#td_15").text(irrSettJSON.settings.minHumidityThreshold);
}


$(document).ready(setIrrigationData());