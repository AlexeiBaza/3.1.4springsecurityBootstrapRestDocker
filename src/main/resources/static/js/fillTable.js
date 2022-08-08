$(document).ready(fillTable);
async function fillTable() {//async - функция всегда возвращает промисс
    $("#users_table_js tbody").empty();
    let usersList = await main.readAll().then(response => response.json());//результат промиса вызовется в функция "then" onFulfilled  которой выполниться при resolve
    let rows='';
    for (let user of usersList) {
        let userRoles = '';
        for (let role of user.roles) {
            userRoles += role.roleName +', '
        }
        userRoles = userRoles.slice(0, -2);

        rows += `<tr id="user${user.id}">
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td> ${userRoles}</td>
                    <td><button class="btn btn-info" onclick="getModalEdit(${user.id})">Edit</button></td>
                    <td><button class="btn btn-danger" onclick="getModalDelete(${user.id})">Delete</button></td>
                 </tr>`;
    }
    $('#users_table_js').children('tbody').append(rows);
}