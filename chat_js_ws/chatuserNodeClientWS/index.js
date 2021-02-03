let express = require("express");
var cors = require('cors')
let server = express();
let bodyParser = require("body-parser");

const PORT = 3001;

let mainServerRoot = require("./server");

//MIDDLEWARES
server.use(bodyParser.json());
server.use(cors())

//ROUTES
server.use("/", mainServerRoot);

//Listening at the port defined in the server_config
server.listen(PORT);