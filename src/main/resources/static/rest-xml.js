$(document).ready(function () {
    showShoesList();
});

function showShoesList() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/shoes/list",
        dataType: "xml",

        error: function (e) {
            alert("An error occurred while processing XML file");
            console.log("XML reading Failed: ", e);
        },

        success: function (response) {
            console.log(response);

            $(response).find("item").each(function () {
                var _name = $(this).find('name:first').text();
                var _gender = $(this).find('genderGroup').text();
                var _size = $(this).find('size:first').text();
                var _type = $(this).find('type').text();
                var _price = $(this).find('price').text();
                var _id = $(this).find('id').text();


                $("div.row").append(' <div class="col-md-4">\n' +
                    '                    <div class="card mb-4 shadow-sm">\n' +
                    '                    <img class="card-img-top" src="../../static/images/'+_id+'.jpg" alt="Photo">' +
                    '                        <div class="card-body">\n' +
                    '                            <p class="h3">' + _name + '</p>\n' +
                    '                            <p class="card-text"><b>Gender/Age group</b>: ' + _gender + ' <br/><b>Type:</b> ' + _type + '<br/>\n' +
                    '                                <b>Size:</b> ' + _size + '</p>\n' +
                    '                            <div class="d-flex justify-content-between align-items-center">\n' +
                    '                                <p>Price: '+_price+' zł</p>\n' +
                    '                                <div class="btn-group">\n' +
                    '                                    <button onclick="getProduct('+_id+')" class="btn btn-sm btn-outline-secondary">Details</button>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>');
            });
        }
    });
}

function getProduct(id) {
    var features = "location=1, status=1, scrollbars=1, width=" + 800 + ", height=" + 800 + ", top=" + 100 + ", left=" + 100;
    window.open("product.html?id="+id, "Product", features);
    window.close();
}

function sort(){

    var data = {};
    data["price"] = $("#priceSelect").val();
    data["manufacturer"] = $("#manufacturerSelect").val();
    data["type"] = $("#typeSelect").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/shoes/list/sort",
        dataType: "xml",
        data : {
            price: data["price"],
            manufacturer_id: data["manufacturer"],
            type_id: data["type"]
        },

        error: function (e) {
            alert("An error occurred while processing XML file");
            console.log("XML reading Failed: ", e);
        },

        success: function (response) {
            $("div.row").empty();
            $(response).find("item").each(function () {
                var _name = $(this).find('name:first').text();
                var _gender = $(this).find('genderGroup').text();
                var _size = $(this).find('size:first').text();
                var _type = $(this).find('type').text();
                var _price = $(this).find('price').text();
                var _id = $(this).find('id').text();


                $("div.row").append(' <div class="col-md-4">\n' +
                    '                    <div class="card mb-4 shadow-sm">\n' +
                    '                    <img class="card-img-top" src="../../static/images/'+_id+'.jpg" alt="Photo">' +
                    '                        <div class="card-body">\n' +
                    '                            <p class="h3">' + _name + '</p>\n' +
                    '                            <p class="card-text"><b>Gender/Age group</b>: ' + _gender + ' <br/><b>Type:</b> ' + _type + '<br/>\n' +
                    '                                <b>Size:</b> ' + _size + '</p>\n' +
                    '                            <div class="d-flex justify-content-between align-items-center">\n' +
                    '                                <p>Price: '+_price+' zł</p>\n' +
                    '                                <div class="btn-group">\n' +
                    '                                    <button onclick="getProduct('+_id+')" class="btn btn-sm btn-outline-secondary">Details</button>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>');
            });
        }
    });

}

