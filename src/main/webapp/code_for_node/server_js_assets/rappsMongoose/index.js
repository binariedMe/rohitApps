'use strict';

var rappMongoose = require('mongoose');

// Connecting to database
rappMongoose.connect('mongodb://localhost:27017/rApps', function() {
    console.log("connection callback");
});

var db = rappMongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function (callback) {
    console.info("The database connection is established.");
});

module.exports = rappMongoose;