import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const MemberDetail = () => {
    const { memberId } = useParams();
    const [member, setMember] = useState({});
    const navigate = useNavigate();

    const css = {
        border: '1px solid tomato',
        margin: '10px',
        padding: '10px',
    };

    useEffect(() => {
        axios.get(`https://jsonplaceholder.typicode.com/users/${memberId}`)
            .then(res => setMember(res.data))
            // res.data: 응답객체의 prop: data, url에서 가져온 데이터 배열
        }, [])

    const onBack = () => {
        // naviage('/');
        navigate(-1);
    }

    return (
        <div style={css}>
            <h2>MEMBERDETAIL PAGE: {memberId}</h2>
            <h4>NAME: {member.name}</h4>
            <ul>
                <li>EMAIL: {member.email}</li>
                <li>PHONE: {member.phone}</li>
                <li>WEBSITE: {member.website}</li>

            </ul>
            <button onClick={onBack}>뒤로</button>


        </div>
    );
};

export default MemberDetail;