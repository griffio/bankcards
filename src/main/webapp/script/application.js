$(function () {

    $.get("application/list", function (data) {

        var tbody = ['<tbody>'];

        $.each(data, function (index, row) {
            tbody += '<tr><td>' + row.bankname + '</td><td>' + row.cardnumber + '</td><td>' + row.expiry + '</td></tr>';
        });

        $("#js-bankcards-table").append(tbody).show();

    }, "json");

    d3.text("application/list", "text/plain", function (csv) {
        tabulateBankcards(d3.csv.parseRows(csv), ["Bank", "Card number", "Expiry"]);
    });

    function tabulateBankcards(data, columns) {

        var table = d3.select("#js-bankcards-d3-table");
        var tbody = table.append("tbody");
        var cells;
        var rows;

        table.append("thead")
            .append("tr")
            .selectAll("th")
            .data(columns)
            .enter()
            .append("th")
            .text(function (column) {
                return column;
            });

        rows = tbody.selectAll("tr")
            .data(data)
            .enter()
            .append("tr");

        rows.selectAll("td")
            .data(function (d) {
                return d3.values(d)
            })
            .enter()
            .append("td")
            .text(function (d) {
                return d;
            });

        return table;
    }

});

