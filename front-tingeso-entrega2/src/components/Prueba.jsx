import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/Prueba.css'; // Importa el archivo de estilos CSS

const Prueba = () => {
  const [listaAutos, setListaAutos] = useState([]);

  const obtenerListaAutos = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/cars/all');
      setListaAutos(response.data);
    } catch (error) {
      console.error('Error al obtener la lista de autos:', error);
    }
  };

  useEffect(() => {
    obtenerListaAutos();
  }, []);

  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Lista de Autos</h2>
      <table className="tabla-autos"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>ID</th>
            <th>Patente</th>
            <th>Modelo</th>
            <th>Año</th>
            <th>Asientos</th>
            <th>ID de Marca</th>
            <th>ID de Tipo</th>
            <th>ID de Motor</th>
          </tr>
        </thead>
        <tbody>
          {listaAutos.map(auto => (
            <tr key={auto.id}>
              <td>{auto.id}</td>
              <td>{auto.patent}</td>
              <td>{auto.model}</td>
              <td>{auto.year}</td>
              <td>{auto.seats}</td>
              <td>{auto.brandId}</td>
              <td>{auto.typeId}</td>
              <td>{auto.engineId}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Prueba;
