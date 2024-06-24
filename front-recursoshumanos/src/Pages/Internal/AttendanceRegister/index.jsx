import React, { useContext, useState } from 'react';
import { AuthContext } from '../../context/AuthContext';

 const AttendanceRegister = () => {
    const [fecha, setFecha] = useState('');
    const [rutUsuario, setRutUsuario] = useState('');
    const { authData } = useContext(AuthContext);



    const handleFirmarEntrada = async () => {
      const horaEntrada = new Date().toLocaleTimeString('it-IT').slice(0, 5); // Formato HH:mm

      const data = {
        horaEntrada,
        marcajeEntrada: true,
        horaSalida: '',
        marcajeSalida: false,
        fecha: getCurrentDate(),
        rutUsuario: authData.user.rut
      };

      console.log(data)
  
      try {
        const response = await fetch('http://localhost:8080/api/asistencia/ingreso', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
  
        if (response.ok) {
          alert('Entrada registrada con éxito');
        } else {
          alert('Error al registrar la entrada');
        }
      } catch (error) {
        console.error('Error al registrar la entrada:', error);
        alert('Error al registrar la entrada');
      }
    };

    const getCurrentDate = () => {
        const today = new Date();
        const yyyy = today.getFullYear();
        let mm = today.getMonth() + 1; // Months start at 0!
        let dd = today.getDate();

        if (dd < 10) dd = '0' + dd;
        if (mm < 10) mm = '0' + mm;

        return yyyy + '-' + mm + '-' + dd;
    }
  
    const handleFirmarSalida = async () => {
      const horaSalida = new Date().toLocaleTimeString('it-IT').slice(0, 5); // Formato HH:mm
      const data = {
        horaEntrada: '',
        marcajeEntrada: true,
        horaSalida,
        marcajeSalida: true,
        fecha: getCurrentDate(),
        rutUsuario: authData.user.rut
      };
  
      try {
        const response = await fetch('http://localhost:8080/api/asistencia/salida', {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        });
  
        if (response.ok) {
          alert('Salida registrada con éxito');
        } else {
          alert('Error al registrar la salida');
        }
      } catch (error) {
        console.error('Error al registrar la salida:', error);
        alert('Error al registrar la salida');
      }
    };
  
    return (
      <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded-lg shadow-md">
        <h1 className="text-2xl font-bold mb-6 text-center">Registro de Asistencia</h1>
        <div className="flex flex-col space-y-4">
          <button
            onClick={handleFirmarEntrada}
            className="w-full bg-green-500 text-white py-4 px-4 rounded-lg hover:bg-green-700 transition duration-200 text-lg"
          >
            Firmar Entrada
          </button>
          <button
            onClick={handleFirmarSalida}
            className="w-full bg-red-500 text-white py-4 px-4 rounded-lg hover:bg-red-700 transition duration-200 text-lg"
          >
            Firmar Salida
          </button>
        </div>
      </div>
    );
  };
  
  export default AttendanceRegister;  