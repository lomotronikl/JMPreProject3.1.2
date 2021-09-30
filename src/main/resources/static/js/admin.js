/*

alert( "Привет 1" );
*/
function funRoleAdmin(){
    var chbox;
    chbox=document.getElementById('roleAdmin');
    if (chbox.checked) {
        chbox.value = "true";
    }
    else {
        chbox.value = "false";
    }
}
function funRoleUser(){
    var chbox;
    chbox=document.getElementById('roleUser');
    if (chbox.checked) {
        chbox.value = "true";
    }
    else {
        chbox.value = "false";
    }
}

$(document).ready(function (){

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (data,status){

            $('.editForm #id').val(data.id);
            $('.editForm #name').val(data.name);
            $('.editForm #lastName').val(data.lastName);
            $('.editForm #username').val(data.username);

            const select = document.querySelector('#userRoles').getElementsByTagName('option');
            for (let i = 0; i < select.length; i++) {

                if (select[i].value == 'ADMIN') select[i].selected = data.roleAdmin;
                if (select[i].value == 'USER') select[i].selected = data.roleUser;

            }
        });
        $('.editForm #editModal').modal();
    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (data,status){
            document.getElementById("formDelete").action = '/admin/delete/'+data.id;
            $('.deleteForm #id_').val(data.id);
            $('.deleteForm #name_').val(data.name);
            $('.deleteForm #lastName_').val(data.lastName);
            $('.deleteForm #username_').val(data.username);

            const select = document.querySelector('#userRoles_').getElementsByTagName('option');
            for (let i = 0; i < select.length; i++) {

                if (select[i].value == 'ADMIN') select[i].selected = data.roleAdmin;
                if (select[i].value == 'USER') select[i].selected = data.roleUser;

            }

        });
        $('.deleteForm #deleteModal').modal();
    });

});

