const mongoose = require('mongoose');

const MRCSchema = new mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
    userid:{
        type:Number,
        required:true
    },
    movieid:{
        type:Number,
        required:true
    },
    review:{
        type: String,
        required: true
    },
    rate:{
        type:Number,
        required:true
    },
    createdAt: {
        type: Date,
        default: Date.now
    }
});

module.exports = mongoose.model('MovieRC', MRCSchema);