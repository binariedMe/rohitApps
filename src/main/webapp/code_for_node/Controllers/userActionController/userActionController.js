module.exports = {
    create : function (req, res) {
        var meetup = new Meetup(req.body);
        meetup.save(function (err, result) {
            res.json(result);
        });
    },
    list : function(req, res){
        Meetup.find({},function(err,results){
            res.json(results);
        })
    },
    addFriend: function(req, res){
        res.end("Request for removing acknowledged!");
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
