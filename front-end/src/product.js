import React, { Component } from 'react';

// const Product = ({product}) => {
//     const { id, name, description, price, quatity } = product;
//     return (
//         <div style={{ display: 'inline-block', width: 300 }}>
//             <input type="hidden" value={id} />
//             <h3>{name}</h3>
//             <p>{description}</p>
//             <p>{price}</p>
//             <p>{quatity}</p>
//         </div>
//     )
// }
let rows = [{
    'description': 'Description',
    'id': 'id',
    'name': 'Name',
    'price': 'Price',
    'quantity': 'Quantity',
}];
const Product = ({ product: { id, name, description, price, quatity }, clickFunction }) =>
    (
        <div className="card" style={{ display: 'inline-block', width: 300 }}>
            <div className="card-header">
                <h3>{name}</h3>
            </div>
            <div className="card-body">
                <p style={{ nowrap: 'nowrap' }}>{description}</p>
                <p>{price}</p>
                <p>{quatity}</p>
                <button className="btn btn-primary" onClick={() => clickFunction(id)}>Add One Item</button>
            </div>
        </div>
    )

class Products extends Component {
    state = { name: "Prasun", products: [] };

    componentDidMount() {
        fetch("/api/products")
            .then(response => response.json())
            .then(json => {
                this.setState({ products: json.products }); console.log(this.state.products);
            })
            .catch(error => console.log(error));

        console.log(this.state.products);
        //.then(json => this.setState({'products':json}));
    }
    sendValuesToCart = (id) => {
        console.log(id);
        fetch("/api/cart", {
            method: "POST",
            body: JSON.stringify({
                "name": this.state.name,
                "items": [
                    {
                        "id": id,
                        "quantity": 1
                    }
                ]
            }),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        }).catch(error => console.log(error));
    }
    render() {
        return (
            <div>
                {this.state.products ? this.state.products.map(product => (
                    <Product key={product.id} product={product} clickFunction={this.sendValuesToCart} />
                )) : null
                }
            </div>
        )
    }
}

export default Products;