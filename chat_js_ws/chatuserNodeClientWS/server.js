let express = require("express");
const soap = require('soap')

let router = express.Router();

const wsdlUrl = 'http://localhost:8080/chatroomws/services/ChatRoom?wsdl'


router.post("/subcribe", (req, res) => {
    soap.createClient(wsdlUrl, function(err, client) {
        client.subscribe({pseudo: req.body.pseudo}, function(err, result) {
            if (err) console.log(err);
            console.log("----- SUBCRIBE-----------------");
            console.log(req.body);
            console.log(result);
            console.log("--------------------------------");
            res.status(200);
            res.json({
                result: result.return ? result.return : null
            })
        });
    });
});

router.post("/unsubcribe", (req, res) => {
    soap.createClient(wsdlUrl, function(err, client) {
        client.unsubscribe({pseudo: req.body.pseudo}, function(err, result) {
            console.log("----- UNSUBCRIBE -----------------");
            console.log(req.body.pseudo);
            console.log(result);
            console.log("--------------------------------");
            res.status(200);
            res.json({
                result: result.return ? result.return : null
            })
        });
    });
});


router.get("/message", (req, res) => {
    soap.createClient(wsdlUrl, function(err, client) {
        client.getMessage({pseudo: req.query.pseudo}, function(err, result, rawResponse, soapHeader, rawRequest) {
            console.log("----- GET MESSAGE-----------------");
            //console.log(req);
            console.log("result");
            console.log(result.return);
            console.log();
            console.log();
            console.log("rawResponse");
            console.log(rawResponse);
            console.log();
            console.log();
            console.log("soapHeader");
            console.log(soapHeader);
            console.log();
            console.log();
            console.log("--------------------------------");
            res.status(200);
            res.json({
                result: result.return ? result.return : null
            })
        });
    });
});


router.post("/message", (req, res) => {
    soap.createClient(wsdlUrl, function(err, client) {
        client.postMessage({pseudo: req.body.pseudo, message: req.body.message}, function(err, result) {
            console.log("----- POST MESSAGE-----------------");
            console.log(req.body.pseudo);
            console.log(result);
            console.log("--------------------------------");
            res.status(200);
            res.json({
                result: result.return ? result.return : null
            })
        });
    });
});


module.exports = router;