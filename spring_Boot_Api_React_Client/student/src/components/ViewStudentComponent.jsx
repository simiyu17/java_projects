

import React, { Component } from 'react'
import StudentService from '../services/StudentService'

class ViewStudentComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            student: {}
        }
    }

    componentDidMount(){
        StudentService.getStudentById(this.state.id).then( res => {
            this.setState({student: res.data});
        })
    }

    render() {
        return (
            <div>
                <br></br>
                <div className = "card col-md-6 offset-md-3">
                    <h3 className = "text-center"> View Student Details</h3>
                    <div className = "card-body">
                        <div className = "row">
                            <label> Student Name: </label>
                            <div> { this.state.student.name }</div>
                        </div>
                        <div className = "row">
                            <label> Student Gender: </label>
                            <div> { this.state.student.gender }</div>
                        </div>
                        <div className = "row">
                            <label> Student City: </label>
                            <div> { this.state.student.city }</div>
                        </div>
                        <div className = "row">
                            <label> Student Country: </label>
                            <div> { this.state.student.country }</div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default ViewStudentComponent

