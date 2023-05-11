import React, { useRef, useState } from 'react';
import style2 from '../css/TodoForm.module.css'


const TodoForm = ({onAdd}) => {
    const textRef = useRef();
    const [text, setText] = useState('');
    
    const onInput = (e) => {
        setText(e.target.value);
        // e.target을 통해서 이벤트가 발생한 태그 호출
    };

    const onSubmit = (e) => {
        e.preventDefault();
        //  HTML 폼(form)의 제출(submit) 이벤트를 막기 위해 사용
        // 이벤트의 기본 동작인 폼 제출이 중단되므로, 페이지가 새로고침되지 않고, 입력한 내용이 유지
        // 폼 제출 이벤트의 기본 동작을 막아서, 추가할 텍스트를 입력한 후에도 페이지가 새로고침되지 않도록 하고 있음

        if(!text) {return}
        onAdd(text)
        setText('');

        textRef.current.focus();
        // 추가버튼 클릭 후 input에 focus 맞추기
    };

    return (
        <div>
            <form className={style2.TodoForm} onSubmit={onSubmit}>
                <input type='text' value={text} onChange={onInput} placeholder='할 일 입력' ref={textRef} />
                <button type='submit'>추가</button>
                {/* submitBtn 클릭 시, form tag의 onSubmit 작동 */}
            </form>
        </div>
    );
};

export default TodoForm;