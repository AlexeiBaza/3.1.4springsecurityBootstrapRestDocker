async function getModalEdit(id) {
    let user = await main.readById(id).then(response => response.json());
    let modalWindowDiv = document.getElementById('modalWindow')
    let editModalWindow =`<div class="modal fade" id="editModal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Edit user</h5>
                                <button class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="row justify-content-center" style="text-align: center">
                                        <div class="col-5">
                                            <form>
                                                <label class="form-label text-center fw-bold">Id</label>
                                                <input class="form-control" id="editUserId" value=${user.id} disabled>

                                                <label class="form-label text-center fw-bold">First Name</label>
                                                <input class="form-control" id="editUserFirstName" value=${user.firstName}>
                                                
                                                <label class="form-label text-center fw-bold">Last Name</label>
                                                <input class="form-control" id="editUserLastName" value=${user.lastName}>
                                                
                                                <label class="form-label text-center fw-bold">Age</label>
                                                <input class="form-control" id="editUserAge" value=${user.age}>
                                                
                                                <label class="form-label text-center fw-bold">Email</label>
                                                <input class="form-control" id="editUserEmail" value=${user.email}>
                                                
                                                <label class="form-label text-center fw-bold">Password</label>
                                                <input class="form-control" id="editUserPassword">
                                                
                                                <label class="form-label text-center fw-bold">Role</label>
                                                <select class="form-control" id="editUserRoles" multiple size=2>
                                                    <option value="ADMIN">ADMIN</option>
                                                    <option value="USER">USER</option>
                                                </select>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button class="btn btn-danger" data-bs-dismiss="modal" onclick="editUser()">Edit</button>
                            </div>
                        </div>
                    </div>
                </div>`;
    modalWindowDiv.innerHTML = editModalWindow;
    $('#editModal').modal('show');
}

async function editUser() {
    let id = $('#editUserId').val();
    let firstName = $('#editUserFirstName').val();
    let lastName = $('#editUserLastName').val();
    let age = $('#editUserAge').val();
    let email = $('#editUserEmail').val();
    let password = $('#editUserPassword').val();
    let roles = $('#editUserRoles').val();

    let user = {
        "id": id,
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email":email,
        "password": password,
        "roles": roles
    }
    main.update(user);

    let userRoles = '';
    if (roles.length !== 0) {
        for (let role of roles) {
            userRoles += role +', '
        }
        userRoles = userRoles.slice(0, -2);
    } else {
        let user = await main.readById(id).then(response => response.json());
        for (let role of user.roles) {
            userRoles += role.roleName +', '
        }
        userRoles = userRoles.slice(0, -2);
    }

    let row = `<td>${id}</td>
               <td>${firstName}</td>
               <td>${lastName}</td>
               <td>${age}</td>
               <td>${email}</td>
               <td> ${userRoles}</td>
               <td><button class="btn btn-info" onclick="getModalEdit(${user.id})">Edit</button></td>
               <td><button class="btn btn-danger" onclick="getModalDelete(${user.id})">Delete</button></td>`;
    $("#user"+id).html(row);
}