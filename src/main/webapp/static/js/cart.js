



const changeQty = document.getElementsByClassName("change-qty")

function setCart(){
    for (let i = 0; i < changeQty.length; i++) {
        let prodId = changeQty[i].getAttribute("data-id")
        let plus = changeQty[i].children[1]
        let minus = changeQty[i].children[0]
        plus.addEventListener('click', () => setCartQty(prodId, 1))
        minus.addEventListener('click', () => setCartQty(prodId, -1))
    }
}

function refreshCart(){
    let quantities = document.getElementsByClassName("quantity")
    for (let i = 0; i < quantities.length; i++) {
        let qty = quantities[i];


    }
}

async function getAndRebuildCart(plusMinus){
    let products = await getItems();
    buildCart(plusMinus, products)

}

async function getItems(){
    const response = await fetch('/cart/get/');
    const product = await response.json();
    console.log(product)
    return product;


}

function buildCart(plusMinus, products){
    let div = document.getElementsByClassName("quantity")
    let priceDiv = document.getElementById("full-price")
    let fullPrice = 0;

    for (let i = 0; i < div.length; i++) {
        let qty = products[i].quantity
        div[i].innerHTML = "Quantity: " + qty
        fullPrice += products[i].price * qty

    }

    priceDiv.innerHTML = "Full price: " + Math.round(fullPrice * 100) / 100 +" EUR"


    // let div = document.getElementsByClassName("review-order")[0]
    // div.innerHTML = ""
    //
    // for (let i = 0; i < products.length; i++) {
    //     div.innerHTML += `
    //             <div className="form-group">
    //                 <div className="col-sm-3 col-xs-3">
    //                     <img className="img-responsive"
    //                         src="./static/img/product_${products[i].id}.jpg" />
    //                 </div>
    //                 <div className="col-sm-6 col-xs-6">
    //                     <div className="col-xs-12" >${products[i].name}</div>
    //                     <div className="col-xs-12" >${products[i].genre}</div>
    //                     <div className="col-xs-12 quantity" >${products[i].quantity}</div>
    //
    //                     <div className="change-qty" >
    //                         <div className="col-xs-12" id="minus">-</div>
    //                         <div className="col-xs-12" id="plus">+</div>
    //                     </div>
    //
    //                 </div>
    //                 <div className="col-sm-3 col-xs-3 text-right">
    //                     <h6 >${products[i].price} ${products[i].currency}</h6>
    //                 </div>
    //             </div>
    //             </div>
    //             `
    // }


}

async function setCartQty(prodId, plusMinus){
    await fetch("/cart/change", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: `{
   "id": ${prodId},
   "plusMinus": ${plusMinus}
  }`,
    });

    await getAndRebuildCart(plusMinus)
}

setCart()