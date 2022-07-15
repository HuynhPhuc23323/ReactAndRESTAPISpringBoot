import axios from "axios";

class HelloWorldService {

    executeHelloWorldService() {
        return axios.get('http://localhost:8080/hello-world');
    }

    executeHelloWorldBeanService() {
        return axios.get('http://localhost:8080/hello-world-bean');
    }

    executeHelloWorldPathVariableService(name) {
        // let userName = 'phuchh266'
        // let password = '123456'

        // let basicAuthHeader = 'Basic ' + window.btoa(`${userName}:${password}`);
        return axios.get(`http://localhost:8080/hello-world-bean/path-variable/${name}`,
            // {
            //     headers: {
            //         Authorization: basicAuthHeader
            //     }
            // }
        );
    }

}

export default new HelloWorldService();