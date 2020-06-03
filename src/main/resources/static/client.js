function searchShoes() {
    $("#response").empty();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoes/search",
        dataType: "json",
        data: {
            shopsNumber: $("#shopNumberSelect").val(),
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
        },

        error: function (e) {
            alert("An error with search shoes!");
            console.log("JSON reading failed: ", e);
        },

        success: function (response) {
            if (response === [] || response.length === 0) {
                alert("Nothing found! Change parameters.");
            }
            else {
                console.log(response);
                $("#response").empty();
                $("#response").append(' <table class="table table-striped">\n' +
                    '            <thead>\n' +
                    '            <tr>\n' +
                    '                <th scope="col">#</th>\n' +
                    '                <th scope="col">Picture</th>\n' +
                    '                <th scope="col">Name</th>\n' +
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
                    var _id = element.shoes.id;
                    var _name = element.shoes.name;
                    var _size = element.size;
                    var _price = element.price;

                    $("tbody").append(' <tr>\n' +
                        '                <th scope="row">' + id++ + '</th>\n' +
                        '                <td> <img style="max-width: 100px;" src="../../static/images/' + _id + '.jpg" alt="Photo"></td>' +
                        '                <td>' + _name + '</td>\n' +
                        '                <td>' + _size + '</td>\n' +
                        '                <td>' + _price + '</td>\n' +
                        '                <td><button class="btn btn-sm btn-outline-secondary" onclick="choose(' + _id + ')">Choose</button></td>\n' +
                        '            </tr>');
                });
                $('html, body').animate({scrollTop: $(document).height()}, 'slow');
            }
        }
    });
}

function setInitValues() {

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoes/set-init",
        dataType: "text",
        data: {
            shopsNumber: $("#shopNumberSelect").val(),
            clientsNumber: $("#clientAgentSelect").val()

        },

        error: function (e) {
            alert("An error with set init values!");
            console.log("JSON reading failed: ", e);
        },

        success: function (result) {
            console.log(result);
            window.location.href = 'search.html';
        }
    });

}

function choose(id) {
    saveChoose(id);
    getProduct(id);
}

function getProduct(id) {
    window.open("product.html?id=" + id, "Product");
    window.close();
}

function saveChoose(id){
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoes/save-choose",
        dataType: "text",
        data: {
            chosenShoesId: id
        },

        error: function (e) {
            alert("An error while save chosen shoes!");
            console.log("An error while save chosen shoes!", e);
        },
    });
}