import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/R1.css'; // Importa el archivo de estilos CSS
import { useParams } from 'react-router-dom';
import moment from 'moment';

const CarListDetail = () => {
  const [data, setData] = useState([]);
  const {carId, admissionDate} = useParams();

  useEffect(() => {
    console.log('useEffect se está ejecutando');
    fetchData();
  }, [carId, admissionDate]);

  const fetchData = async () => {
    console.log('fetchData se está ejecutando');
    try {
      const response = await axios.get( `http://localhost:6081/api/v2/carrepairs/carsList/${carId}/${admissionDate} `);
      console.log('aaaaaaaaaaaaaaaaaaaaaa'); // Log the fetched data for debugging
      console.log('Fetched data:', response.data); // Log the fetched data for debugging
      setData(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };



  return (
    <div className="tabla-container"> {/* Agrega una clase contenedora para aplicar estilos */}
      <h2>Detalle</h2>
      <table className="tabla-r1"> {/* Agrega una clase a la tabla para aplicar estilos */}
        <thead>
          <tr>
            <th>ID Auto</th>
            <th>Patente</th>
            
             <th>Fecha Ingreso </th>
            <th>Fecha Salida </th>
            <th>Fecha Real de Salida </th>

            <th>Reparaciones</th>
            <th>Monto de las reparaciones</th>
            <th>Monto Total de Reparaciones </th>

            <th>Descuentos aplicados</th>
            <th>Montos de los descuentos</th>
            <th>Monto Total de descuentos </th>


            <th>Recargos aplicados</th>
            <th>Montos de los recargos</th>
            <th>Monto Total de recargos </th>

            <th>Monto Final </th> 
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
            <td>{item.id}</td>
            <td>{item.patent}</td>
            <td>{item.admissionDate ? moment.utc(item.admissionDate).local().format('DD/MM/YYYY') : 'N/A'}</td>
              <td>{item.exitDate ? moment.utc(item.exitDate).local().format('DD/MM/YYYY') : 'N/A'}</td>
              <td>{item.realExitDate ? moment.utc(item.realExitDate).local().format('DD/MM/YYYY') : 'N/A'}</td>
            <td>
              <ul>
                {item.repairs.map((repair, i) => (
                  <li key={i}>{repair}</li>
                ))}
              </ul>
            </td>
            <td>
              <ul>
                {item.repairAmounts.map((amount, i) => (
                  <li key={i}>{amount}</li>
                ))}
              </ul>
            </td>
            <td>{item.totalRepairAmounts}</td>
            <td>
              <ul>
                {item.discounts.map((discount, i) => (
                  <li key={i}>{discount}</li>
                ))}
              </ul>
            </td>
            <td>
              <ul>
                {item.discountAmounts.map((amount, i) => (
                  <li key={i}>{amount}</li>
                ))}
              </ul>
            </td>
            <td>{item.totalDiscountAmounts}</td>
            <td>
              <ul>
                {item.surcharges.map((surcharge, i) => (
                  <li key={i}>{surcharge}</li>
                ))}
              </ul>
            </td>
            <td>
              <ul>
                {item.surchargeAmounts.map((amount, i) => (
                  <li key={i}>{amount}</li>
                ))}
              </ul>
            </td>
            <td>{item.totalSurchargeAmounts}</td>
            <td>{item.finalAmount}</td> 
          </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CarListDetail;
