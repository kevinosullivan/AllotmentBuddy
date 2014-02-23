angular.module('controllerBeacon', [ 'serviceBeaconAPI', 'angularMoment', 'ngRoute', 'google-maps' ]).controller(
    'CtrlBeacon',
    [
        '$scope',
        '$route',
        '$timeout',
        'serviceBeaconAPI',
        function($scope, $route, $timeout, serviceBeaconAPI) {

          $scope.versionNumber = g_atiBeaconversionNumber;
          $scope.buildNumber = g_atiBeaconbuildNumber;
          $scope.buildTimestamp = g_atiBeaconbuildTimestamp;
          $scope.versionString = "v" + $scope.versionNumber + " (build " + $scope.buildNumber + " " + $scope.buildTimestamp + ")"

          // If non null, will open edit pane over map display
          $scope.beaconBeingEdited = null;

          $scope.showMap = true;
          $scope.showList = false;

          $scope.myBeacons = null;

          $scope.map = {
            center : {
              latitude : 45,
              longitude : -73
            },
            draggable : true,
            zoom : 8,
            beaconMarkers : []
          };

          $scope.editBeacon = function(event, beaconMetaData) {
            event.preventDefault();
            console.log("Edit Beacon");
            console.log(beaconMetaData);
            $scope.beaconBeingEdited = beaconMetaData;
          }

          /**
           * Load the beacons
           */
          $scope.searchForBeacons = function() {
            console.log("Loading beacons...");

            serviceBeaconAPI.search({
              ts : "" + new Date().getTime(),
              onlyMyBeacons : true
            },
            // Successful response
            function(dataFromAPI) {
              $scope.myBeacons = dataFromAPI;
              console.log($scope.myBeacons);
              $scope.map.beaconMarkers = [];
              if (dataFromAPI != null) {
                var i = 0;
                for (i = 0; i < dataFromAPI.length; i++) {
                  var marker = {
                    id : i,
                    latitude : dataFromAPI[i].latitude,
                    longitude : dataFromAPI[i].longitude,
                    title : "Marker " + i,
                    metaData : $scope.syntaxHighlight(dataFromAPI[i])
                  };
                  // console.log(marker.metaData);
                  $scope.map.beaconMarkers.push(marker);
                }
              }
            },
            // Query Error Response handler
            function(err) {
              console.log(err);
            });
          }

          /**
           * Format the json for pretty printing in HTML.
           * 
           * @see http://stackoverflow.com/a/7220510/156477
           */
          $scope.syntaxHighlight = function(json) {
            if (typeof json != 'string') {
              json = JSON.stringify(json, undefined, 2);
            }
            json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
            var processedJson = json.replace(
                /("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
                  var cls = 'number';
                  if (/^"/.test(match)) {
                    if (/:$/.test(match)) {
                      cls = 'key';
                    } else {
                      cls = 'string';
                    }
                  } else if (/true|false/.test(match)) {
                    cls = 'boolean';
                  } else if (/null/.test(match)) {
                    cls = 'null';
                  }
                  return '<span class="' + cls + '">' + match + '</span>';
                });

            return "<pre>" + processedJson + "</pre>"
          }

          // After minor pause, load the beacons from API.
          $scope.searchForBeacons();

        } ]);
