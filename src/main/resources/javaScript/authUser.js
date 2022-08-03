//Функция getAuthUser исполниться тогда когда документ будет готов (браузер получит весь ее контент - все теги)
$(document).ready(getAuthUser());

async function getAuthUser() {
    let authUser = await userService.readAuth().then(response => response.json());

    let authUserRoles = '';
    for (let role of authUser.roles) {
        authUserRoles += role.roleName.slice(5) +', '
    }
    authUserRoles = authUserRoles.slice(0, -2);

    if (typeof authUser.roles.find(role => role.roleName === "ROLE_Admin") != "undefined") {
        $(".admin").show();
    } else {
        $("#pills-user").addClass("active")
        $("#user-button").addClass("active")
    }

    document.getElementById("authUserEmail").innerHTML = authUser.email;
    document.getElementById("authUserRoles").innerHTML = authUserRoles;

    let row = `<tr id="authUser">
                    <td>${authUser.id}</td>
                    <td>${authUser.firstName}</td>
                    <td>${authUser.lastName}</td>
                    <td>${authUser.age}</td>
                    <td>${authUser.email}</td>
                    <td> ${authUserRoles}</td>
               </tr>`;
    $('#auth_user_table').children('tbody').append(row);
}