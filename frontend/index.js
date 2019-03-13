
var express = require('express');

// создаём Express-приложение
var app = express();
var path = require('path');
var originsWhitelist = [
  'http://localhost:7000'      //this is my front-end url for development

];

app.use('/public',express.static(path.join(__dirname, '/public')));
console.log(path.join(__dirname, '/public'));
app.get('/', function(req, res) {

res.sendFile(path.join(__dirname,  'index.html'));
});

app.listen(7000);
// отправляем сообщение
console.log('Сервер стартовал!');
