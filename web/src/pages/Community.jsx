import {useParams} from 'react-router-dom';
import {useContext, useEffect, useState} from "react";
import SendPost from "../components/SendPost.jsx";
import {ApplicationContext} from "../ApplicationContext.jsx";
import PostView from "../components/PostView.jsx";

function Community() {
    const {id} = useParams();
    const context = useContext(ApplicationContext);

    const [community, setCommunity] = useState(null);
    const [posts, setPosts] = useState([]);
    const [members, setMembers] = useState([]);

    const [refreshKey, setRefreshKey] = useState(0);

    function refreshPosts() {
        console.log("Refreshing")
        setRefreshKey(old => old + 1)
    }

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
    }, [refreshKey]);

    useEffect(() => {
        fetch(`http://localhost:8080/api/communities/${id}/members`)
            .then(response => response.json())
            .then(json => setMembers(json.members))
            .catch(error => console.error(error));
    }, []);

    const memberId = members.find((member) => member.userId === context.user.id)?.id

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
                posts.map((post, index) => (
                    <PostView key={index} post={post} template={community.postTemplateResource}/>
                ))
            }
            {community != null &&
                <SendPost community={community} memberId={memberId} onPostSubmit={refreshPosts}/>
            }
        </>
    )
}

export default Community
