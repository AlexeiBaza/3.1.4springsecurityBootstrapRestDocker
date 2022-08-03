$(document).ready(fillTable)
async function fillTable() {
    $("#usersTable_js  tbody").empty()
    let usersList = await userService.readAll().then(response => response.json())
    let rows=''
    for (let user of usersList) {
        let userRoles = ''
        for (let role of user.roles) {
            userRoles += role.roleName.slice(5) +', '
        }
        userRoles = userRoles.slice(0, -2)
        rows += `<tr id="user${user.id}">
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.email}</td>
                    <td> ${userRoles}</td>
                    <td><button class="btn btn-info" onclick="getModalEdit(${user.id})">Edit</button></td>
                    <td><button class="btn btn-danger" onclick="getModalDelete(${user.id})">Delete</button></td>
                 </tr>`
    }
    $('#users_table').children('tbody').append(rows)
}