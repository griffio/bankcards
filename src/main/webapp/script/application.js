$.get("application/list", function (data) {

    var tbody = ['<tbody>'];

    $.each(data, function (index, row) {
        tbody += '<tr><td>' + row.bankname + '</td><td>' + row.cardnumber + '</td><td>' + row.expiry + '</td></tr>';
    });

    $("#js-bankcards-table").append(tbody).show();

});