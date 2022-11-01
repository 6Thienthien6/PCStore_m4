class AppPage {
    static renderProductItem(obj) {
        return `
        <div class="card mx-3 col-3" style="padding: 0;width: 20%">
  <img src=${obj.image} class="card-img-top" alt="...">
  <div class="card-body">
    <h5 class="card-title">${obj.name}</h5>
    <p class="card-text">${new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(obj.price)}</p>
<!--    <button type="button" class="btn btn-cart">Thêm Vào Giỏ</button>-->
  </div>
</div>
        `

        // return `
        //     <li class="main-product">
        //             <div class="img-product">
        //                 <img class="img-prd"
        //                     src="${obj.image}"
        //                     alt=""
        //                     style="width: 20%;height: 150px"
        //                     >
        //             </div>
        //             <div class="content-product">
        //                 <h3 class="content-product-h3">${obj.name}</h3>
        //                 <div class="content-product-deltals">
        //                     <div class="price">
        //                         <span class="money">${new Intl.NumberFormat('vi-VN', {
        //     style: 'currency',
        //     currency: 'VND'
        // }).format(obj.price)}
        //                         </span>
        //                     </div>
        //                     <button type="button" class="btn btn-cart">Thêm Vào Giỏ</button>
        //                 </div>
        //             </div>
        //         </li>
        // `;
    }
}