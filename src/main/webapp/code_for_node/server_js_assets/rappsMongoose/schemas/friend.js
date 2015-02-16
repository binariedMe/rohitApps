'use strict';

var rappMongoose = require('../index');

var friendSchema = rappMongoose.Schema({
    user_email: String,
    friend_email: String,
    friend_name: String
});

var friendModel = rappMongoose.model('friendModel', friendSchema);

rappMongoose.friendSchema = friendSchema;
rappMongoose.friendModel = friendModel;

module.exports = rappMongoose;