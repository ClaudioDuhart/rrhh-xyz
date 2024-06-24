import React, { useContext, useEffect, useState } from 'react'
import { AuthContext } from '../../context/AuthContext';
import LineChart from '../../components/LineChart';
import { AddButton, DeleteButton, ModifyButton } from '../../components/Buttons';
import ModalForm from '../../components/ModalForm';

const UserPerformance = () => {
  const [usuarios, setUsuarios] = useState([]);
  const [selectedUsuario, setSelectedUsuario] = useState(null);
  const [rendimiento, setRendimiento] = useState([]);
  const [show, setShow] = useState(false);
  const [modalTitle, setModalTitle] = useState('');
  const { authData } = useContext(AuthContext);
  const [form, setForm] = useState({
    puntos: 0,
    periodo: '',
  });

  const fields = [
    { name: 'puntos', label: 'Puntos', type: 'text', required: true },
    { name: 'periodo', label: 'Periodo', type: 'text', required: true }
  ];

  useEffect(() => {
    fetch('http://localhost:8080/api/usuario')
      .then(response => response.json())
      .then(data => setUsuarios(data));
  }, []);

  const handleUsuarioChange = (event) => {
    const usuarioId = event.target.value;
    setSelectedUsuario(usuarioId);

    fetch(`http://localhost:8080/api/rendimiento/por-rut/${usuarioId}`)
      .then(response => response.json())
      .then(data => setRendimiento(data));
  };

  const handleSubmit = async (e) => {

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
        <h1 className="text-3xl font-bold">Rendimiento de Usuarios</h1>
        <div className="flex space-x-4">
        <AddButton handleClick={() => handleShow('Agregar Rendimiento')}/>
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
      <div className='flex flex-row'>
        <div className='w-2/5'>
          <ul className="space-y-1">
            {rendimiento.map((elem) => (
              <li key={elem.Periodo} className="bg-white p-3 rounded shadow-md">
                <p><strong>Periodo:</strong> {elem.periodo}</p>
                <p><strong>Puntos:</strong> {elem.puntos}</p>
              </li>
            ))}
          </ul>
        </div>
        <div className='w-3/5'>
        {rendimiento.length > 0 && <LineChart title="Rendimiento" variable="Puntos de Rendimiento" xaxis={rendimiento.map(a => a.periodo)} yaxis={rendimiento.map(a => a.puntos)} yaxisMax={24}/>}
        </div>
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
};


export default UserPerformance