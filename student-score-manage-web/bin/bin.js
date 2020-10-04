const http = require('http');
const fs = require('fs');
const path = require('path');


var server = http.createServer();
server.on('request', function (request, response) {
    var extnameStr = path.extname(request.url);
    if (request.url === "/favicon.ico") {
        response.end();
    } else if (request.url.startsWith("/")) {
        if (extnameStr === ".css") {
            response.setHeader("Content-Type", "text/css");
        } else if (extnameStr === ".html") {
            response.setHeader("Content-Type", "text/html");
        } else if (extnameStr === '.js') {
            response.setHeader("Content-Type", "application/x-javascript");
        }


        console.log("/src" + request.url);
        fs.readFile(path.join(__dirname, "", "../src/" + request.url), function (err, data) {
            if (!err) {
                response.end(data);
            } else {
                console.log(err);
            }
        });
    } else {
        fs.readFile("." + request.url, function (err, data) {
            if (!err) {
                response.end(data);
            } else {
                console.log(err);
            }
        });
    }
});

server.listen(8080, function () {
    console.log("started")
});