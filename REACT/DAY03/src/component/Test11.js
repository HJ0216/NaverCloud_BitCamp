import React, { useState } from 'react';


const dataList = [
    { id: 1, name: 'chk1', text: '연령(만 14세 이상) 확인(필수)', isChk: false },
    { id: 2, name: 'chk2', text: '개인정보 위탁 처리 동의(필수)', isChk: false },
    { id: 3, name: 'chk3', text: '개인정보 수집 및 이용에 대한 동의(필수)', isChk: false },
    { id: 4, name: 'chk4', text: '이벤트 우대 혜택 동의 안내(선택)',  isChk: false },
]



const Test11 = () => {
    const [data, setData] = useState(dataList);

    const onChk = (e) => {    
        const {name, checked} = e.target;
        // 어떤 checkbox에서 event가 일어났는지 알기 위해 name 값 추출
    
        if(name === 'all'){
            setData(data.map(item => {
                return { // 객체 - 복사(덮어쓰기)
                    ...item,
                    isChk: checked
                }
            }))
        } else {
            setData(data.map(item => item.name === name ? {...item, isChk: checked} : item))
            // {...item} 객체 - 복사(덮어쓰기)
        }
    }



    return (
        <div>
            <p>
                <input type='checkbox'
                       name='all'
                   onChange={onChk}
                    checked={data.filter(item => item.isChk!==true).length < 1}/>
                    {/* 
                        isChk=false인 요소들을 필터링
                        ▶ 결과 배열의 길이가 1보다 작으면(= 모든 요소의 isChk 값이 true이면) true를 반환하고, 그렇지 않으면 false를 반환
                    */}
                <label>약관 동의</label>
            </p>
            <hr/>
            {
                data.map(item => <p key={item.id}>
                                    <input type='checkbox' name={item.name} id={item.name} checked={item.isChk} onChange={onChk} />
                                    {/* checked={item.isChk}의 초기값 설정을 위한 것이며, checked의 T/F변경은 내부적으로 일어남?? */}
                                    <label for={item.name}>{item.text}</label>
                                    {/* label 사용법: 대상에 id 선언 ▶ for 속성에 연결 */}
                                </p>)
            }
        </div>
    );
};

export default Test11;