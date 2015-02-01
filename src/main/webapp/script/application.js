$.get("application/list", function (data) {
    var i;
    var rows = document.createDocumentFragment();
    for (i = 0; i < data.length; i++) {
        var tr = document.createElement("tr");
        var tdBank = document.createElement("td");
        var tdNumber = document.createElement("td");
        var tdExpiry = document.createElement("td");
        tdBank.innerHTML = data[i].bankname;
        tdNumber.innerHTML = data[i].cardnumber;
        tdExpiry.innerHTML = data[i].expiry;
        tr.appendChild(tdBank);
        tr.appendChild(tdNumber);
        tr.appendChild(tdExpiry);
        rows.appendChild(tr);
    }
    $("#js-bankcards-tbody").append(rows);
});