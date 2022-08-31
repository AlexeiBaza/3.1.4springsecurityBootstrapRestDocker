async function getModalDelete(id) {
    let user = await main.readById(id).then(response => response.json());
    let modalWindowDiv = document.getElementById('modalWindow');

    let selectRoles='';
    for (role of user.roles) {
        selectRoles+=`<option>${role.roleName}</option>`
    }

    let deleteModalWindow =`<div class="modal fade" id="delete_modal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Delete user</h5>
                                <button class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                    <div class="row justify-content-center" style="text-align: center">
                                        <div class="col-5">
                                            <form>
                                                <label class="form-label text-center fw-bold">Id</label>
                                                <input class="form-control" value=${user.id} readonly>

                                                <label class="form-label text-center fw-bold">First Name</label>
                                                <input class="form-control" value=${user.firstName} readonly>
                                                
                                                <label class="form-label text-center fw-bold">Last Name</label>
                                                <input class="form-control" value=${user.lastName} readonly>
                                                
                                                <label class="form-label text-center fw-bold">Age</label>
                                                <input class="form-control" value=${user.age} readonly>

                                                <label class="form-label text-center fw-bold">Email</label>
                                                <input class="form-control" value=${user.email} readonly>

                                                <label class="form-label text-center fw-bold">Role</label>
                                                <select class="form-control" multiple size="${Object.keys(user.roles).length}" disabled>
                                                    `+selectRoles+`
                                                </select>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button class="btn btn-danger" data-bs-dismiss="modal" onclick="deleteUser(${user.id})">Delete</button>
                            </div>
                        </div>
                    </div>
                </div>`;
    modalWindowDiv.innerHTML = deleteModalWindow;
    $('#delete_modal').modal('show');
}

function deleteUser(id){
    main.delete(id);
    $('#user'+id).remove();
}