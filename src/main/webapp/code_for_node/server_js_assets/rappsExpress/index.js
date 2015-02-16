'use strict';

var app = require('express')(),
    bodyParser = require('body-parser');

app.use(bodyParser());

module.exports = app;