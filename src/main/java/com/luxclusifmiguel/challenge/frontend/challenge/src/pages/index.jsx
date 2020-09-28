import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

const MainPage = () => {

    return (
        <body>
        <div>
            <h1 align="center">FARFETCH SECOND LIFE.</h1>
        </div>
        <div></div>
        <div className="App">
            <Link to="/form">
            <Button variant="dark" className="mr-2">Start selling</Button>
            </Link>
        </div>
        </body>
    )

}

export default MainPage;