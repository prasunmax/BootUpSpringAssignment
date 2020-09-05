import React, { Component } from 'react';
import OrderCart from './OrderCart';
const CartProduct = ({ product: { id, name, description, price, quantity } }) => {
    // changeHandler= (e) =>{
    //     if (typeof this.props.onChange === 'function') {
    //         this.props.onChange(e.target.value);
    //     }
    // }
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

class Cart extends Component {
    state = { name: "Prasun", totalPrice: 0.0, products: [] ,userToken: localStorage.getItem("userToken")};

    componentDidMount() {
        this.getCart();
    }

    getCart = () => {
        fetch("/api/cart/" + this.state.name, {
            headers: {
                "Authorization": this.state.userToken
                //"Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJyb2xlIjpbXSwic3ViIjoiYWJjQHh5ei5jb20iLCJpYXQiOjE1OTkxMTg5MDksImV4cCI6MjIwMzkxODkwOX0.SYu-o9pwlujr8L4PJ4iyfUPiK4wZDrEC7H2H25JXbu0kvW_1OmFTQxuu3ROeNNseR81sjhtbtwTvOXfFG5HjbA"
            }
        })
            .then(response => response.json())
            .then(json => {
                this.setState({ products: json.items, totalPrice: json.totalAmount }); console.log(this.state.products);
            })
            .catch(error => console.log(error));

        console.log(this.state.products);
    }

    render() {
        return (
            <div>
                <p>Name:{this.state.name}</p>
                <p>Total Price:{this.state.totalPrice}</p>
                {this.state.products ? this.state.products.map(product => (
                    <CartProduct key={product.id} product={product} />
                )) : null
                }
                <br />
                <OrderCart clickFunction={this.getCart}/>
            </div>
        )
    }
}
export default Cart;