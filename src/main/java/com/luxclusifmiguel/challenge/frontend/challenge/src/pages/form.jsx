import React, { Component } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Card, Form, Button, Col } from 'react-bootstrap';
import { Link } from "react-router-dom";
import axios from "axios";

class SellingForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userId: "",
            firstName: "",
            lastName: "",
            email: "",
            country: "",
            phone: "",
            brand: "",
            condition: "",
            size: ""
        }

        this.handleChange = this.handleChange.bind(this);
        this.makePostRequests = this.makePostRequests.bind(this);
    }

    makePostRequests(event) {
        event.preventDefault();
    }

    handleChange = event => {
        this.setState({
            [event.target.name]: event.target.value
        });
    }

    render() {

        const {firstName, lastName, email, country, phone, } = this.state; 

        return (
            <div>
                <h1 align="center">FARFETCH SECOND LIFE.</h1>
                <Card className="App">
                    <Form onSubmit={this.makePostRequests} id="form">
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridFirst">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control required
                                        type="text" name="first name"
                                        value={firstName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter first name" />
                                </Form.Group>
                                <Form.Group as={Col} controlId="formGridLast">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control required
                                        type="text" name="first name"
                                        value={this.state.firstName}
                                        onChange={this.handleChange}
                                        className="form"
                                        placeholder="Enter first name" />
                                </Form.Group>
                            </Form.Row>
                        </Card.Body>
                    </Form>
                </Card>
                <Link to="/">
                    <button type="button" class="btn btn-dark">Get your offer</button>
                </Link>
                <Link to="/">
                    <Button variant="light" className="mr-2">Home page</Button>
                </Link>
            </div>
        );

    }
}
export default SellingForm;