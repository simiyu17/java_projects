

import React, { Component } from 'react'
import StudentService from '../services/StudentService';
import Select from 'react-select'


const genders = [
    { value: 'MALE', label: 'Male' },
    { value: 'FEMALE', label: 'Female' }
  ];

class CreateStudentComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            name: '',
            gender: '',
            city: '',
            country: '',
            options: genders
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeGenderHandler = this.changeGenderHandler.bind(this);
        this.changeCityHandler = this.changeCityHandler.bind(this);
        this.changeCountryHandler = this.changeCountryHandler.bind(this);
        this.saveOrUpdateStudent = this.saveOrUpdateStudent.bind(this);
    }

    // step 3
    componentDidMount() {

        // step 4
        if (this.state.id === '_add') {
            return
        } else {
            StudentService.getStudentById(this.state.id).then((res) => {
                let student = res.data;
                this.setState({
                    name: student.name,
                    gender: student.gender,
                    city: student.city,
                    country: student.country
                });
            });
        }
    }
    saveOrUpdateStudent = (e) => {
        e.preventDefault();
        let student = { name: this.state.name, gender: this.state.gender, city: this.state.city, country: this.state.country };
        console.log('student => ' + JSON.stringify(student));

        // step 5
        if (this.state.id === '_add') {
            StudentService.createStudent(student).then(res => {
                this.props.history.push('/students');
            });
        } else {
            StudentService.updateStudent(student, this.state.id).then(res => {
                this.props.history.push('/students');
            });
        }
    }

    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    changeGenderHandler = (event) => {
        console.log(event.value)
        this.setState({ gender: event.value });
    }

    changeCityHandler = (event) => {
        this.setState({ city: event.target.value });
    }

    changeCountryHandler = (event) => {
        this.setState({ country: event.target.value });
    }

    cancel() {
        this.props.history.push('/students');
    }

    getTitle() {
        if (this.state.id === '_add') {
            return <h3 className="text-center">Add Student</h3>
        } else {
            return <h3 className="text-center">Update Student</h3>
        }
    }
    render() {
        return (
            <div>
                <br></br>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label> Student Full Name: </label>
                                        <input placeholder="Student Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label> Student Gender: </label>
                                        <Select name="gender" value={this.state.gender} onChange={this.changeGenderHandler} options={this.state.options}/>
                                    </div>
                                    <div className="form-group">
                                        <label> City: </label>
                                        <input placeholder="City" name="city" className="form-control"
                                            value={this.state.city} onChange={this.changeCityHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label> Country: </label>
                                        <input placeholder="Country" name="country" className="form-control"
                                            value={this.state.country} onChange={this.changeCountryHandler} />
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveOrUpdateStudent}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{ marginLeft: "10px" }}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default CreateStudentComponent

