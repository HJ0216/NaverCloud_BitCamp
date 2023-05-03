import React from 'react';
import Dog from './components/Dog';
import Test1 from './components/Test1';
import Test2 from './components/Test2';
import Test3 from './components/Test3';
import Test4 from './components/Test4';
import Test5 from './components/Test5';

const App = () => {
  return (
    <div> {/*최상단은 반드시 div로 감싸주어야 한다.*/}
      <Dog />
      <hr/>
      <Dog />
      <hr/>
      <Test1 />
      <hr/>
      <Test2 />
      <hr/>
      <Test3 />
      <hr/>
      <Test4 />
      <hr/>
      <Test5 />
    </div>
  );
};

export default App;