import React from 'react';

const ModalForm = ({ show, handleClose, handleSave, entity, setEntity, fields, title }) => {
  const handleChange = (e) => {
    const { name, value } = e.target;
    setEntity({ ...entity, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    handleSave();
  };

  if (!show) return null;

  return (
    <div className="fixed inset-0 z-50 overflow-auto bg-smoke-light flex">
      <div className="relative p-8 bg-white w-full max-w-md m-auto flex-col flex rounded-lg shadow-xl">
        <h2 className="text-xl font-bold mb-4">{title}</h2>
        <form onSubmit={handleSubmit}>
          {fields.map((field) => (
            <div key={field.name} className="mb-4">
              <label className="block text-gray-700 text-sm font-bold mb-2" htmlFor={field.name}>
                {field.label}
              </label>
              <input
                type={field.type}
                name={field.name}
                value={entity[field.name] || ''}
                onChange={handleChange}
                required={field.required}
                className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          ))}
          <div className="flex items-center justify-between">
            <button
              type="button"
              onClick={handleClose}
              className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            >
              Save
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ModalForm;