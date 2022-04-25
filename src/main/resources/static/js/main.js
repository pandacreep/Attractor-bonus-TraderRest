'use strict'

const ulr = 'http://localhost:8080/api';
start();

function start() {
    fetch(ulr + '/start')
        .then(response => response.json())
        .then(json => {
            console.log(json);
            init()
        });
}

function init() {
    fetch(ulr + '/init')
        .then(response => response.json())
        .then(json => {
            console.log(json);
            getTraderBeforeBuy();
        });
}

function getTraderBeforeBuy() {
    fetch(ulr + '/trader')
        .then(response => response.json())
        .then(json => {
            let post = createPostTrader(json);
            document.getElementById('posts').innerHTML += post;
            buy();
        });
}

function buy() {
    fetch(ulr + '/buy')
        .then(response => response.json())
        .then(json => {
            let goods = `<div class="card bg-warning mt-2"><div class="card-body">`;
            goods += `
                <p>Мы купили товары</p>
                <div class="row"">
                    <div class="col-3 fw-bold">Name</div>
                    <div class="col-3 fw-bold">Price</div>
                    <div class="col-3 fw-bold">Quality</div>
                    <div class="col-3 fw-bold">Weight</div>
                </div>
                `
            for (let i = 0; i < json.length; i++) {
                let good = createLineGood(json[i])
                goods += good;
            }
            ;
            goods += `</div></div>`;
            document.getElementById('posts').innerHTML += goods;
            getTraderAfterBuy();
        });
}

function getTraderAfterBuy() {
    fetch(ulr + '/trader')
        .then(response => response.json())
        .then(json => {
            let post = createPostTrader(json);
            document.getElementById('posts').innerHTML += post;
            selectCity();
        });
}

function selectCity() {
    fetch(ulr + '/city')
        .then(response => response.json())
        .then(json => {
            let post = createPostCity(json);
            document.getElementById('posts').innerHTML += post;
            goToJourney();
        });
}

function sleep(milliseconds) {
    const date = Date.now();
    let currentDate = null;
    do {
        currentDate = Date.now();
    } while (currentDate - date < milliseconds);
}

function goToJourney() {
    passOneDay();
}

function passOneDay() {
    fetch(ulr + '/go')
        .then(response => response.json())
        .then(json => {

            console.log('day:' + json.day);
            console.log('distance:' + json.distance);

            let post = createPostDay(json);
            document.getElementById('posts').innerHTML += post;

            if (json.distance > 0) {
                passOneDay();
            } else {
                getGoods();
            }
        });
}

function getGoods() {
    fetch(ulr + '/goods')
        .then(response => response.json())
        .then(json => {
            let goods = `<div class="card bg-warning mt-2"><div class="card-body">`;
            goods += `
                <p>Доставленные товары</p>
                <div class="row"">
                    <div class="col-3 fw-bold">Name</div>
                    <div class="col-3 fw-bold">Price</div>
                    <div class="col-3 fw-bold">Quality</div>
                    <div class="col-3 fw-bold">Weight</div>
                </div>
                `;
            for (let i = 0; i < json.length; i++) {
                let good = createLineGood(json[i])
                goods += good;
            }
            ;
            goods += `</div></div>`;
            document.getElementById('posts').innerHTML += goods;
            showResult();
        });
}

function showResult() {
    fetch(ulr + '/sell-goods')
        .then(response => response.json())
        .then(json => {
            let goods = `<div class="card bg-warning mt-2"><div class="card-body">`;
            goods += `
                <p>${json.message}</p>
                `
            ;
            goods += `</div></div>`;
            goods += `<h2 class="my-4">Для перезаруска симулятора обновите страницу</h2>`;
            document.getElementById('posts').innerHTML += goods;
        });
}