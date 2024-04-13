import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function CreateCommunity() {
    const VALID_VISIBILITIES = [
        'PUBLIC',
        'PRIVATE',
    ];

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [visibility, setVisibility] = useState('PUBLIC');
    const userId = "7acbd6c8-7eeb-40ee-8fec-af9f2a79ce06";

    function createCommunity() {
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
                        fields: []
                    }
                }
            )
        };

        fetch(`http://localhost:8080/api/communities`, requestOptions)
            .catch(error => console.error(error));

        goHome();
    }

    let navigate = useNavigate();
    const goHome = () =>{
        let path = `home`;
        navigate(path);
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
                    <button type="button" className="btn btn-primary" onClick={createCommunity}>Create</button>
                </form>
            </div>
        </>
    )
}

export default CreateCommunity
