$.get("application/list", function (data) {
    // creates a new tbody container for tr>td elements
    var i;
    var frag = document.createDocumentFragment();
    var tbody = document.createElement("tbody");
    var tr;
    var tdBank;
    var tdNumber;
    var tdExpiry;

    frag.appendChild(tbody);

    for (i = 0; i < data.length; i++) {
        tr = document.createElement("tr");
        tdBank = document.createElement("td");
        tdNumber = document.createElement("td");
        tdExpiry = document.createElement("td");
        tdBank.innerHTML = data[i].bankname;
        tdNumber.innerHTML = data[i].cardnumber;
        tdExpiry.innerHTML = data[i].expiry;
        tr.appendChild(tdBank);
        tr.appendChild(tdNumber);
        tr.appendChild(tdExpiry);
        tbody.appendChild(tr);
    }

    $("#js-bankcards-tbody").html(frag.firstChild.innerHTML).show();

});