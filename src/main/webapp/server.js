// Node server

// Injecting depedancies
var express                     = require('express'),
    app                         = express(),
    bodyParser                  = require('body-parser'),
    mongoose                    = require('mongoose'),
    authenticationController    = require('./code_for_node/Controllers/'),
    messageController           = require('./code_for_node/Controllers/'),
    userActionController        = require('./code_for_node/Controllers/');

// Connecting to database
mongoose.connect('mongodb://localhost:27017/mean-demo');

// Initializing JSON Parser
app.use(bodyParser());

// Initializing the Application.
app.get('/',function(req,res){
    res.sendfile(__dirname + '/index.html');
});


// Handling Post request coming to server
app.post('/log_in',authenticationController.login);
app.post('/register',authenticationController.register);
app.post('/getMessage',messageController.getMessage);
app.post('/submitMessage',messageController.submitMessage);
app.post('/addFriend', userActionController.addFriend);
app.post('/removeFriend',userActionController.removeFriend);
app.post('/getFriendList',userActionController.getFriendList);
app.post('/getUserList',userActionController.getUserList);
app.post('/isUserNameAvailable',userActionController.userNameAvailability);
app.post('/updateProfile',userActionController.updateProfile);

// Handling static request coming to server
app.use('/scripts',express.static(__dirname+'/scripts'));
app.use('/bootstrap-3.2.0-dist', express.static(__dirname+'/bootstrap-3.2.0-dist'));
app.use('/views',express.static(__dirname+'/views'));
app.use('/css',express.static(__dirname+'/css'));
app.use('/images',express.static(__dirname+'/images'));


// Listen to request on port no. 5000
app.listen(5000,function(){console.log("I\'m listening...")});
