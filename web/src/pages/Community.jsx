import {useParams} from 'react-router-dom';
import {useEffect, useState} from "react";

function Community() {
    const {id} = useParams();

    const [community, setCommunity] = useState(null);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/communities/${id}`)
            .then(response => response.json())
            .then(json => setCommunity(json))
            .catch(error => console.error(error));
    }, []);

    useEffect(() => {
        fetch(`http://localhost:8080/api/communities/${id}/posts`)
            .then(response => response.json())
            .then(json => setPosts(json.posts))
            .catch(error => console.error(error));
    }, []);
    return (
        <>
            {community == null && <p>Community is not found</p>}
            {community != null &&
                <div className="m-5 border border-light-subtle p-3 rounded-2">
                    <h1>{community.name}</h1>
                    <h6>{community.visibility}, {community.status}</h6>
                    <h4>{community.description}</h4>
                </div>}
            {posts.length == 0 && <p>No posts found</p>}
            {community != null && posts.length > 0 &&
                posts.map((post) => (
                    <div key={post.id} className="card m-2 w-25">
                        <img src={post} className="card-img-top" alt="..."/>
                        <div className="card-body">
                            <p className="card-text">{post.name}</p>
                        </div>
                    </div>
                ))
            }
        </>
    )
}

export default Community
