const express = require('express');
const mongoose = require('mongoose');
const Router = express.Router();

const Tw = require('../../models/Tw');
const MovieRC = require('../../models/MovieRC');

var list = (req, res, msg = '') => {
    var error = false;

    Tw.find()
        .sort({createdAt:-1})
        .lean()
        .exec()
        .then(tws => {
            res.render('list', {
                tws: tws,
                error: error,
                msg: msg,
            });
        })
        .catch(err => {
            error = err;
            console.error(error);
        });
}

Router.get('/delete/:twId', (req, res) => {
    twId = req.params.twId;
    console.log("delete sucessful");
    Tw.remove({
        _id: twId
    })
        .exec()
        .then(result => {
            res.redirect('/tws/success/Tw well deleted !');
        })
        .catch(err => {
            error = err;
            console.error(error);
        });
});

Router.get('/', (req, res) =>{
    list(req, res)
});
Router.get('/edit/:twId', (req, res) => {
    Tw.findOne({_id: req.params.twId})
        .lean()
        .exec()
        .then(tw => {
            res.render('edit', {
                tw: tw
            });
        })
        .catch(err => {
            error = err;
            console.error(error);
        });
});
Router.get('/:type/:msg', (req, res) => {
    var msg = {
        type: req.params.type,
        msg: req.params.msg
    }
    list(req, res, msg)
});

Router.post('/', (req, res) => {
    if (req.body.message && req.body.message != "") {
        const tw = new Tw({
            _id: new mongoose.Types.ObjectId(),
            message: req.body.message
        })

        tw.save()
            .then(tw => {
                res.redirect('/tws/success/Tw well created');
            })
            .catch(err => {
                res.status(500).json({error: err});    
            })
    } else {
        // res.status(500).json({error: "Please put some values"});
        res.redirect('/tws/danger/Please put some value');
    }
});

Router.post('/update/:twId', (req, res) => {
    twId = req.params.twId;
    message = req.body.message;

    if (req.body.message && req.body.message != "") {
        Tw.update({ _id: twId }, {
                $set: {
                    message: req.body.message,
                }
            })
            .exec()
            .then(tw => {
                res.redirect('/tws/success/Tw well updated');
            })
            .catch(err => {
                // res.status(500).json({ error: err });
                res.redirect('/tws/danger/'+err);
            });
    } else {
        // res.status(500).json({ error: 'Merci de remplire tous les champs' });
        res.redirect('/tws/danger/Please put some value');
    }
});

///////////////////////////////////////////////////

Router.get('/add/:userid/:movieid/:review/:rate', (req, res) => {
        const movierc = new MovieRC({
            _id: new mongoose.Types.ObjectId(),
            userid: req.params.userid,
            movieid: req.params.movieid,
            review: req.params.review,
            rate: req.params.rate
        })

        movierc.save()
            .then(movierc => {
                res.status(200).json({msg:"succeed!!"});  
            })
            .catch(err => {
                res.status(500).json({error: err});    
            })
});

Router.post('/add', (req, res) => {
    const movierc = new MovieRC({
        _id: new mongoose.Types.ObjectId(),
        userid: req.body.userid,
        movieid: req.body.movieid,
        review: req.body.review,
        rate: req.body.rate
    })

    movierc.save()
        .then(movierc => {
            res.status(200).json({msg:"suceed!!"});  
        })
        .catch(err => {
            res.status(500).json({error: err});    
        })
});

Router.get('/movierate', (req, res) => {
    MovieRC.aggregate([
            {'$group': {'_id': '$movieid', 'Avg_rate': {'$avg': '$rate'}}},
            {"$sort":{"Avg_rate":-1}},
            {"$limit": 9}
        ])
        .then(data => {
            console.log(data)
            res.status(200).json(data)
    });
});
var reviewlist = (req, res, msg = '') => {
    var error = false;

    MovieRC.find()
        .sort({createdAt:-1})
        .lean()
        .exec()
        .then(moviercs => {
            res.status(200).json(moviercs)
        })
        .catch(err => {
            error = err;
            console.error(error);
        });
}
Router.get('/reviewlist', (req, res) =>{
    reviewlist(req, res)
});
module.exports = Router;