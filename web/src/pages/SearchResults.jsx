import {useParams} from "react-router-dom";
import {useEffect, useState} from "react";

function SearchResults() {
    const {searchText} = useParams();
    const [communities, setCommunities] = useState([]);

    fetch(`/api/communities?search=${searchText}`)
        .then(response => response.json())
        .then(json => setCommunities(json.communities))
        .catch(error => console.error(error));

    return (
        <>
            <p>Welcome to search results page!</p>
            <p>Showing results for: {searchText}</p>
            <div className="justify-content-center p-2">
                {
                    (communities.length == 0) && <p>No communities found</p>
                }
                {
                    communities.map((community, index) => (
                        <div key={index} className="m-5 border border-light-subtle p-3 rounded-2">
                            <h1> <a href={`/communities/${community.id}`}>{community.name}</a></h1>
                            <h6>{community.visibility}, {community.status}</h6>
                            <h4>{community.description}</h4>
                        </div>
                    ))
                }
            </div>
        </>
    )
}

export default SearchResults
