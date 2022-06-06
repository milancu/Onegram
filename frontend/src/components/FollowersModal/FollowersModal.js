import React from "react"
import "./followersModal.css"
import Single_follow from "../Single_follow";

const FollowersModal = ({type, closeFollowersModal, modalState}) => {

    return (
        <div className={"followers-modal-background"} >
            <div className={"followers-modal-container"} >
                <div className={"followers-modal-close-button"}>
                    <button onClick={() => {closeFollowersModal(false)}}> X </button>
                </div>
                <div className={"followers-modal-follows"} >
                    {JSON.parse(localStorage.getItem(modalState)).map(follow => (
                        <Single_follow follow={follow}/>
                    ))}
                </div>
            </div>
        </div>
    )
}

export default FollowersModal