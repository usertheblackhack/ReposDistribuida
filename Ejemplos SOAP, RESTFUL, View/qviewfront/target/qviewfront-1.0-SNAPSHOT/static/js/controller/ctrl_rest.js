angular.module("app")
.controller("rest_controller", function ($scope, $http) {

    $scope.personas=undefined;
    $scope.url='http://localhost:8081';

    $scope.id="";
    $scope.nombre="";
    $scope.apellido="";
    $scope.cedula="";
    $scope.email="";
    $scope.activo="";

    $(document).ready(function ()
    {
        consumer_rest();
    });


    function consumer_rest(){
        $.ajax({
            method:"POST",
            url:$scope.url+"/api/listar",
            processData:false,
            contentType: false,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    $scope.personas=data;
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.eliminar=(id)=> {
        console.log(id);
        $.ajax({
            method:"Delete",
            url:$scope.url+"/api/eliminar",
            processData:false,
            contentType: false,
            data: JSON.stringify({ id: id}),
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                consumer_rest();
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.actualizar_guardar=(form)=> {
        console.log(form);
        
        let formData=new FormData();
        if(form.id.$viewValue!="")
            formData.append("idpersona",form.id.$viewValue);
        formData.append("nombre",form.nombre.$viewValue);
        formData.append("apellido",form.apellido.$viewValue);
        formData.append("cedula",form.cedula.$viewValue);
        formData.append("correo",form.email.$viewValue);
        formData.append("estado",form.activo.$viewValue);

        $.ajax({
            method:form.id.$viewValue==""?"POST":"PUT",
            url:$scope.url+"/api/"+(form.id.$viewValue==""?"insertar":"actualizar"),
            processData:false,
            contentType: false,
            data:formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    consumer_rest();
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });
    }


    $scope.editar=(obj)=> 
    {
        $('#staticBackdrop').on('shown.bs.modal', function(event) {
            $scope.$apply(function() 
            {
                $scope.id=obj.idpersona;
                $scope.nombre=obj.nombre;
                $scope.apellido=obj.apellido;
                $scope.cedula=obj.cedula;
                $scope.email=obj.correo;
                $scope.activo=obj.estado;
            });
        });
    }


    $scope.buscar=()=> 
    {
        let formData=new FormData();
        formData.append("cedula",$scope.cedula);

        $.ajax({
            method:"POST",
            url:$scope.url+"/api/buscar",
            processData:false,
            contentType: false,
            data: formData,
            beforeSend: function (xhr) {
                console.log("cargando...");
            },
            success: function (data) {
                $scope.$apply(function() 
                {
                    $scope.personas=data;
                });
                console.log(data);
            },
            error: function (objXMLHttpRequest) 
            {
                console.log("error: ", objXMLHttpRequest);
            }
        });         
    }

    
});