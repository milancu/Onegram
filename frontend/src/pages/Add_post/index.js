import React from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import '../../components/Profile_settings_form/profile-settings-form.css'
import ImageUploading from "react-images-uploading";
import * as Constants from "../../gql/query";





export const Add_post = () => {

    function addNewPost() {

        let description = document.getElementById('description').value;
        const query = `{"query":"mutation {\\n\\tcreatePost(input:{\\n\\t\\tdescription:\\"` + description + `\\"\\n\\t}){\\n\\t\\tid\\n\\t}\\n}"}`;

        let formData = new FormData();
        formData.append("operations", query);
        formData.append("file", images[0].file);
        // console.log(images[0].file)

        let xhr = new XMLHttpRequest();
        xhr.open('POST', Constants.POST_GRAPHQL_API, true);
        xhr.setRequestHeader('Authorization', "Bearer " + localStorage.getItem('token'));
        xhr.send(formData);
    }

    let submit = document.getElementById('submit')
    if (submit) {
        submit.addEventListener("click", addNewPost);
    }

    const [images, setImages] = React.useState([]);
    const onChange = (imageList, addUpdateIndex) => {
        // data for submit
        // console.log(imageList, addUpdateIndex);
        setImages(imageList);
    };

    // console.log(images[0]);


    return (
        <div>
            <Header/>
            <form className={"settings-form"}>
                <div className={"setting-photo"}>
                    <ImageUploading
                        value={images}
                        onChange={onChange}
                        dataURLKey="data_url"
                    >
                        {({
                              imageList,
                              onImageUpload,
                              isDragging,
                              dragProps
                          }) => (
                            // write your building UI
                            <div className="upload__image-wrapper">
                                <button
                                    style={isDragging ? {color: "blue"} : null}
                                    onClick={onImageUpload}
                                    {...dragProps}
                                >
                                    Click and Drop here
                                </button>
                                &nbsp;
                                {imageList.map((image, index) => (
                                    <div key={index} className="image-item">
                                        <img src={image.data_url} alt="" width="100"/>
                                    </div>
                                ))}
                            </div>
                        )}
                    </ImageUploading>
                </div>
                <input type="text" id="description" name="description" placeholder={"describe your post"}/>
                <button id={'submit'} className={"accept-button"} type={"button"}>Upload</button>
            </form>
            <Footer/>
        </div>



        //    TODO save button
    );
}

export default Add_post;