'use strict';

var rappsExpress = require('./index');

// Initializing the Application.
rappsExpress.get('/', function(req, res){
    res.sendFile('index.html', {"root": __dirname + "/../../../"});
});

var authenticationController    = require('../../server_js_assets/rappsControllers/authenticationController'),
    messageController           = require('../../server_js_assets/rappsControllers/messageController'),
    userActionController        = require('../../server_js_assets/rappsControllers/userActionController')

rappsExpress.post('/log_in', authenticationController.login);
rappsExpress.post('/register', authenticationController.register);
rappsExpress.post('/getMessage', messageController.getMessage);
rappsExpress.post('/submitMessage', messageController.submitMessage);
rappsExpress.get('/addFriend', userActionController.addFriend);
rappsExpress.post('/removeFriend', userActionController.removeFriend);
rappsExpress.post('/getFriendList', userActionController.getFriendList);
rappsExpress.post('/getUserList', userActionController.getUserList);
rappsExpress.post('/isUserNameAvailable', userActionController.userNameAvailability);
rappsExpress.post('/updateProfile', userActionController.updateProfile);

//Handling static request coming to server
rappsExpress.get(/^(.+)$/, function(req, res) {
    res.sendFile(req.params[0], {'root': '../'});
});

module.exports = rappsExpress;