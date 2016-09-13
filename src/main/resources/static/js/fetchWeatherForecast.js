function updateWeatherForecast() {
    //var json = getDummyJson();
    //obj = JSON.parse(json);
    //var date = new Date(parseInt(obj.nextWateringDate));

    var city = "Brno";

    $("#city_weather_report").text(city + " Weather Report");

    var weatherCanvas = $('<canvas/ id = "clear-day">', {
        'id': 'clear-day'
    }).width(40).height(40);
    $("#some_li").prepend('<figure class="icons"><canvas id="snow" width="40" height="40"></canvas></figure>');

    var temp_key = "_day_temp";
    var curr_temp_key;
    for (var i = 0; i < 6; i++) {
        curr_temp_key = "#" + i.toString() + temp_key;
        $(curr_temp_key).text("18 °C");
    }
    
    var weatherList = ["clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night", "clear-night"];
    
    renderWeatherIcons(weatherList);
};


function getWeatherJSON() {
    return '{"nextWateringDate":1473769468250,"status":"stopped"}';
}


function getDummyJson() {
    return '{"nextWateringDate":1473769468250,"status":"stopped"}';
}

function renderWeatherIcons(weatherList) {
    var icons = new Skycons({
            "color": "#fcb216"
        }),
        list = [
            "partly-cloudy-day"
        ],
        i;

    for (i = list.length; i--;)
        icons.set(list[i], list[i]);
    icons.play();

    var icons = new Skycons({
            "color": "#000"
        }),
        list = [
            "clear-night", "partly-cloudy-night", "cloudy", "clear-day", "sleet", "snow", "wind", "fog"
        ],
        i;

    for (i = list.length; i--;)
        icons.set(list[i], weatherList[i]);
    icons.play();
}

$( document).ready(updateWeatherForecast());