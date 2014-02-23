var serviceBeaconAPI =  angular.module('serviceBeaconAPI', ['ngResource']).factory('serviceBeaconAPI', function ($resource) {
    return $resource('../atibeacon/vaahosts/1/:id/:currentJobId', {id:'@id'}, {
        query:        {method: 'GET',    timeout:10000,    isArray: false},
        get:          {method: 'GET',    timeout:10000},
        remove:       {method: 'DELETE', timeout:10000,    params:{id:'@id', currentJobId:'@currentJobId'}},
        edit:         {method: 'PUT',    timeout:10000,    params:{id:'@id', currentJobId:'@selectedCarReg'}},
        add:          {method: 'POST',   timeout:10000},
        sendMessage:  {method: 'PUT',    timeout:10000,    url: '../sixthsense/vaahosts/1/message/:id', headers: {"Content-Type":"text/plain;charset=UTF-8"}},
        checkStatus:  {method: 'GET',    timeout:60000,    url: '../sixthsense/status/1'},
        checkJobs:    {method: 'GET',    timeout:10000,    isArray: true, url: '../sixthsense/jobs/1'},
        queryPax:     {method: 'GET',    timeout:60000,    isArray: true, url: '../sixthsense/vaapassengers/1'}
    });
});
