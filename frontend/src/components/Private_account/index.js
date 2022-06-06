import React from "react";
import lock from "../../images/lock.png";
import './private.css';

export const Private_account = () => {
    return (
        <div className={"private-account"}>
            <img className={"lock"} src={lock} alt={"private profile"} />
            <h2>This account is private</h2>
            <h4>Follow this account to see their photos</h4>
            <br/>
        </div>
    );
}

export default Private_account;