require('dotenv').config();
const path = require('path');
const moment= require('moment') ;
const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const exphbs = require('express-handlebars');
const mongoose = require('mongoose');

const twsRouter = require('./routes/web/tws');
const twsApiRouter = require('./routes/api/tws');

const connectDB = require('./middelwares/db');
connectDB.connect();
const app = express();
var allowCrossDomain = function (req, res, next){
        res.header('Access-Control-Allow-Origin', 'http://localhost:4200');
        res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE');
        res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization');
        res.header('Access-Control-Allow-Credentials', 'true');
        next();
    };
app.use(allowCrossDomain);
app.use(morgan('dev'));
app.use(express.urlencoded({ extended: false }))
app.use(bodyParser.json());
app.engine('.hbs', exphbs({
    extname: '.hbs',
    defaultLayout: 'main',
    helpers: {
        formatDate: function (date, format) {
            return moment(date, "YYYYMMDD").fromNow();
        },
        isEmpty:(value)=>{
         return value =='';
        },
        isNotEmpty:(value) =>{
            return value !='';
        }
    }
}));
app.set('view engine', '.hbs');

app.use(express.static(path.join(__dirname, 'public')));
app.use(express.static(__dirname + '/node_modules/jquery/dist'));
app.use(express.static(__dirname + '/node_modules/bootstrap/dist'));
app.use(express.static(__dirname + '/node_modules/@fortawesome/fontawesome-free'));

// app.use('/tws', twsRouter);
// app.use('/api/tws', twsApiRouter);
app.use('/', twsRouter);

app.get('/', function(req, res) {
    res.send('It works !');
});

app.use((req, res, next) => {
    const error = new Error('Not Found !');
    error.status = 404;
    next(error);
});

app.listen(process.env.PORT, function() {
    console.log('Server running on localhost:' + process.env.PORT);
});

//connectDB.close();