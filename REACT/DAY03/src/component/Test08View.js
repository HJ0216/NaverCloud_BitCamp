import React from 'react';
import Test08Big from './Test08Big';
import Test08List from './Test08List';


const Test08View = ({one, data, onView}) => {
    return (
        <div className='bigview'>
            <Test08Big one={one} />
            <Test08List data={data} onView={onView}/>
        </div>
    );
};

export default Test08View;
