'use strict'

function createLineGood(good) {
    return `
        <div class="row" id="${good.id}">
            <div class="col-3">${good.type}</div>
            <div class="col-3">${good.price}</div>
            <div class="col-3">${good.quality}</div>
            <div class="col-3">${good.weight}</div>
        </div>
    `;
}

function createPostCity(city) {
    return `
        <div class="card bg-warning mt-2">
            <div class="card-body">
                <p class="m-0">Мы отправляемся в город ${city.name}. До него ${city.distance} лиг</p>
            </div>
        </div>
    `;
}

function createPostTrader(post) {
    return `
        <div class="card bg-warning mt-2" id="${post.id}">
            <div class="card-body">
                <p class="m-0">У нас есть ${post.money} монет и ${post.capacity} свободных мест в телеге</p>
            </div>
        </div>
    `;
}

function createPostDay(post) {
    return `
        <div class="card bg-warning mt-2"">
            <div class="card-body">
                <p class="m-0">День ${post.day}</p>
                <p class="m-0">${post.action}</p>
                <p class="m-0">Осталось проехать ${post.distance} лиг</p>
            </div>
        </div>
    `;
}