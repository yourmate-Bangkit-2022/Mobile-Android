const { faker } = require('@faker-js/faker');

function generateUsers(num) {
    const users = [];
    const password = '$2a$10$jhbnPXHf5A4SzK/.a9MvYusUQ8Vt1/XSZQY7KaApo5u2BmatpM9fC';
    for (let i = 0; i < num; i++) {
        const name = faker.name.firstName().replace(/[^a-zA-Z0-9]/g, '');
        const username = name.toLowerCase().replace(/\s/g, '');
        const email = username + '@gmail.com';
        users.push({
            name,
            username,
            email,
            password,
            profile_image: faker.image.avatar(),
        });
    }
    return users;
}

const { Client } = require('pg')
const client = new Client({
    user: 'postgres',
    host: 'localhost',
    database: 'yourmate-api',
    password: '2852',
    port: 5432,
});

client.connect();

function generateUsersTable() {
    const users = generateUsers(25);
    const query = `INSERT INTO "public"."up_users" (name, username, email, password, profile_image) VALUES ($1, $2, $3, $4, $5)`;
    users.forEach(user => {
        client.query(query, [user.name, user.username, user.email, user.password, user.profile_image]);
    });
}

try {
    // generateUsersTable()
    // getTourismsTable()
} catch (error) {
    console.log(error)
}

function getTourismsTable() {
    const query = `SELECT * FROM "public"."tourisms"`;
    client.query(query, (err, res) => {
        const unsplashRandomUrl = 'https://source.unsplash.com/random/';
        res.rows.map((row, index) => {
            const title = row.title.replace(/[^a-zA-Z0-9]/g, '');
            const imageUrl = unsplashRandomUrl + title;

            const request = require('request');
            request(imageUrl, (error, response) => {
                if (error) {
                    console.log(error)
                } else {
                    const url = response.request.uri.href.replace("w=1080", "w=300");
                    const query = `UPDATE "public"."tourisms" SET image_url = $1 WHERE id = $2`;
                    client.query(query, [url, row.id]);
                }
            }
            )
        })
    })
}