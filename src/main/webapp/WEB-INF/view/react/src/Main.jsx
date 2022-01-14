import React, { Component } from "react";
import ReactDOM from 'react-dom';
import './main.css';

class Main extends Component {
    render() {
        return (
            <div id="main">
                <h1>Word of the Day</h1>
            </div>
        );
    }
}

ReactDOM.render(
    <Main />,
    document.getElementById('react-mountpoint')
);