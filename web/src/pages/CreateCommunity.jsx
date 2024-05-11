import {useContext, useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {ApplicationContext} from "../ApplicationContext.jsx";

function CreateCommunity() {
    const VALID_VISIBILITIES = [
        'PUBLIC',
        'PRIVATE',
    ];

    const FIELD_TYPES = Object.freeze({
        TEXT: 'TEXT',
        DATE: 'DATE',
        IMAGE: 'IMAGE',
        ATTACHMENT: 'ATTACHMENT',
    });

    const context = useContext(ApplicationContext);
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [visibility, setVisibility] = useState('PUBLIC');
    const [hasError, setHasError] = useState(false);
    const [fields, setFields] = useState([createFieldDefinition("Title", FIELD_TYPES.TEXT, false)]);

    useEffect(() => {
        if (context.user == null) {
            setHasError(true);
        }
    });

    function createFieldDefinition(name, type, optional) {
        return {
            name: name,
            type: type,
            optional: optional
        };
    }

    function handleCreationFailure(error) {
        console.log("Error occurred: " + error)
    }

    function createCommunity() {
        const userId = context.user.id;
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(
                {
                    name: new String(name),
                    description: new String(description),
                    adminUserId: new String(userId),
                    visibility: new String(visibility),
                    template: {
                        fields: fields
                    }
                }
            )
        };

        fetch(`/api/communities`, requestOptions)
            .then(response => {
                if(!response.ok) throw new Error("error");
                else goHome();
            })
            .catch(error => handleCreationFailure(error));
    }

    let navigate = useNavigate();
    const goHome = () => {
        let path = `home`;
        navigate(path);
    }

    if (hasError)
        return (
            <>
                <div>
                    You need to log in.
                </div>
            </>
        )

    function setFieldName(index, newName) {
        const nextFields = fields.map((f, i) => {
            if (i === index) {
                f.name = newName
                return f;
            } else {
                return f;
            }
        });
        setFields(nextFields);
    }

    function addField() {
        const nextFields = [
            ...fields,
            createFieldDefinition("new field", FIELD_TYPES.TEXT, true),
        ];
        setFields(nextFields);
    }

    function removeField(index) {
        const nextFields = [
            ...fields.slice(0, index),
            ...fields.slice(index + 1)
        ];
        setFields(nextFields);
    }

    function setFieldType(index, newType) {
        const nextFields = fields.map((f, i) => {
            if (i === index) {
                f.type = newType
                return f;
            } else {
                return f;
            }
        });
        setFields(nextFields);
    }

    function setFieldOptional(index, newOptional) {
        console.log(newOptional)
        const nextFields = fields.map((f, i) => {
            if (i === index) {
                f.optional = newOptional
                return f;
            } else {
                return f;
            }
        });
        setFields(nextFields);
    }

    return (
        <>
            <div className="m-5 border border-light-subtle p-3 rounded-2">
                <h6>Create new community:</h6>
                <form>
                    <div className="row mb-3">
                        <label htmlFor="inputCommunityName" className="col-sm-2 col-form-label">Choose community
                            name</label>
                        <div className="col-sm-10">
                            <input type="text" className="form-control" id="inputCommunityName" value={name}
                                   onChange={(e) => setName(e.target.value)}/>
                        </div>
                    </div>
                    <div className="row mb-3">
                        <label htmlFor="inputDescription" className="col-sm-2 col-form-label">Write a
                            description</label>
                        <div className="col-sm-10">
                            <textarea className="form-control" id="inputDescription" rows="3" value={description}
                                      onChange={(e) => setDescription(e.target.value)}></textarea>
                        </div>
                    </div>
                    <fieldset className="row mb-3">
                        <legend className="col-form-label col-sm-2 pt-0">Visibility</legend>
                        <div className="col-sm-10">
                            {
                                VALID_VISIBILITIES.map(option => (
                                    <div key={option} className="form-check">
                                        <input
                                            type="radio"
                                            name="current-visibility"
                                            className="form-check-input"
                                            id={option}
                                            value={option}
                                            checked={option === visibility}
                                            onChange={event => {
                                                setVisibility(event.target.value);
                                            }}
                                        />
                                        <label className="form-check-label" htmlFor={option}>
                                            {option}
                                        </label>
                                    </div>
                                ))
                            }
                        </div>
                    </fieldset>
                    <fieldset className="row mb-3">
                        <legend className="col-form-label col-sm-2 pt-0">Post template</legend>
                        <div className="col-sm-10">
                            {
                                fields.map((field, index) => (
                                    <div key={index} className="row mb-3">
                                        <label htmlFor="inputFieldName" className="col-sm-2 col-form-label">Field
                                            name</label>
                                        <div className="col-sm-10">
                                            <input type="text" className="form-control" id="inputFieldName"
                                                   value={field.name}
                                                   onChange={(e) => setFieldName(index, e.target.value)}/>
                                        </div>
                                        <label htmlFor="inputFieldType" className="col-sm-2 col-form-label">Field
                                            type</label>
                                        <div className="col-sm-10">
                                            {
                                                Object.keys(FIELD_TYPES).map((key, index) => (
                                                    <div key={key} className="form-check">
                                                        <input
                                                            type="radio"
                                                            name="current-type"
                                                            className="form-check-input"
                                                            id={key}
                                                            value={FIELD_TYPES[key]}
                                                            onChange={event => {
                                                                setFieldType(index, event.target.value);
                                                            }}
                                                        />
                                                        <label className="form-check-label" htmlFor={key}>
                                                            {key}
                                                        </label>
                                                    </div>
                                                ))
                                            }
                                        </div>
                                        <div className="col-sm-10 form-check">
                                            <input type="checkbox" className="form-check-input" id="flexCheckDefault"
                                                   checked={field.optional}
                                                   onChange={(e) => setFieldOptional(index, e.target.checked)}/>
                                            <label className="form-check-label" htmlFor="flexCheckDefault">
                                                Field
                                                is optional
                                            </label>
                                        </div>
                                        <button type="button" className="btn btn-primary" onClick={() => {
                                            removeField(index);
                                        }}>Remove field
                                        </button>
                                    </div>
                                ))
                            }
                            <button type="button" className="btn btn-primary" onClick={addField}>Add new field</button>
                        </div>
                    </fieldset>
                    <button type="button" className="btn btn-primary" onClick={createCommunity}>Create</button>
                </form>
            </div>
        </>
    )
}

export default CreateCommunity
