var app = angular.module('lunatechExercise', ['ngMaterial', 'ngMessages', 'restangular', 'cl.paging', 'uiGmapgoogle-maps']);

(function () {
    'use strict';
    app.config(function (uiGmapGoogleMapApiProvider) {
        uiGmapGoogleMapApiProvider.configure({
            key: 'AIzaSyDP9nG1WatqEVhRhxUfJp0pKmwwNwmba1g',
            v: '3.20', //defaults to latest 3.X anyhow
            libraries: 'weather,geometry,visualization'
        });
    });
}());