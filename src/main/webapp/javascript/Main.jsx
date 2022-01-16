import React, { Component } from "react";
import ReactDOM from 'react-dom';
import '../css/Main.css';

class Main extends Component {
    render() {
        return (
            <div id="main">
                <!--Create menu-->
                <a href="#" class="logo">Portfolio</a>

                <ul class="menu">
                    <li> <a href="#home">Home</a> </li>
                    <li> <a href="#study">Study</a> </li>
                    <li> <a href="#Account">Account</a></li>
                </ul>

                <!--Top section starts-->
                <section class="top" id="home">
                    <div class="textBx">
                        <h1>Word of the Day</h1>

                        <p>Date/Time: <span id="datetime"></span></p>
                        <script>
                            var dt = new Date();
                            document.getElementById("datetime").innerHTML = dt.toLocaleString();
                        </script>
                    </div>
                </section>

                <!--Favorite section-->

                <!--Study button sections-->

            </div>
        );
    }
}

ReactDOM.render(
    <Main />,
    document.getElementById('react-mountpoint')
);