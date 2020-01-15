import ServiceClient from './ServiceClient'

export default class AppServiceClient {

    static loginBad = () => {
        return ServiceClient.get("loginBad");
    }

    static login = (name, pass) => {
        return ServiceClient.post("login", {
            params: { user_name: name, password: pass }
          });
    }

    static addUser = (name, pass, email) => {
        return ServiceClient.post("addUser", {
            params: { username: name, pass: pass,  email: email}
          });
    }
}