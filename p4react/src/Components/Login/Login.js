import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Link from '@material-ui/core/Link';
import Button from '@material-ui/core/Button';
import ServiceClient from '../../Services/ServiceClient';
import ReactDOM from 'react-dom';
import CreateUser from './CreateUser';


const useStyles = makeStyles(theme => ({
  container: {
    display: 'flex',
    flexWrap: 'wrap',
  },
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: 200,
  },

  button: {
    margin: theme.spacing(1),
  },
}));



export default class Login extends React.Component {

  constructor() {
    super();
    this.state = {
      username: '',
      password: ''
    }
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }



  handleSubmit(e) {
    e.preventDefault();

    ServiceClient.get("/login", {
      params: { username: this.state.username, pass: this.state.password }
    }).then((res) => {

      //console.log(res.data);

      // this.props.updateUsers();
    })

    this.setState({
      user_name: '',
      password: ''
    });
  }


  handleInputChange(e) {
    const { value, name } = e.target;
    console.log(value, name);
    this.setState({
      [name]: value
    });
  }


  openCreateUser(e) {
    ReactDOM.render(<CreateUser />, document.getElementById('root'));
  }
  


  render() {
    // RETURN THE COMPONENT


    return (

      <form action="http://localhost:8080/perform_login" noValidate autoComplete="off" method = "POST">
        <ul >
          <li style={{ listStyleType: "none" }}>
            <div>
              <TextField
                id="username"
                name="username"
                className={useStyles.textField}
                label="User Name"
                margin="normal"
                variant="outlined"
                value={this.state.username}
                onChange={this.handleInputChange}
              />
            </div>
          </li>


          <li style={{ listStyleType: "none" }}>
            <div>
              <TextField
                id="password"
                name="password"
                label="Password"
                className={useStyles.textField}
                type="password"
                autoComplete="current-password"
                margin="normal"
                variant="outlined"
                value={this.state.password}
                onChange={this.handleInputChange}
              />

            </div>
          </li>

          <li style={{ listStyleType: "none" }}>

            <div>

              <Button variant="contained" color="primary" className={useStyles.Button} type="submit">
                Login
              </Button>

            </div>
          </li>

          <li style={{ listStyleType: "none" }}>

            <div>
              <Link href="/CreateUser" >
                Register User
              </Link>

              <a href="/CreateUser"> FAQ </a>
              

              <Button color="inherit" onClick={this.openCreateUser}>CreateUser</Button>

            </div>
          </li>

        </ul>
      </form>
    );
  }
}