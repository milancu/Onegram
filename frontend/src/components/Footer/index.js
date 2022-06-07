// src/components/Footer/index.js

import React from "react";
import "./Footer.css";

export const Footer = () => {
    return (
        <nav>
            <div className={"links"}>
                {/*TODO prolinkovani*/}
                <div>API</div>
                <div>Model</div>
                <div>Dokumentace</div>
            </div>
            <div className="links">
                &copy;2022 Onegram for NSS by Přemek Bělka, Phuong Dong Cu, Ondřej Bureš, Jan Pivoňka
            </div>
            <br />
        </nav>
    );
}

export default Footer;
