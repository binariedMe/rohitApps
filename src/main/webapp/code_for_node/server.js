// Node server

// Injecting depedancies
var rappsApp                    = require('./server_js_assets/rappsExpress/rappsRoute');
//var rappsMongoose             = require('./server_js_assets/rappsMongoose/rappsMongoose');

rappsApp.listen(8080, function(){
    console.log("Node server is up and listening on port 8080.");
});
