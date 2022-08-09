function create() {
    let firstName = $("#newUserFirstName").val();
    let lastName = $("#newUserLastName").val();
    let age = $("#newUserAge").val();
    let email = $("#newUserEmail").val();
    let password = $("#newUserPassword").val();
    let roles = $('#roles').val();

    let newUser = {
        "firstName": firstName,
        "lastName": lastName,
        "age": age,
        "email": email,
        "password": password,
        "roles": roles
    }
    main.create(newUser);
}