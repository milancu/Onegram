import React, { Component }  from 'react';
import {useState} from "react";
import './profile_dashboard.css'
import Single_follow from "../../components/Single_follow";

const ModalFollowing = () => {
    return (
        <div className={"center-follower-box"}>
            <div className={'follow-list'}>
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
                <Single_follow />
            </div>

        </div>

    )
}


export default ModalFollowing;
