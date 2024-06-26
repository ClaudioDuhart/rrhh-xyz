import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="bg-blue-600 text-white py-4">
      <div className="container mx-auto flex justify-between items-center">
        <h1 className="text-2xl font-bold">Empresa XYZ</h1>
        <nav>
          <ul className="flex space-x-4">
            <li><a href="#" className="hover:underline">Inicio</a></li>
            <li><a href="#" className="hover:underline">Servicios</a></li>
            <li><a href="#" className="hover:underline">Contacto</a></li>
            <li className="hover:underline"><Link to={"/login"}>Iniciar Sesion</Link></li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
