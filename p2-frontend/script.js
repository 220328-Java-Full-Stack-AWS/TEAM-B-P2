import {createNewOrder} from "./order.js"
const placeOrderElm = document.getElementById('placeorder');

placeOrderElm.addEventListener('click',orderCreate);


async function orderCreate(){
  const timeStamp = Date.now();
  const newOrder=

{
    "creationDate" : "2010-03-01",
    "addressId": 3,
    "userId": 1,
    "orderTotal": 287,
    "orderItems": [{
        "itemTotalAmount":10,
        "quantity":1,
        "productId":3
    },{
        "itemTotalAmount":6776,
        "quantity":10,
        "productId":2
    }]
}


 console.log(newOrder);
  let response1 = await createNewOrder(newOrder);

  if (response.status == 202) {
       alert(`Successfully Submitted!`);
   } else {
       alert("Unable to create!");
   }


}