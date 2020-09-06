import React, { Component } from 'react'; //Optional component
import { connect } from 'react-redux';


import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';

import { logUser } from './actions';

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            token: '',
            userToken: localStorage.getItem("userToken"),
            displayExtends: false,
            validated: false,
            error: {
                message: ''
            }
        }
    }

    componentDidMount() {
        //const { auth, setAuth } = this.context;

        console.log("token" + this.state.userToken);

        //[this.userToken, this.setUserToken] = useState(localStorage.getItem("userToken"));

    }
    logUser() {
        const { email, token } = this.state;
        console.log("this.props", this.props)
        this.props.logUser(email, token);
    }
    //https://www.taniarascia.com/using-context-api-in-react/
    // constructor() {
    //     super();
    //     this.state = { displayExtends: false };
    //     this.toggle = this.toggle.bind(this);
    // }
    toggle = () => {
        this.setState({ displayExtends: !this.state.displayExtends });
    }

    handleSubmit = (event) => {
        const form = event.currentTarget;
        event.preventDefault();
        event.stopPropagation();
        const { email, password } = this.state;
        if (form.checkValidity()) {
            this.setValidated(true);
            fetch("/api/user/login", {
                method: "POST",
                body: JSON.stringify({
                    "email": email,
                    "password": password
                }),
                headers: {
                    "Content-type": "application/json; charset=UTF-8"
                }
            }).then(response => response.json())
                .then(json => {
                    this.setState({userToken: json.auth_token});
                    localStorage.setItem("userToken", json.auth_token);
                    console.log("Getting the details from the auth ", json.auth_token);
                }).catch(error => console.log(error));
        }
    };
    updateValues = event => {
        let val = event.target.value;
        this.setState({ [event.target.name]: val });
    }
    setValidated = (val) => {
        this.setState({ validated: val });
    }
    render() {
        //const { auth, setAuth } = this.context;
        return <>
            <h1>Hello!</h1>
            <p>This is for testing only we will soon integrate with the backend api</p>
            {
                this.state.displayExtends ? (
                    <div>
                        <p>Putting more details in the application</p>
                        <button onClick={this.toggle}>Show Less</button>
                    </div>
                ) : (<div>
                    <button onClick={this.toggle}>Read More</button>
                </div>)
            }
            <hr />
            <Container>
                <Row md={4}>
                    <Form validated={this.state.validated} onSubmit={this.handleSubmit} >
                        <Form.Group controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>
                            <Form.Control required type="email"
                                onChange={event => this.setState({ email: event.target.value })}
                                placeholder="Enter email" />
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>

                        <Form.Group controlId="formBasicPassword">
                            <Form.Label>Password</Form.Label>
                            <Form.Control required
                                type="password"
                                onChange={event => this.setState({ password: event.target.value })}
                                placeholder="Password" />
                        </Form.Group>
                        <Button variant="primary" type="submit">Submit</Button>
                    </Form>
                </Row>
            </Container>
        </>
    }
}

function mapStateToProps(state) {
    const { user } = state;
    return {
        user
    }
}

export default connect(mapStateToProps, { logUser })(App);
//export default App;