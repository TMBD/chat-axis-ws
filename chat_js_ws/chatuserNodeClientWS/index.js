let express = require("express");
var cors = require('cors')
let server = express();
let bodyParser = require("body-parser");

const PORT = 3001;

let mainServerRoot = require("./server");

//MIDDLEWARES
server.use(bodyParser.json());
//server.use(bodyParser.urlencoded({ extended: true }));
server.use(cors())

// server.use((req, res, next) => {
//     res.header('Access-Control-Allow-Origin', '*');

//     // authorized headers for preflight requests
//     // https://developer.mozilla.org/en-US/docs/Glossary/preflight_request
//     res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
//     next();

//     server.options('*', (req, res) => {
//         // allowed XHR methods  
//         res.header('Access-Control-Allow-Methods', 'GET, PATCH, PUT, POST, DELETE, OPTIONS');
//         res.send();
//     });
// });


//ROUTES
server.use("/", mainServerRoot);

//Listening at the port defined in the server_config
server.listen(PORT);