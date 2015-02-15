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
    logIn: function(req, res){
        res.json(req.body);
    }
};
