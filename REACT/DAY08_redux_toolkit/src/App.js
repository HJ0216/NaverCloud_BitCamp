import React from 'react';
import Animal from './component/animal/Animal';
import Color from './component/color/Color';
import Count from './component/count/Count';

const App = () => {
  return (
    <div>
      <Animal />
      <Color />
      <Count />
    </div>  
  );
};

export default App;