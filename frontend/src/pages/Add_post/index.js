import React from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import '../../components/Profile_settings_form/profile-settings-form.css'
import axios from "axios";

var formData = new FormData();

const query = `
mutation($imageDescription: String!){
    createPost(input:{
        description:$imageDescription)
        })
    {
        id
    }
}
`;

// const newPost = {
//     description: document.getElementById("description").value,
//     file: null
// }
//
// const operations = JSON.stringify({query, variables: {newPost}});
// formData.append("operations", operations)
//
// axios({
//     url: "http://localhost:9090/graphql",
//     method: "post",
//     data: formData
// })
//     .then(response => { })
//     .catch(error => { });
//
// const file = document.getElementById("file").files[0];
// formData.append("0", file);

export const Add_post = () => {

    let state = {
        file: null
    }

    function handleFile(e){
        const fileSelector = document.getElementById('file');
        const fileList = e.target.files;
        console.log(fileList);
    }

    return (
        <div>
            <Header/>
            <form className={"settings-form"}>
                <label htmlFor="description">Username:</label><br/>
                <input type="text" id="description" name="description" placeholder={"describe your post"}/><br/>
                <div className={"setting-photo"}>
                    <input type="file"
                           id="file" name="profile-photo"
                           accept="image/png, image/jpeg"/>
                </div>
                <button className={"accept-button"} type={"button"}
                        onClick={(e) =>handleFile(e)}>Upload</button>
            </form>
            <Footer/>
        </div>



        //    TODO save button
    );
}

export default Add_post;