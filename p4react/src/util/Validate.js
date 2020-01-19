
export default class Validate {

    static validateEmail = (text) => {
        
        try {
            //let reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            let reg = /\S+@\S+\.\S+/;
            if (reg.test(text) === false) {
                console.log("Email is Not Correct");
                //this.setState({ email: text })
                return false;
            }
            else {
                //this.setState({ email: text })
                console.log("Email is Correct");
                return true;
            }
        } catch (error) {
            console.log(error);
            console.log("Problem in the validation of email");
            return false;
        }

    }


    static validateUser = (str) => {
        // at least one number, one lowercase and one uppercase letter
        // at least six characters
        //var re = /(?=.\d)(?=.[a-z])(?=.*[A-Z]).{6,}/;


        // at least 4 characters and maximun 10
        //no special characters
        var re = /^[A-Za-z]\w{3,9}$/;
        console.log(re.test(str));
        return re.test(str);
    }

    static validatePassword = (str) => {
        /*
        Minimo 8 caracteres
        Maximo 15
        Al menos una letra mayúscula
        Al menos una letra minucula
        Al menos un dígito
        No espacios en blanco
        Al menos 1 caracter especial
        */

        /*
        at least one Upper character
        at least one lower character
        at least one digit
        at least one special character
        not allow blank space
        */
       var re = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){6,10}$/;
       console.log(re.test(str));
       return re.test(str);
    }

    static validatePasswordTemp = (str) => {       

        /*
        validate that the temporal password is a number
        */
       var re = /^([0-9])*$/;
       console.log(re.test(str));
       return re.test(str);
    }
}