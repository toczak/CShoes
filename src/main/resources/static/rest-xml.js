// function loadDoc() {
//     console.log("wchodze");
//
//     const xhttp = new XMLHttpRequest();
//     xhttp.onreadystatechange = function() {
//         console.log("wchodz1e" + this.readyState + " " + this.status);
//         if (this.readyState === 4 && this.status===200) {
//             myFunction(this);
//         }
//     };
//     xhttp.open("GET", "http://localhost:8080/index", true);
//     xhttp.send();
// }
//
// function myFunction(text) {
//     console.log("wchodze33");
//
//     const parser = new DOMParser();
//     const xmlDoc = parser.parseFromString(text.response, "text/xml");
//     console.log(xmlDoc);
//     // const books = xmlDoc.querySelectorAll("book");
//     // [].forEach.call(books, function(book) {
//     //     const title = book.querySelector("title").firstChild.nodeValue;
//     //     const author = book.querySelector("author").firstChild.nodeValue;
//     //     const genre = book.querySelector("genre").firstChild.nodeValue;
//     //     const price = Number(book.querySelector("price").firstChild.nodeValue);
//     //     const description = book.querySelector("description").firstChild.nodeValue;
//     //     console.log(title, author, gender, price, description);
//     // });
// }
// loadDoc();


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

            $(response).find("item").each(function () {
                var _name = $(this).find('name:first').text();
                var _gender = $(this).find('genderGroup').text();
                var _size = $(this).find('size:first').text();
                var _type = $(this).find('type').text();
                var _price = $(this).find('price').text();
                var _id = $(this).find('id').text();


                $("div.row").append(' <div class="col-md-4">\n' +
                    '                    <div class="card mb-4 shadow-sm">\n' +
                    '                    <img class="card-img-top" src="../static/images/'+_id+'.jpg" alt="Photo">' +
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

