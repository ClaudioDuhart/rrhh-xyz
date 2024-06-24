import React from 'react';

const Services = () => {
  return (
    <section className="py-20">
      <div className="container mx-auto text-center">
        <h3 className="text-3xl font-bold mb-12">Nuestros Servicios</h3>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h4 className="text-2xl font-bold mb-2">Servicio 1</h4>
            <p className="text-gray-700">Descripción del servicio 1.</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h4 className="text-2xl font-bold mb-2">Servicio 2</h4>
            <p className="text-gray-700">Descripción del servicio 2.</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-md">
            <h4 className="text-2xl font-bold mb-2">Servicio 3</h4>
            <p className="text-gray-700">Descripción del servicio 3.</p>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Services;