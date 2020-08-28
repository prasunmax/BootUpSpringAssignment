import React, { Component } from 'react'; //Optional component

class App extends Component {
    state = { displayExtends: false };
    // constructor() {
    //     super();
    //     this.state = { displayExtends: false };
    //     this.toggle = this.toggle.bind(this);
    // }
    toggle = () => {
        this.setState({ displayExtends: !this.state.displayExtends });
    }
    render() {

        return <div>
            <h1>Hello!</h1>
            <p>This is for testing only we will soon integrate with the backend api</p>
            {
                this.state.displayExtends ? (
                    <div>
                        <p>Putting more detals in the application</p>
                        <button onClick={this.toggle}>Show Less</button>
                    </div>
                ) : (<div>
                    <button onClick={this.toggle}>Read More</button>
                </div>)
            }
            <hr />
            
        </div>
    }
}



export default App;