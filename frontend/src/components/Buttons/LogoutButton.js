import { GoogleLogout } from 'react-google-login';

const clientId = "1092533079010-rt6qic7u16ljrdm69tok4n0751apai8o.apps.googleusercontent.com";
const clientSecret = "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";

function LogoutButton(){

    const onSuccess = (res) => {
        console.log("Log out succesful", res.profileObj);
    }

    return(
        <div id={"signOutButton"}>
            <GoogleLogout
                clientId={clientId}
                clientSecret={clientSecret}
                buttonText={"Log OUT"}
                onSuccess={onSuccess} />
        </div>
    )
}

export default LogoutButton;