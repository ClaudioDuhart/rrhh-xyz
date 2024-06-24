import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import "./index.css";
import Login from "./Pages/Login";
import Home from "./Pages/Home";
import Internal from "./Pages/Internal";
import UserAttendance from "./Pages/Internal/UserAttendance";
import UserPerformance from "./Pages/Internal/UserPerformance";
import { AuthProvider } from "./Pages/context/AuthContext";
import UserShift from "./Pages/Internal/UserShifts";
import AttendanceRegister from "./Pages/Internal/AttendanceRegister";

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider >
      <Router>
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/internal" element={<Internal />}>
            <Route path="asistencia" element={<UserAttendance />}/>
            <Route path="rendimiento" element={<UserPerformance /> }/>
            <Route path="turnos" element={<UserShift /> }/>
            <Route path="registro-asistencia" element={<AttendanceRegister /> }/>
          </Route>
        </Routes>
      </Router>
    </AuthProvider>
  </React.StrictMode>,
)
