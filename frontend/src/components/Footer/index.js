// src/components/Footer/index.js

import React from "react";
import "./Footer.css";
import {Link, useNavigate} from "react-router-dom";
import logo from "../../images/logo.png";

export const Footer = (props) => {
    return (
        <nav>
            <div className={"links"}>
                {/*TODO prolinkovani*/}
                <div>API</div>
                <div>Model</div>
                <div>Dokumentace</div>
            </div>
            <div>
                &copy;2022 Onegram for NSS by Přemek Bělka, Phuong Dong Cu, Ondřej Bureš, Jan Pivoňka
            </div>
        </nav>
    );
}

export default Footer;
