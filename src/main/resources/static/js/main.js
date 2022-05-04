ymaps.ready(init);

var placemarks = [
        {
            latitude: 53.92561042,
            longitude: 27.59425471,
            hintContent: "Адрес: ул. Якуба Коласа, д. 28"
        },
        {
            latitude: 53.91166365,
            longitude: 27.59587825,
            hintContent: "Адрес: ул. Платонова, д. 39"
        },
        {
            latitude: 53.92900765,
            longitude: 27.58792812,
            hintContent: "Адрес: ул. Сурганова, д. 50"
        }
    ],
    geoObjects= [];

function init() {
    var map = new ymaps.Map('map', {
        center: [53.91, 27.58],
        zoom: 12,
        controls: ['zoomControl'],
        behaviors: ['drag']
    });

    placemarks.forEach(function (obj) {
        var placemark = new ymaps.Placemark([obj.latitude, obj.longitude], {
            hintContent: obj.hintContent
        })

        map.geoObjects.add(placemark);
    });

}