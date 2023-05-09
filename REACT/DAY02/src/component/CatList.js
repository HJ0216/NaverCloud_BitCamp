import React from 'react';
import CatItem from './CatItem'

// const CatList = (props) => {
const CatList = ({data}) => {
    return (
        <div>
            <ul className='list2'>
                {
                    data.map(item => <CatItem item={item} />)
                    /*
                    item: CatData.js의 데이터 1단위
                    {
                        id: 1,
                        img: 'https://cdn.pixabay.com/photo/2017/07/25/01/22/cat-2536662_960_720.jpg',
                        title: 'cat1'
                    },

                    넘긴 item을 입력받은 CatItem component가 반복문의 대상으로 반복됨
                    (cf. item의 개수만큼 CatItem Component가 생성됨)
                    */
                }
            </ul>
        </div>
    );
};

export default CatList;