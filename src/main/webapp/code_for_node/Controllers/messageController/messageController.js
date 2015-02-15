/**
 * Created by sony on 04-02-2015.
 */
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
    getMessage: function(req, res){
        res.end("Request for Users list acknowledged");
    },
    submitMessage: function(req, res){
        res.end("Request for Users list acknowledged");
    }
};

