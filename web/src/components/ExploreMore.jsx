import community1 from '../assets/community.jpg';
import {useNavigate} from "react-router-dom";
import {useEffect, useState} from "react";

function ExploreMore() {
    const [communities, setCommunities] = useState([]);

    let navigate = useNavigate();
    const routeChange = () => {
        let path = `create-community`;
        navigate(path);
    }

    useEffect(() => {
        fetch(`/api/communities`)
            .then(response => response.json())
            .then(json => setCommunities(json.communities))
            .catch(error => console.error(error));
    }, []);


    return (
        <>
            <h4>Explore more:</h4>
            <div className="d-flex justify-content-center p-2">
                {
                    (communities.length == 0) && <p>No communities found</p>
                }
                {
                    communities.map((community) => (
                        <div key={community.id} className="card m-2 w-25">
                            <a href={`/communities/${community.id}`}>
                                <img src={community1} className="card-img-top" alt="..."/>
                                <div className="card-body">
                                    <p className="card-text">{community.name}</p>
                                </div>
                            </a>
                        </div>
                    ))
                }
            </div>
            <div className="d-flex flex-row justify-content-center">
                <p className="p-2">Can't find what you're looking for?</p>
                <button type="button" className="btn btn-outline-primary d-flex justify-content-end"
                        onClick={routeChange}>Create new
                    community
                </button>
            </div>
        </>
    )
}

export default ExploreMore
