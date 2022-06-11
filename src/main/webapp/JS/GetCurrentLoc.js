let x = 0;
let y = 0;

function getLocation() {
    navigator.geolocation.getCurrentPosition(sucess, fail);
}

function sucess(position) {
    x = position.coords.latitude;
    y = position.coords.longitude;
    console.log(x);
    console.log(y);

    document.getElementById("lat").setAttribute('value', x);
    document.getElementById("lnt").setAttribute('value', y);
}

function fail(error) {
    console.log(error.message);
    alert("현재 위치를 확인 할 수 없습니다.");
}
