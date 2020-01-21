import axios from 'axios'
import LocalStorageService from './LocalStorageService';

/*export default axios.create({
    baseURL: "http://localhost:8080/"
})
*/

/*
ServiceClient.getAxiosInstance(true).get("/recoveryPass", {
            params: { email: email }
          }).then((res) => {
      
            console.log(res.data);
      
            alert(res.data);
            // this.props.updateUsers();
          })
*/



/*con true no pasa por la seguridad*/
/*
ServiceClient.getAxiosInstance(true)({
                method: 'post',
                url: '/nombre_funcion_en_spring',
                headers: { 'content-type': 'application/x-www-form-urlencoded' },
                data: querystring.stringify({
                    username: username,
                    password: pass
                })
            }).then(function (response) {
                console.log(response.data);
                console.log(response.status);

                //var result = response.data;

                localStorageService.setToken(response.data);

                if (response.status === 200) {
                    setRedirect(true);
                    //ReactDOM.render(<Lobby />, document.getElementById('root'));
                }

            }).catch(function (error) {
                //console.log(error);
                //console.log(error.response.status);
                if (error.response.status === 401) {
                    setMsg("User or Password incorrect");

                    localStorageService.setToken("");
                }
            })
*/


export default class ServiceClient {

    static getAxiosInstance(avoidInterceptor = false) {

        const instance = axios.create({
            baseURL: "http://localhost:8080/"
        });

        // LocalstorageService
        const localStorageService = LocalStorageService.getService();

        // Add a request interceptor
        instance.interceptors.request.use(
            config => {
                try {
                    const token = localStorageService.getToken();

                    //alert(!window.$doingLogin+" "+token);

                    if (token && !avoidInterceptor /*!window.$doingLogin*/) {
                        config.headers.Authorization = token;
                        //config.headers['Content-Type'] = 'application/json';    
                        //alert(config.headers.Authorization);
                        //alert(config.headers.Authorization)
                    }

                } catch (error) {
                }


                return config;
            },
            error => {
                Promise.reject(error)
            });


        /* axios.interceptors.response.use((response) => {
             return response
         },
             function (error) {
                 const originalRequest = error.config;
                 if (error.response.status === 401 && !originalRequest._retry) {
 
                     originalRequest._retry = true;
                     //localStorageService.setToken(null);
                     //axios.defaults.headers.common['Authorization'] = null;
 
                     
                     return axios.post('/auth/token',
                         {
                             "refresh_token": localStorageService.getRefreshToken()
                         })
                         .then(res => {
                             if (res.status === 201) {
                                 // 1) put token to LocalStorage
                                 localStorageService.setToken(res.data);
 
                                 // 2) Change Authorization header
                                 axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
 
                                 // 3) return originalRequest object with Axios.
                                 return axios(originalRequest);
                             }
                         })
                    
                 }
 
                 // return Error object with Promise
                 return Promise.reject(error);
             }); */

        return instance;
    }

}