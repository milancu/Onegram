import React from 'react';
import './Modal.css'
import Single_follow from "../../components/Single_follow";

const ModalFollowers = ({close}) => {
    return (
        <div className={"modal-background"}>
            <div className={"center-modal-box"}>
                <div className={'follow-list'}>
                    <button className={"close-btn"} onClick={() => {close(false)}}> X </button>
                    {JSON.parse(localStorage.getItem('followers')).map(followed => (
                        <Single_follow follow={followed}/>
                    ))}
                </div>

            </div>
        </div>
    )
}


export default ModalFollowers;
