// src/components/Header/index.js

import React from "react";
import "./Header.css";

class Header extends React.Component{

    render(){
        return (
            <nav className="Nav">
                <div className="Nav-menus">
                    <div className="Nav-brand">
                        {/*<a className="Nav-brand-logo" href="../../logo.png">*/}
                        {/*<a className="Nav-brand-logo" href="./">*/}
                        {/*    Instagram*/}
                        {/*</a>*/}
z                        <a>Onegram</a>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Header;