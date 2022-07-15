import React, {Component} from "react";
import moment from "moment";

import TodoDataService from "../../api/todo/TodoDataService";
import AuthenticationService from "./AuthenticationService";


class ListTodosComponent extends Component{

    constructor(props){
        super(props)
        this.state = {
            todos: [],
            message:""
        }

        this.addTodoClicked = this.addTodoClicked.bind(this)
        this.updateTodoClicked = this.updateTodoClicked.bind(this)
        this.deleteTodoClicked = this.deleteTodoClicked.bind(this)
        this.refreshTodos = this.refreshTodos.bind(this)

    }

    componentDidMount(){
       this.refreshTodos();
    }

    refreshTodos(){
        let username = AuthenticationService.getLoggedInUserName()
        TodoDataService.retrieveAllTodos(username)
        .then(
            response => {
                this.setState({
                    todos : response.data
                })
            }
        )
    }

    deleteTodoClicked(id){
        let username = AuthenticationService.getLoggedInUserName()

        TodoDataService.deleteTodo(username,id)
        .then(
            response => {
                this.setState({message : `Delete of todo ${id} successful`});
                this.refreshTodos();
            }
        )
    }

    addTodoClicked(){       
        this.props.navigate(`/todos/-1`)
        // -1 vì khi sử dụng method với id = -1 hoặc 0 sẽ chuyển sang tạo mới Todo ở WService       
    }
    
    updateTodoClicked(id){       
        this.props.navigate(`/todos/${id}`)       
    }

    render(){
        return(
            <div>
                <h1>List Todos</h1>
                {this.state.message &&<div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Description</th>
                                <th>Taget Date</th>
                                <th>Is Completed?</th>
                                <th>Update</th>
                                <th>Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.todos.map(
                                    todo => 
                                        <tr key = {todo.id}>
                                            <td>{todo.description}</td>
                                            <td>{todo.done.toString()}</td>
                                            <td>{moment(todo.targetDate).format('YYYY-MM-DD')}</td>
                                            <td>
                                                <button className="btn btn-success"
                                                        onClick={ () => this.updateTodoClicked(todo.id)}>Update</button>
                                            </td>
                                            <td>
                                                <button className="btn btn-warning"
                                                        onClick={ () => this.deleteTodoClicked(todo.id)}>Delete</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addTodoClicked}>Add</button>
                    </div>
                </div>
            </div>
        );
    }
}
export default ListTodosComponent