angular.module("app",["ngRoute"])
        .config(function($routeProvider)
        {
           $routeProvider
                .when("/rest",{
                   controller: "rest_controller",
                   templateUrl: "persona.html"
               })
               .when("/soap",{
                    controller: "soap_controller",
                    templateUrl: "persona.html"
                })
                .otherwise({
                   redirectTo:"/"
               });
        });