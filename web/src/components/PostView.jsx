import {useContext, useEffect, useState} from "react";
import FieldView from "./FieldView.jsx";

function PostView({post, template, memberId, onDelete}) {

    const [member, setMember] = useState({
        name: "no name"
    });

    useEffect(() => {
        fetch(`/api/members/${post.byMemberId}`)
            .then(response => response.json())
            .then(json => setMember(json))
            .catch(error => console.error(error));
    }, []);

    function deletePost() {
        const requestOptions = {
            method: 'DELETE'
        };

        fetch(`/api/posts/${post.id}?memberId=${memberId}`, requestOptions)
            .then(response => onDelete())
            .catch(error => console.error(error));
    }

    return (
        <>
            <div className="card m-2 w-50">
                {
                    post.fields.map((field, index) => (
                        <FieldView key={index} field={field} template={template}/>
                    ))
                }
                <p>by {member.name}</p>
                { memberId != null && memberId === post.byMemberId &&
                    <button type="button" className="btn btn-outline-danger" onClick={deletePost}>Delete</button>
                }
            </div>
        </>
    )
}

export default PostView
