
function supplierEventListener(){
    let suppliers = document.getElementsByName("supplier");
    for (let i = 0; i < suppliers.length; i++) {
        suppliers[i].addEventListener("click", supplierHandler)
    }
}
function supplierHandler(e){
    let supplierButton = e.currentTarget;
    let supplier = supplierButton.innerText;
    sortyBySupplier(supplier);
}

function sortyBySupplier(supplier){
    let productsHtml = document.getElementsByClassName("col mb-5");
    for (let i = 0; i < productsHtml.length; i++) {
        if(productsHtml[i].dataset.supplier === supplier || supplier === 'All'){
            productsHtml[i].style.display='';
        }else{
            productsHtml[i].style.display='none';
        }
    }
}

supplierEventListener();