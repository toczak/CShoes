function searchShoes() {

    var data = {};
    data["shopsNumber"] = $("#shopNumberSelect").val();
    // data["manufacturer"] = $("#manufacturerSelect").val();
    // data["type"] = $("#typeSelect").val();
    // data["isSale"] = $("#saleCheckbox").prop('checked');

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoes/search",
        // contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: {
            shopsNumber: data["shopsNumber"],
            who: $("#whoSelect").val(),
            who_important: $("#who_importantSelect").val(),
            color: $("#colorSelect").val(),
            color_important: $("#color_importantSelect").val(),
            category: $("#categorySelect").val(),
            category_important: $("#category_importantSelect").val(),
            manufacturer: $("#manufacturerSelect").val(),
            manufacturer_important: $("#manufacturer_importantSelect").val(),
            size: $("#sizeSelect").val(),
            priceMin: $("#priceMin").val(),
            priceMax: $("#priceMax").val()
            // price: data["price"],
            // manufacturer_id: data["manufacturer"],
            // type_id: data["type"],
            // isSale: data["isSale"]
        },

        error: function (e) {
            alert("An error with search shoes!");
            console.log("JSON reading failed: ", e);
        },

        success: function (response) {
            console.log(response);
            $("div.row").empty();
            $.each(response, function (index, element) {
                var _name = element.name;
                var _gender = element.genderGroup.name;
                var _size = element.size.size;
                var _type = element.type.name;
                var _price = element.price;
                var _id = element.id;


                $("div.row").append(' <div class="col-md-4">\n' +
                    '                    <div class="card mb-4 shadow-sm">\n' +
                    '                    <img class="card-img-top" src="../../static/images/' + _id + '.jpg" alt="Photo">' +
                    '                        <div class="card-body">\n' +
                    '                            <p class="h3">' + _name + '</p>\n' +
                    '                            <p class="card-text"><b>Gender/Age group</b>: ' + _gender + ' <br/><b>Type:</b> ' + _type + '<br/>\n' +
                    '                                <b>Size:</b> ' + _size + '</p>\n' +
                    '                            <div class="d-flex justify-content-between align-items-center">\n' +
                    '                                <p>Price: ' + _price + ' zł</p>\n' +
                    '                                <div class="btn-group">\n' +
                    '                                    <button onclick="getProduct(' + _id + ')" class="btn btn-sm btn-outline-secondary">Details</button>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>');
            });
        }
    });

}

function showShoesList() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/client/get",
        dataType: "json",

        error: function (e) {
            alert("An error occurred while processing JSON");
            console.log("JSON reading failed: ", e);
        },

        success: function (response) {
            console.log(response);
            $("#response").empty();
            $("#response").append(' <table class="table table-striped">\n' +
                '            <thead>\n' +
                '            <tr>\n' +
                '                <th scope="col">#</th>\n' +
                '                <th scope="col">Picture</th>\n' +
                '                <th scope="col">Name</th>\n' +
                '                <th scope="col">For</th>\n' +
                '                <th scope="col">Color</th>\n' +
                '                <th scope="col">Category</th>\n' +
                '                <th scope="col">Manufacturer</th>\n' +
                '                <th scope="col">Size</th>\n' +
                '                <th scope="col">Price</th>\n' +
                '                <th scope="col">Action</th>\n' +
                '            </tr>\n' +
                '            </thead>\n' +
                '            <tbody>\n' +
                '            </tbody>\n' +
                '        </table>');
            var id = 1;
            $.each(response, function (index, element) {
                var _id = element.id;
                var _name = element.name;
                var _gender = element.genderGroup.name;
                var _size = element.size.size;
                var _type = element.type.name;
                var _price = element.price;
                var _color = element.color.name;
                var _manufacturer = element.manufacturer.name;

                $("tbody").append(' <tr>\n' +
                    '                <th scope="row">' + id++ + '</th>\n' +
                    '                <td> <img style="max-width: 100px;" src="../../static/images/' + _id + '.jpg" alt="Photo"></td>' +
                    '                <td>' + _name + '</td>\n' +
                    '                <td>' + _gender + '</td>\n' +
                    '                <td>' + _color + '</td>\n' +
                    '                <td>' + _type + '</td>\n' +
                    '                <td>' + _manufacturer + '</td>\n' +
                    '                <td>' + _size + '</td>\n' +
                    '                <td>' + _price + '</td>\n' +
                    '                <td><button class="btn btn-sm btn-outline-secondary" onclick="choose(' + _id + ')">Choose</button></td>\n' +
                    '            </tr>');
            });
            $('html, body').animate({scrollTop: $(document).height()}, 'slow');

        }
    });
}

function choose(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/get/" + id,
        dataType: "json",

        error: function (e) {
            alert("An error occurred while processing JSON");
            console.log("JSON reading failed: ", e);
        },

        success: function (response) {
            console.log(response);
                alert("Wybrałeś buty: " + response.name + ".\nRozmiar: " + response.size.size + "\nCena:" + response.price);
        }
    });
}