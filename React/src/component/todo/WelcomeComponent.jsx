import React, {Component} from "react";
import HelloWorldService from "../../api/todo/HelloWorldService";

class WelcomeComponent extends Component{

    constructor(props){
        super(props)
        this.state = {
            welcomeMessage : ''
        }


        this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this)
        this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this)
        this.handleError = this.handleError.bind(this)

    }

    render(){
        return(
            <>
                <h1>Welcome!</h1>
                <div className="container">
                    Welcome {this.props.params.name}. You can manage your todos <a href="/todos">here</a>
                    <div className="container">
                        Click here to get a customize welcome message.
                        <button onClick={this.retrieveWelcomeMessage} 
                                className="btn btn-success">Get Welcome Message</button>
                    </div>
                </div>
                <div className="container">
                    {this.state.welcomeMessage}
                </div>
            </>
        );
    }

    retrieveWelcomeMessage(){
        HelloWorldService.executeHelloWorldPathVariableService(this.props.params.name)
        .then(response => this.handleSuccessfulResponse(response))
        .catch(error => this.handleError(error))
    }

    handleSuccessfulResponse(response){
        console.log(response)
        this.setState({welcomeMessage: response.data.message})
    }

    handleError(error){
        console.log(error.response)
        let errorMessage = '';

        if(error.message){
            errorMessage +=error.message
        }

        if(error.response && error.response.data){
            errorMessage += error.response.data.message
        }

        this.setState({welcomeMessage: errorMessage})
    }

}

export default WelcomeComponent