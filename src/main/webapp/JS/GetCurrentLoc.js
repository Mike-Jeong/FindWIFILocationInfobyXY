
function getLocation() {
    navigator.geolocation.getCurrentPosition(sucess, fail);
}

function sucess(position) {

    var x = 0;
    var y = 0;

    x = position.coords.latitude;
    y = position.coords.longitude;

    var time = position.timestamp;

    console.log(time);

    document.getElementById("lat").setAttribute('value', x);
    document.getElementById("lnt").setAttribute('value', y);
}

function fail(error) {
    console.log(error.message);
    alert("현재 위치를 확인 할 수 없습니다.");
}
