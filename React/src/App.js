import './App.css';
import './bootstrap.css'
import React,{ Component } from 'react';
import TodoApp from './component/todo/TodoApp';

class App extends Component {
  render(){
    return (
      <div className="App">
        <TodoApp/>
      </div>
    );

  }
}

export default App;
