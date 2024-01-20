angular.module("app")
.controller("soap_controller", function ($scope, $http) {


    $scope.personas=undefined;
    $scope.url='http://localhost:8082/soapproject/swspersona?wsdl';

    $scope.id="";
    $scope.nombre="";
    $scope.apellido="";
    $scope.cedula="";
    $scope.email="";
    $scope.activo="";

    $(document).ready(function ()
    {
        consumer_soap("L",undefined,$scope.url,undefined);
    });


    function consumer_soap(body_type,type,url,object)
    {
        var soap=""
        if(body_type=="I")
        {   
            soap=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soapproject.ejemploagentemovil.mycompany.com/">
            <soapenv:Header/>
            <soapenv:Body>
               <soap:insertarPersona>
                  <!--Optional:-->
                  <persona>
                     <!--Optional:-->
                     <apellido>${object.apellido}</apellido>
                     <!--Optional:-->
                     <cedula>${object.cedula}</cedula>
                     <!--Optional:-->
                     <correo>${object.correo}</correo>
                     <!--Optional:-->
                     <estado>${object.estado}</estado>
                     <idpersona>?</idpersona>
                     <!--Optional:-->
                     <nombre>${object.nombre}</nombre>
                  </persona>
               </soap:insertarPersona>
            </soapenv:Body>
         </soapenv:Envelope>`
        }
        else if(body_type=="A")
        {
            soap=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soapproject.ejemploagentemovil.mycompany.com/">
            <soapenv:Header/>
            <soapenv:Body>
               <soap:actualizarPersona>
                  <!--Optional:-->
                  <persona>
                     <!--Optional:-->
                     <apellido>${object.apellido}</apellido>
                     <!--Optional:-->
                     <cedula>${object.cedula}</cedula>
                     <!--Optional:-->
                     <correo>${object.correo}</correo>
                     <!--Optional:-->
                     <estado>${object.estado}</estado>
                     <idpersona>${object.idpersona}</idpersona>
                     <!--Optional:-->
                     <nombre>${object.nombre}</nombre>
                  </persona>
               </soap:actualizarPersona>
            </soapenv:Body>
         </soapenv:Envelope>`
        }
        else if(body_type=="E")
        {
            soap=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soapproject.ejemploagentemovil.mycompany.com/">
            <soapenv:Header/>
            <soapenv:Body>
               <soap:eliminarPersona>
                  <id>${object.idpersona}</id>
               </soap:eliminarPersona>
            </soapenv:Body>
         </soapenv:Envelope>`
        }
        else if(body_type=="B")
        {
            soap=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soapproject.ejemploagentemovil.mycompany.com/">
            <soapenv:Header/>
            <soapenv:Body>
               <soap:buscarPersona>
                  <!--Optional:-->
                  <cedula>${object.cedula}</cedula>
               </soap:buscarPersona>
            </soapenv:Body>
         </soapenv:Envelope>`
        }
        else if(body_type=="L")
        {
            soap=`<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://soapproject.ejemploagentemovil.mycompany.com/">
            <soapenv:Header/>
            <soapenv:Body>
               <soap:listarPersonas/>
            </soapenv:Body>
         </soapenv:Envelope>`
        }
        else soap=undefined;

        if (soap==undefined) {console.log("Error no coincide con el tipo"); return undefined;}

        var objXMLHttpRequest = new XMLHttpRequest();
        objXMLHttpRequest.open(type==undefined?'POST':type, url, true);
        objXMLHttpRequest.onreadystatechange = function () {
            if(objXMLHttpRequest.readyState == 4 && objXMLHttpRequest.status == 200 && body_type=="L" || body_type=="B"){
                result = objXMLHttpRequest;
                console.log(objXMLHttpRequest.responseText);
                var x2js = new X2JS();
                var jsonObj = x2js.xml_str2json( objXMLHttpRequest.responseText );
                console.log(jsonObj);
                resultado="";
                if(body_type=="L")
                    resultado=jsonObj.Envelope.Body.listarPersonasResponse.return
                else
                    resultado=jsonObj.Envelope.Body.buscarPersonaResponse.return
                $scope.$apply(function() 
                {
                    console.log(resultado+" ----")
                    $scope.personas=resultado;
                });
            }else if (body_type!="L" && body_type!="B") consumer_soap("L",undefined,$scope.url,undefined);
        }
        objXMLHttpRequest.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
        objXMLHttpRequest.send(soap);
    }

    $scope.eliminar=(id)=> {
        console.log(id);
        consumer_soap("E",undefined,$scope.url,{idpersona:id});
    }
    
    $scope.actualizar_guardar=(form)=> {
        console.log(form);
        if(form.id.$viewValue==undefined)
        {
            consumer_soap("I",undefined,$scope.url,{
                nombre:form.nombre.$viewValue,
                apellido:form.apellido.$viewValue,
                cedula:form.cedula.$viewValue,
                correo:form.email.$viewValue,
                estado:form.activo.$viewValue,
            });
        }else{
             
            consumer_soap("A",undefined,$scope.url,{
                idpersona:form.id.$viewValue,
                nombre:form.nombre.$viewValue,
                apellido:form.apellido.$viewValue,
                cedula:form.cedula.$viewValue,
                correo:form.email.$viewValue,
                estado:form.activo.$viewValue,
            });
        }
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
        consumer_soap("B",undefined,$scope.url,{
            cedula:$scope.cedula
        });
    }



});