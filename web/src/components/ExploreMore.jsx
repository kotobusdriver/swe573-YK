import community from '../assets/community.jpg';
import { useNavigate } from "react-router-dom";

function ExploreMore() {
    let navigate = useNavigate();
    const routeChange = () =>{
        let path = `create-community`;
        navigate(path);
    }

    return (
        <>
            <h4>Explore more:</h4>
            <div className="d-flex justify-content-center p-2">
                <div className="card m-2 w-25">
                    <img src={community} className="card-img-top" alt="..."/>
                    <div className="card-body">
                        <p className="card-text">Community 1</p>
                    </div>
                </div>
                <div className="card m-2 w-25">
                    <img src={community} className="card-img-top" alt="..."/>
                    <div className="card-body">
                        <p className="card-text">Community 2</p>
                    </div>
                </div>
            </div>
            <div className="d-flex flex-row justify-content-center">
                <p className="p-2">Can't find what you're looking for?</p>
                <button type="button" className="btn btn-outline-primary d-flex justify-content-end" onClick={routeChange}>Create new
                    community
                </button>
            </div>
        </>
    )
}

export default ExploreMore
