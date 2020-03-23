var mysql = require('mysql');
const fetch = require('node-fetch');

const idPaises = [];
let comprobador = false;
let id;

var connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'magni_tp2'
});

connection.connect(function(error){
    if(error){
        console.error('Error en la conexion: ' + err.stack);
    }else{
       console.log('Conexion establecida');
       obtener();
    }
});


async function obtener(){
    
    for (let index = 1; index <= 300; index++) {
        await fetch(`https://restcountries.eu/rest/v2/callingcode/${index}`)
        .then(res => res.json())
        .then(data => { 
            vacio(data);
        })
        .catch(err => {
            console.log(err)
            connection.end();
        });
    } 
    connection.end();
}

function vacio(Dato){ 
    if(Dato.status === 404){
        console.log("Pagina vacia");        
    } else{   
        guardarSQL(Dato); 
    }
}

function guardarSQL(Registro){ 

    for (let i = 0; i < idPaises.length; i++) { 
        if(idPaises[i] === Registro[0].numericCode){
            comprobador = true;
            id = Registro[0].numericCode;
            break;
        }
    }

    if(comprobador === true){ 
        let sql = 'UPDATE pais SET nombrePais = ? , capitalPais = ? , region = ? , poblacion = ? , latitud = ? , longitud = ? WHERE codigoPais = ?';
        connection.query(sql, 
            [Registro[0].name, Registro[0].capital, Registro[0].region, Registro[0].population, Registro[0].latlng[0], Registro[0].latlng[1], Registro[0].numericCode], 
            function(error, result){
                if(error){
                    console.log(error)
                    throw error;
                }else{ 
                    console.log(result);
                }
            }
        );
        comprobador = false;
    }
    else{
        idPaises.push(Registro[0].numericCode);
        connection.query(
            'INSERT INTO pais(codigoPais, nombrePais, capitalPais, region, poblacion, latitud, longitud) VALUES (?, ?, ?, ?, ?, ? ,?)',
            [Registro[0].numericCode, Registro[0].name, Registro[0].capital, Registro[0].region, Registro[0].population, Registro[0].latlng[0], Registro[0].latlng[1]],
            function(error, result){
                if(error){
                    console.log(error)
                    throw error;
                }else{
                    console.log(result);
                }
            }
        );
    }
    
}
