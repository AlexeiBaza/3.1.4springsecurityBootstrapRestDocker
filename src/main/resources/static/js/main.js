//атрибут async для функции всегда возращает promise
//await можно использовать только внутри async-функций. await заставит интерпретатор
// JavaScript ждать до тех пор, пока промис справа от await не выполнится.
const main = {//объект userService
    create: async (user) => await fetch('http://localhost:8080/api/v1/admin/users',
        {
            "method": "POST",
            "headers": {
                "Content-Type": "application/json"
            },
            "body": JSON.stringify(user)
        }),
    readAuth: async () => await fetch("http://localhost:8080/api/v1/admin/user",
        {
            "method": "GET",
            "headers": {"Accept": "application/json"},
        }),
    readAll: async () => await fetch("http://localhost:8080/api/v1/admin/users",
        {
            "method": "GET",
            "headers": {"Accept": "application/json"},
        }),
    readById: async (id) => await fetch(`http://localhost:8080/api/v1/admin/users/${id}`,
        {
            "method": "GET",
            "headers": {"Accept": "application/json"},
        }),
    update: async (user) => await fetch('http://localhost:8080/api/v1/admin/users/${id}',
        {
            "method": "PUT",
            "headers": {"Content-Type": "application/json"},
            "body": JSON.stringify(user)
        }),
    delete: async (id) => await fetch(`http://localhost:8080/api/v1/admin/users/${id}`,
        {
            method: "DELETE"
        })
};