(function () {
    'use strict';

    app.controller('mainController', function ($scope, Restangular, $mdDialog) {
        var baseCountries = Restangular.all('countries');
        var baseAirports = Restangular.oneUrl('airports');
        var baseRunways = Restangular.oneUrl('runways');

        $scope.countries = [];
        $scope.selectedItem = null;
        $scope.searchText = null;
        $scope.querySearch = querySearch;
        $scope.selectedItemChange = selectedItemChange;

        $scope.airports = [];
        $scope.runways = [];

        $scope.currentPage = 0;

        $scope.paging = {
            current: 1,
            total: 0,
            limit: 10,
            align: 'center start',
            onPageChanged: loadPages
        };

        $scope.runwaysPaging = {
            current: 1,
            limit: 50,
            total: 0
        };

        function loadPages() {
            $scope.currentPage = $scope.paging.current;
            if ($scope.selectedItem) {
                baseAirports.getList($scope.selectedItem.code, {
                    page: $scope.paging.current,
                    limit: $scope.paging.limit
                })
                    .then(function (airports) {
                        $scope.airports = airports;
                    })
            }
        }

        function querySearch(query) {
            return query ? $scope.countries.filter(createFilterFor(query)) : $scope.countries;
        }

        baseCountries.getList().then(function (countries) {
            $scope.countries = countries;
        });

        $scope.showTabDialog = function (ev, airport) {
            baseRunways.one('count', airport.ident).get().then(function (count) {
                $scope.runwaysPaging.total = Math.ceil(count / $scope.paging.limit);
            });

            baseRunways.getList(airport.ident, {
                page: 1,
                limit: $scope.runwaysPaging.limit
            }).then(function (runways) {
                $scope.runways = runways;

                $mdDialog.show({
                    controller: DialogController,
                    templateUrl: 'runwaysDialog.tmpl.html',
                    parent: angular.element(document.body),
                    targetEvent: ev,
                    clickOutsideToClose: true,
                    locals: {
                        airport: airport,
                        runways: $scope.runways
                    }
                })
            });


        };

        function createFilterFor(query) {
            var lowercaseQuery = angular.lowercase(query);
            return function filterFn(country) {
                if (angular.lowercase(country.name).indexOf(lowercaseQuery) === 0 || (angular.lowercase(country.code).indexOf(lowercaseQuery) === 0)) {
                    return true;
                }
            };
        }

        function selectedItemChange(item) {
            if (JSON.stringify(item) != undefined) {
                baseAirports.one('count', $scope.selectedItem.code).get().then(function (count) {
                    $scope.paging.total = Math.ceil(count / $scope.paging.limit);
                });

                baseAirports.getList($scope.selectedItem.code, {
                    page: 1,
                    limit: $scope.paging.limit
                }).then(function (airports) {
                    $scope.airports = airports;
                })
            } else {
                $scope.airports = [];
                $scope.paging.total = 0;
            }
        }
    })
})();

function DialogController($scope, $mdDialog, airport, runways) {
    $scope.airport = airport;
    $scope.runways = runways;
    $scope.map = { center: {
        latitude: airport.latitude_deg,
        longitude: airport.longitude_deg
    }, zoom: 5 };

    $scope.hide = function () {
        $mdDialog.hide();
    };

    $scope.cancel = function () {
        $mdDialog.cancel();
    };

    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
}