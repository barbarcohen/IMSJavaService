// I know this code is not beautiful, call me stupid and get over it...

var weather = {city:"Default", max_temp: [0, 0, 0, 0, 0, 0], cloudsPercentage: [0, 0, 0, 0, 0, 0], clouds: ["clear-day", "clear-day", "clear-day", "clear-day", "clear-day", "clear-day", ]};

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
    
    parseJSON(getDummyWeatherJSON());
    
    var temp_key = "_day_temp";
    var curr_temp_key;
    for (var i = 0; i < 6; i++) {
        curr_temp_key = "#" + i.toString() + temp_key;
        $(curr_temp_key).text(weather.max_temp[i] + " °C");
    }
    
    var weatherListNextDays = weather.clouds.slice(1,6); // ["clear-day", "clear-day", "clear-day", "clear-day", "clear-day", "clear-day", "clear-day", "clear-day", ];//[weather.clouds.slice(0,1)];
    var weatherListTomorrow = weather.clouds.slice(0,1); //[weather.clouds.slice(1,6)];
    
    console.log(weatherListTomorrow + "\n" + weatherListNextDays);
    renderWeatherIcons(weatherListTomorrow, weatherListNextDays);
};


function getDummyJson() {
    return '{"nextWateringDate":1473769468250,"status":"stopped"}';
}

function parseJSON(weatherJSONString){
    dayMaxTemp = [0, 0, 0, 0, 0, 0];
    var i;
    
    var weatherJSON = JSON.parse(weatherJSONString);
    weather.city = weatherJSON.city.name;
    
    $.each(weatherJSON.list, function(i, item) {
        if(i>48){
            return;
        }
        
        if (weather.max_temp[Math.floor(i / 8)] < item.main.temp_max){
            weather.max_temp[Math.floor(i / 8)] = item.main.temp_max;
        }
        
        weather.cloudsPercentage[Math.floor(i / 8)] += (parseInt(item.clouds.all) / 8);
    });
    
    for(var i=0; i < weather.max_temp.length; i++){
        weather.max_temp[i] = Math.round(weather.max_temp[i] / 10);
    }
    
    for(var i=0; i< weather.cloudsPercentage.length; i++){
        if(weather.cloudsPercentage[i] < 25){
            weather.clouds[i] = "clear-day";
        } else if (weather.cloudsPercentage[i] < 75){
            weather.clouds[i] = "partly-cloudy-day";
        } else{
            weather.clouds[i] = "cloudy";
        }
    }
    
    console.log(weather)
}

function getNextDays(){
    
}

function renderWeatherIcons(weatherTomorrow, weatherNextDays) {
    var icons = new Skycons({
            "color": "#fcb216"
        }),
        list = [
            "partly-cloudy-day"
        ],
        i;

    for (i = list.length; i--;)
        icons.set(list[i], weatherTomorrow[i % weatherTomorrow.length]);
    icons.play();

    var icons = new Skycons({
            "color": "#000"
        }),
        list = [
            "clear-night", "partly-cloudy-night", "cloudy", "clear-day", "sleet", "snow", "wind", "fog"
        ],
        i;

    for (i = list.length; i--;)
        icons.set(list[i], weatherNextDays[i  % weatherNextDays.length]);
    icons.play();
}

$(document).ready(updateWeatherForecast());

