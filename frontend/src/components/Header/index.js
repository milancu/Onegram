// src/components/Header/index.js

import React from "react";
import "./Header.css";
import logo from '../../logo.png'

class Header extends React.Component {

    render() {
        return (
            <nav>
                <div className="header-line">
                    <img className="logo" src={logo} alt="Logo"/>
                    <a className="title">Onegram</a>
                </div>
            </nav>
        );
    }
}

export default Header;