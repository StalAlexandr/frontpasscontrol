'use strict';

var gulp = require('gulp'),
    watch = require('gulp-watch'),
    prefixer = require('gulp-autoprefixer'),
    uglify = require('gulp-uglify'),
    sass = require('gulp-sass'),
    sourcemaps = require('gulp-sourcemaps'),
    rigger = require('gulp-rigger'),
    cssmin = require('gulp-minify-css'),
    imagemin = require('gulp-imagemin'),
    pngquant = require('imagemin-pngquant'),
    rimraf = require('rimraf'),
    browserSync = require("browser-sync"),
    replace = require('gulp-replace'),
    webserver = require('gulp-webserver'),
    path = require('path'),
    reload = browserSync.reload;
  
    var URL = 'http://localhost:8080';
    
    var config = {
    server: {
        baseDir: "./build"
    },
    tunnel: true,
    host: 'localhost',
    port: 9000,
    logPrefix: "Frontend_Devil"
};
    
    
var configpath = {
    build: { //Тут мы укажем куда складывать готовые после сборки файлы
        html: 'build/',
        js: 'build/js/',
        css: 'build/css/',
        img: 'build/img/',
        fonts: 'build/fonts/'
    },
    src: { //Пути откуда брать исходники
        html: 'src/*.html', //Синтаксис src/*.html говорит gulp что мы хотим взять все файлы с расширением .html
        js: 'src/js/',
        css: 'src/css/',
        img: 'src/img/**/*.*', //Синтаксис img/**/*.* означает - взять все файлы всех расширений из папки и из вложенных каталогов
        fonts: 'src/fonts/**/*.*'
    },
    watch: { //Тут мы укажем, за изменением каких файлов мы хотим наблюдать
        html: 'src/**/*.html',
        js: 'src/js/**/*.js',
        style: 'src/style/**/*.scss',
        img: 'src/img/**/*.*',
        fonts: 'src/fonts/**/*.*'
    },
    clean: './build'
};    
    

gulp.task('hello', function(done) {
  console.log("HTTP Server Started");
  done();
});



gulp.task('html:build', function (build) {
    
    gulp.src(path.join(configpath.src.js, '**/*.js'))
    //    .pipe(jsmin())
        .pipe(replace('http://localhost:8080', URL))
        .pipe(gulp.dest(configpath.build.js))
    
    gulp.src(path.join(configpath.src.css, '**/*.css'))
    //    .pipe(jsmin())
        .pipe(replace('http://localhost:9000/api', URL))
        .pipe(gulp.dest(configpath.build.css))
        
        gulp.src(configpath.src.html) //Выберем файлы по нужному пути
        .pipe(rigger()) //Прогоним через rigger
        .pipe(gulp.dest(configpath.build.html)) //Выплюнем их в папку build
        .pipe(reload({stream: true})); //И перезагрузим наш сервер для обновлений
        
         build();
       
         
});


gulp.task('webserver', function (server) {
    browserSync(config);
    server();
});

