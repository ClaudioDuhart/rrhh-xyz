import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../../context/AuthContext';
import { AddButton, DeleteButton, ModifyButton } from '../../components/Buttons';
import ModalForm from '../../components/ModalForm';

const UserShifts = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [turnos, setTurnos] = useState([]);
  const [error, setError] = useState([]);
  const [show, setShow] = useState(false);
  const [modalTitle, setModalTitle] = useState('');
  const [selectedUsuario, setSelectedUsuario] = useState(null);
  const { authData } = useContext(AuthContext);
  const [form, setForm] = useState({
    turnoEntrada: '',
    turnoSalida: '',
    fecha: ''
  });

  const fields = [
    { name: 'fecha', label: 'Fecha', type: 'date', required: true },
    { name: 'turnoEntrada', label: 'Turno Entrada', type: 'time', required: true },
    { name: 'turnoSalida', label: 'Turno Salida', type: 'time', required: true },
  ];


  useEffect(() => {
    fetch('http://localhost:8080/api/usuario')
      .then(response => response.json())
      .then(data => setUsuarios(data));
  }, []);

  const handleUsuarioChange = (event) => {
    const usuarioId = event.target.value;
    setSelectedUsuario(usuarioId);

    fetch(`http://localhost:8080/api/turno/por-rut/${usuarioId}`)
      .then(response => response.json())
      .then(data => setTurnos(data));
  };

  const formatDate = (dateString) => {
    const date = new Date(dateString);
    return `${date.getDate() + 1}/${date.getMonth() + 1}/${date.getFullYear()}`;
  };
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    setError(null);
    const [year, month, day] = form.fecha.split('-');
    const fechaISO = `${year}-${month}-${day}`;

    try {
      const response = await fetch('http://localhost:8080/api/turno', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'rutUsuario': selectedUsuario,
          turnoEntrada: form.turnoEntrada,
          turnoSalida: form.turnoSalida,
          fecha: fechaISO
        }),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const newTurno = await response.json();
      
      // Actualizar la lista de turnos
      setTurnos((prevTurnos) => [...prevTurnos, newTurno]);
      
      // Limpiar el formulario
      setForm({
        turnoEntrada: '',
        turnoSalida: '',
        fecha: ''
      });
    } catch (error) {
      setError('Error al asignar el turno. Intenta nuevamente.');
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
        <h1 className="text-3xl font-bold">Calendario de Turnos</h1>
        <div className="flex space-x-4">
          <AddButton handleClick={() => handleShow('Agregar Turno')}/>
        </div>
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
      <div className="overflow-x-auto">
        <table className="min-w-full bg-white border border-gray-200">
          <thead className="bg-gray-100">
            <tr>
              <th className="border border-gray-200 px-4 py-2">Fecha</th>
              <th className="border border-gray-200 px-4 py-2">Turno Entrada</th>
              <th className="border border-gray-200 px-4 py-2">Turno Salida</th>
            </tr>
          </thead>
          <tbody>
            {turnos.map((turno) => (
              <tr key={turno.id}>
                <td className="border border-gray-200 px-4 py-2">{formatDate(turno.fecha)}</td>
                <td className="border border-gray-200 px-4 py-2">{turno.turnoEntrada}</td>
                <td className="border border-gray-200 px-4 py-2">{turno.turnoSalida}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <ModalForm
        show={show}
        handleClose={handleCloseModal}
        handleSave={handleSubmit}
        entity={form}
        setEntity={setForm}
        fields={fields}
        title={modalTitle}
      />
    </div>
  );
}

export default UserShifts