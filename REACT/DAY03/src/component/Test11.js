import React, { useState } from 'react';


const dataList = [
    { id: 1, name: 'chk1', text: '연령(만 14세 이상) 확인(필수)', isChk: false },
    { id: 2, name: 'chk2', text: '개인정보 위탁 처리 동의(필수)', isChk: false },
    { id: 3, name: 'chk3', text: '개인정보 수집 및 이용에 대한 동의(필수)', isChk: false },
    { id: 4, name: 'chk4', text: '이벤트 우대 혜택 동의 안내(선택)',  isChk: false },
]



const Test11 = () => {
    const [data, setData] = useState(dataList);
    // useState를 선언할 경우, data는 setter를 통한 주입만 가능

    const onChk = (e) => {    
        const {name, checked} = e.target;
        // e.target: 이벤트가 발생한 태그 - name, value 반환
        // * 어떤 태그에서 event가 일어났는지 알기 위해 name 값 추출
        // checked 값은 e.target.checked로부터 가져오므로 isChk 값을 참조하지 않

        if(name === 'all'){
            setData(data.map(item => {
                return {
                    ...item,
                    // 객체 - 복사(덮어쓰기)
                    // 객체를 수정할 때는 항상 원본 객체를 변경하지 않고 새로운 객체를 생성하고 수정하는 것이 좋음
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
                       id='all'
                   onChange={onChk}
                    checked={data.filter(item => item.isChk!==true).length < 1}/>
                    {/* 
                        isChk=false인 요소들을 필터링
                        ▶ 결과 배열의 길이가 1보다 작으면(= 모든 요소의 isChk 값이 true이면) true를 반환하고, 그렇지 않으면 false를 반환
                    */}
                <label for='all'>약관 동의</label>
            </p>
            <hr/>
            {
                data.map(item => <p key={item.id}>
                                    <input type='checkbox' name={item.name} id={item.name} checked={item.isChk} onChange={onChk} />
                                    {/* 
                                        checked={item.isChk}에서 isChk의 초기값은 dataList 배열에서 제공된 초기값으로 설정
                                        input type='checkbox' 클릭 ▶ onClick event: checked 변경 ▶ onChange 호출 ▶onChk: isChk 변경
                                        * 클릭 이벤트에서는 checked 값을 변경하고, 체크 상태 변경 이벤트에서는 checked 값을 참조하여 state를 변경
                                    */}
                                    <label for={item.name}>{item.text}</label>
                                    {/* label 사용법: 대상에 id 선언 ▶ for 속성에 연결 */}
                                </p>)
            }
        </div>
    );
};

export default Test11;