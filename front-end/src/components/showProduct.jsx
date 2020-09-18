import React from 'react';

export const Product = ({ product: { id, name, description, price, quantity }, clickFunction }) =>
    (
        <div className="card-list">
            <div className="card" >
                <div className="card-header">
                    <h3>{name}</h3>
                </div>
                <div className="card-body">
                    <p style={{ nowrap: 'nowrap' }}>Name: {description}</p>
                    <p>Price: {price}</p>
                    <p>Quantity: {quantity}</p>
                    {clickFunction ? <button className="btn btn-primary" onClick={() => clickFunction(id, price)}>Add One Item</button> : ''}
                </div>
            </div>
        </div>
    )
