import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS

const R1 = () => {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    try {
      const response = await axios.get('http://localhost:8090/api/v1/details/report1vPrueba');
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>R1: Reporte de detalle de boletas para cada auto</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>Patente</th>
            <th>Monto Inicial</th>
            <th>Descuento por Reparación</th>
            <th>Descuento por Día</th>
            <th>Descuento por Bonos</th>
            <th>Recargo por Kilómetro</th>
            <th>Recargo por Retraso</th>
            <th>Recargo por Antigüedad</th>
            <th>Monto Final</th>
            <th>Fecha Ingreso </th>

          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.patent}</td>
              <td>{item.initialAmount}</td>
              <td>{item.discountByRepair}</td>
              <td>{item.discountByDay}</td>
              <td>{item.discountByBonus}</td>
              <td>{item.surchargeByKm}</td>
              <td>{item.surchargeByDelay}</td>
              <td>{item.surchargeByAge}</td>
              <td>{item.finalAmount}</td>
              <td>{item.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default R1;
