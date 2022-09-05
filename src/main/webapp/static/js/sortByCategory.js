
function categoryEventListener(){
    let categories = document.getElementsByClassName("dropdown-item");
    for (let i = 0; i < categories.length; i++) {
        categories[i].addEventListener("click", categoryHandler)
    }
}
function categoryHandler(e){
    let categoryButton = e.currentTarget;
    let category = categoryButton.innerText;
    sortyByCategory(category);
}

function sortyByCategory(category){
let productsHtml = document.getElementsByClassName("col mb-5");
    for (let i = 0; i < productsHtml.length; i++) {
        if(productsHtml[i].dataset.category === category || category === 'All'){
            productsHtml[i].style.display='';
        }else{
            productsHtml[i].style.display='none';
        }
    }
}

categoryEventListener();