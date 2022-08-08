$(document).ready(getAuthUser());
async function getAuthUser() {
    let authUser = await main.readAuth().then(response => response.json());

    let authUserRoles = '';
    for (let role of authUser.roles) {
        authUserRoles += role.roleName +', '
    }
    authUserRoles = authUserRoles.slice(0, -2);

    if (typeof authUser.roles.find(role => role.roleName === "ADMIN") != "undefined") {
        $(".adminPanel").show();
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
    $('#auth_user_table_js').children('tbody').append(row);
}