function getDummyWeatherJSON() {
    return '{"city":{"id":3078610,"name":"Brno","coord":{"lat":49.195221,"lon":16.60796}},"country":null,"message":"0.0312","list":[{"dt":1473778800,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":301.3,"humidity":45,"pressure":992.17,"temp_min":300.506,"temp_max":301.3},"dt_txt":"2016-09-13 15:00:00"},{"dt":1473789600,"clouds":{"all":8},"rain":{"3h":0},"main":{"temp":294.56,"humidity":69,"pressure":991.93,"temp_min":293.966,"temp_max":294.56},"dt_txt":"2016-09-13 18:00:00"},{"dt":1473800400,"clouds":{"all":24},"rain":{"3h":0},"main":{"temp":290.84,"humidity":73,"pressure":992.27,"temp_min":290.448,"temp_max":290.84},"dt_txt":"2016-09-13 21:00:00"},{"dt":1473811200,"clouds":{"all":8},"rain":{"3h":0},"main":{"temp":289.71,"humidity":79,"pressure":991.94,"temp_min":289.514,"temp_max":289.71},"dt_txt":"2016-09-14 00:00:00"},{"dt":1473822000,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":287.782,"humidity":80,"pressure":991.52,"temp_min":287.782,"temp_max":287.782},"dt_txt":"2016-09-14 03:00:00"},{"dt":1473832800,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":290.536,"humidity":76,"pressure":991.52,"temp_min":290.536,"temp_max":290.536},"dt_txt":"2016-09-14 06:00:00"},{"dt":1473843600,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":299.21,"humidity":51,"pressure":991.66,"temp_min":299.21,"temp_max":299.21},"dt_txt":"2016-09-14 09:00:00"},{"dt":1473854400,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":300.738,"humidity":42,"pressure":990.98,"temp_min":300.738,"temp_max":300.738},"dt_txt":"2016-09-14 12:00:00"},{"dt":1473865200,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":300.261,"humidity":41,"pressure":989.8,"temp_min":300.261,"temp_max":300.261},"dt_txt":"2016-09-14 15:00:00"},{"dt":1473876000,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":293.774,"humidity":54,"pressure":989.78,"temp_min":293.774,"temp_max":293.774},"dt_txt":"2016-09-14 18:00:00"},{"dt":1473886800,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":291.264,"humidity":63,"pressure":989.95,"temp_min":291.264,"temp_max":291.264},"dt_txt":"2016-09-14 21:00:00"},{"dt":1473897600,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":288.339,"humidity":80,"pressure":989.77,"temp_min":288.339,"temp_max":288.339},"dt_txt":"2016-09-15 00:00:00"},{"dt":1473908400,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":285.772,"humidity":89,"pressure":989.22,"temp_min":285.772,"temp_max":285.772},"dt_txt":"2016-09-15 03:00:00"},{"dt":1473919200,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":288.005,"humidity":81,"pressure":989.13,"temp_min":288.005,"temp_max":288.005},"dt_txt":"2016-09-15 06:00:00"},{"dt":1473930000,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":296.837,"humidity":54,"pressure":989.14,"temp_min":296.837,"temp_max":296.837},"dt_txt":"2016-09-15 09:00:00"},{"dt":1473940800,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":299.814,"humidity":45,"pressure":987.85,"temp_min":299.814,"temp_max":299.814},"dt_txt":"2016-09-15 12:00:00"},{"dt":1473951600,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":299.276,"humidity":41,"pressure":986.42,"temp_min":299.276,"temp_max":299.276},"dt_txt":"2016-09-15 15:00:00"},{"dt":1473962400,"clouds":{"all":8},"rain":{"3h":0},"main":{"temp":295.542,"humidity":45,"pressure":986.47,"temp_min":295.542,"temp_max":295.542},"dt_txt":"2016-09-15 18:00:00"},{"dt":1473973200,"clouds":{"all":44},"rain":{"3h":0},"main":{"temp":294.078,"humidity":53,"pressure":986.28,"temp_min":294.078,"temp_max":294.078},"dt_txt":"2016-09-15 21:00:00"},{"dt":1473984000,"clouds":{"all":12},"rain":{"3h":0},"main":{"temp":292.536,"humidity":68,"pressure":985.4,"temp_min":292.536,"temp_max":292.536},"dt_txt":"2016-09-16 00:00:00"},{"dt":1473994800,"clouds":{"all":0},"rain":{"3h":0},"main":{"temp":290.835,"humidity":80,"pressure":985.09,"temp_min":290.835,"temp_max":290.835},"dt_txt":"2016-09-16 03:00:00"},{"dt":1474005600,"clouds":{"all":32},"rain":{"3h":0},"main":{"temp":290.309,"humidity":83,"pressure":985.42,"temp_min":290.309,"temp_max":290.309},"dt_txt":"2016-09-16 06:00:00"},{"dt":1474016400,"clouds":{"all":76},"rain":{"3h":0},"main":{"temp":293.207,"humidity":65,"pressure":985.74,"temp_min":293.207,"temp_max":293.207},"dt_txt":"2016-09-16 09:00:00"},{"dt":1474027200,"clouds":{"all":76},"rain":{"3h":0},"main":{"temp":294.12,"humidity":53,"pressure":985.57,"temp_min":294.12,"temp_max":294.12},"dt_txt":"2016-09-16 12:00:00"},{"dt":1474038000,"clouds":{"all":48},"rain":{"3h":0},"main":{"temp":294.549,"humidity":50,"pressure":984.72,"temp_min":294.549,"temp_max":294.549},"dt_txt":"2016-09-16 15:00:00"},{"dt":1474048800,"clouds":{"all":64},"rain":{"3h":0},"main":{"temp":291.556,"humidity":60,"pressure":984.74,"temp_min":291.556,"temp_max":291.556},"dt_txt":"2016-09-16 18:00:00"},{"dt":1474059600,"clouds":{"all":36},"rain":{"3h":0},"main":{"temp":289.6,"humidity":64,"pressure":984.75,"temp_min":289.6,"temp_max":289.6},"dt_txt":"2016-09-16 21:00:00"},{"dt":1474070400,"clouds":{"all":80},"rain":{"3h":0},"main":{"temp":287.52,"humidity":86,"pressure":984.42,"temp_min":287.52,"temp_max":287.52},"dt_txt":"2016-09-17 00:00:00"},{"dt":1474081200,"clouds":{"all":92},"rain":{"3h":1},"main":{"temp":286.673,"humidity":96,"pressure":983.61,"temp_min":286.673,"temp_max":286.673},"dt_txt":"2016-09-17 03:00:00"},{"dt":1474092000,"clouds":{"all":92},"rain":{"3h":1},"main":{"temp":286.897,"humidity":99,"pressure":983.55,"temp_min":286.897,"temp_max":286.897},"dt_txt":"2016-09-17 06:00:00"},{"dt":1474102800,"clouds":{"all":92},"rain":{"3h":1},"main":{"temp":287.562,"humidity":100,"pressure":984.1,"temp_min":287.562,"temp_max":287.562},"dt_txt":"2016-09-17 09:00:00"},{"dt":1474113600,"clouds":{"all":92},"rain":{"3h":0},"main":{"temp":288.926,"humidity":100,"pressure":984.33,"temp_min":288.926,"temp_max":288.926},"dt_txt":"2016-09-17 12:00:00"},{"dt":1474124400,"clouds":{"all":76},"rain":{"3h":0},"main":{"temp":289.96,"humidity":89,"pressure":984.82,"temp_min":289.96,"temp_max":289.96},"dt_txt":"2016-09-17 15:00:00"},{"dt":1474135200,"clouds":{"all":44},"rain":{"3h":0},"main":{"temp":288.049,"humidity":91,"pressure":985.9,"temp_min":288.049,"temp_max":288.049},"dt_txt":"2016-09-17 18:00:00"},{"dt":1474146000,"clouds":{"all":44},"rain":{"3h":0},"main":{"temp":287.19,"humidity":88,"pressure":986.27,"temp_min":287.19,"temp_max":287.19},"dt_txt":"2016-09-17 21:00:00"}]}';
}