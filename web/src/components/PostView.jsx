import {useEffect, useState} from "react";
import FieldView from "./FieldView.jsx";

function PostView({post, template}) {

    const [member, setMember] = useState({
        name: "no name"
    });

    useEffect(() => {
        fetch(`http://localhost:8080/api/members/${post.byMemberId}`)
            .then(response => response.json())
            .then(json => setMember(json))
            .catch(error => console.error(error));
    }, []);

    return (
        <>
            <div className="card m-2 w-25">
                <div className="card-body">
                    <p className="card-text">by {member.name}</p>
                </div>
                <div className="col-sm-10">
                    {
                        post.fields.map((field, index) => (
                            <FieldView key={index} field={field} template={template}/>
                        ))
                    }
                </div>
            </div>
        </>
    )
}

export default PostView
