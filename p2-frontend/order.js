
let newOrderResourceURL = "http://localhost:8080/cart/newOrder";

export async function createNewOrder(newOrder) {
    let response = await fetch(
        newOrderResourceURL,
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newOrder)
        }
    );

    return response;
}
