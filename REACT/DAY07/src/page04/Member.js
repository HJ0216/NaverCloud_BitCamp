import React from 'react';
import { Link, useNavigate } from 'react-router-dom';

const Member = ({item}) => {
    const {id, name, email} = item;
    const navigate = useNavigate();

    const css = {
        border: '2px solid dodgerblue',
        margin: '10px',
        padding: '10px',
    };

    const onGo = () => {
        navigate(`/member/${id}`);
    }


    return (
        <div style={css}>
            <p>ID: {id}</p>
            <h4>NAME: {name}</h4>
            <h5>EMAIL: {email}</h5>

            <p><Link to={`/member/${id}`}>Details</Link></p>
            <button onClick={onGo}>Details</button>
            {/* 
            Link 사용 시에는, Routes도 필수
            1. Member.js: Link
            2. App04.js: Route path: :memberId
            3. MemberDetail.js: useParams()
            */}
        </div>
    );
};

export default Member;