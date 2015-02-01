$.get("application/list", function (data) {
    // creates a new tbody container for tr>td elements
    if (window.console) {
        console.log(data);
    }
    var i;
    var rows = document.createDocumentFragment();
    var tbody = document.createElement("tbody");
    rows.appendChild(tbody);
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
        tbody.appendChild(tr);
    }
    $("#js-bankcards-tbody").html(rows.firstChild.innerHTML)
});