var rappMongoose = require('../../rappsMongoose/schemas/friend'),
    friendModel = rappMongoose.friendModel;

module.exports = {
    create : function (req, res) {

    },
    list : function(req, res){

    },
    addFriend: function(req, res){

        var userEmail = 'test5555@gmail.com'; // req.param
        var friendEmail = 'test5555'; // req.param
        var friendName = 'test55555'; // req.param

        var firstFriend = new friendModel({ user_email: userEmail, friend_email: friendEmail, friend_name: friendName});

        firstFriend.save(function (err) {
            console.log('meow', err);
        });

        res.send("Request for adding friend completed!");

    },
    removeFriend: function(req, res){
        res.end("Request for removing acknowledged!");
    },
    getUserList: function(req, res){
        res.end("Request for User acknowledged!");
    },
    getFriendList: function(req, res){
        res.end("Request for friend acknowledged!");
    },
    userNameAvailability: function(req, res){
        res.end("Request for Users list acknowledged");
    },
    updateProfile: function(req, res){
        res.end("Request for Users list acknowledged");
    }
};
