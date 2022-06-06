import React from "react"
import "./followersModal.css"
import Single_follow from "../Single_follow";
import {useEffect} from 'react';

const FollowersModal = ({type, closeFollowersModal, modalState}) => {

    const followingData = JSON.parse(localStorage.getItem('following'));
    // console.log(followingData)
    let displayData = JSON.parse(localStorage.getItem(modalState));

    for (let i = 0; i < displayData.length; i++) {
        displayData[i].followed = false;
        for (let j = 0; j < followingData.length; j++) {
            if (displayData[i].id === followingData[j].id) {
                displayData[i].followed = true;
                break;
            }
        }
    }


    localStorage.setItem("displayData", JSON.stringify(displayData));
    // console.log(JSON.parse(localStorage.getItem("displayData")));

    return (
        <div className={"followers-modal-background"}>
            <div className={"followers-modal-container"}>
                <div className={"followers-modal-close-button"}>
                    <button onClick={() => {
                        closeFollowersModal(false)
                    }}> X
                    </button>
                </div>
                <div className={"followers-modal-follows"}>
                    {JSON.parse(localStorage.getItem("displayData")).map(follow => (
                        <Single_follow follow={follow}/>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default FollowersModal