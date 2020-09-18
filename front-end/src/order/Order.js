import React, { Component } from 'react';

const OrderProduct = ({ product: { id, name, description, price, quantity } }) => {
    return (
        <div className="card" style={{ display: 'inline-block', width: 300 }}>
            <input type="hidden" value={id} />
            <div className="card-header">
                <h3>{name}</h3>
            </div>
            <div className="card-body">
                <p>Description: {description}</p>
                <p>Price: {price}</p>
                <p>Quantity: {quantity}</p>
            </div>
        </div>
    )
}

export default class Order extends Component {
    state = { name: "Prasun", totalPrice: 0.0, products: [] };

    // componentDidMount() {
    //     fetch("/api/cart/" + this.state.name)
    //         .then(response => response.json())
    //         .then(json => {
    //             this.setState({ products: json.items, totalPrice: json.totalAmount }); console.log(this.state.products);
    //         })
    //         .catch(error => console.log(error));

    //     console.log(this.state.products);
    //     //.then(json => this.setState({'products':json}));
    // }
    render() {
        return (
            <div>
                <p>Name:{this.state.name}</p>
                <p>Total Price:{this.state.totalPrice}</p>
                {
                // this.state.products ? this.state.products.map(product => (
                //     <OrderProduct key={product.id} product={product} />
                // )) : null
                }
            </div>
        )
    }
}
