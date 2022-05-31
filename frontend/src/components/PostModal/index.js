
export const PostModal = (props) => {
    const description = props.caption ? props.caption : "fuck off"

    return window.confirm(description)
}

export default PostModal