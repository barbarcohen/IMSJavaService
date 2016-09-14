var humidityValues = [];
var nReloads = 0;
var currentHumidity = 0;

var graph = Morris.Line({
    element: 'graph1',
    data: data(0),
    xkey: 'x',
    ykeys: ['y', 'z'],
    labels: ['sin()', 'cos()'],
    parseTime: false,
    ymin: 0.0,
    ymax: 100.0,
    hideHover: true
});

function data(offset) {
    var ret = [];
    for (var x = 0; x <= 360; x += 10) {
        var v = (offset + x) % 360;
        ret.push({
            x: x,
            y: humidityValues[Math.floor(x/10) + nReloads]
           // z: Math.cos(Math.PI * v / 180).toFixed(4)
        });
    }
    return ret;
}

function update() {
    humidityValues[nReloads + 36] = getCurrentDummyHumidity();
    nReloads++;
    graph.setData(data(5 * nReloads));
    $('#reloadStatus').text(nReloads + ' reloads');
}

function getCurrentDummyHumidity(){
    return wateringData.deviceData.humidity;
}

function initHumidityValues(){
    for(var i=0; i<1000; i++){
        humidityValues[i] = 0;
    }
}

function runThis(){
    initHumidityValues();
    setInterval(update, 1000);
}

$(document).ready(runThis());