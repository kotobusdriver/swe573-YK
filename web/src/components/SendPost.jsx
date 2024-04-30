import {useState} from "react";
import FieldEntry from "./FieldEntry.jsx";
import {useNavigate} from "react-router-dom";

function SendPost(props) {
    const fieldDefinitions = props.community.postTemplateResource.fields;
    const [fields, setFields] = useState(createInitialValues(fieldDefinitions));

    function createInitialValues(fieldDefinitions) {
        return fieldDefinitions.map(f => {
            return {
                definition: f,
                value: null
            }
        });
    }

    function createPost() {
        // TODO
        console.log(fields)

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    communityId: new String(props.community.id),
                    byMemberId: new String(props.memberId),
                    fields: fields.map((f) => {
                        return {
                            templateFieldId: new String(f.definition.id),
                            data: new String(f.value)
                        }
                    })
                }
            )
        };

        fetch(`/api/posts`, requestOptions)
            .then(response => {props.onPostSubmit();})
            .catch(error => console.log("An error occurred", error));
    }

    function setFieldValue(field, value) {
        field.value = value;
    }

    return (
        <>
            <div className="m-5 border border-light-subtle p-3 rounded-2">
                <h4>Send a new post</h4>
                <form>
                    <fieldset className="row mb-3">
                        <div className="col-sm-10">
                            {
                                fields.map((field, index) => (
                                    <FieldEntry key={index} field={field.definition} onValueChange={(e) => setFieldValue(field, e)}/>
                                ))
                            }
                        </div>
                    </fieldset>
                    <button type="button" className="btn btn-primary" onClick={createPost}>Post</button>
                </form>
            </div>
        </>
    )
}

export default SendPost
