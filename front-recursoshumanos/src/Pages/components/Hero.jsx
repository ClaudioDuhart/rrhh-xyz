import React from 'react';

const Hero = () => {
  return (
    <section className="bg-gray-100 py-20">
      <div className="container mx-auto text-center">
        <h2 className="text-4xl font-bold mb-4">Bienvenidos a Nuestra Empresa</h2>
        <p className="text-lg mb-8">Ofrecemos las mejores soluciones para tu negocio.</p>
        <a href="#" className="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700">Descubre Más</a>
      </div>
    </section>
  );
};

export default Hero;
