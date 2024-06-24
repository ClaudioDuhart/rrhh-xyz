import React, { useState, useEffect, useContext } from 'react';
import { useLocation, useNavigate, useOutletContext } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';

const UserAttendance = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [selectedUsuario, setSelectedUsuario] = useState(null);
  const [asistencias, setAsistencias] = useState([]);
  const [show, setShow] = useState(false);
  const [modalTitle, setModalTitle] = useState('');
  const { authData } = useContext(AuthContext);

  console.log(authData);

  const [form, setForm] = useState({
    fecha: '',
    horaEntrada: '',
    marcajeEntrada: '',
    horaSalida: '',
    marcajeSalida: ''
  });

  const fields = [
    { name: 'fecha', label: 'Puntos', type: 'date', required: true },
    { name: 'horaEntrada', label: 'Periodo', type: 'text', required: true },
    { name: 'marcajeEntrada', label: 'Periodo', type: 'text', required: true },
    { name: 'horaSalida', label: 'Periodo', type: 'text', required: true },
    { name: 'marcajeSalida', label: 'Periodo', type: 'text', required: true }
  ];

  useEffect(() => {
    fetch('http://localhost:8080/api/usuario')
      .then(response => response.json())
      .then(data => setUsuarios(data));
  }, []);

  const handleUsuarioChange = (event) => {
    const usuarioId = event.target.value;
    setSelectedUsuario(usuarioId);

    fetch(`http://localhost:8080/api/asistencia/por-rut/${usuarioId}`)
      .then(response => response.json())
      .then(data => setAsistencias(data));
  };

  const handleSubmit = async (e) => {
    console.log(form)
    console.log(selectedUsuario)

  try {
      const response = await fetch('http://localhost:8080/api/rendimiento', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'rutUsuario': selectedUsuario,
          puntos: form.puntos,
          periodo: form.periodo,
        }),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const newRendimiento = await response.json();
      
      // Actualizar la lista de turnos
      setRendimiento((prevRendimientos) => [...prevRendimientos, newRendimiento]);
      
      // Limpiar el formulario
      setForm({
        puntos: 0,
        periodo: '',
      });
    } catch (error) {
      console.error('Error posting turno:', error);
    }
  };

  const handleShow = (title) => {
    setModalTitle(title);
    setShow(true);
  };

  const handleCloseModal = () => setShow(false);

  return (
    <div className="container mx-auto py-8">
      <div className="flex justify-between items-center align-center mb-6">
        <h1 className="text-3xl font-bold">Asistencia de Usuario</h1>
      </div>
      <div className="mb-4">
        <label htmlFor="usuario" className="block text-sm font-medium text-gray-700">Seleccione un Usuario</label>
        <select
          id="usuario"
          className="mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
          onChange={handleUsuarioChange}
        >
          <option value="">Seleccione un usuario</option>
          {usuarios.map(usuario => (
            <option key={usuario.id} value={usuario.rut}>{usuario.nombre}</option>
          ))}
        </select>
      </div>
      {selectedUsuario && (
        <div>
          <h2 className="text-2xl font-bold mb-4">Asistencias para {usuarios.find(usuario => usuario.rut === selectedUsuario)?.nombre}</h2>
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white border border-gray-200 rounded-lg">
              <thead className="bg-gray-100">
                <tr>
                  <th className="px-4 py-2 text-left text-gray-600 font-semibold">Fecha</th>
                  <th className="px-4 py-2 text-left text-gray-600 font-semibold">Hora Entrada</th>
                  <th className="px-4 py-2 text-left text-gray-600 font-semibold">Marcaje Entrada</th>
                  <th className="px-4 py-2 text-left text-gray-600 font-semibold">Hora Salida</th>
                  <th className="px-4 py-2 text-left text-gray-600 font-semibold">Marcaje Salida</th>
                </tr>
              </thead>
              <tbody>
                {asistencias.map((asistencia) => (
                  <tr key={asistencia.id} className="border-t">
                    <td className="px-4 py-2">{new Date(asistencia.fecha).toLocaleDateString()}</td>
                    <td className="px-4 py-2">{asistencia.horaEntrada}</td>
                    <td className="px-4 py-2">{asistencia.marcajeEntrada ? 'Sí' : 'No'}</td>
                    <td className="px-4 py-2">{asistencia.horaSalida}</td>
                    <td className="px-4 py-2">{asistencia.marcajeSalida ? 'Sí' : 'No'}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default UserAttendance;